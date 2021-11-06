package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_READ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEARNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LEISURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEFROM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMETO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Schedule;

/**
 * A utility class containing a list of {@code Schedule} objects to be used in
 * tests.
 */
public class TypicalSchedule {

    public static final Schedule MEETING_1 = new ScheduleBuilder().withDescription("meeting1").withDate("12-8-2022")
            .withTags("legal").build();
    public static final Schedule MEETING_2 = new ScheduleBuilder().withDescription("meeting2").withDate("12-9-2022")
            .withTags("legal").build();
    public static final Schedule MEETING_3 = new ScheduleBuilder().withDescription("meeting3").withDate("13-9-2022")
            .withTags("legal").build();
    public static final Schedule MEETING_4 = new ScheduleBuilder().withDescription("meeting4").withDate("14-9-2022")
            .withTags("legal").build();

    // Manually added - Schedule's details found in {@code CommandTestUtil}
    public static final Schedule READ = new ScheduleBuilder().withDescription(VALID_DESCRIPTION_READ)
            .withDate(VALID_DATE).withTags(VALID_TAG_LEARNING).build();
    public static final Schedule TRAVEL = new ScheduleBuilder().withDescription(VALID_DESCRIPTION_TRAVEL)
            .withTimeFrom(VALID_TIMEFROM).withTimeTo(VALID_TIMETO).withTags(VALID_TAG_LEISURE).build();;

    private TypicalSchedule() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical schedules.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Schedule schedule : getTypicalSchedules()) {
            ab.addSchedule(schedule);
        }
        return ab;
    }

    public static List<Schedule> getTypicalSchedules() {
        return new ArrayList<>(Arrays.asList(MEETING_1, MEETING_2, MEETING_3, MEETING_4, READ, TRAVEL));
    }
}
