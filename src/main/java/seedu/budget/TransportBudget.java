package seedu.budget;

import seedu.entry.ExpenseCategory;

/**
 * Class representing the transport budget category.
 */
public class TransportBudget extends Budget {

    /**
     * Constructor initializing category, name and limit of the transport budget.
     * @param limit Limit of the budget category
     */
    public TransportBudget(double limit) {
        this.category = ExpenseCategory.TRANSPORT;
        this.name = ExpenseCategory.TRANSPORT.toString();
        this.limit = limit;
    }
}
