package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

abstract class Event<T> {
    private final String description;
    private final String date;
    private final boolean isDone;

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

    /**
     * Marks this {@code Event} as done.
     *
     * @return the {@code Event} that is marked as done.
     */
    abstract T markAsDone();

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
