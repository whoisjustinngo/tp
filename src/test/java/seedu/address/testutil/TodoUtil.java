package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.model.todo.Todo;

/**
 * A utility class for Todo.
 */
public class TodoUtil {

    /**
     * Returns an add command string for adding the {@code todo}.
     */
    public static String getAddTodoCommand(Todo todo) {
        return AddTodoCommand.COMMAND_WORD + " " + getTodoDetails(todo);
    }

    /**
     * Returns the part of command string for the given {@code todo}'s details.
     */
    public static String getTodoDetails(Todo todo) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + todo.getDescription());
        return sb.toString();
    }
}
