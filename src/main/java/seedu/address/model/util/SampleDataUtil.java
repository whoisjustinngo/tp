package seedu.address.model.util;

import static seedu.address.logic.parser.AddCustomGoalCommandParser.DATE_FORMAT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Policy;
import seedu.address.model.person.Relationship;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Relationship("client"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("neighbours")),
            new Person(new Name("Bernice Yu"), new Relationship("client"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues"),
                    getPolicySet(
                            new Policy(new Name("Prudential"), 2231, new Name("Hospitalisation"), 100)),
                    Status.valueOf("FRESH"),
                    "High Net Worth Individual"),
            new Person(new Name("Charlotte Oliveiro"), new Relationship("client"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("relatives")),
            new Person(new Name("David Li"), new Relationship("friend"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("classmates")),
            new Person(new Name("Irfan Ibrahim"), new Relationship("friend"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Relationship("friend"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues", "AIG"))
        };
    }

    public static Schedule[] getSampleSchedules() {
        return new Schedule[] {
            new Schedule("CS2103 Lecture", "16-12-2021", "1600", "1800", false, getTagSet("school"), "N", ""),
            new Schedule("CS2103 Lecture", "23-12-2021", "1600", "1800", false, getTagSet("school"), "N", ""),
            new Schedule("CS2103 Lecture", "30-12-2021", "1600", "1800", false, getTagSet("school"), "N", ""),
            new Schedule("CS2100 Lab", "17-12-2021", "1300", "1400", false, getTagSet("school"), "N", ""),
            new Schedule("CS2100 Lab", "24-12-2021", "1300", "1400", false, getTagSet("school"), "N", ""),
            new Schedule("First Meeting with Carol", "18-12-2021", "1600", "1800",
                    false, getTagSet("work"), "N", ""),
        };
    }

    public static Todo[] getSampleTodos() {
        return new Todo[] {
            new Todo("read up on cryptocurrencies", getTagSet("finance", "urgent"), true),
            new Todo("read book : The Intelligent Investor", getTagSet("finance")),
            new Todo("read book : Steve Jobs biography", getTagSet("leisure"))
        };
    }

    public static CustomGoal[] getSampleCustomGoals() {
        return new CustomGoal[] {
                new CustomGoal("earn $2000 in commissions", 2000,
                        LocalDate.parse("31-12-2021", DateTimeFormatter.ofPattern(DATE_FORMAT)), LocalTime.MAX)
                        .updateProgress(700),
                new CustomGoal("call 30 clients", 30,
                        LocalDate.parse("31-12-2021", DateTimeFormatter.ofPattern(DATE_FORMAT)), LocalTime.MAX)
                        .updateProgress(31),
                new CustomGoal("reach net worth $50000", 50000,
                        LocalDate.parse("10-10-2021", DateTimeFormatter.ofPattern(DATE_FORMAT)), LocalTime.MAX)
                        .updateProgress(25000),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Schedule sampleSchedule : getSampleSchedules()) {
            sampleAb.addSchedule(sampleSchedule);
        }
        for (Todo sampleTodo : getSampleTodos()) {
            sampleAb.addTodo(sampleTodo);
        }
        for (CustomGoal sampleCustomGoal : getSampleCustomGoals()) {
            sampleAb.addCustomGoal(sampleCustomGoal);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set of policies containing the list of policies given.
     */
    public static Set<Policy> getPolicySet(Policy... policies) {
        return Arrays.stream(policies)
                .collect(Collectors.toSet());
    }

}
