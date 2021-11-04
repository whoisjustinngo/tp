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
        if (contextualMatcher.matches()) {
            matcher = contextualMatcher;
        } else if (basicMatcher.matches()) {
            matcher = basicMatcher;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final Tab tab = Tab.valueOf(matcher.group("tab"));
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        this.targetTab = tab;

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD:
                return new AddCustomGoalCommandParser().parse(arguments);

            case CONTACTS:
                return new AddCommandParser().parse(arguments);

            case SCHEDULE:
                return new AddScheduleCommandParser().parse(arguments);

            case TODOS:
                return new AddTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case AddPolicyCommand.COMMAND_WORD:
            switch (tab) {
            case DASHBOARD:
                // fallthrough
            case SCHEDULE:
            case TODOS:
                throw new ParseException(MESSAGE_INVALID_TAB);
            case CONTACTS:
                //fallthrough
            case DETAILS:
                return new AddPolicyCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case UpdateCustomGoalCommand.COMMAND_WORD:
            switch(tab) {

            case DASHBOARD:
                return new UpdateCustomGoalCommandParser().parse(arguments);

            case SCHEDULE:
                //fallthrough
            case TODOS:
            case CONTACTS:
            case DETAILS:
                throw new ParseException(MESSAGE_INVALID_TAB);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case ListCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD:
                // fallthrough
            case DETAILS:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS:
                return new ListCommand();

            case SCHEDULE:
            return new ListSchedulesCommand();

            case TODOS:
                return new ListTodosCommand();

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case EditCommand.COMMAND_WORD:
            switch (tab) {
            case DETAILS:
                //fall through
            case DASHBOARD:
                throw new ParseException(MESSAGE_INVALID_TAB);
            case CONTACTS:
                return new EditCommandParser().parse(arguments);

            case TODOS:
                return new EditTodoCommandParser().parse(arguments);

            case SCHEDULE:
                return new EditScheduleCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case DeleteCommand.COMMAND_WORD:
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

        case FindCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD:
                // fallthrough
            case DETAILS:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS:
                return new FindCommandParser().parse(arguments);

            case TODOS:
            return new FindTodoCommandParser().parse(arguments);

            case SCHEDULE:
                return new FindScheduleCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case FilterCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD:
                throw new ParseException(MESSAGE_INVALID_TAB);
            case CONTACTS:
                return new FilterCommandParser().parse(arguments);
            case SCHEDULE:
                return new FilterScheduleCommandParser().parse(arguments);
            case TODOS:
                return new FilterTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case SelectContactCommand.COMMAND_WORD:
            switch (tab) {
            case DETAILS:
                //fallthrough
            case CONTACTS:
                this.targetTab = Tab.DETAILS;
                return new SelectContactCommandParser().parse(arguments);

            case DASHBOARD:
                // fallthrough
            case SCHEDULE:
            case TODOS:
                throw new ParseException(MESSAGE_INVALID_TAB);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case ClearCommand.COMMAND_WORD:
            switch (tab) {

            case CONTACTS:
                return new ClearCommand();

            case DASHBOARD:
                // fallthrough
            case DETAILS:
            case SCHEDULE:
            case TODOS:
                throw new ParseException(MESSAGE_INVALID_TAB);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case DoneTodoCommand.COMMAND_WORD:
            switch (tab) {

            case TODOS:
                return new DoneTodoCommandParser().parse(arguments);

            case DASHBOARD:
                // fallthrough
            case DETAILS:
            case SCHEDULE:
            case CONTACTS:
                throw new ParseException(MESSAGE_INVALID_TAB);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case TabCommand.COMMAND_WORD:
            this.targetTab = Tab.toEnum(arguments);
            return new TabSwitchCommandParser().parse(this.targetTab.toString());

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
            case TODOS:
                // fallthrough
            case SCHEDULE:
            case CONTACTS:
            case DASHBOARD:
                throw new ParseException(MESSAGE_INVALID_TAB);
            case DETAILS:
                return new AddNoteCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        case EditStatusCommand.COMMAND_WORD:
            switch(tab) {
            case TODOS:
            case SCHEDULE:
            case DASHBOARD:
                throw new ParseException(MESSAGE_INVALID_TAB);
            case CONTACTS:
                //fallthrough
            case DETAILS:
                return new EditStatusCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        case ShowUpcomingEventsCommand.COMMAND_WORD:
            switch(tab) {
            case SCHEDULE:
                return new ShowUpcomingEventsCommand();
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        case ShowPastEventsCommand.COMMAND_WORD:
            switch(tab) {
            case SCHEDULE:
                return new ShowPastEventsCommand();
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * Always switches to where it last ran the context.
     * @return
     */
    public Command goToContextTab() throws ParseException {
        return new TabSwitchCommandParser().parse(this.targetTab.toString());
    }
}
