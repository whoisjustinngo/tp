package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.todo.Todo;

/**
 * Filters and lists all todos whose attributes (description, tags) contain any of the argument keywords.
 * Keyword matching is case insensitive.
 * Also supports filtering and listing all todos which are marked as done, or all todos which are not marked as done.
 */
public class FilterTodoCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all todos according to their attributes "
            + "(case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION KEYWORDS]... "
            + "[" + PREFIX_TAG + "TAG KEYWORDS]... "
            + "[" + PREFIX_DONE + "YES or NO]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "read "
            + PREFIX_TAG + "personalDevelopment nonUrgent "
            + PREFIX_DONE + "yes";

    private final Predicate<Todo> predicate;

    public FilterTodoCommand(Predicate<Todo> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TODOS_LISTED_OVERVIEW, model.getFilteredTodoList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTodoCommand // instanceof handles nulls
                && predicate.equals(((FilterTodoCommand) other).predicate)); // state check
    }
}
