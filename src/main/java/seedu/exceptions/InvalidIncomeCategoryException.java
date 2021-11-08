package seedu.exceptions;

/**
 * Thrown when the supposed IncomeCategory given is not supported / recognised.
 */
public class InvalidIncomeCategoryException extends InputException { 
    public InvalidIncomeCategoryException(String message) {
        super(message);
    }
}
