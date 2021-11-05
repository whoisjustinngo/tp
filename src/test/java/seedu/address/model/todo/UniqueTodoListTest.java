package seedu.address.model.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTodos.READ;
import static seedu.address.testutil.TypicalTodos.TRAVEL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.todo.exceptions.DuplicateTodoException;
import seedu.address.model.todo.exceptions.TodoNotFoundException;

public class UniqueTodoListTest {

    private final UniqueTodoList uniqueTodoList = new UniqueTodoList();

    @Test
    public void contains_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.contains(null));
    }

    @Test
    public void contains_todoNotInList_returnsFalse() {
        assertFalse(uniqueTodoList.contains(READ));
    }

    @Test
    public void contains_todoInList_returnsTrue() {
        uniqueTodoList.add(READ);
        assertTrue(uniqueTodoList.contains(READ));
    }

    @Test
    public void add_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.add(null));
    }

    @Test
    public void add_duplicateTodo_throwsDuplicateTodoException() {
        uniqueTodoList.add(READ);
        assertThrows(DuplicateTodoException.class, () -> uniqueTodoList.add(READ));
    }

    @Test
    public void setTodo_nullTargetTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTodo(null, READ));
    }

    @Test
    public void setTodo_nullEditedTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTodo(READ, null));
    }

    @Test
    public void setTodo_targetTodoNotInList_throwsTodoNotFoundException() {
        assertThrows(TodoNotFoundException.class, () -> uniqueTodoList.setTodo(READ, READ));
    }

    @Test
    public void setTodo_editedTodoIsSameTodo_success() {
        uniqueTodoList.add(READ);
        uniqueTodoList.setTodo(READ, READ);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(READ);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void remove_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.remove(null));
    }

    @Test
    public void remove_todoDoesNotExist_throwsTodoNotFoundException() {
        assertThrows(TodoNotFoundException.class, () -> uniqueTodoList.remove(READ));
    }

    @Test
    public void remove_existingTodo_removesTodo() {
        uniqueTodoList.add(READ);
        uniqueTodoList.remove(READ);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTodos_nullUniqueTodoList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTodos((UniqueTodoList) null));
    }

    @Test
    public void setTodos_uniqueTodoList_replacesOwnListWithProvidedUniqueTodoList() {
        uniqueTodoList.add(READ);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(TRAVEL);
        uniqueTodoList.setTodos(expectedUniqueTodoList);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTodos_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTodoList.setTodos((List<Todo>) null));
    }

    @Test
    public void setTodos_list_replacesOwnListWithProvidedList() {
        uniqueTodoList.add(READ);
        List<Todo> todoList = Collections.singletonList(TRAVEL);
        uniqueTodoList.setTodos(todoList);
        UniqueTodoList expectedUniqueTodoList = new UniqueTodoList();
        expectedUniqueTodoList.add(TRAVEL);
        assertEquals(expectedUniqueTodoList, uniqueTodoList);
    }

    @Test
    public void setTodos_listWithDuplicateTodos_throwsDuplicateTodoException() {
        List<Todo> listWithDuplicateTodos = Arrays.asList(READ, READ);
        assertThrows(DuplicateTodoException.class, () -> uniqueTodoList.setTodos(listWithDuplicateTodos));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> uniqueTodoList.asUnmodifiableObservableList().remove(0));
    }
}
