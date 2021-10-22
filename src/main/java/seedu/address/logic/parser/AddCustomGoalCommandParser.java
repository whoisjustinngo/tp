package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCustomGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customGoal.CustomGoal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddTodoCommand object
 */
public class AddCustomGoalCommandParser implements Parser<AddCustomGoalCommand> {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String TIME_FORMAT = "HHmm";
    public static final String MESSAGE_WRONG_DATETIME_FORMAT =
            "Please use date in " + DATE_FORMAT + " format and time in " + TIME_FORMAT + " 24hr format\n";
    public static final String MESSAGE_WRONG_GOAL_FORMAT = 
            "Goal has to be an integer";

    /**
     * Parses the given {@code String} of arguments in the context of the AddTodoCommand
     * and returns an AddTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCustomGoalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_GOAL, PREFIX_END_DATE, PREFIX_END_TIME); 
        // add more fields

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_GOAL)
                || ((!arePrefixesPresent(argMultimap, PREFIX_END_DATE)) && arePrefixesPresent(argMultimap,
                PREFIX_END_TIME))  // user specified an end time without an end date
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCustomGoalCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        int goal;
        try {
            goal = Integer.parseInt(argMultimap.getValue(PREFIX_GOAL).get());    
        } catch (NumberFormatException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_WRONG_GOAL_FORMAT));
        }
        
        LocalTime endTime;
        try {  // if have time
            endTime = LocalTime.parse(argMultimap.getValue(PREFIX_END_TIME).get(), DateTimeFormatter.ofPattern(TIME_FORMAT));
        } catch (NoSuchElementException nsee) { // if user didnt specify a time
            endTime = LocalTime.MAX;
        } catch (DateTimeParseException dtpe) { // if user specified a wrong time
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_WRONG_DATETIME_FORMAT));
        }
        
        LocalDate endDate;
        try {  // if have date
            endDate = LocalDate.parse(argMultimap.getValue(PREFIX_END_DATE).get(), DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (NoSuchElementException nsee) { // if user didnt specify a date
            endDate = LocalDate.MAX;
        } catch (DateTimeParseException dtpe) { // if user specified a wrong time
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_WRONG_DATETIME_FORMAT));
        }
        

        CustomGoal customGoal = new CustomGoal(description, goal, endDate, endTime);

        return new AddCustomGoalCommand(customGoal);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
