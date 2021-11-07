package seedu.reminder;

public abstract class BudgetReminder {
    protected String month;
    protected String budgetName;
    protected double currBudgetAmount;
    protected double budgetLimit;

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

    @Override
    public abstract String toString();
}
