package seedu.address.model.customGoal.exceptions;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("This date is invalid!");
    }
}
