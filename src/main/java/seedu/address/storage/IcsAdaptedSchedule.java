package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.RRule;
import seedu.address.model.event.Schedule;

public class IcsAdaptedSchedule {
    private Component schedule;
    private int repeatNumberOfTimes = 1;

    /**
     * Represents a schedule in ics.
     *
     * @param event a component that represents a schedule
     */
    public IcsAdaptedSchedule(Component event) {
        this.schedule = event;
    }

    /**
     * Converts Schedules as ics component into Schedule model.
     *
     * @return a Schedule object
     * @throws java.text.ParseException
     */
    public Schedule toModel() throws java.text.ParseException {
        String description = this.schedule.getProperty(Property.SUMMARY).getValue();
        String dtStartString = this.schedule.getProperty(Property.DTSTART).getValue();
        DateTime dtStart = new DateTime(dtStartString);
        String dtEndString = this.schedule.getProperty(Property.DTEND).getValue();
        DateTime dtEnd = new DateTime(dtEndString);
        requireAllNonNull(description, dtStartString, dtEndString);

        String date = Instant.ofEpochMilli(dtStart.getTime()).atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
        String from = Instant.ofEpochMilli(dtStart.getTime()).atZone(ZoneId.systemDefault())
                .toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
        String to = Instant.ofEpochMilli(dtEnd.getTime()).atZone(ZoneId.systemDefault())
                .toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));

        RRule repeatRule = (RRule) this.schedule.getProperty(Property.RRULE);
        if (repeatRule == null) {
            this.repeatNumberOfTimes = 1;
        } else {
            this.repeatNumberOfTimes = repeatRule.getRecur().getCount();
        }
        return new Schedule(description, date, from, to, false, new HashSet<>());
    }

    public int getRepeatNumberOfTimes() {
        return this.repeatNumberOfTimes;
    }
}
