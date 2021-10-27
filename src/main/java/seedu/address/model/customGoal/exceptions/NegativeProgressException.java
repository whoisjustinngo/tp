package seedu.address.model.customGoal.exceptions;

public class NegativeProgressException extends RuntimeException {
    public NegativeProgressException() {
        super("Cannot set progress to negative value!");
    }
}
