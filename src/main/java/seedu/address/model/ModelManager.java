package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.analytics.ClientAnalytics;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;
import seedu.address.storage.IcsScheduleStorage;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final Index defaultSelectedPersonIndex = Index.fromZeroBased(0);
    private final AddressBook addressBook;
    private final ClientAnalytics clientAnalytics;
    private final UserPrefs userPrefs;
    private final IcsScheduleStorage icsScheduleStorage;
    private final FilteredList<CustomGoal> filteredCustomGoals;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> selectedPerson;
    private final IntegerProperty selectedPersonIndex;
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
        this.icsScheduleStorage = new IcsScheduleStorage();
        filteredCustomGoals = new FilteredList<>(this.addressBook.getCustomGoalList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTodos = new FilteredList<>(this.addressBook.getTodoList());
        filteredSchedule = new FilteredList<>(this.addressBook.getScheduleList());
        selectedPersonIndex = new SimpleIntegerProperty(defaultSelectedPersonIndex.getOneBased());
        selectedPerson = new FilteredList<>(this.addressBook.getPersonList());
        setDefaultSelection(selectedPerson);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    private void setDefaultSelection(FilteredList<Person> selectedPerson) {
        // set default select person to be the first person in list
        if (selectedPerson.size() > 1) {
            Person firstPerson = selectedPerson.get(defaultSelectedPersonIndex.getZeroBased());
            selectedPerson.setPredicate((person)->person.equals(firstPerson));
        }
    }

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

    //@@author whoisjustinngo
    // =========== Analytics
    // ================================================================================
    @Override
    public ClientAnalytics getAnalytics() {
        return this.clientAnalytics;
    }

    // =========== AddressBook
    // ================================================================================

    //@@author
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

    //@@author kslui99
    @Override
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return addressBook.hasTodo(todo);
    }

    //@@author rickyaandrew
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

    //@@author whoisjustinngo
    @Override
    public boolean hasCustomGoal(CustomGoal toAdd) {
        requireNonNull(toAdd);
        return addressBook.hasCustomGoal(toAdd);
    }

    //@@author
    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    //@@author kslui99
    @Override
    public void deleteTodo(Todo target) {
        addressBook.removeTodo(target);
    }

    //@@author rickyaandrew
    @Override
    public void deleteSchedule(Schedule target) {
        addressBook.removeSchedule(target);
    }

    //@@author whoisjustinngo
    @Override
    public void deleteCustomGoal(Index goalToDelete) {
        addressBook.deleteCustomGoal(goalToDelete);
    }

    //@@author
    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    //@@author kslui99
    @Override
    public void addTodo(Todo todo) {
        addressBook.addTodo(todo);
    }

    //@@author rickyaandrew
    @Override
    public void addSchedule(Schedule schedule) {
        addressBook.addSchedule(schedule);
    }

    //@@author whoisjustinngo
    @Override
    public void addCustomGoal(CustomGoal toAdd) {
        addressBook.addCustomGoal(toAdd);
    }

    @Override
    public int getNumOfCustomGoals() {
        return this.getFilteredCustomGoalList().size();
    }

    //@@author
    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //@@author kslui99
    @Override
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);

        addressBook.setTodo(target, editedTodo);
    }

    //@@author rickyaandrew
    @Override
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);

        addressBook.setSchedule(target, editedSchedule);
    }

    //@@author whoisjustinngo
    @Override
    public void updateCustomGoal(Index goalToUpdate, float valueToUpdateBy) {
        addressBook.updateCustomGoal(goalToUpdate, valueToUpdateBy);
    }

    //@@author
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

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getSelectedPersonList() {
        return selectedPerson;
    }

    /**
     * Returns an unmodifiable value of the index of the {@code Person} selected
     * @return
     */
    @Override
    public IntegerProperty getSelectedPersonIndex() {
        return selectedPersonIndex;
    }

    @Override
    public void updateSelectedPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        selectedPerson.setPredicate(predicate);
    }

    @Override
    public void updateSelectedPersonIndex(int i) {
        requireNonNull(i);
        selectedPersonIndex.set(i);
    }

    //@@author kslui99
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

    @Override
    public void updateFilteredTodoList(Predicate<Todo> predicate) {
        requireNonNull(predicate);
        filteredTodos.setPredicate(predicate);
    }

    //@@author rickyaandrew
    // =========== Filtered Schedule List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Schedule} backed by the
     * internal list
     */
    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return filteredSchedule;
    }

    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        requireNonNull(predicate);
        filteredSchedule.setPredicate(predicate);
    }

    //@@author whoisjustinngo
    // =========== Filtered Custom Goal List Accessors
    // =============================================================

    @Override
    public ObservableList<CustomGoal> getFilteredCustomGoalList() {
        return filteredCustomGoals;
    }

    //@@author
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

    //@@author SpdPnd98
    @Override
    public List<Schedule> importSchedule(File file) throws IOException, ParseException {
        requireNonNull(file);
        return icsScheduleStorage.importSchedule(file);
    }

}
