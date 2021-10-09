package seedu.address.model.event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Timetable {
    private final HashMap<String, DayPlan> timetable;

    public Timetable() {
        this.timetable = new HashMap<String, DayPlan>();
    }

    public String add(Schedule currTask) {
        if (this.timetable.get(currTask.getDate()) == null) {
            DayPlan dayPlan = new DayPlan();
            dayPlan.addSchedule(currTask);
            this.timetable.put(currTask.getDate(), dayPlan);
            return "Task added to schedule";
        }
        return this.timetable.get(currTask.getDate()).addSchedule(currTask);
    }

    public String delete(Schedule currTask) {
        String UIMessage = this.timetable.get(currTask.getDate()).deleteSchedule(currTask);
        if (!this.timetable.get(currTask.getDate()).hasSchedule()) {
            this.timetable.remove(currTask.getDate());
        }
        return UIMessage;
    }

    public String view() {
        if (this.timetable.size() == 0) {
            return "No schedule found";
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

    public String clearDayPlan(String date) {
        this.timetable.remove(date);
        return "Plans for the day has cleared.";
    }

    public boolean isClash(Schedule currTask) {
        try {
            return this.timetable.get(currTask.getDate()).isClash(currTask);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private Comparator<String> getComparator() {
        return new Comparator<String>() {
            @Override
            public int compare(String firstDate, String secondDate) {
                String[] firstDateArr = firstDate.split("-");
                String[] secondDateArr = secondDate.split("-");
                int firstIntDate = Integer.parseInt(firstDateArr[2] + firstDateArr[1] + firstDateArr[1]);
                int secondIntDate = Integer.parseInt(secondDateArr[2] + secondDateArr[1] + secondDateArr[1]);
                return (firstIntDate > secondIntDate) ? -1 : 1;
            }
        };
    }

}
