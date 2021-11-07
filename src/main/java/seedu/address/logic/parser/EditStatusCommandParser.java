package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STATUS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Status;

/**
 * Parses input arguments and creates a new EditStatusCommand object
 */
public class EditStatusCommandParser implements Parser<EditStatusCommand> {

    /**
     * Used for separation of index and status.
     */
    private static final Pattern EDIT_STATUS_ARGS_FORMAT = Pattern
            .compile("(?<index>\\d+) (?<status>.*)");

    //@@author mokdarren
    /**
     * Parses the given {@code String} of arguments in the context of the EditStatusCommand
     * and returns an EditStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final Matcher matcher = EDIT_STATUS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStatusCommand.MESSAGE_USAGE));
        }
        final String statusArg = matcher.group("status");
        if (!Status.contains(statusArg.toUpperCase())) {
            throw new ParseException(String.format(MESSAGE_INVALID_STATUS, EditStatusCommand.MESSAGE_USAGE));
        }
        try {
            final String indexArg = matcher.group("index");
            Index index = ParserUtil.parseIndex(indexArg);

            return new EditStatusCommand(index, Status.valueOf(statusArg.toUpperCase()));
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, e);
        }
    }


}
