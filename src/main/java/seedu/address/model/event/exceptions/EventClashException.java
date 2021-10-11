package seedu.address.model.event.exceptions;

public class EventClashException extends RuntimeException {
    public EventClashException() {
        super("Schedule got clashes. Please select a different timeslot.");
    }
}
