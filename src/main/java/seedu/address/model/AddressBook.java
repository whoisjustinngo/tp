package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.customGoal.UniqueCustomGoalList;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.UniqueScheduleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.todo.Todo;
import seedu.address.model.todo.UniqueTodoList;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by
 * .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueCustomGoalList customGoals;
    private final UniquePersonList persons;
    private final UniqueTodoList todos;
    private final UniqueScheduleList schedule;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication among constructors.
     */
    {
        customGoals = new UniqueCustomGoalList();
        persons = new UniquePersonList();
        todos = new UniqueTodoList();
        schedule = new UniqueScheduleList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customGoals list with {@code customGoals}.
     * {@code customGoals} must not contain duplicates.
     */
    public void setCustomGoals(List<CustomGoal> customGoals) {
        this.customGoals.setCustomGoals(customGoals);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the todo list with {@code todos}. {@code todos} must
     * not contain duplicate todos.
     */
    public void setTodos(List<Todo> todos) {
        this.todos.setTodos(todos);
    }

    /**
     * Replaces the contents of the Schedules list with {@code Schedules}.
     * {@code Schedules} must not contain duplicate Schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedule.setSchedules(schedules);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setCustomGoals(newData.getCustomGoalList());
        setPersons(newData.getPersonList());
        setTodos(newData.getTodoList());
        setSchedules(newData.getScheduleList());
    }


    //// customGoal-level operations

    /**
     * Adds a given customGoal to the list of CustomGoals.
     */
    public void addCustomGoal(CustomGoal toAdd) {
        requireNonNull(toAdd);
        customGoals.add(toAdd);
    }

    /**
     * Deletes the <code>CustomGoal</code> corresponding to the specified <code>Index</code> from the list.
     * @param goalToDelete The <code>Index</code> of the desired <code>CustomGoal</code> to be deleted (1-based).
     */
    public void deleteCustomGoal(Index goalToDelete) {
        requireNonNull(goalToDelete);
        customGoals.delete(goalToDelete);
    }

    /**
     * Checks if specified goal is already in the list of CustomGoals.
     * @param goal The goal to check.
     * @return Whether the goal is currently in the list of CustomGoals or not.
     */
    public boolean hasCustomGoal(CustomGoal goal) {
        requireNonNull(goal);
        return customGoals.contains(goal);
    }

    /**
     * Increases the progress of the <code>CustomGoal</code> corresponding to the specified <code>Index</code> by the
     * specified value.
     * @param goalToUpdate The <code>Index</code> of the desired <code>CustomGoal</code>.
     * @param valueToUpdateBy The value to update the progress of the specified <code>CustomGoal</code> by.
     */
    public void updateCustomGoal(Index goalToUpdate, float valueToUpdateBy) {
        requireNonNull(goalToUpdate);
        customGoals.update(goalToUpdate, valueToUpdateBy);
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book. The person must not already exist in the
     * address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}. {@code target} must exist in the address book. The
     * person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in
     * the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// todo-level operations

    /**
     * Returns true if a todo with the same description as {@code todo} exists in
     * the list of todos.
     */
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return todos.contains(todo);
    }

    /**
     * Adds a todo to the list of todos. The todo must not already exist in the list
     * of todos.
     */
    public void addTodo(Todo t) {
        todos.add(t);
    }

    /**
     * Replaces the given todo {@code target} in the list with
     * {@code editedTodo}. {@code target} must exist in the address book. The
     * {@code editedTodo} must not be the same as another
     * existing todo in the address book.
     */
    public void setTodo(Todo target, Todo editedTodo) {
        requireNonNull(editedTodo);

        todos.setTodo(target, editedTodo);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in
     * the address book.
     */
    public void removeTodo(Todo key) {
        todos.remove(key);
    }

    //// schedule-level operations

    /**
     * Adds a schedule to the list of schedules. The schedule must not already exist
     * in the list of schedules.
     */
    public void addSchedule(Schedule s) {
        schedule.add(s);
        schedule.sort();
    }

    /**
     * Returns true if a schedule with the same description as {@code schedule}
     * exists in the list of schedules.
     */
    public boolean hasSchedule(Schedule s) {
        requireNonNull(schedule);
        return schedule.contains(s);
    }

    /**
     * Replaces the given schedule {@code target} in the list with
     * {@code editedSchedule}. {@code target} must exist in the address book. The
     * schedule identity of {@code editedSchedule} must not be the same as another
     * existing schedule in the address book.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireNonNull(editedSchedule);

        schedule.setSchedule(target, editedSchedule);
    }

    /**
     * Removes {@code schedule} from this {@code AddressBook}. {@code schedule} must exist in
     * the address book.
     */
    public void removeSchedule(Schedule key) {
        schedule.remove(key);
    }

    /**
     * Checks if {@code schedule} clashes from this {@code AddressBook}.
     */
    public boolean hasScheduleClash(Schedule key) {
        return schedule.isClash(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<CustomGoal> getCustomGoalList() {
        return customGoals.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Todo> getTodoList() {
        return todos.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        return schedule.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
