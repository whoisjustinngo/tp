package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFindCommand =
                new FilterCommand((Predicate) new TagsContainsKeywordsPredicate(Arrays.asList("Criminal", "Client")));
        assertParseSuccess(parser, " t/Criminal Client", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/\n Criminal \n \t Client  \t", expectedFindCommand);
    }
}
