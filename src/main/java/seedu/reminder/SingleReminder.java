package seedu.reminder;

/**
 * Class representing the reminder message when an expense is added.
 */
public class SingleReminder extends BudgetReminder {

    /**
     * Constructor initializing the reminder with all relevant fields.
     * @param month Current month
     * @param budgetName Name of budget that was exceeded
     * @param currBudgetAmount Current amount in the budget
     * @param budgetLimit Current limit of the budget
     */
    public SingleReminder(String month, String budgetName, double currBudgetAmount, double budgetLimit) {
        this.month = month;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = budgetLimit;
    }

    /**
     * Returns the reminder as a string.
     * @return Reminder as a string
     */
    @Override
    public String toString() {
        return "Current " + month + " " + budgetName + " budget: $"
                + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit);
    }

}
