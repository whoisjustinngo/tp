package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customGoal.CustomGoal;
import seedu.address.model.todo.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Jackson-friendly version of {@link seedu.address.model.customGoal.CustomGoal}.
 */
class JsonAdaptedCustomGoal {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "CustomGoal has fields missing!";

    private final String goalDescription;
    private final float goal;
    private final float progress;
    private final String dateAdded;
    private final String endDate;
    private final String timeAdded;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedCustomGoal} with the given details.
     */
    @JsonCreator
    public JsonAdaptedCustomGoal(@JsonProperty("goalDescription") String goalDescription,
                                 @JsonProperty("goal") float goal,
                                 @JsonProperty("progress") float progress,
                                 @JsonProperty("dateAdded") String dateAdded,
                                 @JsonProperty("endDate") String endDate,
                                 @JsonProperty("timeAdded") String timeAdded,
                                 @JsonProperty("endTime") String endTime) {
        this.goalDescription = goalDescription;
        this.goal = goal;
        this.progress = progress;
        this.dateAdded = dateAdded;
        this.timeAdded = timeAdded;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code CustomGoal} into this class for Jackson use.
     */
    public JsonAdaptedCustomGoal(CustomGoal source) {
        goalDescription = source.getDescription();
        goal = source.getGoal();
        progress = source.getProgress();
        dateAdded = source.getDateAddedValue();
        timeAdded = source.getTimeAddedValue();
        endDate = source.getEndDateValue();
        endTime = source.getEndTimeValue();
    }

    /**
     * Converts this Jackson-friendly adapted CustomGoal object into the model's {@code CustomGoal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted CustomGoal.
     */
    public CustomGoal toModelType() throws IllegalValueException {
        if (goalDescription == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        final String modelGoalDescription = goalDescription;
        final float modelGoal = goal;
        final float modelProgress = progress;
        final String modelDateAdded = dateAdded;
        final String modelEndDate = endDate;
        final String modelTimeAdded = timeAdded;
        final String modelEndTime = endTime;
        return new CustomGoal(modelGoalDescription
                , modelGoal
                , modelProgress
                , modelDateAdded
                , modelTimeAdded
                , modelEndDate
                , modelEndTime);
    }

}
