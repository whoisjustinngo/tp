package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Schedule;

/**
 * Adds a Schedule.
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Schedule to your list of Events. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "lesson " 
            + PREFIX_DATE + "16-05-2021 " 
            + PREFIX_FROM + "1400 "
            + PREFIX_TO + "1600";

    public static final String MESSAGE_SUCCESS = "New Schedule added: %1$s";

    private final Schedule schedule;

    /**
     * Creates an AddScheduleCommand to add the specified {@code Schedule}
     */
    public AddScheduleCommand(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addSchedule(schedule);
        return new CommandResult(String.format(MESSAGE_SUCCESS, schedule));
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddScheduleCommand // instanceof handles nulls
                && schedule.equals(((AddScheduleCommand) other).schedule));
    }
}
