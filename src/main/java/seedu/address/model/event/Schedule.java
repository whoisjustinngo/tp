package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

public class Schedule extends Event {
    private final LocalDate taskDate;
    private final String date;
    private final int timeFrom;
    private final int timeTo;

    public Schedule(String description, String date, int timeFrom, int timeTo, boolean isDone) {
        super(description, isDone);
        requireNonNull(date);
        this.taskDate = LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    public String getDate() {
        return this.date;
    }

    public int getTimeFrom() {
        return this.timeFrom;
    }

    public int getTimeTo() {
        return this.timeTo;
    }

    @Override
    public Schedule markAsDone() {
        return new Schedule(this.getDescription(), this.getDate(), this.getTimeFrom(), this.getTimeTo(), true);
    }

    @Override
    public String toString() {
        return String.format("%s on %s from %d to %d", this.getDescription(), this.getDate(), this.timeFrom,
                this.timeTo);
    }
}
