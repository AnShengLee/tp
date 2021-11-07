package seedu.reminder;

public class NoReminder extends BudgetReminder {
    public NoReminder() {
        this.month = null;
        this.budgetName = null;
        this.currBudgetAmount = 0;
        this.budgetLimit = 0;
    }

    @Override
    public String toString() {
        return "Budget reminders are only given for current month!";
    }
}
