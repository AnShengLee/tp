package seedu.reminder;

import seedu.utility.BudgetManager;

public class DoubleNearingBudgetReminder extends DoubleReminder {

    public DoubleNearingBudgetReminder(String month, String budgetName, double currBudgetAmount, double budgetLimit,
                                       double currOverallAmount, double overallLimit, double totalBudget) {
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
        double extendedBudget = overallLimit - totalBudget + budgetLimit;
        if (extendedBudget == budgetLimit) {
            return "Nearing both " + month + " " + budgetName + " budget ($"
                    + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                    + ") and " + month + " OVERALL budget ($" + roundTwoDecimalPlace(currOverallAmount)
                    + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                    + "\nConsider adjusting your OVERALL budget before adjusting your " + budgetName + " budget!"
                    + "\nCurrently you cannot extend your " + budgetName
                    + " budget without first extending your OVERALL budget!";
        } else {
            return "Nearing both " + month + " " + budgetName + " budget ($"
                    + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                    + ") and " + month + " OVERALL budget ($" + roundTwoDecimalPlace(currOverallAmount)
                    + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                    + "\nConsider adjusting your OVERALL budget before adjusting your " + budgetName + " budget!"
                    + "\nCurrently you can extend your " + budgetName
                    + " budget up until $" + roundTwoDecimalPlace(extendedBudget) + "!";
        }

    }

}
