package seedu.budget;

import seedu.entry.ExpenseCategory;

/**
 * Class representing the misc budget category.
 */
public class MiscBudget extends Budget {

    /**
     * Constructor initializing category, name and limit of the misc budget.
     * @param limit Limit of the budget category
     */
    public MiscBudget(double limit) {
        this.category = ExpenseCategory.MISC;
        this.name = ExpenseCategory.MISC.toString();
        this.limit = limit;
    }
}
