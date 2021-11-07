package seedu.reminder;

public class BudgetSetReminder extends BudgetReminder {

    public BudgetSetReminder(String budgetName, double budgetLimit) {
        this.month = null;
        this.budgetName = budgetName;
        this.currBudgetAmount = 0;
        this.budgetLimit = budgetLimit;
    }

    @Override
    public String toString() {
        return budgetName + " budget set to $" + roundTwoDecimalPlace(budgetLimit);
    }
}
