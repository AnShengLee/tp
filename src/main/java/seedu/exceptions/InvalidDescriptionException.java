package seedu.exceptions;

/**
 * Thrown when the supposed description is blank. 
 */
public class InvalidDescriptionException extends InputException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
