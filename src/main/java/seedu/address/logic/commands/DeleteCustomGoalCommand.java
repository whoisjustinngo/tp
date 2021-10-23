package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

/**
 * Adds a person to the address book.
 */
public class DeleteCustomGoalCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes the custom goal corresponding to the " +
            "specified index"
            + "Parameters: "
            + "INDEX_OF_GOAL_TO_UPDATE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "- deletes custom goal 1";

    public static final String MESSAGE_SUCCESS = "Successfully deleted custom goal %s";
    public static final String MESSAGE_INVALID_INDEX = "The specified index is invalid";

    private final Index goalToDelete;

    public DeleteCustomGoalCommand(Index goalToDelete) {
        requireNonNull(goalToDelete);
        this.goalToDelete = goalToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        
        if (this.goalToDelete.getOneBased() > model.getNumOfCustomGoals()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        model.deleteCustomGoal(this.goalToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.goalToDelete.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomGoalCommand // instanceof handles nulls
                &&  this.equals(((DeleteCustomGoalCommand) other)));
    }
}
