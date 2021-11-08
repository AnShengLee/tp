package seedu.exceptions;

/**
 * Thrown when user adds an entry that exceeds 1000000.
 */
public class EntryAmountExceedLimitException extends InputException {
    public EntryAmountExceedLimitException(String message) {
        super(message);
    }
}
