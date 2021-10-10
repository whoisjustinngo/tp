package seedu.address.testutil;

import seedu.address.model.todo.Todo;

/**
 * A utility class to help with building Todo objects.
 */
public class TodoBuilder {

    public static final String DEFAULT_DESCRIPTION = "read";

    private String description;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the TodoBuilder with the data of {@code todoToCopy}.
     */
    public TodoBuilder(Todo todoToCopy) {
        description = todoToCopy.getDescription();
    }

    /**
     * Sets the description of the {@code Todo} that we are building.
     */
    public TodoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }


    public Todo build() {
        return new Todo(description);
    }

}
