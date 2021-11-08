package seedu.exceptions;

/**
 * Thrown when user attempts to add an expense to the FinancialManager that causes the total sum of expenses to be more
 * than 100000000000.
 */
public class ExpenseOverflowException extends Exception {
    public ExpenseOverflowException(String message) {
        super(message);
    }
}
