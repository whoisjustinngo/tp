package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.ExDate;
import net.fortuna.ical4j.model.property.RRule;
import seedu.address.model.event.Schedule;


public class IcsAdaptedSchedule {
    private Component schedule;
    private int repeatNumberOfTimes = 1;

    // Logically, these are public holidays
    private List<LocalDate> excludedDates = new ArrayList<>();

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
        Property summary = this.schedule.getProperty(Property.SUMMARY);
        Property dtstartProperty = this.schedule.getProperty(Property.DTSTART);
        Property dtendProperty = this.schedule.getProperty(Property.DTEND);
        requireAllNonNull(summary, dtstartProperty, dtendProperty);

        String description = summary.getValue();
        String dtStartString = dtstartProperty.getValue();
        DateTime dtStart = new DateTime(dtStartString);
        String dtEndString = dtendProperty.getValue();
        DateTime dtEnd = new DateTime(dtEndString);
        requireAllNonNull(description, dtStartString, dtEndString);

        String date = Instant.ofEpochMilli(dtStart.getTime()).atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
        String from = Instant.ofEpochMilli(dtStart.getTime()).atZone(ZoneId.systemDefault())
                .toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
        String to = Instant.ofEpochMilli(dtEnd.getTime()).atZone(ZoneId.systemDefault())
                .toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));

        RRule repeatRule = (RRule) this.schedule.getProperty(Property.RRULE);

        List<ExDate> exDates = this.schedule.getProperties(Property.EXDATE);
        for (Iterator i = exDates.iterator(); i.hasNext();) {
            ExDate exDate = (ExDate) i.next();
            DateTime dtExDate = (new DateTime(exDate.getValue()));
            excludedDates.add(Instant.ofEpochMilli(dtExDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (repeatRule == null) {
            this.repeatNumberOfTimes = 1;
        } else {
            this.repeatNumberOfTimes = repeatRule.getRecur().getCount();
        }
        return new Schedule(description, date, from, to, false, new HashSet<>(),
                "N", "");
    }

    public int getRepeatNumberOfTimes() {
        return this.repeatNumberOfTimes;
    }

    /**
     * Checks if the schedule is to be excluded from the list.
     *
     * @param schedule
     * @return
     */
    public boolean isInExcluded(Schedule schedule) {
        return excludedDates.stream()
                .anyMatch(localDate -> localDate.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"))
                .equals(schedule.getDate()));
    }
}
