package seedu.exceptions;

/**
 * Thrown when entry amount given is less than 0.05.
 */
public class EntryAmountBelowMinException extends InputException {
    public EntryAmountBelowMinException(String message) {
        super(message);
    }
}
