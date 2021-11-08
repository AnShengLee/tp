package seedu.reminder;

/**
 * Abstract class representing the reminder messages returned in the BudgetManager class.
 */
public abstract class BudgetReminder {
    protected String month;
    protected String budgetName;
    protected double currBudgetAmount;
    protected double budgetLimit;

    /**
     * Returns a string of a value rounded to two decimal places.
     * @param value Double value to be rounded
     * @return String of a value with two decimal places
     */
    protected String roundTwoDecimalPlace(double value) {
        String displayValue = String.format("%.2f", value);
        return displayValue;
    }

    /**
     * Abstract method to convert the BudgetReminder object to a string.
     * @return String of the BudgetReminder object
     */
    @Override
    public abstract String toString();
}
