package seedu.address.model.todo;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.todo.exceptions.DuplicateTodoException;

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
}
