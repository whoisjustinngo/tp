package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.exceptions.InvalidTimeException;

public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddScheduleCommand and returns an AddScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_FROM,
                PREFIX_TO);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_FROM, PREFIX_TO)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        String date = argMultimap.getValue(PREFIX_DATE).get();
        String from = argMultimap.getValue(PREFIX_FROM).get();
        String to = argMultimap.getValue(PREFIX_TO).get();

        try {
            Schedule schedule = new Schedule(description, date, from, to, false);
            return new AddScheduleCommand(schedule);
        } catch (InvalidTimeException e) {
            throw new ParseException(e.getMsg());
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
