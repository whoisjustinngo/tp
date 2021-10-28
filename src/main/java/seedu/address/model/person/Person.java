package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Relationship relationship;
    private final Set<Tag> tags = new HashSet<>();

    // Client information
    private final Set<Policy> policies = new HashSet<>();

    private final Status status;
    private final String notes;
    private final LocalDateTime lastUpdated;

    /**
     * Every field must be present without client details.
     */
    public Person(Name name, Relationship relationship, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.relationship = relationship;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        status = Status.FRESH;
        notes = "";
        lastUpdated = LocalDateTime.now();
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Relationship relationship, Phone phone, Email email, Address address, Set<Tag> tags,
                  Set<Policy> policies, Status status, String notes) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.relationship = relationship;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.status = status;
        this.notes = notes;
        this.policies.addAll(policies);
        lastUpdated = LocalDateTime.now();
    }
    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public Status getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable policy set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Policy> getPolicies() {
        return Collections.unmodifiableSet(policies);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getRelationship().equals(getRelationship())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Relationship: ")
                .append(getRelationship())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
