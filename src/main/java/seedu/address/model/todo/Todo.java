package seedu.address.model.todo;

import static java.util.Objects.requireNonNull;

/**
 * Represents a task to be done.
 * Guarantees: immutable
 */
public class Todo {
    private final String description;

    /**
     * Constructs a Todo.
     *
     * @param description The description of this todo.
     */
    public Todo(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * Returns true if both Todos have the same description.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.description.equals(description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
