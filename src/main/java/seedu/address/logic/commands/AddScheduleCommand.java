package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURR_DAILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURR_WEEKLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURR_YEARLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Schedule;

/**
 * Adds a Schedule.
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Schedule to your list of Events. "
            + "Parameters: " + PREFIX_DESCRIPTION + "DESCRIPTION " + PREFIX_DATE + "DATE " + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO " + PREFIX_TAG + "TAG (" + PREFIX_RECURR_DAILY + " or " + PREFIX_RECURR_WEEKLY + " or "
            + PREFIX_RECURR_YEARLY + "RECURR TILL)\n" + "Example: " + COMMAND_WORD + " " + PREFIX_DESCRIPTION
            + "lesson " + PREFIX_DATE + "16-05-2021 " + PREFIX_FROM + "1400 " + PREFIX_TO + "1600 " + PREFIX_TAG
            + "important " + PREFIX_RECURR_DAILY + "18-05-2021";

    public static final String MESSAGE_SUCCESS = "New Schedule added: %1$s";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule clashes with"
            + " your existing list of Schedules";

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

        switch (this.schedule.getRecurrType()) {
        case "N":
            if (model.hasScheduleClash(this.schedule)) {
                throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            if (model.hasSchedule(this.schedule)) {
                throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            model.addSchedule(this.schedule);
            break;

        case "D":
            LocalDateTime recurrToD = schedule.getRecurrDateTo();
            Schedule scheduleCheckClashD = new Schedule(this.schedule);
            while (recurrToD.compareTo(scheduleCheckClashD.getTaskDateTimeFrom()) > 0) {
                if (model.hasScheduleClash(scheduleCheckClashD)) {
                    throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
                }
                if (model.hasSchedule(scheduleCheckClashD)) {
                    throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
                }
                scheduleCheckClashD = scheduleCheckClashD.addOneDay();
            }
            Schedule scheduleToAddD = new Schedule(this.schedule);
            while (recurrToD.compareTo(scheduleToAddD.getTaskDateTimeFrom()) > 0) {
                model.addSchedule(scheduleToAddD);
                scheduleToAddD = scheduleToAddD.addOneDay();
            }
            break;

        case "W":
            LocalDateTime recurrToW = schedule.getRecurrDateTo();
            Schedule scheduleCheckClashW = new Schedule(this.schedule);
            while (recurrToW.compareTo(scheduleCheckClashW.getTaskDateTimeFrom()) > 0) {
                if (model.hasScheduleClash(scheduleCheckClashW)) {
                    throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
                }
                if (model.hasSchedule(scheduleCheckClashW)) {
                    throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
                }
                scheduleCheckClashW = scheduleCheckClashW.addOneWeek();
            }
            Schedule scheduleToAddW = new Schedule(this.schedule);
            while (recurrToW.compareTo(scheduleToAddW.getTaskDateTimeFrom()) > 0) {
                model.addSchedule(scheduleToAddW);
                scheduleToAddW = scheduleToAddW.addOneWeek();
            }
            break;

        case "Y":
            LocalDateTime recurrToY = schedule.getRecurrDateTo();
            Schedule scheduleCheckClashY = new Schedule(this.schedule);
            while (recurrToY.compareTo(scheduleCheckClashY.getTaskDateTimeFrom()) > 0) {
                if (model.hasScheduleClash(scheduleCheckClashY)) {
                    throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
                }
                if (model.hasSchedule(scheduleCheckClashY)) {
                    throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
                }
                scheduleCheckClashY.addOneYear();
            }
            Schedule scheduleToAddY = new Schedule(this.schedule);
            while (recurrToY.compareTo(scheduleToAddY.getTaskDateTimeFrom()) > 0) {
                model.addSchedule(scheduleToAddY);
                scheduleToAddY = scheduleToAddY.addOneYear();
            }
            break;

        default:
            break;
        }

        // make a for loop here checking for clashes and continuous adding of the
        // schedule
        return new CommandResult(String.format(MESSAGE_SUCCESS, schedule));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddScheduleCommand // instanceof handles nulls
                        && schedule.equals(((AddScheduleCommand) other).schedule));
    }
}
