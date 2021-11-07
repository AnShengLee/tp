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
        double intermediateValue = Math.round(value * 100);
        double roundedValue = intermediateValue / 100;
        String displayValue = Double.toString(roundedValue);
        int decimalPointIndex = displayValue.indexOf(".");
        if (displayValue.substring(decimalPointIndex).length() < 3) {
            displayValue += "0";
        }
        return displayValue;
    }

    /**
     * Abstract method to convert the BudgetReminder object to a string.
     * @return String of the BudgetReminder object
     */
    @Override
    public abstract String toString();
}
