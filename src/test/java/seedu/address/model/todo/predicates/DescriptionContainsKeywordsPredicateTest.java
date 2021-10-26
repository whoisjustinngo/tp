package seedu.address.model.todo.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("Appeal"));
        assertTrue(predicate.test(new TodoBuilder().withDescription("Appeal Travel").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Appeal", "Travel"));
        assertTrue(predicate.test(new TodoBuilder().withDescription("Appeal Travel").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Travel", "Shop"));
        assertTrue(predicate.test(new TodoBuilder().withDescription("Appeal Shop").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("aPPeAl", "tRAvEl"));
        assertTrue(predicate.test(new TodoBuilder().withDescription("Appeal Travel").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TodoBuilder().withDescription("Appeal").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Shop"));
        assertFalse(predicate.test(new TodoBuilder().withDescription("Appeal Travel").build()));
    }
}
