package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customGoal.exceptions.NegativeProgressException;

/**
 * Creates a command to update the progress of a specific CustomGoal.
 */
public class UpdateCustomGoalCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": updates a custom goal by a specified value for the "
            + "user to track their progress. "
            + "Parameters: "
            + "INDEX_OF_GOAL_TO_UPDATE "
            + PREFIX_VALUE + "VALUE "
            + "Example: " + COMMAND_WORD + " "
            + "1 " + PREFIX_VALUE + "20.3\n"
            + "This adds 20.3 to the progress of custom goal 1";

    public static final String MESSAGE_SUCCESS = "Successfully updated progress of custom goal %s with value %s";
    public static final String MESSAGE_RESET = "Progress cannot be negative, progress of custom goal %s "
            + "is reset to 0";
    public static final String MESSAGE_INVALID_INDEX = "The specified index is invalid";

    private final Index goalToUpdate;
    private final float valueToUpdateBy;

    /**
     * Creates an UpdateCustomGoalCommand.
     * @param goalToUpdate The (1-based) <code>Index</code> of the CustomGoal to update.
     * @param value The value with which to increase the progress of the corresponding CustomGoal by.
     */
    public UpdateCustomGoalCommand(Index goalToUpdate, float value) {
        requireNonNull(goalToUpdate);
        this.goalToUpdate = goalToUpdate;
        this.valueToUpdateBy = value;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.goalToUpdate.getOneBased() > model.getNumOfCustomGoals()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        try {
            model.updateCustomGoal(this.goalToUpdate, this.valueToUpdateBy);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.goalToUpdate.getOneBased(),
                    formatFloat(this.valueToUpdateBy)));
        } catch (NegativeProgressException ex) {
            return new CommandResult(String.format(MESSAGE_RESET, this.goalToUpdate.getOneBased(),
                    formatFloat(this.valueToUpdateBy)));
        }
    }

    /**
     * Converts the provided float to a string while truncating all trailing zeroes.
     * @param f The float to convert and truncate.
     * @return The string representation of the given float with the trailing zeroes truncated.ß
     */
    public String formatFloat(float f) {
        if (f == (long) f) {
            return String.format("%d", (long) f);
        } else {
            return String.format("%s", f);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateCustomGoalCommand // instanceof handles nulls
                && this.equals(((UpdateCustomGoalCommand) other)));
    }
}
