package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.logic.commands.EditTodoCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;

/**
 * A utility class for Todo.
 */
public class TodoUtil {

    /**
     * Returns an add command string for adding the {@code todo}.
     */
    public static String getAddTodoCommand(Todo todo) {
        return " " + AddTodoCommand.COMMAND_WORD + " " + getTodoDetails(todo);
    }

    /**
     * Returns a filter command string for filtering {@code todo}s.
     */
    public static String getFilterTodoCommand(Todo todo) {
        return " " + AddTodoCommand.COMMAND_WORD + " " + getTodoDetails(todo);
    }

    /**
     * Returns the part of command string for the given {@code todo}'s details.
     */
    public static String getTodoDetails(Todo todo) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + todo.getDescription());
        todo.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTodoDescriptor}'s details.
     */
    public static String getEditTodoDescriptorDetails(EditTodoCommand.EditTodoDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription()
                .ifPresent(description -> sb.append(PREFIX_DESCRIPTION).append(description).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
