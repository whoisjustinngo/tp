package seedu.address.model.analytics;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ClientStub;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

import java.util.HashMap;

public class Analytics {

    private final ReadOnlyAddressBook addressBook;
    private final ObservableList<Person> persons;
    private final TrackedValueList values;

    public Analytics(ReadOnlyAddressBook addressBook) {
        this.addressBook = addressBook;
        this.persons = addressBook.getPersonList();
        this.values = new TrackedValueList(Status.values()); // default is to track all values
        
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
        HashMap<Status, Integer> counts = new HashMap<Status, Integer>();
        System.out.println("trying to update");
        for (Person person: this.persons) {
            if (person instanceof ClientStub) {
                Status status = ((ClientStub) person).getClientStatus();
                if (!counts.containsKey(status)) {
                    counts.put(status, 1);
                } else {
                    int currentValue = counts.get(status);
                    counts.put(status, currentValue + 1);
                }
            }
        }
        values.update(counts);
    }
    
    public int getValueOfTrackedField(Status status) {
        return this.values.getValue(status);
    }
    
    public ObservableList<Integer> getValues() {
        return this.values.asUnmodifiableList();
    }
}
