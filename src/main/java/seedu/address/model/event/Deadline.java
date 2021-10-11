package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import java.time.LocalDate;

/**
 * A Deadline Event
 */
public class Deadline extends Event<Deadline> {
    private final LocalDate taskDate;

    /**
     * Primary Constructor for Deadline.
     *
     * @param description is the description for this {@code Deadline}.
     * @param date        is this {@code Deadline}'s date.
     * @param isDone      determine whether this {@code Deadline} is completed or
     *                    not.
     */
    public Deadline(String description, String date, boolean isDone) {
        super(description, date, isDone);
        requireNonNull(date);
        this.taskDate = LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
    }

    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(this.getDescription(), this.getDate(), true);
    }

    @Override
    public String toString() {
        return String.format("%s by %s", this.getDescription(), this.getDate());
    }

}
