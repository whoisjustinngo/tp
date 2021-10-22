package seedu.address.model.customGoal;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomGoal {
    
    private final String goalDescription;
    private final int goal;
    private final LocalDate dateAdded;
    private final LocalDate endDate;
    private final LocalTime timeAdded;
    private final LocalTime endTime;

    
    public CustomGoal(String description, int goal, LocalDate endDate, LocalTime endTime) {
        this.goalDescription = description;
        this.goal = goal;
        this.endDate = endDate;
        this.endTime = endTime;
        this.dateAdded = LocalDate.now();
        this.timeAdded = LocalTime.now();
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
}
