package seedu.exceptions;

/**
 * Thrown when user attempts to add an income to the FinancialManager that causes the total sum of incomes to be more
 * than 100000000000.
 */
public class IncomeOverflowException extends Exception {
    public IncomeOverflowException(String message) {
        super(message);
    }
}
