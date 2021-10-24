package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.todo.predicates.DescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTodoCommand object
 */
public class FindTodoCommandParser implements Parser<FindTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTodoCommand
     * and returns a FindTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTodoCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindTodoCommand(
                new DescriptionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
