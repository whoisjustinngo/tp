package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import java.time.LocalDate;

public class Deadline extends Event {
    private final LocalDate taskDate;
    private final String date;

    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        requireNonNull(date);
        this.taskDate = LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
        this.date = date;
    }

    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    public String getDate() {
        return this.date;
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
