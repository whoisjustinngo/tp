package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.Schedule;

public class FilterScheduleCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all schedule whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: /ATTRIBUTE KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DESCRIPTION + " lesson\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DATE + " 12-01-2021\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FROM + " 1400\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TO + " 1600";

    private final Predicate<Schedule> predicate;

    public FilterScheduleCommand(Predicate<Schedule> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SCHEDULE_LISTED_OVERVIEW, model.getFilteredScheduleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterScheduleCommand // instanceof handles nulls
                && predicate.equals(((FilterScheduleCommand) other).predicate)); // state check
    }
}
