package seedu.budget;

import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Abstract class representing all possible budgets in the system.
 */
public abstract class Budget {
    protected ExpenseCategory category;
    protected String name;
    protected double limit;

    /**
     * Returns the category of the specified budget.
     * @return Category of the budget
     */
    public ExpenseCategory getCategory() {
        return this.category;
    }

    /**
     * Returns the name of the specified budget.
     * @return Name of the budget
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the budget limit to specified value.
     * @param amount New budget limit to be set
     */
    public void setLimit(double amount) {
        this.limit = amount;
    }

    /**
     * Returns the budget limit of the specified budget.
     * @return Budget limit of budget
     */
    public double getLimit() {
        return this.limit;
    }

    /**
     * Returns the sum of all expenses in the budget category for the current month.
     * @param entries ArrayList of expenses from FinacialTracker
     * @param date Date containing the current month and year
     * @return Sum of all expenses in the budget category for the current month
     */
    public double calAmount(ArrayList<Expense> entries, LocalDate date) {
        double amount = 0;
        for (Expense expense : entries) {
            if ((expense.getCategory() == this.category) & isCorrectMonthYear(expense, date)) {
                amount += expense.getValue();
            }
        }
        assert amount >= 0;
        return amount;
    }

    protected boolean isCorrectMonthYear(Expense expense, LocalDate date) {
        return expense.getDate().getMonth() == date.getMonth() & expense.getDate().getYear() == date.getYear();
    }
}
