package seedu.address.model.event;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.event.exceptions.EmptyEventException;
import seedu.address.model.event.exceptions.EventClashException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Class which saves and manages all the {@code Schedule}. Makes sure that no
 * {@code Schedule} clashes one another.
 */
public class DayPlan {
    private final ArrayList<Schedule> scheduledTasks;

    public DayPlan() {
        this.scheduledTasks = new ArrayList<Schedule>();
    }

    public DayPlan(ArrayList<Schedule> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    /**
     * Adds the current {@code Schedule} into the {@code DayPlan}.
     *
     * @param currTask the {@code Schedule} which will be added into this
     *                 {@code DayPlan}.
     * @return a message stating if the {@code Schedule} is added into this
     *         {@code DayPlan}.
     */
    public String addSchedule(Schedule currTask) {
        if (this.isClash(currTask)) {
            throw new EventClashException();
        }
        this.scheduledTasks.add(currTask);
        return currTask.toString() + " is added into the timetable";
    }

    /**
     * Deletes the current {@code Schedule} from the {@code DayPlan}.
     *
     * @param currTask the {@code Schedule} which will be deleted from this
     *                 {@code DayPlan}.
     * @return a message stating if the {@code Schedule} is deleted from this
     *         {@code DayPlan}.
     */
    public String deleteSchedule(Schedule currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();

        boolean isRemoved = scheduledTasks
                .removeIf(task -> (task.getTimeFrom() == currTimeFrom && task.getTimeTo() == currTimeTo));
        if (!isRemoved) {
            throw new EventNotFoundException();
        }
        return currTask.toString() + " is removed from timetable.";
    }

    /**
     * Views all the {@code Schedule} which are already added into this
     * {@code DayPlan}.
     *
     * @return all the {@code Schedule} which are already in this {@code DayPlan}.
     */
    public String viewDayPlan() {
        if (this.scheduledTasks.size() == 0) {
            throw new EmptyEventException();
        }
        this.scheduledTasks.sort(getComparatorEarlyToLate());
        String dayPlans = "";
        for (int i = 0; i < this.scheduledTasks.size(); i++) {
            dayPlans += this.scheduledTasks.get(i).toString() + "\n";
        }
        return dayPlans;
    }

    /**
     * Checks if the current {@code Schedule} clashes with any {@code Schedule}
     * which are already added in the {@code DayPlan}.
     *
     * @param currTask is the {@code Schedule} which will be checked if clash
     *                 happens.
     * @return boolean, true if there is a clash in {@code Timetable}.
     */
    public boolean isClash(Schedule currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();

        ArrayList<Schedule> clashList = new ArrayList<Schedule>();

        this.scheduledTasks.stream()
                .filter(task -> (currTimeFrom > task.getTimeFrom() && currTimeFrom < task.getTimeTo())
                        || (currTimeTo > task.getTimeFrom() && currTimeTo < task.getTimeTo())
                        || (task.getTimeFrom() == currTimeFrom) || (task.getTimeTo() == currTimeTo))
                .forEach(task -> clashList.add(task));
        return clashList.size() != 0;
    }

    /**
     * Checks if there is any {@code Schedule} in this {@code DayPlan}.
     *
     * @return true is there is a {@code Schedule} in this {@code DayPlan}.
     */
    public boolean hasSchedule() {
        return this.scheduledTasks.size() != 0;
    }

    private Comparator<Schedule> getComparatorEarlyToLate() {
        return new Comparator<Schedule>() {
            @Override
            public int compare(Schedule firstTask, Schedule secondTask) {
                return (firstTask.getTimeFrom() > secondTask.getTimeFrom()) ? 1 : -1;
            }
        };
    }
}
