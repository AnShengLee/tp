package seedu.budget;

import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class representing the overall budget category.
 */
public class OverallBudget extends Budget {

    /**
     * Constructor initializing category, name and limit of the overall budget.
     * @param limit Limit of budget category
     */
    public OverallBudget(double limit) {
        this.category = ExpenseCategory.OVERALL;
        this.name = "OVERALL";
        this.limit = limit;
    }

    /**
     * Returns the sum of all expenses in the current month.
     * @param entries ArrayList of expenses from FinacialTracker
     * @param date Date containing the current month and year
     * @return Sum of all expenses in the current month
     */
    @Override
    public double calAmount(ArrayList<Expense> entries, LocalDate date) {
        double amount = 0;
        for (Expense expense : entries) {
            if (isCorrectMonthYear(expense, date)) {
                amount += expense.getValue();
            }
        }
        assert amount >= 0;
        return amount;
    }
}
