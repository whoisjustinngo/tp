package seedu.address.model.event.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.event.Schedule;

public class ScheduleContainsFromPredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleContainsFromPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        return keywords.stream().anyMatch(
                keyword -> StringUtil.containsWordIgnoreCase(String.valueOf(schedule.getTimeFrom()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleContainsFromPredicate // instanceof handles nulls
                        && keywords.equals(((ScheduleContainsFromPredicate) other).keywords)); // state check
    }
}
