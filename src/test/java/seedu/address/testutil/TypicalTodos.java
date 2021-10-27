package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEARNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEISURE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.todo.Todo;

/**
 * A utility class containing a list of {@code Todo} objects to be used in tests.
 */
public class TypicalTodos {

    public static final Todo APPEAL = new TodoBuilder().withDescription("appeal")
            .withTags("legal").build();
    public static final Todo BAKE = new TodoBuilder().withDescription("bake")
            .withTags("cooking").build();
    public static final Todo SHOP = new TodoBuilder().withDescription("shop")
            .withTags("fun").build();
    public static final Todo EAT = new TodoBuilder().withDescription("eat")
            .withTags("fun").build();

    // Manually added - Todo's details found in {@code CommandTestUtil}
    public static final Todo READ = new TodoBuilder().withDescription(VALID_DESCRIPTION_READ)
            .withTags(VALID_TAG_LEARNING).build();
    public static final Todo TRAVEL = new TodoBuilder().withDescription(VALID_DESCRIPTION_TRAVEL)
            .withTags(VALID_TAG_LEISURE).build();;

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
        return new ArrayList<>(Arrays.asList(APPEAL, BAKE, SHOP, EAT));
    }
}
