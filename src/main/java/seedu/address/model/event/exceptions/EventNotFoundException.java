package seedu.address.model.event.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("The Event is not found in the table.");
    }
}
