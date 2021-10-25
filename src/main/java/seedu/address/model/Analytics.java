package seedu.address.model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

public class Analytics {
    
    private final ReadOnlyAddressBook addressBook;
    private final ObservableList<Person> persons;
    
    Analytics(ReadOnlyAddressBook addressBook) {
        this.addressBook = addressBook;
        this.persons = addressBook.getPersonList();
        this.persons.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateAnalytics();
            }
        });
        updateAnalytics(); // initial update
    }
    
    private void updateAnalytics() {
        System.out.println("update analytics");
    }
    
}
