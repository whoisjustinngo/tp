package seedu.address.model.todo.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.todo.Todo;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Todo> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Todo todo) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(todo.getDescription(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
