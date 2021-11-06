package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Schedule;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

public class ScheduleBuilder {
    public static final String DEFAULT_DESCRIPTION = "read";
    public static final String DEFAULT_DATE = "12/1/2022";
    public static final String DEFAULT_TIMEFROM = "1600";
    public static final String DEFAULT_TIMETO = "1800";
    public static final String DEFAULT_RECURTYPE = "N";
    public static final String DEFAULT_RECURDATE = "";

    private String description;
    private String date;
    private String timeFrom;
    private String timeTo;
    private String recurType;
    private String recurDate;
    private Set<Tag> tags;
    private boolean isDone = false;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        this.description = DEFAULT_DESCRIPTION;
        this.date = DEFAULT_DATE;
        this.timeFrom = DEFAULT_TIMEFROM;
        this.timeTo = DEFAULT_TIMETO;
        this.recurType = DEFAULT_RECURTYPE;
        this.recurDate = DEFAULT_RECURDATE;
        tags = new HashSet<>();
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code ScheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        this.description = scheduleToCopy.getDescription();
        this.date = scheduleToCopy.getDate();
        this.timeFrom = String.valueOf(scheduleToCopy.getTimeFrom());
        this.timeTo = String.valueOf(scheduleToCopy.getTimeTo());
        this.recurType = scheduleToCopy.getRecurrType();
        this.recurDate = scheduleToCopy.getRecurrDate();
        tags = new HashSet<>(scheduleToCopy.getTags());
    }

    /**
     * Sets the description of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Marks the Schedule that we are building as done.
     */
    public ScheduleBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the timeFrom of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    /**
     * Sets the timeTo of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    /**
     * Sets the recurType of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withRecurType(String recurType) {
        this.recurType = recurType;
        return this;
    }

    /**
     * Sets the recurDate of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withRecurDate(String recurDate) {
        this.recurDate = recurDate;
        return this;
    }

    public Schedule build() {
        return new Schedule(description, date, timeFrom, timeTo, isDone, tags, recurType, recurDate);
    }
}
