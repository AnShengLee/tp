package seedu.reminder;

public class DoubleExceededBudgetReminder extends DoubleReminder {

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
