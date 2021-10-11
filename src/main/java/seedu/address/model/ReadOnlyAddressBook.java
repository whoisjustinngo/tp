package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list. This list will not contain
     * any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the todos list. This list will not contain
     * any duplicate todos.
     */
    ObservableList<Todo> getTodoList();

    /**
     * Returns an unmodifiable view of the schedules list. This list will not
     * contain any duplicate schedules.
     */
    ObservableList<Schedule> getScheduleList();

}
