package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DoneTodoCommand object
 */
public class DoneTodoCommandParser implements Parser<DoneTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DoneTodoCommand
     * and returns a DoneTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneTodoCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneTodoCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTodoCommand.MESSAGE_USAGE), pe);
        }
    }

}
