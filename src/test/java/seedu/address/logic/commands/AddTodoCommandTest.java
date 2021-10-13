package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;
import seedu.address.testutil.TodoBuilder;

class AddTodoCommandTest {

    @Test
    public void constructor_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTodoCommand(null));
    }

    @Test
    public void execute_todoAcceptedByModel_addSuccessful() throws Exception {
        AddTodoCommandTest.ModelStubAcceptingTodoAdded modelStub = new AddTodoCommandTest.ModelStubAcceptingTodoAdded();
        Todo validTodo = new TodoBuilder().build();

        CommandResult commandResult = new AddTodoCommand(validTodo).execute(modelStub);

        assertEquals(String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTodo), modelStub.todosAdded);
    }

    @Test
    public void execute_duplicateTodo_throwsCommandException() {
        Todo validTodo = new TodoBuilder().build();
        AddTodoCommand addTodoCommand = new AddTodoCommand(validTodo);
        AddTodoCommandTest.ModelStub modelStub = new AddTodoCommandTest.ModelStubWithTodo(validTodo);

        assertThrows(CommandException.class, AddTodoCommand.MESSAGE_DUPLICATE_TODO, () ->
                addTodoCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Todo read = new TodoBuilder().withDescription("read").build();
        Todo travel = new TodoBuilder().withDescription("travel").build();
        AddTodoCommand addReadCommand = new AddTodoCommand(read);
        AddTodoCommand addTravelCommand = new AddTodoCommand(travel);

        // same object -> returns true
        assertTrue(addReadCommand.equals(addReadCommand));

        // same values -> returns true
        AddTodoCommand addReadCommandCopy = new AddTodoCommand(read);
        assertTrue(addReadCommand.equals(addReadCommandCopy));

        // different types -> returns false
        assertFalse(addReadCommand.equals(1));

        // null -> returns false
        assertFalse(addReadCommand.equals(null));

        // different todo -> returns false
        assertFalse(addReadCommand.equals(addTravelCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(Todo target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Todo> getFilteredTodoList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTodoList(Predicate<Todo> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasScheduleClash(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single todo.
     */
    private class ModelStubWithTodo extends AddTodoCommandTest.ModelStub {
        private final Todo todo;

        ModelStubWithTodo(Todo todo) {
            requireNonNull(todo);
            this.todo = todo;
        }

        @Override
        public boolean hasTodo(Todo todo) {
            requireNonNull(todo);
            return this.todo.equals(todo);
        }
    }

    /**
     * A Model stub that always accept the todo being added.
     */
    private class ModelStubAcceptingTodoAdded extends AddTodoCommandTest.ModelStub {
        final ArrayList<Todo> todosAdded = new ArrayList<>();

        @Override
        public boolean hasTodo(Todo todo) {
            requireNonNull(todo);
            return todosAdded.stream().anyMatch(todo::equals);
        }

        @Override
        public void addTodo(Todo todo) {
            requireNonNull(todo);
            todosAdded.add(todo);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
