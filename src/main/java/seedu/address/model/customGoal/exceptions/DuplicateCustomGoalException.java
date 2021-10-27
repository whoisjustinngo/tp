package seedu.address.model.customGoal.exceptions;

public class DuplicateCustomGoalException extends RuntimeException {
    public DuplicateCustomGoalException() {
        super("This goal already exists!");
    }
}
