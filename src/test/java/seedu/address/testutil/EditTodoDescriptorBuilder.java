package seedu.address.testutil;

import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.model.todo.Todo;

/**
 * A utility class to help with building EditTodoDescriptor objects.
 */
public class EditTodoDescriptorBuilder {

    private EditTodoDescriptor descriptor;

    public EditTodoDescriptorBuilder() {
        descriptor = new EditTodoDescriptor();
    }

    public EditTodoDescriptorBuilder(EditTodoDescriptor descriptor) {
        this.descriptor = new EditTodoDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTodoDescriptor} with fields containing {@code todo}'s details
     */
    public EditTodoDescriptorBuilder(Todo todo) {
        descriptor = new EditTodoDescriptor();
        descriptor.setDescription(todo.getDescription());
    }

    /**
     * Sets the {@code description} of the {@code EditTodoDescriptor} that we are building.
     */
    public EditTodoDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    public EditTodoDescriptor build() {
        return descriptor;
    }
}
