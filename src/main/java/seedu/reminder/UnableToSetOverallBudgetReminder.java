package seedu.reminder;

/**
 * Class representing the reminder message when the overall budget cannot be set.
 */
public class UnableToSetOverallBudgetReminder extends BudgetReminder {
    private double totalBudget;

    /**
     * Constructor initializing message with all relevant fields.
     * @param budgetName Name of the budget
     * @param budgetLimit Limit of the budget
     * @param totalBudget Sum of all sub-budgets and expenses
     */
    public UnableToSetOverallBudgetReminder(String budgetName, double budgetLimit, double totalBudget) {
        this.month = null;
        this.budgetName = budgetName;
        this.currBudgetAmount = 0;
        this.budgetLimit = budgetLimit;
        this.totalBudget = totalBudget;
    }

    /**
     * Returns the reminder as a string.
     * @return Reminder as a string.
     */
    @Override
    public String toString() {
        return "OVERALL budget must be greater than all sub-budgets and spending!"
                + "\nCurrently sum of all sub-budgets/spending: $" + roundTwoDecimalPlace(totalBudget);
    }
}
