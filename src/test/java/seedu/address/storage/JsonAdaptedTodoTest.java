package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTodo.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTodos.BAKE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedTodoTest {
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DESCRIPTION = BAKE.getDescription();
    private static final List<JsonAdaptedTag> VALID_TAGS = BAKE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTodoDetails_returnsTodo() throws Exception {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(BAKE);
        assertEquals(BAKE, todo.toModelType());
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, todo::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTodo todo =
                new JsonAdaptedTodo(VALID_DESCRIPTION, invalidTags);
        assertThrows(IllegalValueException.class, todo::toModelType);
    }

}
