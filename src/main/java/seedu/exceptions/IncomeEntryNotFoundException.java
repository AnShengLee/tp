package seedu.exceptions;

/**
 * Thrown when the index given by the user that corresponds to the index of an income entry does not exist.
 */
public class IncomeEntryNotFoundException extends Exception {
    public IncomeEntryNotFoundException(String message) {
        super(message);
    }
}
