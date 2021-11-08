package seedu.exceptions;

/**
 * Thrown if required Category parameter is added by user.
 */
public class BlankCategoryException extends InputException {
    public BlankCategoryException(String message) {
        super(message);
    }
}
