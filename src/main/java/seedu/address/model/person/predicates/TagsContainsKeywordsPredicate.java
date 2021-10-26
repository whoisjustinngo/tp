package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.size() == 0) {
            return false;
        }

        // all keywords must be present in tag
        for (String keyword : keywords) {
            boolean hasMatch = person.getTags().stream()
                    .anyMatch(tag -> tag.getTagName().equalsIgnoreCase(keyword));
            if (!hasMatch) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
