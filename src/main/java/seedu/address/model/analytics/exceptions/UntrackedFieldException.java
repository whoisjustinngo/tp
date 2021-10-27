package seedu.address.model.analytics.exceptions;

public class UntrackedFieldException extends RuntimeException {

    public UntrackedFieldException() {
        super("This field is untracked");
    }
}
