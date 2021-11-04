package seedu.address.model.event.exceptions;

public class InvalidScheduleInputException extends RuntimeException {
    public InvalidScheduleInputException() {
        super("Invalid input. Please check and try again");
    }
}
