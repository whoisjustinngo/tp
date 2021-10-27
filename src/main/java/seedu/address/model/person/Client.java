package seedu.address.model.person;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book. Contains extra client related data
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client extends Person {
    private final Set<Policy> policies = new HashSet<>();
    private final Status status;
    private String notes;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Relationship relationship, Phone phone, Email email, Address address,
                  Set<Tag> tags, Set<Policy> policies) {
        super(name, relationship, phone, email, address, tags);
        this.policies.addAll(policies);
        status = Status.FRESH;
        notes = "";
    }

    /**
     * Set Client's notes
     * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Set Client's notes
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Add to policy to client's portfolio
     * @param policy
     */
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    /**
     * Remove to policy to client's portfolio from index
     * @param policy
     */
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }
}
