package seedu.address.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.analytics.ClientAnalytics;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Todo> PREDICATE_SHOW_ALL_TODOS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULE = unused -> true;

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

    /**
     * Returns the AddressBook
     */
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
     * Returns true if a CustomGoal with the same description, goal, and/or endDate and/or endTime as
     * {@code toAdd} exists in the list of customGoals.
     */
    boolean hasCustomGoal(CustomGoal toAdd);

    /**
     * Returns true if a Schedule with the same description, date and time as
     * {@code schedule} exists in the list of Schedule.
     */
    boolean hasSchedule(Schedule schedule);

    /**
     * Returns true if a Schedule clashes with {@code schedule} exists in the list
     * of schedules.
     */
    boolean hasScheduleClash(Schedule schedule);

    /**
     * Deletes the given person. The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given todo. The todo must exist in the address book.
     */
    void deleteTodo(Todo target);

    /**
     * Deletes the given todo. The todo must exist in the address book.
     */
    void deleteSchedule(Schedule target);

    /**
     * Adds the given CustomGoal, which must not already exist in the list of customGoals.
     */
    void addCustomGoal(CustomGoal toAdd);

    /**
     * Adds the given person. {@code person} must not already exist in the address
     * book.
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

    /**
     * Gets the {@code Analytics} object for this addressBook.
     */
    ClientAnalytics getAnalytics();

    /**
     * Replaces the given todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the address book. The
     * {@code editedTodo} must not be the same as another existing todo.
     */
    void setTodo(Todo target, Todo editedTodo);

    /**
     * Replaces the given schedule {@code target} with {@code editedSchedule}.
     * {@code target} must exist in the address book. The schedule identity of
     * {@code editedSchedule} must not be the same as another existing schedule in
     * the address book.
     */
    void setSchedule(Schedule target, Schedule editedSchedule);


    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of a selected person list
     */
    ObservableList<Person> getSelectedPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    /**
     * Updates the filter of the selected person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSelectedPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the filtered todo list
     */
    ObservableList<Todo> getFilteredTodoList();

    /**
     * Returns an unmodifiable view of the filtered schedule list
     */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Returns an unmodifiable view of the filtered custom goal list
     */
    ObservableList<CustomGoal> getFilteredCustomGoalList();

    /**
     * Updates the filter of the filtered todo list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredTodoList(Predicate<Todo> predicate);

    /**
     * Updates the filter of the filtered schedule list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredScheduleList(Predicate<Schedule> predicate);

    void updateCustomGoal(Index goalToUpdate, float valueToUpdateBy);

    int getNumOfCustomGoals();

    void deleteCustomGoal(Index goalToDelete);

    List<Schedule> importSchedule(File file) throws IOException, ParseException;
}
