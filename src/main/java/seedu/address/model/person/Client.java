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
    private final String notes;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Relationship relationship, Phone phone, Email email, Address address,
                  Set<Tag> tags) {
        super(name, relationship, phone, email, address, tags);
        this.policies.addAll(policies);
        status = Status.FRESH;
        notes = "";
        System.out.println("Client has been created");
    }

    /**
     * Overloaded function adding clients with existing policies
     */
    public Client(Name name, Relationship relationship, Phone phone, Email email, Address address,
                  Set<Tag> tags, Set<Policy> policies) {
        super(name, relationship, phone, email, address, tags);
        this.policies.addAll(policies);
        status = Status.FRESH;
        notes = "";
    }

    public Set<Policy> getPolicies() {
        return policies;
    }

    public Status getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }
}
