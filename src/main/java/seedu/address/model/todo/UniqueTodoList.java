package seedu.address.model.todo;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.todo.exceptions.DuplicateTodoException;
import seedu.address.model.todo.exceptions.TodoNotFoundException;

/**
 * A list of todos that enforces uniqueness between its elements and does not allow nulls.
 * A todo is considered unique by comparing using {@code Todo#equals(Object)}. As such, adding and updating of
 * todos uses Todo#equals(Object) for equality so as to ensure that the todo being added or updated is
 * unique in the UniquePersonList. Also, the removal of a todo uses Todo#equals(Object) so
 * as to ensure that the todo with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Todo#equals(Object)
 */
public class UniqueTodoList implements Iterable<Todo> {

    private final ObservableList<Todo> internalList = FXCollections.observableArrayList();
    private final ObservableList<Todo> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent todo as the given argument.
     */
    public boolean contains(Todo toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Todo to the list.
     * The Todo must not already exist in the list.
     */
    public void add(Todo toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTodoException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent todo from the list.
     * The todo must exist in the list.
     */
    public void remove(Todo toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TodoNotFoundException();
        }
    }

    public void setTodos(UniqueTodoList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code todos}.
     * {@code todos} must not contain duplicate todos.
     */
    public void setTodos(List<Todo> todos) {
        requireAllNonNull(todos);
        if (!todosAreUnique(todos)) {
            throw new DuplicateTodoException();
        }

        internalList.setAll(todos);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Todo> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Todo> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTodoList // instanceof handles nulls
                        && internalList.equals(((UniqueTodoList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code todos} contains only unique todos.
     */
    private boolean todosAreUnique(List<Todo> todos) {
        for (int i = 0; i < todos.size() - 1; i++) {
            for (int j = i + 1; j < todos.size(); j++) {
                if (todos.get(i).equals(todos.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
