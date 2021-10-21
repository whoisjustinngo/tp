package seedu.address.model.event;

import java.time.LocalDateTime;

import seedu.address.model.event.exceptions.InvalidTimeException;

public class Schedule extends Event<Schedule> {
    public static final String ERROR_MSG_LETTERS_IN_TIME =
            "Please provide a proper time formatting between 0000 - 2359";
    public static final String ERROR_MSG_INVALID_TIME_RANGE =
            "Please provide a proper time range between 0000 - 2359.";
    public static final String ERROR_MSG_INVALID_TIME_SQEUENCE =
            "Time <from> cannot be greater than time <to>.";

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
        this.checkTimeRangeAndFormatting(timeFrom, timeTo);
        String dateCopy = date;
        if (dateCopy.contains("/")) {
            dateCopy = dateCopy.replace("/", "-");
        }
        this.taskDateTimeFrom = this.getLocalDateTime(dateCopy, timeFrom);
        this.taskDateTimeTo = this.getLocalDateTime(dateCopy, timeTo);
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

    public boolean isSameSchedule(Schedule schedule) {
        return this.getTaskDateTimeFrom().equals(schedule.getTaskDateTimeFrom()) 
                && this.getTaskDateTimeTo().equals(schedule.getTaskDateTimeTo())
                && this.getDescription().equals(schedule.getDescription())
                && this.getDate().equals(schedule.getDate())
                && this.getTimeFrom() == schedule.getTimeFrom()
                && this.getTimeTo() == schedule.getTimeTo();
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

    private void checkTimeFormatting(String time) {
        try {
            int hour = Integer.parseInt(time) / 100;
            int minute = Integer.parseInt(time) % 100;
            if (hour > 23 || hour < 0) {
                throw new InvalidTimeException(ERROR_MSG_INVALID_TIME_RANGE);
            }
            if (minute > 59 || minute < 0) {
                throw new InvalidTimeException(ERROR_MSG_INVALID_TIME_RANGE);
            }
        } catch (NumberFormatException e) {
            throw new InvalidTimeException(ERROR_MSG_LETTERS_IN_TIME);
        }
    }

    private void checkTimeRange(String timeFrom, String timeTo) {
        if (Integer.parseInt(timeFrom) > Integer.parseInt(timeTo)) {
            throw new InvalidTimeException(ERROR_MSG_INVALID_TIME_SQEUENCE);
        }
    }

    private void checkTimeRangeAndFormatting(String timeFrom, String timeTo) {
        this.checkTimeFormatting(timeFrom);
        this.checkTimeFormatting(timeTo);
        this.checkTimeRange(timeFrom, timeTo);
    }

    private LocalDateTime getLocalDateTime(String dateCopy, String time) {
        return LocalDateTime.of(Integer.parseInt(dateCopy.split("-")[2]), Integer.parseInt(dateCopy.split("-")[1]),
                Integer.parseInt(dateCopy.split("-")[0]), getHour(time), getMinute(time));
    }
}
