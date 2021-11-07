package seedu.budget;

import seedu.entry.ExpenseCategory;

/**
 * Class representing the entertainment budget category.
 */
public class EntertainmentBudget extends Budget {
    /**
     * Constructor initializing category, name and limit of the entertainment budget.
     * @param limit Limit of the budget category
     */
    public EntertainmentBudget(double limit) {
        this.category = ExpenseCategory.ENTERTAINMENT;
        this.name = ExpenseCategory.ENTERTAINMENT.toString();
        this.limit = limit;
    }
}
