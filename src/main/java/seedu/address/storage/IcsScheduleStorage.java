package seedu.address.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.exceptions.InvalidTimeException;
import seedu.address.model.tag.Tag;

public class IcsScheduleStorage implements ScheduleStorage {
    @Override
    public List<Schedule> importSchedule(File file) throws IOException, ParseException {
        // get ics
        FileInputStream fileInputStream = new FileInputStream(file);
        CalendarBuilder calendarBuilder = new CalendarBuilder();
        Calendar calendar; //ical4j Calendar object
        try {
            calendar = calendarBuilder.build(fileInputStream);
        } catch (ParserException e) {
            throw new ParseException("Calendar is not well formed!");
        }
        // create schedules
        List<Schedule> allSchedules = new ArrayList<>();
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            try {
                IcsAdaptedSchedule icsAdaptedSchedule = new IcsAdaptedSchedule((Component) i.next());
                List<Schedule> schedules = this.createSchedule(icsAdaptedSchedule.toModel(),
                        icsAdaptedSchedule.getRepeatNumberOfTimes());
                allSchedules.addAll(schedules);
            } catch (InvalidTimeException | java.text.ParseException e) {
                throw new ParseException(e.getMessage());
            }
        }
        return allSchedules;
    }

    private List<Schedule> createSchedule(Schedule schedule, Integer repeatTimes) {
        List<Schedule> schedulesList = new ArrayList<>();
        if (repeatTimes == 1) {
            Set<Tag> tagList = new HashSet<>();
            tagList.add(new Tag("exam"));
            schedule = new Schedule(schedule.getDescription(), schedule.getDate(),
                    String.valueOf(schedule.getTimeFrom()), String.valueOf(schedule.getTimeTo()), schedule.isDone(),
                    tagList);
        } else {
            // TODO: reassign generateRecurrence to what is specified in repeatRule
            // TODO: reading weeks
        }
        for (int ind = 0; ind < 1; ind++) {
            schedulesList.add(schedule);
            // TODO: increment the schedule by a week
        }

        return schedulesList;
    }
}
