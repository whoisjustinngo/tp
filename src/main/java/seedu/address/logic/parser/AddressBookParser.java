package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ERROR_PARSING_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.MainWindow;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

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

        final String tab = MainWindow.getTab();
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            switch (tab) {

            case MainWindow.DASHBOARD_TAB:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case MainWindow.CONTACTS_TAB:
                return new AddCommandParser().parse(arguments);

            case MainWindow.EVENTS_TAB:
                // TODO by Ricky
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            case MainWindow.TODOS_TAB:
                return new AddTodoCommand();

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case EditCommand.COMMAND_WORD:
            switch (tab) {

            case MainWindow.CONTACTS_TAB:
                return new EditCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case DeleteCommand.COMMAND_WORD:
            switch (tab) {

            case MainWindow.DASHBOARD_TAB:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case MainWindow.CONTACTS_TAB:
                return new DeleteCommandParser().parse(arguments);

            case MainWindow.EVENTS_TAB:
                // TODO by Ricky
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            case MainWindow.TODOS_TAB:
                // TODO by KS
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            default:
                throw new ParseException(MESSAGE_ERROR_PARSING_TAB);
            }

        case ClearCommand.COMMAND_WORD:
            switch (tab) {

            case MainWindow.CONTACTS_TAB:
                return new ClearCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case FindCommand.COMMAND_WORD:
            switch (tab) {

            case MainWindow.CONTACTS_TAB:
                return new FindCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }


        case ListCommand.COMMAND_WORD:
            switch (tab) {

            case MainWindow.DASHBOARD_TAB:
                throw new ParseException(MESSAGE_INVALID_TAB);

            case MainWindow.CONTACTS_TAB:
                return new ListCommand();

            case MainWindow.EVENTS_TAB:
                // TODO by Ricky
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

            case MainWindow.TODOS_TAB:
                // TODO by KS
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND); // Placeholder for now, please replace this in v1.2

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
