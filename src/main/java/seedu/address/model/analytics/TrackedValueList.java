package seedu.address.model.analytics;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.analytics.exceptions.UntrackedFieldException;
import seedu.address.model.person.Status;

public class TrackedValueList {

    private final ObservableList<Integer> internalValueList = FXCollections.observableArrayList();
    private final ObservableList<Integer> internalUnmodifiableValuesList =
            FXCollections.unmodifiableObservableList(internalValueList);

    private final HashMap<Status, Integer> statusToIndexMappings;
    private final HashSet<Status> trackedFields;

    TrackedValueList(Status[] toTrack) {
        statusToIndexMappings = new HashMap<Status, Integer>();
        this.trackedFields = new HashSet<Status>();
        int index = 0;
        for (Status state: toTrack) {
            requireNonNull(state);
            statusToIndexMappings.put(state, index);
            internalValueList.add(0);
            index++;
            this.trackedFields.add(state);
        }
    }

    private int getIndex(Status status) {
        if (this.statusToIndexMappings.containsKey(status)) {
            return this.statusToIndexMappings.get(status);
        } else {
            throw new UntrackedFieldException();
        }
    }

    public int getValue(Status status) {
        requireNonNull(status);
        int index = getIndex(status);
        return this.internalValueList.get(index);
    }

    public ObservableList<Integer> asUnmodifiableList() {
        return this.internalUnmodifiableValuesList;
    }

    /**
     * Updates the tracked value list with values from the provided hashmap.
     * @param counts Counts of the number of clients having each ClientState.
     */
    public void update(HashMap<Status, Integer> counts) {
        for (Status status: counts.keySet()) {
            if (this.trackedFields.contains(status)) {
                int index = this.getIndex(status);
                int value = counts.get(status);
                this.internalValueList.set(index, value);
            }
        }
    }
}
