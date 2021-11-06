package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Tab.CONTACTS;
import static seedu.address.commons.core.Tab.DASHBOARD;
import static seedu.address.commons.core.Tab.TODOS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTodoCommand;
import seedu.address.logic.commands.DoneTodoCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditTodoCommand;
import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTodoCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTodosCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.todo.Todo;
import seedu.address.model.todo.predicates.DescriptionContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTodoDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TodoBuilder;
import seedu.address.testutil.TodoUtil;

public class AddressBookParserTest {
    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CONTACTS.getLabel()
                + PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addTodo() throws Exception {
        Todo todo = new TodoBuilder().build();
        AddTodoCommand command = (AddTodoCommand) parser.parseCommand(TODOS.getLabel()
                + TodoUtil.getAddTodoCommand(todo));
        assertEquals(new AddTodoCommand(todo), command);
    }

    @Test
    public void parseCommand_clearContact() throws Exception {
        assertTrue(parser.parseCommand(CONTACTS.getLabel() + " "
                + ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(CONTACTS.getLabel() + " "
                + ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(CONTACTS.getLabel() + " "
                + DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteTodo() throws Exception {
        DeleteTodoCommand command = (DeleteTodoCommand) parser.parseCommand(TODOS.getLabel() + " "
                + DeleteTodoCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteTodoCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editContact() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(CONTACTS.getLabel() + " "
                + EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_editTodo() throws Exception {
        Todo todo = new TodoBuilder().build();
        EditTodoDescriptor descriptor = new EditTodoDescriptorBuilder(todo).build();
        EditTodoCommand command = (EditTodoCommand) parser.parseCommand(TODOS.getLabel() + " "
                + EditTodoCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + TodoUtil.getEditTodoDescriptorDetails(descriptor));
        assertEquals(new EditTodoCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(DASHBOARD.getLabel()
                + " " + ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(DASHBOARD.getLabel()
                + " " + ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findContact() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(CONTACTS.getLabel() + " "
                + FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTodo() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTodoCommand command = (FindTodoCommand) parser.parseCommand(TODOS.getLabel() + " "
                + FindTodoCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTodoCommand(new DescriptionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(DASHBOARD.getLabel() + " "
                + HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(DASHBOARD.getLabel() + " "
                + HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listContacts() throws Exception {
        assertTrue(parser.parseCommand(CONTACTS.getLabel() + " "
                + ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(CONTACTS.getLabel() + " "
                + ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_listTodos() throws Exception {
        assertTrue(parser.parseCommand(
                TODOS.getLabel() + " " + ListTodosCommand.COMMAND_WORD) instanceof ListTodosCommand);
        assertTrue(parser.parseCommand(TODOS.getLabel() + " " + ListTodosCommand.COMMAND_WORD + " 3")
                instanceof ListTodosCommand);
    }

    @Test
    public void parseCommand_doneTodo() throws Exception {
        Todo todo = new TodoBuilder().build();
        DoneTodoCommand command = (DoneTodoCommand) parser.parseCommand(TODOS.getLabel() + " "
                + DoneTodoCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased());
        assertEquals(new DoneTodoCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                DASHBOARD.getLabel() + " unknownCommand"));
    }
}
