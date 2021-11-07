package seedu.budget;

import seedu.entry.ExpenseCategory;

/**
 * Class representing the medical budget category.
 */
public class MedicalBudget extends Budget {

    /**
     * Constructor initializing category, name and limit of the medical budget.
     * @param limit Limit of the budget category
     */
    public MedicalBudget(double limit) {
        this.category = ExpenseCategory.MEDICAL;
        this.name = ExpenseCategory.MEDICAL.toString();
        this.limit = limit;
    }
}
