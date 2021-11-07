package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new SelectContactCommandParser object
 */
public class SelectContactCommandParser implements Parser<SelectContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectContactCommand
     * and returns a SelectContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectContactCommand(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, e);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
