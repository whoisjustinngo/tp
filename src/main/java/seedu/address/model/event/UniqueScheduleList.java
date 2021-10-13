package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.DuplicateScheduleException;
import seedu.address.model.event.exceptions.EventNotFoundException;

public class UniqueScheduleList implements Iterable<Schedule> {
    private final ObservableList<Schedule> internalList = FXCollections.observableArrayList();
    private final ObservableList<Schedule> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent schedule as the given
     * argument.
     */
    public boolean contains(Schedule toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Schedule to the list. The Schedule must not already exist in the list.
     */
    public void add(Schedule schedule) {
        requireNonNull(schedule);
        if (contains(schedule)) {
            throw new DuplicateScheduleException();
        }
        internalList.add(schedule);
    }

    public void setSchedules(UniqueScheduleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code schedules}. {@code schedules}
     * must not contain duplicate schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        requireAllNonNull(schedules);
        if (!schedulesAreUnique(schedules)) {
            throw new DuplicateScheduleException();
        }

        internalList.setAll(schedules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Schedule> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Removes the equivalent schedule from the list.
     * The todo must exist in the list.
     */
    public void remove(Schedule toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    @Override
    public Iterator<Schedule> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueScheduleList // instanceof handles nulls
                        && internalList.equals(((UniqueScheduleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code schedules} contains only unique schedules.
     */
    private boolean schedulesAreUnique(List<Schedule> schedule) {
        for (int i = 0; i < schedule.size() - 1; i++) {
            for (int j = i + 1; j < schedule.size(); j++) {
                if (schedule.get(i).equals(schedule.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
