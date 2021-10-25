package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.analytics.ClientAnalytics;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final ClientAnalytics clientAnalytics;
    private final UserPrefs userPrefs;
    private final FilteredList<CustomGoal> filteredCustomGoals;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Todo> filteredTodos;
    private final FilteredList<Schedule> filteredSchedule;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.clientAnalytics = new ClientAnalytics(this.addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomGoals = new FilteredList<>(this.addressBook.getCustomGoalList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTodos = new FilteredList<>(this.addressBook.getTodoList());
        filteredSchedule = new FilteredList<>(this.addressBook.getScheduleList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // =========== Analytics
    // ================================================================================
    @Override
    public ClientAnalytics getAnalytics() {
        return this.clientAnalytics;
    }

    // =========== AddressBook
    // ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return addressBook.hasTodo(todo);
    }

    @Override
    public boolean hasCustomGoal(CustomGoal toAdd) {
        requireNonNull(toAdd);
        return addressBook.hasCustomGoal(toAdd);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteTodo(Todo target) {
        addressBook.removeTodo(target);
    }

    @Override
    public void deleteSchedule(Schedule target) {
        addressBook.removeSchedule(target);
    }

    @Override
    public void addCustomGoal(CustomGoal toAdd) {
        addressBook.addCustomGoal(toAdd);
    }

    @Override
    public void deleteCustomGoal(Index goalToDelete) {
        addressBook.deleteCustomGoal(goalToDelete);
    }

    @Override
    public void updateCustomGoal(Index goalToUpdate, float valueToUpdateBy) {
        addressBook.updateCustomGoal(goalToUpdate, valueToUpdateBy);
    }

    @Override
    public int getNumOfCustomGoals() {
        return this.getFilteredCustomGoalList().size();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTodo(Todo todo) {
        addressBook.addTodo(todo);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        addressBook.addSchedule(schedule);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }
    // =========== Filtered Person List Accessors

    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }
    // =========== Filtered Todo List Accessors

    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Todo} backed by the
     * internal list
     */
    @Override
    public ObservableList<Todo> getFilteredTodoList() {
        return filteredTodos;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Schedule} backed by the
     * internal list
     */
    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return filteredSchedule;
    }

    @Override
    public ObservableList<CustomGoal> getFilteredCustomGoalList() {
        return filteredCustomGoals;
    }

    @Override
    public void updateFilteredTodoList(Predicate<Todo> predicate) {
        requireNonNull(predicate);
        filteredTodos.setPredicate(predicate);
    }

    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        requireNonNull(predicate);
        filteredSchedule.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook) && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    @Override
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return addressBook.hasSchedule(schedule);
    }

    @Override
    public boolean hasScheduleClash(Schedule schedule) {
        requireNonNull(schedule);
        return addressBook.hasScheduleClash(schedule);
    }
}
