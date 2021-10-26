package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_READ;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTodos.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.todo.Todo;
import seedu.address.testutil.EditTodoDescriptorBuilder;
import seedu.address.testutil.TodoBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTodoCommand.
 */
public class EditTodoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Todo editedTodo = new TodoBuilder().build();
        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder(editedTodo).build();
        EditTodoCommand editTodoCommand = new EditTodoCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditTodoCommand.MESSAGE_EDIT_TODO_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTodo(model.getFilteredTodoList().get(0), editedTodo);

        assertCommandSuccess(editTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTodo = Index.fromOneBased(model.getFilteredTodoList().size());
        Todo lastTodo = model.getFilteredTodoList().get(indexLastTodo.getZeroBased());

        TodoBuilder todoInList = new TodoBuilder(lastTodo);
        Todo editedTodo = todoInList.withDescription(VALID_DESCRIPTION_TRAVEL).build();

        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withDescription(VALID_DESCRIPTION_TRAVEL)
                .build();
        EditTodoCommand editTodoCommand = new EditTodoCommand(indexLastTodo, descriptor);

        String expectedMessage = String.format(EditTodoCommand.MESSAGE_EDIT_TODO_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTodo(lastTodo, editedTodo);

        assertCommandSuccess(editTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTodoCommand editTodoCommand = new EditTodoCommand(INDEX_FIRST, new EditTodoDescriptor());
        Todo editedTodo = model.getFilteredTodoList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditTodoCommand.MESSAGE_EDIT_TODO_SUCCESS, editedTodo);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTodoUnfilteredList_failure() {
        Todo firstTodo = model.getFilteredTodoList().get(INDEX_FIRST.getZeroBased());
        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder(firstTodo).build();
        EditTodoCommand editTodoCommand = new EditTodoCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editTodoCommand, model, EditTodoCommand.MESSAGE_DUPLICATE_TODO);
    }

    @Test
    public void execute_invalidTodoIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withDescription(VALID_DESCRIPTION_TRAVEL)
                .build();
        EditTodoCommand editTodoCommand = new EditTodoCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTodoCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTodoCommand standardCommand = new EditTodoCommand(INDEX_FIRST, DESC_READ);

        // same values -> returns true
        EditTodoDescriptor copyDescriptor = new EditTodoDescriptor(DESC_READ);
        EditTodoCommand commandWithSameValues = new EditTodoCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTodoCommand(INDEX_SECOND, DESC_READ)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTodoCommand(INDEX_FIRST, DESC_TRAVEL)));
    }

}
