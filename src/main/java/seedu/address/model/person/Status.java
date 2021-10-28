package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a client/lead's status
 */
public enum Status {
    FRESH,
    APPROACH,
    QUOTED,
    CLOSED,
    LOST;

    public static final String MESSAGE_CONSTRAINTS = "Value is not a valid status!";
    private static final Set<String> values = new HashSet<String>(Status.values().length);

    static {
        for (Status f: Status.values()) {
            values.add(f.name());
        }
    }

    public static boolean contains(String value) {
        return values.contains(value);
    }
}
