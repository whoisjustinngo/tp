package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.todo.predicates.DescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all todos whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTodoCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all todos whose descriptions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " read travel bake";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindTodoCommand(DescriptionContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindTodoCommand // instanceof handles nulls
                && predicate.equals(((FindTodoCommand) other).predicate)); // state check
    }
}
