package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LEARNING;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LEISURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEARNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEISURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTodos.READ;
import static seedu.address.testutil.TypicalTodos.TRAVEL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandParserTest {
    private AddTodoCommandParser parser = new AddTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Todo expectedTodo = new TodoBuilder(TRAVEL).withTags(VALID_TAG_LEISURE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_TRAVEL + TAG_DESC_LEISURE,
                new AddTodoCommand(expectedTodo));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, DESCRIPTION_READ + DESCRIPTION_TRAVEL, new AddTodoCommand(expectedTodo));

        // multiple tags - all accepted
        Todo expectedTodoMultipleTags = new TodoBuilder(TRAVEL).withTags(VALID_TAG_LEARNING, VALID_TAG_LEISURE)
                .build();
        assertParseSuccess(parser, DESCRIPTION_TRAVEL + TAG_DESC_LEARNING + TAG_DESC_LEISURE,
                new AddTodoCommand(expectedTodoMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Todo expectedTodo = new TodoBuilder(READ).withTags().build();
        assertParseSuccess(parser, DESCRIPTION_READ, new AddTodoCommand(expectedTodo));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_READ, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tag
        assertParseFailure(parser, DESCRIPTION_TRAVEL + INVALID_TAG_DESC + VALID_TAG_LEISURE,
                Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_READ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
    }
}
