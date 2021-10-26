package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTodoCommand;
import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.testutil.EditTodoDescriptorBuilder;

public class EditTodoCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTodoCommand.MESSAGE_USAGE);

    private EditTodoCommandParser parser = new EditTodoCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION_READ, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTodoCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_READ, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_READ, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_READ;

        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withDescription(VALID_DESCRIPTION_READ)
                .build();
        EditTodoCommand expectedCommand = new EditTodoCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_READ + DESCRIPTION_TRAVEL;

        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withDescription(VALID_DESCRIPTION_TRAVEL)
                .build();
        EditTodoCommand expectedCommand = new EditTodoCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
