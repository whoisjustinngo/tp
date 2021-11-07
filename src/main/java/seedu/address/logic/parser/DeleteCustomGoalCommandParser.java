package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCustomGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCustomGoalCommand object.
 */
public class DeleteCustomGoalCommandParser implements Parser<DeleteCustomGoalCommand> {

    private static final String MESSAGE_INVALID_INDEX = "Please specify a valid index";

    /**
     * Parses the provided string and creates a proper {@link DeleteCustomGoalCommand} that can be
     * subsequently executed.
     * @param args The string to parse.
     * @return The DeleteCustomGoalCommand created from parsing.
     * @throws ParseException If the argument given is invalid.
     */
    public DeleteCustomGoalCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
            return new DeleteCustomGoalCommand(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_INDEX, e);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
