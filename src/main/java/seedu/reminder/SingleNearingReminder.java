package seedu.reminder;

import java.time.LocalDate;

public class SingleNearingReminder extends BudgetReminder {

    public SingleNearingReminder(String month, String budgetName, double currBudgetAmount, double budgetLimit) {
        this.month = month;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = budgetLimit;
    }

    @Override
    public String toString() {
        return "You are almost reaching the " + month + " " + budgetName + " budget: $"
                + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                + "\nConsider readjusting your " + month + " " + budgetName + " budget!";
    }
}
