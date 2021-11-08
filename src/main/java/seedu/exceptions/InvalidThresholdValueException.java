package seedu.exceptions;

/**
 * Thrown when the threshold value given cannot be parsed to a double, or when it is not between 0 and 1 exclusive, 
 * or when it is not in 2 decimal places.
 */
public class InvalidThresholdValueException extends InputException {
    public InvalidThresholdValueException(String message) {
        super(message);
    }
}
