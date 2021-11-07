package seedu.reminder;

public class SingleExceededReminder extends BudgetReminder {

    public SingleExceededReminder(String month, String budgetName, double currBudgetAmount, double budgetLimit) {
        this.month = month;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = budgetLimit;
    }

    @Override
    public String toString() {
        return "You have exceeded the " + month + " " + budgetName + " budget: $"
                + roundTwoDecimalPlace(currBudgetAmount) + "/" + "$" + roundTwoDecimalPlace(budgetLimit)
                + "\nConsider readjusting your " + month + " " + budgetName + " budget!";
    }
}
