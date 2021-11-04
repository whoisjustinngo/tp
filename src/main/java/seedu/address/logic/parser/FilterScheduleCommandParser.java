package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEUDLE_INPUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.exceptions.InvalidScheduleInputException;
import seedu.address.model.event.predicates.ScheduleContainsDatePredicate;
import seedu.address.model.event.predicates.ScheduleContainsFromPredicate;
import seedu.address.model.event.predicates.ScheduleContainsKeywordsPredicate;
import seedu.address.model.event.predicates.ScheduleContainsToPredicate;
import seedu.address.model.event.predicates.ScheduleTagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterScheduleCommand object
 */
public class FilterScheduleCommandParser implements Parser<FilterScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * FilterCommand and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterScheduleCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_FROM,
                PREFIX_TO, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterScheduleCommand.MESSAGE_USAGE));
        }
        try {
            Predicate<Schedule> descriptionPredicate = getPredicateFromMultimap(PREFIX_DESCRIPTION, argMultimap);
            Predicate<Schedule> tagsPredicate = getPredicateFromMultimap(PREFIX_TAG, argMultimap);
            Predicate<Schedule> datePredicate = getPredicateFromMultimap(PREFIX_DATE, argMultimap);
            Predicate<Schedule> fromPredicate = getPredicateFromMultimap(PREFIX_FROM, argMultimap);
            Predicate<Schedule> toPredicate = getPredicateFromMultimap(PREFIX_TO, argMultimap);
            return new FilterScheduleCommand(
                    descriptionPredicate.and(tagsPredicate).and(datePredicate).and(fromPredicate).and(toPredicate));
        } catch (InvalidScheduleInputException e) {
            throw new ParseException(MESSAGE_INVALID_SCHEUDLE_INPUT);
        }
    }

    private Predicate<Schedule> getPredicateFromMultimap(Prefix prefix, ArgumentMultimap argMultimap)
            throws InvalidScheduleInputException {
        Optional<String> filter = argMultimap.getValue(prefix);
        if (filter.isEmpty()) {
            return unused -> true;
        }
        String[] keywords = filter.get().split("\\s+");
        if (prefix.getPrefix() == PREFIX_TAG.getPrefix()) {
            return new ScheduleTagContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_DESCRIPTION.getPrefix())) {
            return new ScheduleContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_DATE.getPrefix())) {
            return new ScheduleContainsDatePredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_FROM.getPrefix())) {
            return new ScheduleContainsFromPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_TO.getPrefix())) {
            return new ScheduleContainsToPredicate(Arrays.asList(keywords));
        } else {
            return unused -> true;
        }
    }
}
