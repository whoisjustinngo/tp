package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customGoal.CustomGoal;

/**
 * Command to add a custom goal to the dashboard.
 */
public class AddCustomGoalCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new custom goal to the dashboard. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_GOAL + "GOAL "
            + "[" + PREFIX_END_DATE + "END_DATE" + "] "
            + "[" + PREFIX_END_TIME + "END_TIME" + "]\n"
            + "Note: 1. if you specify an end time, you have to specify an end date as well\n"
            + "2. GOAL has to be a non-negative number\n"
            + "Example 1: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "call 20 clients "
            + PREFIX_GOAL + "20 "
            + PREFIX_END_DATE + "16-05-2021 "
            + PREFIX_END_TIME + "1600\n"
            + "Example 2: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "earn $1000 in commissions "
            + PREFIX_GOAL + "1000 "
            + PREFIX_END_DATE + "23-07-2021";
    public static final String MESSAGE_SUCCESS = "New custom goal created added: %1$s";
    public static final String MESSAGE_DUPLICATE_CUSTOM_GOAL = "This custom goal already exists";

    private final CustomGoal toAdd;

    /**
     * Creates an AddCustomGoalCommand to add the specified CustomGoal.
     */
    public AddCustomGoalCommand(CustomGoal goal) {
        requireNonNull(goal);
        toAdd = goal;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCustomGoal(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOM_GOAL);
        }

        model.addCustomGoal(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCustomGoalCommand // instanceof handles nulls
                && toAdd.equals(((AddCustomGoalCommand) other).toAdd));
    }
}
