package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

abstract class Event {
    private final String description;
    private final boolean isDone;

    protected Event(String description, boolean isDone) {
        requireNonNull(description);
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    abstract Event markAsDone();
}
