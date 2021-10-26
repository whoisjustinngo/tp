package seedu.address.model.todo.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagsContainsKeywordsPredicate firstPredicate = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("learning"));
        assertTrue(predicate.test(new TodoBuilder().withTags("learning", "leisure").build()));

        // Multiple keywords, only one matches
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("learning", "leisure"));
        assertFalse(predicate.test(new TodoBuilder().withTags("learning", "personalDevelopment").build()));
        assertFalse(predicate.test(new TodoBuilder().withTags("personalDevelopment", "learning").build()));
        assertFalse(predicate.test(new TodoBuilder().withTags("learning").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("lEaRNing"));
        assertTrue(predicate.test(new TodoBuilder().withTags("learning").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TodoBuilder().withTags("learning").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("development"));
        assertFalse(predicate.test(new TodoBuilder().withTags("learning", "leisure").build()));

        // Keywords match description, but does not match tag
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("read", "travel"));
        assertFalse(predicate.test(new TodoBuilder().withDescription("read").build()));
    }
}
