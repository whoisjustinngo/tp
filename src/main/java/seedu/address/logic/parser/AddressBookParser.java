package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ERROR_PARSING_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import seedu.address.logic.commands.TabSwitchCommand;
import seedu.address.logic.commands.UpdateCustomGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    private static final String DASHBOARD_TAB_ID = "dashboardTab";
    private static final String CONTACTS_TAB_ID = "contactsTab";
    private static final String SCHEDULE_TAB_ID = "scheduleTab";
    private static final String TODOS_TAB_ID = "todosTab";
    private static final String DETAILS_TAB_ID = "detailsTab";

    private static final String DASHBOARD_PREFIX = "dashboard";
    private static final String CONTACTS_PREFIX = "contacts";
    private static final String SCHEDULE_PREFIX = "schedule";
    private static final String TODOS_PREFIX = "todos";

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<tab>\\S+) (?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern CONTEXTUAL_COMMAND_FORMAT = Pattern
            .compile("(?<prefixTab>\\S+) /(?<tab>\\S+) (?<commandWord>\\S+)(?<arguments>.*)");

    private String targetTab;

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
        if (contextualMatcher.matches()) {
            matcher = contextualMatcher;
        } else if (basicMatcher.matches()) {
            matcher = basicMatcher;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String tab = toTabIDs(matcher.group("tab"));
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        this.targetTab = toTabPrefix(tab);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                return new AddCustomGoalCommandParser().parse(arguments);

            case CONTACTS_TAB_ID:
                return new AddCommandParser().parse(arguments);

            case SCHEDULE_TAB_ID:
                return new AddScheduleCommandParser().parse(arguments);

            case TODOS_TAB_ID:
                return new AddTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case AddPolicyCommand.COMMAND_WORD:
            switch (tab) {
            case CONTACTS_TAB_ID:
                //fallthrough
            case DETAILS_TAB_ID:
                return new AddPolicyCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case UpdateCustomGoalCommand.COMMAND_WORD:
            switch(tab) {

            case DASHBOARD_TAB_ID:
                return new UpdateCustomGoalCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case ListCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS_TAB_ID:
                return new ListCommand();

            case SCHEDULE_TAB_ID:
                return new ListSchedulesCommand();

            case TODOS_TAB_ID:
                return new ListTodosCommand();

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case EditCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS_TAB_ID:
                return new EditCommandParser().parse(arguments);

            case TODOS_TAB_ID:
                return new EditTodoCommandParser().parse(arguments);

            case SCHEDULE_TAB_ID:
                return new EditScheduleCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case DeleteCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                return new DeleteCustomGoalCommandParser().parse(arguments);

            case CONTACTS_TAB_ID:
                return new DeleteCommandParser().parse(arguments);

            case SCHEDULE_TAB_ID:
                return new DeleteScheduleCommandParser().parse(arguments);

            case TODOS_TAB_ID:
                return new DeleteTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case FindCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS_TAB_ID:
                return new FindCommandParser().parse(arguments);

            case TODOS_TAB_ID:
                return new FindTodoCommandParser().parse(arguments);

            case SCHEDULE_TAB_ID:
                return new FindScheduleCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case FilterCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);
            case CONTACTS_TAB_ID:
                return new FilterCommandParser().parse(arguments);
            case SCHEDULE_TAB_ID:
                return new FilterScheduleCommandParser().parse(arguments);
            case TODOS_TAB_ID:
                return new FilterTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case SelectContactCommand.COMMAND_WORD:
            switch (tab) {
            case DETAILS_TAB_ID:
                //fallthrough
            case CONTACTS_TAB_ID:
                return new SelectContactCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case ClearCommand.COMMAND_WORD:
            switch (tab) {

            case CONTACTS_TAB_ID:
                return new ClearCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case DoneTodoCommand.COMMAND_WORD:
            switch (tab) {

            case TODOS_TAB_ID:
                return new DoneTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case TabSwitchCommand.COMMAND_WORD:
            this.targetTab = arguments;
            return new TabSwitchCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ImportCommand.COMMAND_WORD:
            return new ImportCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case AddNoteCommand.COMMAND_WORD:
            switch(tab) {
            case DETAILS_TAB_ID:
                return new AddNoteCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        case EditStatusCommand.COMMAND_WORD:
            switch(tab) {
            case CONTACTS_TAB_ID:
                //fallthrough
            case DETAILS_TAB_ID:
                return new EditStatusCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * Converts convinience tab names to tab IDs.
     *
     * @param tab tabname provided, might be tab IDs themselves
     * @return
     */
    private String toTabIDs(String tab) throws ParseException {
        switch (tab) {
        case DASHBOARD_PREFIX:
            return DASHBOARD_TAB_ID;

        case CONTACTS_PREFIX:
            return CONTACTS_TAB_ID;

        case TODOS_PREFIX:
            return TODOS_TAB_ID;

        case SCHEDULE_PREFIX:
            return SCHEDULE_TAB_ID;

        case DASHBOARD_TAB_ID:
        case CONTACTS_TAB_ID:
        case TODOS_TAB_ID:
        case SCHEDULE_TAB_ID:
            return tab;

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    private String toTabPrefix(String tabId) throws ParseException {
        switch (tabId) {

        case DASHBOARD_TAB_ID:
            return DASHBOARD_PREFIX;

        case CONTACTS_TAB_ID:
            return CONTACTS_PREFIX;

        case TODOS_TAB_ID:
            return TODOS_PREFIX;

        case SCHEDULE_TAB_ID:
            return SCHEDULE_PREFIX;

        default:
            throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
        }
    }

    /**
     * Always switches to where it last ran the context.
     * @return
     */
    public Command goToContextTab() throws ParseException {
        return new TabSwitchCommandParser().parse(this.targetTab);
    }
}
