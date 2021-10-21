package seedu.address.model.event.exceptions;

public class InvalidTimeException extends RuntimeException {
    private final String msg;

    /**
     * Exception for user who have keyed in invalid time format.
     *
     * @param msg is the error message.
     */
    public InvalidTimeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
