package seedu.exceptions;

/**
 * Thrown when the supposed ExpenseCategory given is not supported / recognised.
 */
public class InvalidExpenseCategoryException extends InputException {
    public InvalidExpenseCategoryException(String message) {
        super(message);
    }
}
