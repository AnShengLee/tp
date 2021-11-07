package seedu.reminder;

/**
 * Class representing the reminder message when a budget is successfully set.
 */
public class BudgetSetReminder extends BudgetReminder {

    /**
     * Constructor initializing the month, name, current amount and the limit of the budget.
     * @param budgetName Name of the budget that was set
     * @param budgetLimit Limit of the budget that was set
     */
    public BudgetSetReminder(String budgetName, double budgetLimit) {
        this.month = null;
        this.budgetName = budgetName;
        this.currBudgetAmount = 0;
        this.budgetLimit = budgetLimit;
    }

    /**
     * Returns the reminder as a string.
     * @return Reminder as a string.
     */
    @Override
    public String toString() {
        return budgetName + " budget set to $" + roundTwoDecimalPlace(budgetLimit);
    }
}
