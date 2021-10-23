package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;

/**
 * Jackson-friendly version of {@link Todo}.
 */
class JsonAdaptedTodo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Todo's %s field is missing!";

    private final String description;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given todo details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description") String description,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.description = description;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Todo source) {
        description = source.getDescription();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted todo object into the model's {@code Todo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted todo.
     */
    public Todo toModelType() throws IllegalValueException {
        final List<Tag> todoTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            todoTags.add(tag.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        final String modelDescription = description;

        final Set<Tag> modelTags = new HashSet<>(todoTags);

        return new Todo(modelDescription, modelTags);
    }

}
