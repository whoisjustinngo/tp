package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": imports a schedule.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_IMPORT_WINDOW = "Opened import window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_IMPORT_WINDOW, true);
    }
}
