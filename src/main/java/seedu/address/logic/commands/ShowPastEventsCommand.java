package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.model.Model;

public class ShowPastEventsCommand extends Command {
    public static final String COMMAND_WORD = "showpast";

    public static final String MESSAGE_SUCCESS = "All past Events are shown.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(schedule -> LocalDateTime.now().compareTo(schedule.getTaskDateTimeTo()) > 0);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
