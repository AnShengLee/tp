package seedu.exceptions;

/**
 * This is the parent class for all user input related exception.
 */
public class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }
}
