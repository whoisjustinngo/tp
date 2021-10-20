package seedu.address.model.event;

import java.time.LocalDateTime;

public class Schedule extends Event<Schedule> {
    private final LocalDateTime taskDateTimeFrom;
    private final LocalDateTime taskDateTimeTo;
    private final int timeFrom;
    private final int timeTo;

    /**
     * Priamry Constructor
     *
     * @param description description for this {@code Schedule}.
     * @param date        date for this {@code Schedule}.
     * @param timeFrom    start time for this {@code Schedule}.
     * @param timeTo      end time for this {@code Schedule}.
     * @param isDone      if this {@code Schedule} is completed.
     */
    public Schedule(String description, String date, String timeFrom, String timeTo, boolean isDone) {
        super(description, date, isDone);
        String dateCopy = date;
        if (dateCopy.contains("/")) {
            dateCopy = dateCopy.replace("/", "-");
        }
        this.taskDateTimeFrom = LocalDateTime.of(Integer.parseInt(dateCopy.split("-")[2]),
                Integer.parseInt(dateCopy.split("-")[1]), Integer.parseInt(dateCopy.split("-")[0]), getHour(timeFrom),
                getMinute(timeFrom));
        this.taskDateTimeTo = LocalDateTime.of(Integer.parseInt(dateCopy.split("-")[2]),
                Integer.parseInt(dateCopy.split("-")[1]), Integer.parseInt(dateCopy.split("-")[0]), getHour(timeTo),
                getMinute(timeTo));
        this.timeFrom = Integer.parseInt(timeFrom);
        this.timeTo = Integer.parseInt(timeTo);
    }

    public LocalDateTime getTaskDateTimeFrom() {
        return this.taskDateTimeFrom;
    }

    public LocalDateTime getTaskDateTimeTo() {
        return this.taskDateTimeTo;
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
        return String.format("at %s from %04d to %04d", this.getDate(), this.getTimeFrom(), this.getTimeTo());
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
        return String.format("%s on %s from %04d to %04d", this.getDescription(), this.getDate(), this.timeFrom,
                this.timeTo);
    }

    private int getHour(String time) {
        return Integer.parseInt(time) / 100;
    }

    private int getMinute(String time) {
        return Integer.parseInt(time) % 100;
    }
}
