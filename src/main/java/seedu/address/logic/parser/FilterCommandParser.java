package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.RelationshipContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_RELATIONSHIP, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        Predicate<Person> tagsPredicate = getPredicateFromMultimap(PREFIX_TAG, argMultimap);
        Predicate<Person> relationshipPredicate = getPredicateFromMultimap(PREFIX_RELATIONSHIP, argMultimap);
        Predicate<Person> emailPredicate = getPredicateFromMultimap(PREFIX_EMAIL, argMultimap);
        Predicate<Person> addressPredicate = getPredicateFromMultimap(PREFIX_ADDRESS, argMultimap);
        Predicate<Person> phonePredicate = getPredicateFromMultimap(PREFIX_PHONE, argMultimap);
        return new FilterCommand(tagsPredicate.and(relationshipPredicate).and(emailPredicate)
                .and(addressPredicate).and(phonePredicate));
    }

    private Predicate<Person> getPredicateFromMultimap(Prefix prefix, ArgumentMultimap argMultimap) {
        Optional<String> filter = argMultimap.getValue(prefix);
        if (filter.isEmpty()) {
            return unused -> true;
        }
        String[] keywords = filter.get().split("\\s+");
        if (prefix.getPrefix() == PREFIX_TAG.getPrefix()) {
            return new TagsContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_RELATIONSHIP.getPrefix())) {
            return new RelationshipContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_EMAIL.getPrefix())) {
            return new EmailContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_NAME.getPrefix())) {
            return new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_PHONE.getPrefix())) {
            return new PhoneContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_ADDRESS.getPrefix())) {
            return new AddressContainsKeywordsPredicate(Arrays.asList(keywords));
        } else {
            return unused -> true;
        }
    }
}
