package seedu.reminder;

public class UnableToSetOverallBudgetReminder extends BudgetReminder {
    private double totalBudget;

    public UnableToSetOverallBudgetReminder(String budgetName, double budgetLimit, double totalBudget) {
        this.month = null;
        this.budgetName = budgetName;
        this.currBudgetAmount = 0;
        this.budgetLimit = budgetLimit;
        this.totalBudget = totalBudget;
    }

    @Override
    public String toString() {
        return "OVERALL budget must be greater than all sub-budgets and spending!"
                + "\nCurrently sum of all sub-budgets/spending: $" + roundTwoDecimalPlace(totalBudget);
    }
}
