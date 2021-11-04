package seedu.address.model.event.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.exceptions.InvalidScheduleInputException;

public class ScheduleContainsDatePredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleContainsDatePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) throws InvalidScheduleInputException {
        System.out.println(keywords.get(0));
        checkDateFormatting(keywords.get(0));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(schedule.getDateTime(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleContainsDatePredicate // instanceof handles nulls
                && keywords.equals(((ScheduleContainsDatePredicate) other).keywords)); // state check
    }

    private void checkDateFormatting(String str) {
        String copy = str;
        if (!copy.contains("-")) {
            throw new InvalidScheduleInputException();
        }
        String[] date = copy.split("-");
        if (date.length != 3) {
            throw new InvalidScheduleInputException();
        }
        if (Integer.parseInt(date[0]) > 31 || Integer.parseInt(date[0]) < 0) {
            throw new InvalidScheduleInputException();
        }
        if (Integer.parseInt(date[1]) > 12 || Integer.parseInt(date[1]) < 0) {
            throw new InvalidScheduleInputException();
        }
        if (Integer.parseInt(date[2]) < 0) {
            throw new InvalidScheduleInputException();
        }
    }
}
