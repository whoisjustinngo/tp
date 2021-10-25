package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Set;

public class ClientStub extends Person {
    
    private final Status clientStatus;
    
    public ClientStub(Name name, Relationship relationship, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, relationship, phone, email, address, tags);
        this.clientStatus = Status.FRESH;
    }

    public Status getClientStatus() {
        return clientStatus;
    }
}
