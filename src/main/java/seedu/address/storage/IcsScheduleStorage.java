package seedu.address.storage;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.RRule;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Schedule;
import seedu.address.model.event.exceptions.InvalidTimeException;
import seedu.address.model.tag.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE;

public class IcsScheduleStorage implements ScheduleStorage {
    @Override
    public List<Schedule> importSchedule(File file) throws IOException, ParseException{
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
                List<Schedule> schedules = this.createSchedule(icsAdaptedSchedule.ToModel(),
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
