package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TODOS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;

/**
 * Marks an existing Todo as done.
 */
public class DoneTodoCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the todo, identified "
            + "by the index number used in the displayed todo list, as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TODO_SUCCESS = "Marked this Todo as done: %1$s";

    private final Index index;

    /**
     * @param index of the todo in the filtered todo list to mark as done
     */
    public DoneTodoCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Todo> lastShownList = model.getFilteredTodoList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Todo todoToMark = lastShownList.get(index.getZeroBased());
        assert todoToMark != null;
        Todo markedTodo = todoToMark.getDoneVersion();

        model.setTodo(todoToMark, markedTodo);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODOS);
        return new CommandResult(String.format(MESSAGE_DONE_TODO_SUCCESS, markedTodo));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoneTodoCommand)) {
            return false;
        }

        // state check
        DoneTodoCommand e = (DoneTodoCommand) other;
        return index.equals(e.index);
    }
}
