package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.Set;

public class ClientStub extends Person {
    
    private final StatusStub clientStatus;
    
    public ClientStub(Name name, Relationship relationship, Phone phone, Email email, Address address, Set<Tag> tags,
     StatusStub status) {
        super(name, relationship, phone, email, address, tags);
        this.clientStatus = status;
    }

    public ClientState getClientState() {
        return this.clientStatus.getState();
    }
    
    public LocalDateTime getLastUpdated(){
        return this.clientStatus.getLastUpdated();
    }
}
