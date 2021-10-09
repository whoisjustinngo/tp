package seedu.address.model.event;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.event.exceptions.EmptyEventException;
import seedu.address.model.event.exceptions.EventClashException;
import seedu.address.model.event.exceptions.EventNotFoundException;

public class DayPlan {
    private final ArrayList<Schedule> scheduledTasks;

    public DayPlan() {
        this.scheduledTasks = new ArrayList<Schedule>();
    }

    public DayPlan(ArrayList<Schedule> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    public String addSchedule(Schedule currTask) {
        if (this.isClash(currTask)) {
            throw new EventClashException();
        }
        this.scheduledTasks.add(currTask);
        return currTask.toString() + " is added into the timetable";
    }

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

    public String moveSchedule(Schedule from, Schedule to) {
        DayPlan copyScheduledTasks = new DayPlan(new ArrayList<>(this.scheduledTasks));
        copyScheduledTasks.deleteSchedule(from);

        if (copyScheduledTasks.isClash(to)) {
            return "Unable to make changes to the Timetable due to clashes. Please try again.";
        }
        this.deleteSchedule(from);
        this.addSchedule(to);
        return "Changes have been made successfully.";
    }

    public void markDone(Schedule currTask) {
        int index = isExist(currTask);
        if (index < 0) {
            return;
        }
        this.scheduledTasks.set(index, currTask.markAsDone());
    }

    public int isExist(Schedule currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();
        String description = currTask.getDescription();

        for (int i = 0; i < this.scheduledTasks.size(); i++) {
            Schedule pointerTask = this.scheduledTasks.get(i);
            if (isSameTask(pointerTask, currTimeFrom, currTimeTo, description)) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasSchedule() {
        return this.scheduledTasks.size() != 0;
    }

    private boolean isSameTask(Schedule pointerTask, int currTimeFrom, int currTimeTo, String description) {
        int pointerFrom = pointerTask.getTimeFrom();
        int pointerTo = pointerTask.getTimeTo();
        String pointerDescription = pointerTask.getDescription();
        return (pointerFrom == currTimeFrom && currTimeTo == pointerTo && description.equals(pointerDescription));
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
