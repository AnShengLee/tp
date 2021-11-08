package seedu.exceptions;

/**
 * Thrown when the supposed index cannot be converted from String to int. Or when the index is out of expected range.  
 */
public class InvalidIndexException extends InputException {
    public InvalidIndexException(String message) {
        super(message);
    }
}
