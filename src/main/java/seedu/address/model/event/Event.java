package seedu.address.model.event;

public class Event {
    private final String description;
    private final boolean isDone;

    public Event(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public Event markAsDone() {
        return new Event(this.getDescription(), true);
    }
}
