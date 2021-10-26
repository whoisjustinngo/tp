package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditScheduleCommand;
import seedu.address.logic.commands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditScheduleCommand object
 */
public class EditScheduleCommandParser implements Parser<EditScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_FROM,
                PREFIX_TO);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScheduleCommand.MESSAGE_USAGE),
                    pe);
        }

        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editScheduleDescriptor.setDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editScheduleDescriptor.setDate(argMultimap.getValue(PREFIX_DATE).get());
        }
        if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
            editScheduleDescriptor.setTimeFrom(argMultimap.getValue(PREFIX_FROM).get());
        }
        if (argMultimap.getValue(PREFIX_TO).isPresent()) {
            editScheduleDescriptor.setTimeTo(argMultimap.getValue(PREFIX_TO).get());
        }
        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditScheduleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditScheduleCommand(index, editScheduleDescriptor);
    }
}
