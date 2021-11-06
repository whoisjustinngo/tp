package seedu.address.model.todo;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a task to be done.
 * Guarantees: immutable
 */
public class Todo {
    public static final String MESSAGE_INVALID_DESCRIPTION_LENGTH =
            "The description of a Todo should not be more than 70 characters in length.";
    private static final int MAX_DESCRIPTION_LENGTH = 70;

    private final String description;
    private final Set<Tag> tags = new HashSet<>();
    private final boolean isDone;

    /**
     * Every field except isDone must be present and not null. isDone is automatically set to false.
     */
    public Todo(String description, Set<Tag> tags) {
        requireAllNonNull(description, tags);
        this.description = description;
        this.tags.addAll(tags);
        this.isDone = false;
    }

    /**
     * Every field must be present and not null.
     */
    public Todo(String description, Set<Tag> tags, boolean isDone) {
        requireAllNonNull(description, tags);
        this.description = description;
        this.tags.addAll(tags);
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Creates and returns a {@code Todo} with the same details but is marked as done.
     */
    public Todo getDoneVersion() {
        return new Todo(description, tags, true);
    }

    /**
     * Returns true if a given Todo description has a valid length.
     */
    public static boolean isValidDescriptionLength(String test) {
        return test.length() <= MAX_DESCRIPTION_LENGTH;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription());

        if (isDone) {
            builder.append(" [done]");
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
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
        return Objects.hash(description, tags, isDone);
    }
}
