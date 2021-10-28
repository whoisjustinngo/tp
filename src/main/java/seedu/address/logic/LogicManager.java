package seedu.address.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.analytics.ClientAnalytics;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String FAILED_SCHEDULES_MESSAGE =
            "There are {%d} timeslots that do not fit in your current schedule!";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public List<CommandResult> execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        // Command 1
        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        // Command 2
        CommandResult goToContextCommanResult;
        Command contextCommand = addressBookParser.goToContextTab();
        goToContextCommanResult = contextCommand.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        List<CommandResult> commandResults = new ArrayList<>();
        commandResults.add(goToContextCommanResult); // run tabswitch first, so that the last command run can be safe
        commandResults.add(commandResult);

        return commandResults;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Todo> getFilteredTodoList() {
        return model.getFilteredTodoList();
    }

    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return model.getFilteredScheduleList();
    }

    @Override
    public ObservableList<CustomGoal> getFilteredCustomGoalList() {
        return model.getFilteredCustomGoalList();
    }

    /**
     * Generates schedules
     */
    @Override
    public void importSchedule(File openedFile)
            throws IOException, ParseException, CommandException {
        List<Schedule> scheduleList = model.importSchedule(openedFile);
        List<Schedule> getValidSchedules = scheduleList.stream()
                .filter(schedule -> !(model.hasScheduleClash(schedule) && model.hasSchedule(schedule)))
                .collect(Collectors.toList());
        if (getValidSchedules.size() != scheduleList.size()) {
            throw new CommandException(String.format(FAILED_SCHEDULES_MESSAGE,
                    scheduleList.size() - getValidSchedules.size()));
        }
        getValidSchedules.forEach(schedule -> model.addSchedule(schedule));
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ClientAnalytics getAnalytics() {
        return model.getAnalytics();
    }
}
