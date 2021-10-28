package seedu.address.storage;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Schedule;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents a Schedule Storage, exclusively for ics files
 */
public interface ScheduleStorage {
    List<Schedule> importSchedule(File file) throws IOException, ParseException;
}
