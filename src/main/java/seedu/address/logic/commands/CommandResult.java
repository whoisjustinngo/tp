package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.TabSwitch;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should switch to a new tab */
    private final boolean switchTab;

    private final TabSwitch.Tab tabId;

    /** A new window should be shown to the user. */
    private final boolean showImport;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.switchTab = false;
        this.tabId = null;
        this.showImport = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code switchTab}, and {@code tabId}, and other fields set to their default
     * value.
     */
    public CommandResult(String feedbackToUser, boolean switchTab, TabSwitch.Tab tabId) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.switchTab = switchTab;
        this.tabId = tabId;
        this.showImport = false;
    }

    public CommandResult(String feedbackToUser, boolean showImport) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.switchTab = false;
        this.tabId = null;
        this.showImport = showImport;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public TabSwitch.Tab getTabId() {
        return tabId;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isSwitchTab() {
        return switchTab;
    }

    public boolean isShowImport() {
        return showImport;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
