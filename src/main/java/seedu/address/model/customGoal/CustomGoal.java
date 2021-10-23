package seedu.address.model.customGoal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomGoal {

    private static final String TIME_FORMAT = "HH:mm";
    private static final String DATE_FORMAT = "E, dd MMM yyyy";

    private final String goalDescription;
    private final int goal;
    private int progress;
    private final LocalDate dateAdded;
    private final LocalDate endDate;
    private final LocalTime timeAdded;
    private final LocalTime endTime;

    // TODO change the date and time to optionals
    public CustomGoal(String description, int goal, LocalDate endDate, LocalTime endTime) {
        this.goalDescription = description.trim();
        this.goal = goal;
        this.progress = 0;
        this.endDate = endDate;
        this.endTime = endTime;
        this.dateAdded = LocalDate.now();
        this.timeAdded = LocalTime.now();
    }
    
    public CustomGoal(String goalDescription, int goal, int progress, String dateAdded, String timeAdded, String endDate,
                      String endTime) {
        this.goalDescription = goalDescription.trim();
        this.goal = goal;
        this.progress = progress;
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
    public String toString() { // TODO update to a better format
        return "CustomGoal{" +
                "goalDescription='" + goalDescription + '\'' +
                ", goal=" + goal +
                ", dateAdded=" + dateAdded +
                ", dateEnd=" + endDate +
                ", timeAdded=" + timeAdded +
                ", timeEnd=" + endTime +
                '}';
    }

    public String getDescription() {
        return this.goalDescription;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getGoal() {
        return this.goal;
    }

    public String getEndDateValue() {
        return this.endDate.isEqual(LocalDate.MAX) ? "-" :
                this.endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
    public String getEndTimeValue() {
        return this.endTime.equals(LocalTime.MAX) ? "-" : 
                this.endTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
    
    public String getDateAddedValue() {
        return this.dateAdded.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
    public String getTimeAddedValue() {
        return this.timeAdded.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
