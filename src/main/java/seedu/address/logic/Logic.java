package seedu.address.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import net.fortuna.ical4j.data.ParserException;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.analytics.ClientAnalytics;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of todos */
    ObservableList<Todo> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered list of schedule */
    ObservableList<Schedule> getFilteredScheduleList();

    /** Returns an unmodifiable view of the filtered list of custom goal */
    ObservableList<CustomGoal> getFilteredCustomGoalList();

    /** Generates schedules */
    void importSchedule(File file) throws IOException, ParserException,
            CommandException, ParseException, java.text.ParseException;

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ClientAnalytics getAnalytics();
}
