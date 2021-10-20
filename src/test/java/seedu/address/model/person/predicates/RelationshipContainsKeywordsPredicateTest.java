package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;



class RelationshipContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("client");
        List<String> secondPredicateKeywordList = Arrays.asList("friend", "client");

        RelationshipContainsKeywordsPredicate firstPredicate = new RelationshipContainsKeywordsPredicate(
                                                                                firstPredicateKeywordList);
        RelationshipContainsKeywordsPredicate secondPredicate = new RelationshipContainsKeywordsPredicate(
                                                                                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RelationshipContainsKeywordsPredicate firstPredicateCopy = new RelationshipContainsKeywordsPredicate(
                                                                                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword exact
        RelationshipContainsKeywordsPredicate predicate = new RelationshipContainsKeywordsPredicate(
                Collections.singletonList("client"));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("client").build()));

        // One keyword not exact
        predicate = new RelationshipContainsKeywordsPredicate(Collections.singletonList("cli"));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("client").build()));

        // Mixed-case keywords
        predicate = new RelationshipContainsKeywordsPredicate(Arrays.asList("cliEnt"));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("client").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RelationshipContainsKeywordsPredicate predicate = new RelationshipContainsKeywordsPredicate(
                                                                                    Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAddress("client").build()));

        // Non-matching keyword
        predicate = new RelationshipContainsKeywordsPredicate(Arrays.asList("mom"));
        assertFalse(predicate.test(new PersonBuilder().withName("friend").build()));

        // Keywords match phone, email and address, but does not match relationship
        predicate = new RelationshipContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withRelationship("client").build()));
    }
}
