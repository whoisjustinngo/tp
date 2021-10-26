package seedu.address.model.event.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.event.Schedule;

public class ScheduleContainsDatePredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleContainsDatePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(schedule.getDateTime(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleContainsDatePredicate // instanceof handles nulls
                && keywords.equals(((ScheduleContainsDatePredicate) other).keywords)); // state check
    }
}
