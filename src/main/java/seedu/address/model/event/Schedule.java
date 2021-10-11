package seedu.address.model.event;

import java.time.LocalDate;

public class Schedule extends Event<Schedule> {
    private final LocalDate taskDate;
    private final int timeFrom;
    private final int timeTo;

    public Schedule(String description, String date, int timeFrom, int timeTo, boolean isDone) {
        super(description, date, isDone);
        this.taskDate = LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    public int getTimeFrom() {
        return this.timeFrom;
    }

    public int getTimeTo() {
        return this.timeTo;
    }

    /**
     * Gets the formatted time and date in a proper {@code String}.
     *
     * @return formatteed time and date.
     */
    public String getDateTime() {
        return String.format("at %s from %d to %d", this.getDate(), this.getTimeFrom(), this.getTimeTo());
    }

    @Override
    public Schedule markAsDone() {
        return new Schedule(this.getDescription(), this.getDate(), this.getTimeFrom(), this.getTimeTo(), true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return (otherSchedule.getDescription().equals(this.getDescription())
                && otherSchedule.getTimeFrom() == this.getTimeFrom() && otherSchedule.getTimeTo() == this.getTimeTo()
                && otherSchedule.getDate().equals(this.getDate()));
    }

    @Override
    public String toString() {
        return String.format("%s on %s from %d to %d", this.getDescription(), this.getDate(), this.timeFrom,
                this.timeTo);
    }
}
