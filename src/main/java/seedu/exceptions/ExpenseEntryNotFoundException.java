package seedu.exceptions;

/**
 * Thrown when the index given by the user that corresponds to the index of an expense entry does not exist.
 */
public class ExpenseEntryNotFoundException extends Exception {
    public ExpenseEntryNotFoundException(String message) {
        super(message);
    }
}
