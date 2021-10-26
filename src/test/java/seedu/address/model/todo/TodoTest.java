package seedu.address.model.todo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEARNING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTodos.READ;
import static seedu.address.testutil.TypicalTodos.TRAVEL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoBuilder;

public class TodoTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Todo todo = new TodoBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> todo.getTags().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Todo readCopy = new TodoBuilder(READ).build();
        assertTrue(READ.equals(readCopy));

        // same object -> returns true
        assertTrue(READ.equals(READ));

        // null -> returns false
        assertFalse(READ.equals(null));

        // different type -> returns false
        assertFalse(READ.equals(5));

        // different todo -> returns false
        assertFalse(READ.equals(TRAVEL));

        // different description -> returns false
        Todo editedRead = new TodoBuilder(READ).withDescription(VALID_DESCRIPTION_TRAVEL).build();
        assertFalse(READ.equals(editedRead));

        // different tags -> returns true
        editedRead = new TodoBuilder(READ).withTags(VALID_TAG_LEARNING).build();
        assertTrue(READ.equals(editedRead));
    }
}
