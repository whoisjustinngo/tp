package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null, null, false));
        assertThrows(NullPointerException.class, () -> new Deadline(null, "12-12-2021", false));
        assertThrows(NullPointerException.class, () -> new Deadline("Return book", null, false));
    }

    @Test
    public void marking_deadline_as_done() {
        Deadline deadline = new Deadline("test", "12-12-2021", false);
        assertFalse(deadline.isDone());
        deadline = deadline.markAsDone();
        assertTrue(deadline.isDone());
    }
}
