package seedu.address.model.analytics;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.analytics.exceptions.UntrackedFieldException;
import seedu.address.model.person.ClientState;

public class TrackedValueList {

    private final ObservableList<Integer> internalValueList = FXCollections.observableArrayList();
    private final ObservableList<Integer> internalUnmodifiableValuesList =
            FXCollections.unmodifiableObservableList(internalValueList);

    private final HashMap<ClientState, Integer> statusToIndexMappings;
    private final HashSet<ClientState> trackedFields;

    TrackedValueList(ClientState[] toTrack) {
        statusToIndexMappings = new HashMap<ClientState, Integer>();
        this.trackedFields = new HashSet<ClientState>();
        int index = 0;
        for (ClientState state: toTrack) {
            requireNonNull(state);
            statusToIndexMappings.put(state, index);
            internalValueList.add(0);
            index++;
            this.trackedFields.add(state);
        }
    }

    private int getIndex(ClientState state) {
        if (this.statusToIndexMappings.containsKey(state)) {
            return this.statusToIndexMappings.get(state);
        } else {
            throw new UntrackedFieldException();
        }
    }

    public int getValue(ClientState state) {
        requireNonNull(state);
        int index = getIndex(state);
        return this.internalValueList.get(index);
    }

    public ObservableList<Integer> asUnmodifiableList() {
        return this.internalUnmodifiableValuesList;
    }

    /**
     * Updates the tracked value list with values from the provided hashmap.
     * @param counts Counts of the number of clients having each ClientState.
     */
    public void update(HashMap<ClientState, Integer> counts) {
        for (ClientState state: counts.keySet()) {
            if (this.trackedFields.contains(state)) {
                int index = this.getIndex(state);
                int value = counts.get(state);
                this.internalValueList.set(index, value);
            }
        }
    }
}
