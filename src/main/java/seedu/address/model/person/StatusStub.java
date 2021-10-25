package seedu.address.model.person;

import java.time.LocalDateTime;

public class StatusStub {

    private final ClientState state;
    private final LocalDateTime lastUpdated;

    StatusStub(ClientState state) {
        this.state = state;
        this.lastUpdated = LocalDateTime.now();
    }

    public ClientState getState() {
        return state;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
