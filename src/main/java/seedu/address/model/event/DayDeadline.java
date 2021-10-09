package seedu.address.model.event;

import java.util.ArrayList;

import seedu.address.model.event.exceptions.EmptyEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

public class DayDeadline {
    private final ArrayList<Deadline> dailyDeadline;

    public DayDeadline() {
        this.dailyDeadline = new ArrayList<Deadline>();
    }

    public DayDeadline(ArrayList<Deadline> dailyDeadline) {
        this.dailyDeadline = dailyDeadline;
    }

    public String addDeadline(Deadline deadline) {
        this.dailyDeadline.add(deadline);
        return "Deadline added.";
    }

    public String deleteDeadline(Deadline deadline) {
        String deadlineDescription = deadline.getDescription();

        boolean isRemoved = this.dailyDeadline
                .removeIf(currDeadline -> (currDeadline.getDescription().equals(deadlineDescription)));
        if (!isRemoved) {
            throw new EventNotFoundException();
        }
        return deadline.toString() + " is removed.";
    }

    public String viewDeadlines() {
        if (this.dailyDeadline.size() == 0) {
            throw new EmptyEventException();
        }
        String dayPlans = "";
        for (int i = 0; i < this.dailyDeadline.size(); i++) {
            dayPlans += this.dailyDeadline.get(i).toString() + "\n";
        }
        return dayPlans;
    }

    public void markDone(Deadline deadline) {
        int index = checkExist(deadline);
        if (index < 0) {
            return;
        }
        this.dailyDeadline.set(index, deadline.markAsDone());
    }

    public boolean hasDeadline() {
        return this.dailyDeadline.size() != 0;
    }

    private int checkExist(Deadline deadline) {
        String deadlineDescription = deadline.getDescription();
        for (int i = 0; i < this.dailyDeadline.size(); i++) {
            Deadline currDeadline = this.dailyDeadline.get(i);
            if (currDeadline.getDescription().equals(deadlineDescription)) {
                return i;
            }
        }
        return -1;
    }

}
