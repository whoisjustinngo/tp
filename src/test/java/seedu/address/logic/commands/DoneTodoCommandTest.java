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
 * Contains integration tests (interaction with the Model) and unit tests for DoneTodoCommand.
 */
public class DoneTodoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Todo todoToMark = model.getFilteredTodoList().get(INDEX_FIRST.getZeroBased());
        Todo markedTodo = todoToMark.getDoneVersion();
        DoneTodoCommand doneTodoCommand = new DoneTodoCommand(INDEX_FIRST);

        String expectedMessage = String.format(DoneTodoCommand.MESSAGE_DONE_TODO_SUCCESS, markedTodo);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTodo(model.getFilteredTodoList().get(0), markedTodo);

        assertCommandSuccess(doneTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTodoList().size() + 1);
        DoneTodoCommand doneTodoCommand = new DoneTodoCommand(outOfBoundIndex);

        assertCommandFailure(doneTodoCommand, model, Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneTodoCommand doneFirstCommand = new DoneTodoCommand(INDEX_FIRST);
        DoneTodoCommand doneSecondCommand = new DoneTodoCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneTodoCommand doneFirstCommandCopy = new DoneTodoCommand(INDEX_FIRST);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
