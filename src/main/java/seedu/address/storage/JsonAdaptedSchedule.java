package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Schedule;
import seedu.address.model.tag.Tag;

public class JsonAdaptedSchedule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String description;
    private final String date;
    private final String from;
    private final String to;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("description") String description, @JsonProperty("date") String date,
            @JsonProperty("from") String from, @JsonProperty("to") String to,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.description = description;
        this.date = date;
        this.from = from;
        this.to = to;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        this.description = source.getDescription();
        this.date = source.getDate();
        this.from = String.valueOf(source.getTimeFrom());
        this.to = String.valueOf(source.getTimeTo());
        tagged.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's
     * {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
        final List<Tag> scheduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            scheduleTags.add(tag.toModelType());
        }
        if (this.description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        if (this.date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }
        if (this.from == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "from"));
        }
        if (this.to == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "to"));
        }
        final String modelDescription = this.description;
        final String modelDate = this.date;
        final String modelFrom = this.from;
        final String modelTo = this.to;
        final Set<Tag> modelTags = new HashSet<>(scheduleTags);

        return new Schedule(modelDescription, modelDate, modelFrom, modelTo, false, modelTags);
    }
}
