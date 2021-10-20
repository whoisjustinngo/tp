package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Relationship} matches any of the keywords given.
 */
public class RelationshipContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public RelationshipContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> (person.getRelationship().value.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RelationshipContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RelationshipContainsKeywordsPredicate) other).keywords)); // state check
    }

}
