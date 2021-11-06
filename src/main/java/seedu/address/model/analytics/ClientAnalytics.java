package seedu.address.model.analytics;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.HashMap;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

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
        this.values = new TrackedValueList(Status.values()); // default is to track all values in status

        this.persons.addListener(new ListChangeListener<Person>() { // listen for changes in contacts
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateAnalytics();
            }
        });
        updateAnalytics(); // initialUpdate
    }

    private void updateAnalytics() {
        HashMap<Status, Integer> counts = new HashMap<Status, Integer>();
        System.out.println("Update status counts");
        for (Person person: this.persons) {
            if (person.getRelationship().value.equals("client")) {
                LocalDateTime lastUpdated = person.getLastUpdated();
                Status status = person.getStatus();
                // check if expired
                if (isInThisQuarter(lastUpdated)) {
                    if (!counts.containsKey(status)) {
                        counts.put(status, 1);
                    } else {
                        int currentValue = counts.get(status);
                        counts.put(status, currentValue + 1);
                    }
                }
            }
        }
        values.update(counts);
    }

    public int getValueOfTrackedField(Status state) {
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
