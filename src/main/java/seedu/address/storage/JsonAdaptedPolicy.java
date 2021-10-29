package seedu.address.storage;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Policy;



/**
 * Jackson-friendly version of {@link seedu.address.model.person.Policy}.
 */
class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy has fields missing!";


    public final String name;
    public final String insurer;
    public final int number;
    public final float commission;


    /**
     * Constructs a {@code JsonAdaptedCustomGoal} with the given details.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("name") String name,
                                 @JsonProperty("insurer") String insurer,
                                 @JsonProperty("commission") float commission,
                                 @JsonProperty("number") int number) {
        this.name = name;
        this.insurer = insurer;
        this.commission = commission;
        this.number = number;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        name = source.getName().fullName;
        insurer = source.getInsurer().fullName;
        commission = source.getCommission().floatValue();
        number = source.getNumber();
    }

    /**
     * Converts this Jackson-friendly adapted CustomGoal object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted CustomGoal.
     */
    public Policy toModelType() throws IllegalValueException {
        if (name == null || insurer == null || Objects.isNull(commission) || Objects.isNull(number)) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        final Name name = new Name(this.name);
        final Name insurer = new Name(this.insurer);
        final float commission = this.commission;
        final int number = this.number;
        return new Policy(insurer, number, name, commission);
    }

}
