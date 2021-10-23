package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.event.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.todo.Todo;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_GOAL = "Custom Goals list contains duplicate custom goal(s).";
    public static final String MESSAGE_DUPLICATE_TODO = "Todos list contains duplicate todo(s).";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "Schedule list contains duplicate schedule(s).";

    private final List<JsonAdaptedCustomGoal> customGoals = new ArrayList<>();
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTodo> todos = new ArrayList<>();
    private final List<JsonAdaptedSchedule> schedules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and todos.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("customGoals") List<JsonAdaptedCustomGoal> customGoals,
                                       @JsonProperty("todos") List<JsonAdaptedTodo> todos,
                                       @JsonProperty("schedules") List<JsonAdaptedSchedule> schedules) {
        this.customGoals.addAll(customGoals);
        this.persons.addAll(persons);
        this.todos.addAll(todos);
        this.schedules.addAll(schedules);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        customGoals.addAll(source.getCustomGoalList().stream().map(JsonAdaptedCustomGoal::new).collect(Collectors.toList()));
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        todos.addAll(source.getTodoList().stream().map(JsonAdaptedTodo::new).collect(Collectors.toList()));
        schedules.addAll(source.getScheduleList().stream().map(JsonAdaptedSchedule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedCustomGoal jsonAdaptedCustomGoal : customGoals) {
            CustomGoal customGoal = jsonAdaptedCustomGoal.toModelType();
            if (addressBook.hasCustomGoal(customGoal)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GOAL);
            }
            addressBook.addCustomGoal(customGoal);
        }

        for (JsonAdaptedTodo jsonAdaptedTodo : todos) {
            Todo todo = jsonAdaptedTodo.toModelType();
            if (addressBook.hasTodo(todo)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TODO);
            }
            addressBook.addTodo(todo);
        }

        for (JsonAdaptedSchedule jsonAdaptedSchedule : schedules) {
            Schedule schedule = jsonAdaptedSchedule.toModelType();
            if (addressBook.hasSchedule(schedule)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            addressBook.addSchedule(schedule);
        }

        return addressBook;
    }

}
