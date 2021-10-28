package seedu.address.logic;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.RRule;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
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

import static seedu.address.logic.commands.AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE;

public class ImportCalendar {

    private ImportCalendar() { // should not be instantiated

    }
    /**
     * Generates Schedule based on ics file definition.
     * The .ics file needs to follow the following shape (download from NUSMods)
     * - DTSTART
     * - DTEND
     * - SUMMARY
     *
     * Optional:
     * - RRULE
     *   - FREQ [int]
     *
     * @param openedFile the file that is to be opened
     * @throws ParseException
     * @throws java.text.ParseException
     * @throws IOException
     * @throws CommandException
     */
    public static void generateSchedules(File openedFile, Model model)
            throws ParseException, java.text.ParseException, IOException, CommandException {
        // get ics
        FileInputStream fileInputStream = new FileInputStream(openedFile);
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
            Component component = (Component) i.next();
            String description = component.getProperty(Property.SUMMARY).getValue();
            if(description == null) {
                throw new ParseException("Error! Description for Schedule not found!");
            }

            String dtStartString = component.getProperty(Property.DTSTART).getValue();
            if(dtStartString == null) {
                throw new ParseException("Error! Description for Schedule not found!");
            }
            DateTime dtStart = new DateTime(dtStartString);

            String dtEndString = component.getProperty(Property.DTEND).getValue();
            if(dtEndString == null) {
                throw new ParseException("Error! Description for Schedule not found!");
            }
            DateTime dtEnd = new DateTime(dtEndString);

            String date = Instant.ofEpochMilli(dtStart.getTime()).atZone(ZoneId.systemDefault())
                    .toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
            String from = Instant.ofEpochMilli(dtStart.getTime()).atZone(ZoneId.systemDefault())
                    .toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
            String to = Instant.ofEpochMilli(dtEnd.getTime()).atZone(ZoneId.systemDefault())
                    .toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));

            RRule repeatRule = (RRule) component.getProperty(Property.RRULE);
            Integer repeatTimes;
            if(repeatRule == null) {
                repeatTimes = 1;
            } else {
                repeatTimes = repeatRule.getRecur().getCount();
            }

            try {
                Schedule schedule = new Schedule(description, date, from, to, false, new HashSet<>());;
                List<Schedule> validSchedules = ImportCalendar.createSchedule(schedule, repeatTimes, model);
                allSchedules.addAll(validSchedules);
            } catch (InvalidTimeException e) {
                throw new ParseException(e.getMsg());
            }
        }
        // if reached here, can safely add as there are no exceptions
        allSchedules.forEach(schedule -> model.addSchedule(schedule));
    }


    private static List<Schedule> createSchedule(Schedule schedule, Integer repeatTimes, Model model) throws CommandException {
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
        for(int ind = 0; ind < 1; ind++) {
            if (model.hasScheduleClash(schedule)) {
                throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            if (model.hasSchedule(schedule)) {
                throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            schedulesList.add(schedule);
            // TODO: increment the schedule by a week
        }

        return schedulesList;
    }
}
