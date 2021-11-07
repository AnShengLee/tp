package seedu.reminder;

import seedu.budget.Budget;

/**
 * Abstract class representing reminders dealing with both the sub-budget and the overall budget.
 */
public abstract class DoubleReminder extends BudgetReminder {
    protected double currOverallAmount;
    protected double overallLimit;
    protected double totalBudget;
}
