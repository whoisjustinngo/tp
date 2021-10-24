package seedu.address.model.todo.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.todo.Todo;

/**
 * Tests that a {@code Todo}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Todo> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Todo todo) {
        // all keywords must be present in tag
        for (String keyword : keywords) {
            boolean hasMatch = todo.getTags().stream()
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
