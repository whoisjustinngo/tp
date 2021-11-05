package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ERROR_PARSING_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Tab;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DoneTodoCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditStatusCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListSchedulesCommand;
import seedu.address.logic.commands.ListTodosCommand;
import seedu.address.logic.commands.SelectContactCommand;
import seedu.address.logic.commands.ShowPastEventsCommand;
import seedu.address.logic.commands.ShowUpcomingEventsCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.UpdateCustomGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<tab>\\S+) (?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern CONTEXTUAL_COMMAND_FORMAT = Pattern
            .compile("(?<prefixTab>\\S+) /(?<tab>\\S+) (?<commandWord>\\S+)(?<arguments>.*)");

    private Tab targetTab;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher;
        final Matcher basicMatcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher contextualMatcher = CONTEXTUAL_COMMAND_FORMAT.matcher(userInput.trim());
        final Tab tab;
        if (contextualMatcher.matches()) {
            matcher = contextualMatcher;
            tab = Tab.aliasToEnum(matcher.group("tab"));
        } else if (basicMatcher.matches()) {
            matcher = basicMatcher;
            tab = Tab.tabIdToEnum(matcher.group("tab"));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        this.targetTab = tab;

        switch (commandWord) {

        // General commands that work on all tabs
        case TabCommand.COMMAND_WORD:
            return handleTabCommand(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ImportCommand.COMMAND_WORD:
            return new ImportCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        // Tab-specific commands that work differently on different tabs
        case AddCommand.COMMAND_WORD:
            return handleAddCommand(tab, arguments);

        case ListCommand.COMMAND_WORD:
            return handleListCommand(tab);

        case EditCommand.COMMAND_WORD:
            return handleEditCommand(tab, arguments);

        case DeleteCommand.COMMAND_WORD:
            return handleDeleteCommand(tab, arguments);

        case FindCommand.COMMAND_WORD:
            return handleFindCommand(tab, arguments);

        case FilterCommand.COMMAND_WORD:
            return handleFilterCommand(tab, arguments);

        // Dashboard tab commands that only work on Dashboard tab
        case UpdateCustomGoalCommand.COMMAND_WORD:
            return handleUpdateCustomGoalCommand(tab, arguments);

        // Contacts tab commands that only work on Contacts tab
        case ClearCommand.COMMAND_WORD:
            return handleClearCommand(tab);

        // Contacts and Details tabs commands that only work on Contacts tab and Details tab
        case SelectContactCommand.COMMAND_WORD:
            return handleSelectContactCommand(tab, arguments);

        case EditStatusCommand.COMMAND_WORD:
            return handleEditStatusCommand(tab, arguments);

        case AddPolicyCommand.COMMAND_WORD:
            return handleAddPolicyCommand(tab, arguments);

        case AddNoteCommand.COMMAND_WORD:
            return handleAddNoteCommand(tab, arguments);

        // Schedule tab commands that only work on Schedule tab
        case ShowUpcomingEventsCommand.COMMAND_WORD:
            return handleShowUpcomingEventsCommand(tab);

        case ShowPastEventsCommand.COMMAND_WORD:
            return handleShowPastEventsCommand(tab);

        // Todos Tab commands that only work on Todos tab
        case DoneTodoCommand.COMMAND_WORD:
            return handleDoneTodoCommand(tab, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Always switches to where it last ran the context.
     * @return
     */
    public Command goToContextTab() throws ParseException {
        return new TabCommandParser().parse(this.targetTab.toString());
    }

    private Command handleTabCommand(String arguments) throws ParseException {
        this.targetTab = Tab.aliasToEnum(arguments.trim());
        return new TabCommandParser().parse(this.targetTab.toString());
    }

    private Command handleAddCommand(Tab tab, String arguments) throws ParseException {
        // Add command is valid on all tabs except Details
        switch (tab) {

        case DASHBOARD:
            return new AddCustomGoalCommandParser().parse(arguments);

        case CONTACTS:
            return new AddCommandParser().parse(arguments);

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case SCHEDULE:
            return new AddScheduleCommandParser().parse(arguments);

        case TODOS:
            return new AddTodoCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleListCommand(Tab tab) throws ParseException {
        // List command is valid on all tabs except Dashboard
        switch (tab) {

        case DASHBOARD:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            // fallthrough

        case DETAILS:
            this.targetTab = Tab.CONTACTS;
            return new ListCommand();

        case SCHEDULE:
            return new ListSchedulesCommand();

        case TODOS:
            return new ListTodosCommand();

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleEditCommand(Tab tab, String arguments) throws ParseException {
        // Edit command is valid on all tabs except Dashboard and Details
        switch (tab) {

        case DASHBOARD:
            // fallthrough

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            return new EditCommandParser().parse(arguments);

        case SCHEDULE:
            return new EditScheduleCommandParser().parse(arguments);

        case TODOS:
            return new EditTodoCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleDeleteCommand(Tab tab, String arguments) throws ParseException {
        // Delete command is valid on all tabs except Details
        switch (tab) {

        case DASHBOARD:
            return new DeleteCustomGoalCommandParser().parse(arguments);

        case CONTACTS:
            return new DeleteCommandParser().parse(arguments);

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case SCHEDULE:
            return new DeleteScheduleCommandParser().parse(arguments);

        case TODOS:
            return new DeleteTodoCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleFindCommand(Tab tab, String arguments) throws ParseException {
        // Find command is valid on all tabs except Dashboard and Details
        switch (tab) {

        case DASHBOARD:
            // fallthrough

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            return new FindCommandParser().parse(arguments);

        case SCHEDULE:
            return new FindScheduleCommandParser().parse(arguments);

        case TODOS:
            return new FindTodoCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleFilterCommand(Tab tab, String arguments) throws ParseException {
        // Filter command is valid on all tabs except Dashboard and Details
        switch (tab) {

        case DASHBOARD:
            // fallthrough

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            return new FilterCommandParser().parse(arguments);

        case SCHEDULE:
            return new FilterScheduleCommandParser().parse(arguments);

        case TODOS:
            return new FilterTodoCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleUpdateCustomGoalCommand(Tab tab, String arguments) throws ParseException {
        // Update Custom Goal command is only valid on Dashboard tab
        switch(tab) {

        case DASHBOARD:
            return new UpdateCustomGoalCommandParser().parse(arguments);

        case CONTACTS:
            // fallthrough

        case DETAILS:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case TODOS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleClearCommand(Tab tab) throws ParseException {
        // Clear command is only valid on Contacts tab
        switch (tab) {

        case CONTACTS:
            return new ClearCommand();

        case DASHBOARD:
            // fallthrough

        case DETAILS:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case TODOS:
            // fallthrough
            throw new ParseException(MESSAGE_INVALID_TAB);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleSelectContactCommand(Tab tab, String arguments) throws ParseException {
        // Select Contact command is only valid on Contacts and Details tabs
        switch (tab) {

        case DETAILS:
            // fallthrough

        case CONTACTS:
            this.targetTab = Tab.DETAILS;
            return new SelectContactCommandParser().parse(arguments);

        case DASHBOARD:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case TODOS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleEditStatusCommand(Tab tab, String arguments) throws ParseException {
        // Edit Status command is only valid on Contacts and Details tabs
        switch(tab) {

        case TODOS:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case DASHBOARD:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            // fallthrough

        case DETAILS:
            return new EditStatusCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleAddPolicyCommand(Tab tab, String arguments) throws ParseException {
        // Add Policy command is only valid on Contacts and Details tabs
        switch (tab) {

        case DASHBOARD:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case TODOS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            // fallthrough

        case DETAILS:
            return new AddPolicyCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleAddNoteCommand(Tab tab, String arguments) throws ParseException {
        // Add Note command is only valid on Contacts and Details tabs
        switch(tab) {

        case TODOS:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case DASHBOARD:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case CONTACTS:
            // fallthrough

        case DETAILS:
            return new AddNoteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleShowUpcomingEventsCommand(Tab tab) throws ParseException {
        // Show Upcoming Events command is only valid on Schedule tab
        switch(tab) {

        case TODOS:
            // fallthrough

        case DASHBOARD:
            // fallthrough

        case CONTACTS:
            // fallthrough

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case SCHEDULE:
            return new ShowUpcomingEventsCommand();

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleShowPastEventsCommand(Tab tab) throws ParseException {
        // Show Past Events command is only valid on Schedule tab
        switch(tab) {

        case TODOS:
            // fallthrough

        case DASHBOARD:
            // fallthrough

        case CONTACTS:
            // fallthrough

        case DETAILS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        case SCHEDULE:
            return new ShowPastEventsCommand();

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private Command handleDoneTodoCommand(Tab tab, String arguments) throws ParseException {
        // Done Todo command is only valid on Todos tab
        switch (tab) {

        case TODOS:
            return new DoneTodoCommandParser().parse(arguments);

        case DASHBOARD:
            // fallthrough

        case DETAILS:
            // fallthrough

        case SCHEDULE:
            // fallthrough

        case CONTACTS:
            throw new ParseException(MESSAGE_INVALID_TAB);

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }
}
