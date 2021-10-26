package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTodoCommand;
import seedu.address.model.todo.predicates.DescriptionContainsKeywordsPredicate;

public class FindTodoCommandParserTest {

    private FindTodoCommandParser parser = new FindTodoCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTodoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTodoCommand() {
        // no leading and trailing whitespaces
        FindTodoCommand expectedFindTodoCommand =
                new FindTodoCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("Appeal", "travel")));
        assertParseSuccess(parser, "Appeal travel", expectedFindTodoCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Appeal \n \t travel  \t", expectedFindTodoCommand);
    }

}
