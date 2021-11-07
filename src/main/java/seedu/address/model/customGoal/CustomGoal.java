package seedu.address.model.customGoal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a custom goal which is to be displayed on the dashboard.
 */
public class CustomGoal {

    private static final String TIME_FORMAT = "HH:mm";
    private static final String DATE_FORMAT = "E, dd MMM yyyy";

    private final String goalDescription;
    private final float goal;
    private final float progress;
    private final LocalDate dateAdded;
    private final LocalDate endDate;
    private final LocalTime timeAdded;
    private final LocalTime endTime;

    /**
     * Creates a new custom goal.
     * @param description The description of the goal.
     * @param goal The value of the goal, i.e. the value the user is aiming to achieve.
     * @param endDate The date by which to complete the goal.
     * @param endTime The time by which to complete the goal.
     */
    public CustomGoal(String description, float goal, LocalDate endDate, LocalTime endTime) {
        this.goalDescription = description.trim();
        this.goal = goal;
        this.progress = 0;
        this.endDate = endDate;
        this.endTime = endTime;
        this.dateAdded = LocalDate.now();
        this.timeAdded = LocalTime.now();
    }

    /**
     * Works just like {@link CustomGoal#CustomGoal(java.lang.String, float, java.time.LocalDate, java.time.LocalTime)}
     * except this takes additional <code>String</code> parameters specifying the date and time the custom goal was
     * created.
     * @param dateAdded The date the custom goal was created.
     * @param timeAdded The time the custom goal was created.
     */
    public CustomGoal(String goalDescription, float goal, float progress, String dateAdded, String timeAdded,
                      String endDate,
                      String endTime) {
        this.goalDescription = goalDescription.trim();
        this.goal = goal;
        this.progress = Math.max(progress, 0);
        this.dateAdded = LocalDate.parse(dateAdded, DateTimeFormatter.ofPattern(DATE_FORMAT));
        this.timeAdded = LocalTime.parse(timeAdded, DateTimeFormatter.ofPattern(TIME_FORMAT));
        if (endDate.equals("-")) {
            this.endDate = LocalDate.MAX;
        } else {
            this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(DATE_FORMAT));;
        }
        if (endTime.equals("-")) {
            this.endTime = LocalTime.MAX;
        } else {
            this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
        }
    }

    private CustomGoal(CustomGoal oldGoal, float valueToUpdateBy) {
        this.goalDescription = oldGoal.getDescription();
        this.goal = oldGoal.getGoal();
        this.progress = Math.max((oldGoal.getProgress() + valueToUpdateBy), 0);
        this.dateAdded = oldGoal.dateAdded;
        this.timeAdded = oldGoal.timeAdded;
        this.endDate = oldGoal.endDate;
        this.endTime = oldGoal.endTime;
    }

    public CustomGoal updateProgress(float valueToUpdateBy) {
        return new CustomGoal(this, valueToUpdateBy);
    }

    public boolean isComplete() {
        return this.progress >= this.goal;
    }

    /**
     * Compares this CustomGoal with another object to check if they are the same.
     * Two CustomGoals are considered equal if they have the same goalDescription, goal, endDate, and endTime.
     * @param other The other object to compare this to.
     * @return Whether the other object is equal to this CustomGoal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof CustomGoal)) {
            return false;
        }

        CustomGoal otherGoal = (CustomGoal) other;
        return this.goalDescription.equals(otherGoal.goalDescription)
                && this.goal == otherGoal.goal
                && this.endDate.isEqual(otherGoal.endDate)
                && this.endTime.equals(otherGoal.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalDescription, goal, dateAdded, endDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.goalDescription);
        if (!this.endDate.isEqual(LocalDate.MAX)) {
            sb.append(" by ");
            sb.append(this.getEndDateValue());
        }
        if (!this.endTime.equals(LocalTime.MAX)) {
            sb.append(" ");
            sb.append(this.getEndTimeValue());
        }
        sb.append(" - progress: ");
        sb.append(this.getProgressValue());
        sb.append("/");
        sb.append(this.getGoalValue());
        return sb.toString();
    }

    // @@author whoisjustinngo-reused
    // function to truncate trailing 0s when printing adapted from https://stackoverflow.com/a/14126736
    /**
     * Converts the provided float to a string while truncating all trailing zeroes.
     * @param f The float to convert and truncate.
     * @return The string representation of the given float with the trailing zeroes truncated.
     */
    public String formatFloat(float f) {
        if (f == (long) f) {
            return String.format("%d", (long) f);
        } else {
            return String.format("%s", f);
        }
    }
    // @@author

    public String getDescription() {
        return this.goalDescription;
    }

    public float getProgress() {
        return this.progress;
    }

    public float getGoal() {
        return this.goal;
    }

    public String getEndDateValue() {
        return this.endDate.isEqual(LocalDate.MAX)
                ? "-" : this.endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String getEndTimeValue() {
        return this.endTime.equals(LocalTime.MAX) ? "-" : this.endTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public String getDateAddedValue() {
        return this.dateAdded.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String getTimeAddedValue() {
        return this.timeAdded.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public String getProgressValue() {
        return formatFloat(this.progress);
    }

    public String getGoalValue() {
        return formatFloat(this.goal);
    }

    /**
     * Checks if the current custom goal is overdue
     */
    public boolean isOverdue() {
        if (LocalDate.now().isEqual(this.endDate)) {
            return LocalTime.now().isAfter(this.endTime);
        } else {
            return LocalDate.now().isAfter(this.endDate);
        }
    }
}
