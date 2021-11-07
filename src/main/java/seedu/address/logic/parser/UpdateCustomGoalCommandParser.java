package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateCustomGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateCustomGoalCommand object.
 */
public class UpdateCustomGoalCommandParser implements Parser<UpdateCustomGoalCommand> {

    private static final String MESSAGE_INVALID_VALUE_FORMAT = "Please specify a valid value (can be an integer or "
            + "a decimal value)";
    private static final String MESSAGE_INVALID_INDEX = "Please specify a valid index";

    /**
     * Parses the provided string and creates a proper {@link UpdateCustomGoalCommand} that can be
     * subsequently executed.
     * @param args The string to parse.
     * @return The UpdateCustomGoalCommand created from parsing.
     * @throws ParseException If the argument given is invalid.
     */
    public UpdateCustomGoalCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VALUE);
        if (!arePrefixesPresent(argMultimap, PREFIX_VALUE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateCustomGoalCommand.MESSAGE_USAGE, MESSAGE_INVALID_VALUE_FORMAT));
        }

        try {
            Index index = ParserUtil.parseIndex(args.trim().split(" ")[0]);
            float valueToUpdateBy = Float.parseFloat(argMultimap.getValue(PREFIX_VALUE).get());
            return new UpdateCustomGoalCommand(index, valueToUpdateBy);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_INDEX, e);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX));
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_VALUE_FORMAT));
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
