package seedu.budget;

import seedu.entry.ExpenseCategory;

/**
 * Class representing the bills budget category.
 */
public class BillsBudget extends Budget {

    /**
     * Constructor initializing category, name and limit of the bills budget.
     * @param limit Limit of the budget category
     */
    public BillsBudget(double limit) {
        this.category = ExpenseCategory.BILLS;
        this.name = ExpenseCategory.BILLS.toString();
        this.limit = limit;
    }
}
