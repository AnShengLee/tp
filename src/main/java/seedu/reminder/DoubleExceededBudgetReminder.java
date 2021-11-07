package seedu.reminder;

/**
 * Class representing the reminder message when both the sub-budget and the overall budget are exceeded.
 */
public class DoubleExceededBudgetReminder extends DoubleReminder {

    /**
     * Constructor initializing the reminder message with all relevant fields.
     * @param month Current month
     * @param budgetName Name of the budget that was exceeded
     * @param currBudgetAmount Current amount in the exceeded budget
     * @param budgetLimit Current limit of the exceeded budget
     * @param currOverallAmount Current amount in the overall budget
     * @param overallLimit Current overall budget limit
     * @param totalBudget Sum of all sub-budgets and expenses
     */
    public DoubleExceededBudgetReminder(String month, String budgetName, double currBudgetAmount,
                                                double budgetLimit, double currOverallAmount,
                                                double overallLimit, double totalBudget) {
        this.month = month;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = budgetLimit;
        this.currOverallAmount = currOverallAmount;
        this.overallLimit = overallLimit;
        this.totalBudget = totalBudget;
    }

    /**
     * Returns the reminder as a string.
     * @return Reminder as a string.
     */
    @Override
    public String toString() {
        return "Exceeded both " + month + " " + budgetName + " budget ($"
                + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                + ") and " + month + " OVERALL budget ($" + roundTwoDecimalPlace(currOverallAmount)
                + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                + "\nConsider adjusting your OVERALL budget to $" + roundTwoDecimalPlace(totalBudget)
                + " before adjusting your " + budgetName + " budget!"
                + "\nCurrently you cannot extend your " + budgetName
                + " budget without first extending your OVERALL budget!";
    }
}
