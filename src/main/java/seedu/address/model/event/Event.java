package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

abstract class Event<T> {
    protected final String description;
    protected final String date;
    protected final boolean isDone;

    protected Event(String description, String date, boolean isDone) {
        requireNonNull(description);
        requireNonNull(date);
        String dateCopy = date;
        if (dateCopy.contains("/")) {
            dateCopy = dateCopy.replace("/", "-");
        }
        this.description = description;
        this.date = dateCopy;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public boolean isDone() {
        return this.isDone;
    }
}
