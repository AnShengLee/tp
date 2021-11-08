package seedu.exceptions;

/**
 * Thrown when the user attempts to add in an input that contains a number that does not match the intended 
 * requirements.
 */
public class InvalidAmountException extends InputException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
