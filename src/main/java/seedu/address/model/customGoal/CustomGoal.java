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

    
    public CustomGoal(String description, int goal, LocalDate endDate, LocalTime endTime) {
        this.goalDescription = description.trim();
        this.goal = goal;
        this.progress = 0;
        this.endDate = endDate;
        this.endTime = endTime;
        this.dateAdded = LocalDate.now();
        this.timeAdded = LocalTime.now();
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

    public String getEndDateForDisplay() {
        return this.endDate.isEqual(LocalDate.MAX) ? "-" :
                this.endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
    public String getEndTimeForDisplay() {
        return this.endTime.equals(LocalTime.MAX) ? "-" : 
                this.endTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
    
    
}
