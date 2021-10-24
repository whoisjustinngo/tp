package seedu.address.model.todo.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;

public class TodoIsDonePredicateTest {

    @Test
    public void test_todoIsDone_returnsTrue() {
        TodoIsDonePredicate predicate = new TodoIsDonePredicate();
        assertTrue(predicate.test(new TodoBuilder().withDone().build()));
    }

    @Test
    public void test_todoIsNotDone_returnsFalse() {
        TodoIsDonePredicate predicate = new TodoIsDonePredicate();
        assertFalse(predicate.test(new TodoBuilder().build()));
    }
}
