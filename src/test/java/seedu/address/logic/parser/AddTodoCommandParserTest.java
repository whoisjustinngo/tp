package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTodos.READ;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.model.todo.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandParserTest {
    private AddTodoCommandParser parser = new AddTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Todo expectedTodo = new TodoBuilder(READ).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_READ, new AddTodoCommand(expectedTodo));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, DESCRIPTION_READ + DESCRIPTION_READ, new AddTodoCommand(expectedTodo));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_READ,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_READ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
    }
}
