package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Todo> PREDICATE_SHOW_ALL_TODOS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a Todo with the same description as {@code todo} exists in
     * the list of todos.
     */
    boolean hasTodo(Todo todo);

    /**
     * Deletes the given person. The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
<<<<<<< HEAD
     * Adds the given person. {@code person} must not already exist in the address
     * book.
=======
     * Deletes the given todo.
     * The todo must exist in the address book.
     */
    void deleteTodo(Todo target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
>>>>>>> daa6be7ae935578f12644fb9f4e25b3f003a5cd0
     */
    void addPerson(Person person);

    /**
     * Adds the given Todo. {@code todo} must not already exist in the list of
     * todos.
     */
    void addTodo(Todo todo);

    /**
     * Adds the given Schedule. {@code schedule} must not already exist in the list
     * of schedules.
     */
    void addSchedule(Schedule schedule);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book. The person identity of
     * {@code editedPerson} must not be the same as another existing person in the
     * address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered todo list */
    ObservableList<Todo> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered schedule list */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered todo list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredTodoList(Predicate<Todo> predicate);
}
