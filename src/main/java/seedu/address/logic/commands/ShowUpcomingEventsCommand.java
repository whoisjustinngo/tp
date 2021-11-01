package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

public class ShowUpcomingEventsCommand extends Command {
    public static final String COMMAND_WORD = "showupcoming";

    public static final String MESSAGE_SUCCESS = "All upcoming Events are shown.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(schedule -> LocalDateTime.now().compareTo(schedule.getTaskDateTimeTo()) < 0);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
