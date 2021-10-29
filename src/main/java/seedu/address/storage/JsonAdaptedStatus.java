package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Status;

/**
 * Jackson-friendly version of {@link Status}.
 */
class JsonAdaptedStatus {

    private final String status;

    /**
     * Constructs a {@code JsonAdaptedStatus} with the given {@code status}.
     */
    @JsonCreator
    public JsonAdaptedStatus(String status) {
        this.status = status;
    }

    /**
     * Converts a given {@code Status} into this class for Jackson use.
     */
    public JsonAdaptedStatus(Status source) {
        status = source.toString();
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * Converts this Jackson-friendly adapted status object into the model's {@code Status} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted status.
     */
    public Status toModelType() throws IllegalValueException {
        if (!Status.contains(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        return Status.valueOf(status);
    }

}
