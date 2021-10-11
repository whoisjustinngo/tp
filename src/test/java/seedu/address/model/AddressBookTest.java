package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTodos.READ;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.todo.Todo;
import seedu.address.model.todo.exceptions.DuplicateTodoException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TodoBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Todo> newTodos = Arrays.asList(READ);
        AddressBookStub newData = new AddressBookStub(newPersons, newTodos);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTodos_throwsDuplicateTodoException() {
        // Two todos with the same description
        Todo editedRead = new TodoBuilder(READ).withDescription(VALID_DESCRIPTION_READ).build();
        List<Person> newPersons = Arrays.asList(ALICE);
        List<Todo> newTodos = Arrays.asList(READ, editedRead);
        AddressBookStub newData = new AddressBookStub(newPersons, newTodos);

        assertThrows(DuplicateTodoException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasTodo_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTodo(null));
    }

    @Test
    public void hasTodo_todoNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTodo(READ));
    }

    @Test
    public void hasTodo_todoInAddressBook_returnsTrue() {
        addressBook.addTodo(READ);
        assertTrue(addressBook.hasTodo(READ));
    }

    @Test
    public void hasTodo_todoWithSameDescriptionInAddressBook_returnsTrue() {
        addressBook.addTodo(READ);
        Todo editedRead = new TodoBuilder(READ).withDescription(VALID_DESCRIPTION_READ).build();
        assertTrue(addressBook.hasTodo(editedRead));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getTodoList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTodoList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list and todos list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Todo> todos = FXCollections.observableArrayList();
        private final ObservableList<Schedule> schedules = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Todo> todos) {
            this.persons.setAll(persons);
            this.todos.setAll(todos);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Todo> getTodoList() {
            return todos;
        }

        @Override
        public ObservableList<Schedule> getScheduleList() {
            return schedules;
        }
    }

}
