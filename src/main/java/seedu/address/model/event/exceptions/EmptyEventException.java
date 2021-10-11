package seedu.address.model.event.exceptions;

public class EmptyEventException extends RuntimeException {
    public EmptyEventException() {
        super("Event is empty, thus would not be able to delete any event");
    }
}
