package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Adds a Todo.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "/todos add";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from add todo");
    }
}
