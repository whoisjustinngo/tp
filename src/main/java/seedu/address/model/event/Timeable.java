package seedu.address.model.event;

import java.util.Comparator;

abstract class Timeable<T> {
    /**
     * Adds an {@code Event} to the Timetable.
     * 
     * @param currTask is the current {@code Event}.
     * @return a {@code String} message if the {@code Event} is added.
     */
    abstract String add(T currTask);

    /**
     * Deletes an {@code Event} from the Timetable.
     * 
     * @param currTask is the current {@code Event}.
     * @return a {@code String} message if the {@code Event} is deleted.
     * @param currTask
     * @return
     */
    abstract String delete(T currTask);

    /**
     * Views the timetable.
     * 
     * @return {@code String} which contains information from the timetable.
     */
    abstract String view();

    protected Comparator<String> getComparator() {
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
