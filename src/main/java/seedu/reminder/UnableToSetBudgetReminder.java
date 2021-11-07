package seedu.reminder;

/**
 * Class representing the reminder message when budget cannot be set.
 */
public class UnableToSetBudgetReminder extends BudgetReminder {
    private double overallLimit;
    private double newBudgetLimit;
    private double newTotalBudget;

    /**
     * Constructor initializing message with all relevant fields.
     * @param budgetName Name of the budget
     * @param currBudgetAmount Current amount in the budget
     * @param overallLimit Current limit of the budget
     * @param newBudgetLimit New limit to be set
     * @param newTotalBudget New sum of all sub-budgets and expenses if new limit is successfully set
     */
    public UnableToSetBudgetReminder(String budgetName, double currBudgetAmount, double overallLimit,
                                     double newBudgetLimit, double newTotalBudget) {
        this.month = null;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = 0;
        this.overallLimit = overallLimit;
        this.newBudgetLimit = newBudgetLimit;
        this.newTotalBudget = newTotalBudget;
    }

    /**
     * Returns the reminder as a string.
     * @return The reminder as a string.
     */
    @Override
    public String toString() {
        if (newBudgetLimit < currBudgetAmount && newTotalBudget > overallLimit) {
            return budgetName + " budget must be greater than current " + budgetName + " spending of $"
                    + roundTwoDecimalPlace(currBudgetAmount) + "."
                    + "\nWith new " + budgetName
                    + " budget, total of all sub-budgets/spending will exceed OVERALL budget ($"
                    + roundTwoDecimalPlace(newTotalBudget) + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                    + "\nIncrease your OVERALL budget first!";
        } else if (newBudgetLimit < currBudgetAmount) {
            return budgetName + " budget must be greater than current " + budgetName + " spending of $"
                    + roundTwoDecimalPlace(currBudgetAmount) + ".";
        } else {
            return "With new " + budgetName
                    + " budget, total of all sub-budgets/spending will exceed OVERALL budget ($"
                    + roundTwoDecimalPlace(newTotalBudget) + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                    + "\nIncrease your OVERALL budget first!";
        }
    }
}
