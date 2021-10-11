package seedu.address.model.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import seedu.address.model.event.exceptions.EmptyEventException;

/**
 * A table for the {@code Deadline}.
 */
public class DeadlineTable extends Timeable<Deadline> {
    private final HashMap<String, DayDeadline> deadlineTableHash;

    /**
     * Primary Constructor.
     */
    public DeadlineTable() {
        this.deadlineTableHash = new HashMap<String, DayDeadline>();
    }

    @Override
    public String add(Deadline deadline) {
        if (this.deadlineTableHash.get(deadline.getDate()) == null) {
            DayDeadline dayDeadline = new DayDeadline();
            dayDeadline.addDeadline(deadline);
            this.deadlineTableHash.put(deadline.getDate(), dayDeadline);
            return "Task added to schedule";
        }
        return this.deadlineTableHash.get(deadline.getDate()).addDeadline(deadline);
    }

    @Override
    public String delete(Deadline deadline) {
        String UIMessage = this.deadlineTableHash.get(deadline.getDate()).deleteDeadline(deadline);
        if (!this.deadlineTableHash.get(deadline.getDate()).hasDeadline()) {
            this.deadlineTableHash.remove(deadline.getDate());
        }
        return UIMessage;
    }

    @Override
    public String view() {
        if (this.deadlineTableHash.size() == 0) {
            throw new EmptyEventException();
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
}
