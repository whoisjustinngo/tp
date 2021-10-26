package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.todo.Todo;
import seedu.address.model.todo.predicates.DescriptionContainsKeywordsPredicate;
import seedu.address.model.todo.predicates.TagsContainsKeywordsPredicate;
import seedu.address.model.todo.predicates.TodoIsDonePredicate;
import seedu.address.model.todo.predicates.TodoIsNotDonePredicate;

/**
 * Parses input arguments and creates a new FilterTodoCommand object
 */
public class FilterTodoCommandParser implements Parser<FilterTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTodoCommand
     * and returns a FilterTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTodoCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_TAG, PREFIX_DONE);

        if (!argMultimap.getPreamble().isEmpty() || args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTodoCommand.MESSAGE_USAGE));
        }

        Predicate<Todo> descriptionPredicate = getPredicateFromMultimap(PREFIX_DESCRIPTION, argMultimap);
        Predicate<Todo> tagsPredicate = getPredicateFromMultimap(PREFIX_TAG, argMultimap);
        Predicate<Todo> donePredicate = getPredicateFromMultimap(PREFIX_DONE, argMultimap);

        return new FilterTodoCommand(descriptionPredicate.and(tagsPredicate).and(donePredicate));
    }

    private Predicate<Todo> getPredicateFromMultimap(Prefix prefix, ArgumentMultimap argMultimap)
            throws ParseException {
        Optional<String> filter = argMultimap.getValue(prefix);

        if (filter.isEmpty()) {
            return unused -> true;
        }

        String[] keywords = filter.get().split("\\s+");
        if (prefix.getPrefix().equals(PREFIX_DESCRIPTION.getPrefix())) {
            return new DescriptionContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_TAG.getPrefix())) {
            return new TagsContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (prefix.getPrefix().equals(PREFIX_DONE.getPrefix())) {
            return getDonePredicateFromKeywords(keywords);
        } else {
            return unused -> true;
        }
    }

    private Predicate<Todo> getDonePredicateFromKeywords(String[] keywords) throws ParseException {
        String keyword = keywords[0];
        String showDoneTodosKeyword = "yes";
        String showUndoneTodosKeyword = "no";
        if (keyword.equals(showDoneTodosKeyword)) {
            return new TodoIsDonePredicate();
        } else if (keyword.equals(showUndoneTodosKeyword)) {
            return new TodoIsNotDonePredicate();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTodoCommand.MESSAGE_USAGE));
        }
    }
}
