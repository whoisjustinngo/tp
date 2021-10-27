package seedu.address.model.analytics;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.HashMap;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ClientState;
import seedu.address.model.person.ClientStub;
import seedu.address.model.person.Person;

/**
 * A class which tracks various fields in the list of contacts for display on the dashboard.
 */
public class ClientAnalytics {

    private final ReadOnlyAddressBook addressBook;
    private final ObservableList<Person> persons;
    private final TrackedValueList values;

    /**
     * Creates a new ClientAnalytics object that tracks various fields in the contacts for display on the dashboard.
     */
    public ClientAnalytics(ReadOnlyAddressBook addressBook) {
        requireNonNull(addressBook);
        this.addressBook = addressBook;
        this.persons = addressBook.getPersonList();
        this.values = new TrackedValueList(ClientState.values()); // default is to track all values in status

        this.persons.addListener(new ListChangeListener<Person>() { // listen for changes in contacts
            @Override
            public void onChanged(Change<? extends Person> c) {
                System.out.println("change: " + c);
                updateAnalytics();
            }
        });
        updateAnalytics(); // initialUpdate
    }

    private void updateAnalytics() {
        HashMap<ClientState, Integer> counts = new HashMap<ClientState, Integer>();
        System.out.println("trying to update");
        for (Person person: this.persons) {
            if (person instanceof ClientStub) { // TODO update with client
                ClientStub client = (ClientStub) person;
                LocalDateTime lastUpdated = client.getLastUpdated();
                ClientState state = client.getClientState();
                // check if expired
                if (isInThisQuarter(lastUpdated)) {
                    if (!counts.containsKey(state)) {
                        counts.put(state, 1);
                    } else {
                        int currentValue = counts.get(state);
                        counts.put(state, currentValue + 1);
                    }
                }
            }
        }
        values.update(counts);
    }

    public int getValueOfTrackedField(ClientState state) {
        return this.values.getValue(state);
    }

    private boolean isInThisQuarter(LocalDateTime entry) {
        return entry.get(IsoFields.QUARTER_OF_YEAR) == LocalDateTime.now().get(IsoFields.QUARTER_OF_YEAR)
                && entry.getYear() == LocalDateTime.now().getYear();
    }

    public ObservableList<Integer> getValues() {
        return this.values.asUnmodifiableList();
    }
}
