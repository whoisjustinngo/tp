package seedu.address.model.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import seedu.address.model.event.exceptions.EmptyEventException;

public class Timetable extends Timeable<Schedule> {
    private final HashMap<String, DayPlan> timetable;

    public Timetable() {
        this.timetable = new HashMap<String, DayPlan>();
    }

    @Override
    public String add(Schedule currTask) {
        if (this.timetable.get(currTask.getDate()) == null) {
            DayPlan dayPlan = new DayPlan();
            dayPlan.addSchedule(currTask);
            this.timetable.put(currTask.getDate(), dayPlan);
            return "Task added to schedule";
        }
        return this.timetable.get(currTask.getDate()).addSchedule(currTask);
    }

    @Override
    public String delete(Schedule currTask) {
        String UiMessage = this.timetable.get(currTask.getDate()).deleteSchedule(currTask);
        if (!this.timetable.get(currTask.getDate()).hasSchedule()) {
            this.timetable.remove(currTask.getDate());
        }
        return UiMessage;
    }

    @Override
    public String view() {
        if (this.timetable.size() == 0) {
            throw new EmptyEventException();
        }
        Map<String, DayPlan> sortedMap = new TreeMap<>(this.getComparator());
        sortedMap.putAll(this.timetable);
        String allScheduledTasks = "";
        Iterator<String> activeDates = sortedMap.keySet().iterator();

        while (activeDates.hasNext()) {
            String currDate = activeDates.next();
            allScheduledTasks += currDate + "\n" + this.timetable.get(currDate).viewDayPlan();
        }
        return allScheduledTasks;
    }

    public DayPlan getDayPlan(String date) {
        return this.timetable.get(date);
    }

    /**
     * Clears the {@code Schedule} for this day.
     *
     * @param date the date which {@code Schedule} will be cleared.
     * @return a message if the {@code Schedule} is cleared.
     */
    public String clearDayPlan(String date) {
        this.timetable.remove(date);
        return "Plans for the day has cleared.";
    }

    /**
     * Checks of the added {@code Schedule} clashes with the added {@code Schedule}.
     *
     * @param currTask the {@code Schedule} to be added.
     * @return boolean if the {@code Schedule} clashes.
     */
    public boolean isClash(Schedule currTask) {
        try {
            return this.timetable.get(currTask.getDate()).isClash(currTask);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
