package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSchedule.READ;
import static seedu.address.testutil.TypicalSchedule.TRAVEL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateScheduleException;
import seedu.address.model.event.exceptions.EventNotFoundException;

public class UniqueScheduleListTest {
    private final UniqueScheduleList uniqueScheduleList = new UniqueScheduleList();

    @Test
    public void contains_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.contains(null));
    }

    @Test
    public void contains_scheduleNotInList_returnsFalse() {
        assertFalse(uniqueScheduleList.contains(READ));
    }

    @Test
    public void contains_scheduleInList_returnsTrue() {
        uniqueScheduleList.add(READ);
        assertTrue(uniqueScheduleList.contains(READ));
    }

    @Test
    public void add_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.add(null));
    }

    @Test
    public void add_duplicateSchedule_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(READ);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList.add(READ));
    }

    @Test
    public void setSchedule_nullTargetSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedule(null, READ));
    }

    @Test
    public void setSchedule_nullEditedSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedule(READ, null));
    }

    @Test
    public void setSchedule_targetScheduleNotInList_throwsScheduleNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueScheduleList.setSchedule(READ, READ));
    }

    @Test
    public void setSchedule_editedScheduleIsSameSchedule_success() {
        uniqueScheduleList.add(READ);
        uniqueScheduleList.setSchedule(READ, READ);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(READ);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void remove_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.remove(null));
    }

    @Test
    public void remove_scheduleDoesNotExist_throwsScheduleNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueScheduleList.remove(READ));
    }

    @Test
    public void remove_existingSchedule_removesSchedule() {
        uniqueScheduleList.add(READ);
        uniqueScheduleList.remove(READ);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nulluniqueScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((UniqueScheduleList) null));
    }

    @Test
    public void setSchedules_uniqueScheduleList_replacesOwnListWithProvideduniqueScheduleList() {
        uniqueScheduleList.add(READ);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(TRAVEL);
        uniqueScheduleList.setSchedules(expectedUniqueScheduleList);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((List<Schedule>) null));
    }

    @Test
    public void setSchedules_list_replacesOwnListWithProvidedList() {
        uniqueScheduleList.add(READ);
        List<Schedule> scheduleList = Collections.singletonList(TRAVEL);
        uniqueScheduleList.setSchedules(scheduleList);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(TRAVEL);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_listWithDuplicateSchedules_throwsDuplicateScheduleException() {
        List<Schedule> listWithDuplicateSchedules = Arrays.asList(READ, READ);
        assertThrows(DuplicateScheduleException.class, () ->
                uniqueScheduleList.setSchedules(listWithDuplicateSchedules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueScheduleList.asUnmodifiableObservableList().remove(0));
    }
}
