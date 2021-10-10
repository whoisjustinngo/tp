package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TabSwitch;

import static java.util.Objects.requireNonNull;

public class TabSwitchCommand extends Command{

    public static final String COMMAND_WORD = "tab";

    private TabSwitch.Tab tabId;

    public TabSwitchCommand(TabSwitch.Tab tabId) {
        requireNonNull(tabId);
        this.tabId = tabId;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(generateSuccessMessage(), true, this.tabId);
    }

    private String generateSuccessMessage() {
        String message = String.format("Switched to %s", tabId);
        return message;
    }
}
