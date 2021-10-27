package seedu.address.model.todo.predicates;

import java.util.function.Predicate;

import seedu.address.model.todo.Todo;

/**
 * Tests whether a Todo is not marked as done.
 */
public class TodoIsNotDonePredicate implements Predicate<Todo> {

    public TodoIsNotDonePredicate() {
    }

    @Override
    public boolean test(Todo todo) {
        return !todo.isDone();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoIsNotDonePredicate); // instanceof handles nulls
    }

}
