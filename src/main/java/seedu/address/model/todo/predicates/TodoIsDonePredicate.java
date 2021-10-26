package seedu.address.model.todo.predicates;

import java.util.function.Predicate;

import seedu.address.model.todo.Todo;

/**
 * Tests whether a Todo is marked as done.
 */
public class TodoIsDonePredicate implements Predicate<Todo> {

    public TodoIsDonePredicate() {
    }

    @Override
    public boolean test(Todo todo) {
        return todo.isDone();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoIsDonePredicate); // instanceof handles nulls
    }

}
