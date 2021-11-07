package seedu.reminder;

import java.time.LocalDate;

/**
 * Class representing the reminder message when a single budget is nearing its budget limit.
 */
public class SingleNearingReminder extends BudgetReminder {

    /**
     * Constructor initializing the reminder with all relevant fields.
     * @param month Current month
     * @param budgetName Name of budget that was exceeded
     * @param currBudgetAmount Current amount in the budget
     * @param budgetLimit Current limit of the budget
     */
    public SingleNearingReminder(String month, String budgetName, double currBudgetAmount, double budgetLimit) {
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
        return "You are almost reaching the " + month + " " + budgetName + " budget: $"
                + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                + "\nConsider readjusting your " + month + " " + budgetName + " budget!";
    }
}
