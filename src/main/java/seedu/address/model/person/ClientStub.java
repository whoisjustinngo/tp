package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class ClientStub extends Person {

    private final StatusStub clientStatus;

    /**
     * Stub for client class which inherits from person.
     */
    public ClientStub(Name name, Relationship relationship, Phone phone, Email email, Address address, Set<Tag> tags,
        StatusStub status) {
        super(name, relationship, phone, email, address, tags);
        this.clientStatus = status;
    }

    public ClientState getClientState() {
        return this.clientStatus.getState();
    }

    public LocalDateTime getLastUpdated() {
        return this.clientStatus.getLastUpdated();
    }
}
