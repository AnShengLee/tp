package seedu.reminder;

public class UnableToSetBudgetReminder extends BudgetReminder {
    private double overallLimit;
    private double newBudgetLimit;
    private double newTotalBudget;

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

    @Override
    public String toString() {
        if (newBudgetLimit < currBudgetAmount & newTotalBudget > overallLimit) {
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
