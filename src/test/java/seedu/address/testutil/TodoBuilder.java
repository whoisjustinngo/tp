package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Todo objects.
 */
public class TodoBuilder {

    public static final String DEFAULT_DESCRIPTION = "read";

    private String description;
    private Set<Tag> tags;
    private boolean isDone = false;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        description = DEFAULT_DESCRIPTION;
        tags = new HashSet<>();
    }

    /**
     * Initializes the TodoBuilder with the data of {@code todoToCopy}.
     */
    public TodoBuilder(Todo todoToCopy) {
        description = todoToCopy.getDescription();
        tags = new HashSet<>(todoToCopy.getTags());
    }

    /**
     * Sets the description of the {@code Todo} that we are building.
     */
    public TodoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Todo} that we are building.
     */
    public TodoBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Marks the todo that we are building as done.
     */
    public TodoBuilder withDone() {
        this.isDone = true;
        return this;
    }


    public Todo build() {
        return new Todo(description, tags, isDone);
    }

}
