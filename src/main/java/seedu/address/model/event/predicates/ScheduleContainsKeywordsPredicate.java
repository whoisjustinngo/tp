package seedu.address.model.event.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.event.Schedule;

public class ScheduleContainsKeywordsPredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(schedule.getDescription(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ScheduleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
