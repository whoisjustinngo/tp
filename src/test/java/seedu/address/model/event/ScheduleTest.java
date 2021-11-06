package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEARNING;
import static seedu.address.testutil.TypicalSchedule.READ;
import static seedu.address.testutil.TypicalSchedule.TRAVEL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Schedule schedule = new ScheduleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> schedule.getTags().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Schedule readCopy = new ScheduleBuilder(READ).build();
        assertTrue(READ.equals(readCopy));

        // same object -> returns true
        assertTrue(READ.equals(READ));

        // null -> returns false
        assertFalse(READ.equals(null));

        // different type -> returns false
        assertFalse(READ.equals(5));

        // different Schedule -> returns false
        assertFalse(READ.equals(TRAVEL));

        // different description -> returns false
        Schedule editedRead = new ScheduleBuilder(READ).withDescription(VALID_DESCRIPTION_TRAVEL).build();
        assertFalse(READ.equals(editedRead));

        // different tags -> returns true
        editedRead = new ScheduleBuilder(READ).withTags(VALID_TAG_LEARNING).build();
        assertTrue(READ.equals(editedRead));
    }
}
