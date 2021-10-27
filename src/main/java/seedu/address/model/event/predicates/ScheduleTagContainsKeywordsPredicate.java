package seedu.address.model.event.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.event.Schedule;

public class ScheduleTagContainsKeywordsPredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        // all keywords must be present in tag
        for (String keyword : keywords) {
            boolean hasMatch = schedule.getTags().stream()
                    .anyMatch(tag -> tag.getTagName().equalsIgnoreCase(keyword));
            if (!hasMatch) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ScheduleTagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
