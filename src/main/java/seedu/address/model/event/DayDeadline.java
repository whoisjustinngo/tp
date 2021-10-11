package seedu.address.model.event;

import java.util.ArrayList;

import seedu.address.model.event.exceptions.EmptyEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Daily Deadlines.
 */
public class DayDeadline {
    private final ArrayList<Deadline> dailyDeadline;

    public DayDeadline() {
        this.dailyDeadline = new ArrayList<Deadline>();
    }

    public DayDeadline(ArrayList<Deadline> dailyDeadline) {
        this.dailyDeadline = dailyDeadline;
    }

    /**
     * Adds a deadline to the current day.
     *
     * @param deadline {@code Deadline} to be added
     * @return {@code String} message stating that deadline is added.
     */
    public String addDeadline(Deadline deadline) {
        this.dailyDeadline.add(deadline);
        return "Deadline added.";
    }

    /**
     * Deletes {@code Deadline} from the current day
     *
     * @param deadline the {@code Deadline} to be deleted
     * @return {@code String} message stating if the deadline is removed.
     */
    public String deleteDeadline(Deadline deadline) {
        String deadlineDescription = deadline.getDescription();

        boolean isRemoved = this.dailyDeadline
                .removeIf(currDeadline -> (currDeadline.getDescription().equals(deadlineDescription)));
        if (!isRemoved) {
            throw new EventNotFoundException();
        }
        return deadline.toString() + " is removed.";
    }

    /**
     * Views the {@code Deadline} for the current day.
     *
     * @return {@code String} of all the {@code Deadline} for the current day.
     */
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

    /**
     * Marks {@code Deadline} as done.
     *
     * @param deadline is the {@code Deadline} to be marked as done.
     */
    public void markDone(Deadline deadline) {
        int index = checkExist(deadline);
        if (index < 0) {
            return;
        }
        this.dailyDeadline.set(index, deadline.markAsDone());
    }

    /**
     * Checks if there is any {@code Deadline} on the current day.
     *
     * @return boolean if there is a {@code Deadline}.
     */
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
