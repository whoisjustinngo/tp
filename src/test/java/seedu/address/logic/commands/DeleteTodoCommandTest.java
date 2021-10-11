package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTodos.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.todo.Todo;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTodoCommand}.
 */
public class DeleteTodoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Todo todoToDelete = model.getFilteredTodoList().get(INDEX_FIRST.getZeroBased());
        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteTodoCommand.MESSAGE_DELETE_TODO_SUCCESS, todoToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTodo(todoToDelete);

        assertCommandSuccess(deleteTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(outOfBoundIndex);

        assertCommandFailure(deleteTodoCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTodoCommand deleteFirstCommand = new DeleteTodoCommand(INDEX_FIRST);
        DeleteTodoCommand deleteSecondCommand = new DeleteTodoCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTodoCommand deleteFirstCommandCopy = new DeleteTodoCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
