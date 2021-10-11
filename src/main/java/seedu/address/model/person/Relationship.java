package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a contact Relationship in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRelationship(String)}
 */
public class Relationship {

    public static final String MESSAGE_CONSTRAINTS = "Relationship should either be friend or client";

    /*
     * Either friend or client
     */
    public static final String VALIDATION_REGEX = "^(friend|client)$";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid contact relationship.
     */
    public Relationship(String address) {
        requireNonNull(address);
        checkArgument(isValidRelationship(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid contact relationship.
     */
    public static boolean isValidRelationship(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Relationship // instanceof handles nulls
                && value.equals(((Relationship) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
