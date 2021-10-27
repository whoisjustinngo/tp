package seedu.address.model.todo.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;

public class TodoIsNotDonePredicateTest {

    @Test
    public void test_todoIsNotDone_returnsTrue() {
        TodoIsNotDonePredicate predicate = new TodoIsNotDonePredicate();
        assertTrue(predicate.test(new TodoBuilder().build()));
    }

    @Test
    public void test_todoIsDone_returnsFalse() {
        TodoIsNotDonePredicate predicate = new TodoIsNotDonePredicate();
        assertFalse(predicate.test(new TodoBuilder().withDone().build()));
    }
}
