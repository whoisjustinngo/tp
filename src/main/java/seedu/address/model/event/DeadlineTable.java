package seedu.address.model.event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DeadlineTable {
    private final HashMap<String, DayDeadline> deadlineTableHash;

    public DeadlineTable() {
        this.deadlineTableHash = new HashMap<String, DayDeadline>();
    }

    public String add(Deadline deadline) {
        if (this.deadlineTableHash.get(deadline.getDate()) == null) {
            DayDeadline dayDeadline = new DayDeadline();
            dayDeadline.addDeadline(deadline);
            this.deadlineTableHash.put(deadline.getDate(), dayDeadline);
            return "Task added to schedule";
        }
        return this.deadlineTableHash.get(deadline.getDate()).addDeadline(deadline);
    }

    public String delete(Deadline deadline) {
        String UIMessage = this.deadlineTableHash.get(deadline.getDate()).deleteDeadline(deadline);
        if (!this.deadlineTableHash.get(deadline.getDate()).hasDeadline()) {
            this.deadlineTableHash.remove(deadline.getDate());
        }
        return UIMessage;
    }

    public String view() {
        if (this.deadlineTableHash.size() == 0) {
            return "No deadlines found";
        }
        Map<String, DayDeadline> sortedMap = new TreeMap<>(this.getComparator());
        sortedMap.putAll(this.deadlineTableHash);
        String allScheduledTasks = "";
        Iterator<String> activeDates = sortedMap.keySet().iterator();

        while (activeDates.hasNext()) {
            String currDate = activeDates.next();
            allScheduledTasks += currDate + "\n" + this.deadlineTableHash.get(currDate).viewDeadlines();
        }
        return allScheduledTasks;
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
