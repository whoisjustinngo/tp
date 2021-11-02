package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOAL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCustomGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.customGoal.exceptions.InvalidDateException;

/**
 * Parses input arguments and creates a new AddCustomGoalCommand object
 */
public class AddCustomGoalCommandParser implements Parser<AddCustomGoalCommand> {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String TIME_FORMAT = "HHmm";
    public static final String MESSAGE_WRONG_DATETIME_FORMAT =
            "Please use a valid date and time. Date has to be a date in the calendar in " + DATE_FORMAT
                    + " format, and time in " + TIME_FORMAT + " 24hr format\n E.g. \".... bydate/16-05-2021 "
                    + "bytime/1320\"";
    public static final String MESSAGE_WRONG_GOAL_FORMAT =
            "Goal has to be a number greater than 0!";
    public static final String MESSAGE_MISSING_DESCRIPTION = "Description cannot be empty!";

    /**
     * Parses the provided string and creates a proper {@link AddCustomGoalCommand} that can be
     * subsequently executed.
     *
     * @param args The string to parse.
     * @return TheAddCustomGoalCommand created from parsing.
     * @throws ParseException If the argument given is invalid.
     */
    public AddCustomGoalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_GOAL, PREFIX_END_DATE, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_GOAL)
                || ((!arePrefixesPresent(argMultimap, PREFIX_END_DATE)) && arePrefixesPresent(argMultimap,
                PREFIX_END_TIME)) // user specified an end time without an end date
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCustomGoalCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        if (description.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_DESCRIPTION));
        }

        float goal;
        try {
            goal = Float.parseFloat(argMultimap.getValue(PREFIX_GOAL).get());
            if (goal <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_WRONG_GOAL_FORMAT));
            }
        } catch (NumberFormatException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_WRONG_GOAL_FORMAT));
        }

        LocalTime endTime;
        try { // if have time
            endTime = LocalTime.parse(argMultimap.getValue(PREFIX_END_TIME).get(),
                    DateTimeFormatter.ofPattern(TIME_FORMAT));
        } catch (NoSuchElementException nsee) { // if user didn't specify a time
            endTime = LocalTime.MAX;
        } catch (DateTimeParseException dtpe) { // if user specified a wrong time
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_WRONG_DATETIME_FORMAT));
        }

        LocalDate endDate;
        try { // if have date
            endDate = LocalDate.parse(argMultimap.getValue(PREFIX_END_DATE).get(),
                    DateTimeFormatter.ofPattern(DATE_FORMAT));
            checkIfSameDate(argMultimap.getValue(PREFIX_END_DATE).get(), endDate);
        } catch (NoSuchElementException nsee) { // if user didnt specify a date
            endDate = LocalDate.MAX;
        } catch (DateTimeParseException | InvalidDateException ex) { // if user specified a wrong time
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_WRONG_DATETIME_FORMAT));
        }

        CustomGoal customGoal = new CustomGoal(description, goal, endDate, endTime);

        return new AddCustomGoalCommand(customGoal);
    }

    private void checkIfSameDate(String intendedDate, LocalDate parsedDate) { // to account for java just choosing
        // the closest valid date
        String actualDate = parsedDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (!actualDate.equals(intendedDate)) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
