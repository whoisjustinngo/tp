package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LEARNING;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LEISURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEARNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEISURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTodoCommand;
import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditTodoDescriptorBuilder;

public class EditTodoCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

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
        assertParseFailure(parser, "-5" + DESCRIPTION_READ, MESSAGE_INVALID_TODO_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_READ, MESSAGE_INVALID_TODO_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid tag followed by valid description
        assertParseFailure(parser, "1" + INVALID_TAG_DESC + DESCRIPTION_READ, Tag.MESSAGE_CONSTRAINTS);

        // valid description followed by valid tag followed by invalid tag.
        assertParseFailure(parser, "1" + DESCRIPTION_READ
                + VALID_TAG_LEARNING + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Todo} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_LEISURE + TAG_DESC_LEARNING + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_LEISURE + TAG_EMPTY + TAG_DESC_LEARNING,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_LEISURE + TAG_DESC_LEARNING,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_READ + TAG_DESC_LEARNING + TAG_DESC_LEISURE;

        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withDescription(VALID_DESCRIPTION_READ)
                .withTags(VALID_TAG_LEARNING, VALID_TAG_LEISURE).build();
        EditTodoCommand expectedCommand = new EditTodoCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_READ;
        EditTodoCommand.EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_READ).build();
        EditTodoCommand expectedCommand = new EditTodoCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_LEISURE;
        descriptor = new EditTodoDescriptorBuilder().withTags(VALID_TAG_LEISURE).build();
        expectedCommand = new EditTodoCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_READ + TAG_DESC_LEARNING + DESCRIPTION_TRAVEL
                + TAG_DESC_LEARNING + TAG_DESC_LEISURE;

        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withDescription(VALID_DESCRIPTION_TRAVEL)
                .withTags(VALID_TAG_LEARNING, VALID_TAG_LEISURE).build();
        EditTodoCommand expectedCommand = new EditTodoCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTodoCommand.EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder().withTags().build();
        EditTodoCommand expectedCommand = new EditTodoCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
