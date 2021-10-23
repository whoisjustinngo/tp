package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.model.tag.Tag;
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
        descriptor.setTags(todo.getTags());
    }

    /**
     * Sets the {@code description} of the {@code EditTodoDescriptor} that we are building.
     */
    public EditTodoDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTodoDescriptor}
     * that we are building.
     */
    public EditTodoDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTodoDescriptor build() {
        return descriptor;
    }
}
