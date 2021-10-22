package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.todo.Todo;

/**
 * A utility class containing a list of {@code Todo} objects to be used in tests.
 */
public class TypicalTodos {

    public static final Todo READ = new TodoBuilder().withDescription("read").build();
    public static final Todo TRAVEL = new TodoBuilder().withDescription("travel").build();
    public static final Todo SHOP = new TodoBuilder().withDescription("shop").build();
    public static final Todo EAT = new TodoBuilder().withDescription("eat").build();

    private TypicalTodos() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical todos.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Todo todo : getTypicalTodos()) {
            ab.addTodo(todo);
        }
        return ab;
    }

    public static List<Todo> getTypicalTodos() {
        return new ArrayList<>(Arrays.asList(READ, TRAVEL));
    }
}
