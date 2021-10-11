package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ERROR_PARSING_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTodosCommand;
import seedu.address.logic.commands.TabSwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    private static final String DASHBOARD_TAB_ID = "dashboardTab";
    private static final String CONTACTS_TAB_ID = "contactsTab";
    private static final String SCHEDULE_TAB_ID = "scheduleTab";
    private static final String TODOS_TAB_ID = "todosTab";

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<tab>\\S+) (?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String tab = matcher.group("tab");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS_TAB_ID:
                return new AddCommandParser().parse(arguments);

            case SCHEDULE_TAB_ID:
                // TODO by Ricky
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            case TODOS_TAB_ID:
                return new AddTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case EditCommand.COMMAND_WORD:
            switch (tab) {

            case CONTACTS_TAB_ID:
                return new EditCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case DeleteCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS_TAB_ID:
                return new DeleteCommandParser().parse(arguments);

            case SCHEDULE_TAB_ID:
                // TODO by Ricky
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            case TODOS_TAB_ID:
                return new DeleteTodoCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case ClearCommand.COMMAND_WORD:
            switch (tab) {

            case CONTACTS_TAB_ID:
                return new ClearCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case FindCommand.COMMAND_WORD:
            switch (tab) {

            case CONTACTS_TAB_ID:
                return new FindCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case TabSwitchCommand.COMMAND_WORD:
            return new TabSwitchCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            switch (tab) {

            case DASHBOARD_TAB_ID:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case CONTACTS_TAB_ID:
                return new ListCommand();

            case SCHEDULE_TAB_ID:
                // TODO by Ricky
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            case TODOS_TAB_ID:
                return new ListTodosCommand();

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
