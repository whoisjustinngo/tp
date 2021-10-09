package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScheduleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(null, null, 1600, 1800, false));
        assertThrows(NullPointerException.class, () -> new Schedule(null, "12-12-2021", 1600, 1800, false));
        assertThrows(NullPointerException.class, () -> new Schedule("test", null, 1600, 1800, false));
    }

    @Test
    public void marking_schedule_as_done() {
        Schedule schedule = new Schedule("test", "12-12-2021", 1600, 1800, false);
        assertFalse(schedule.isDone());
        schedule = schedule.markAsDone();
        assertTrue(schedule.isDone());
    }
}
