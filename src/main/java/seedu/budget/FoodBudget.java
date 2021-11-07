package seedu.budget;

import seedu.entry.ExpenseCategory;

/**
 * Class representing the food budget category.
 */
public class FoodBudget extends Budget {

    /**
     * Constructor initializing category, name and limit of the food budget.
     * @param limit Limit of the budget category
     */
    public FoodBudget(double limit) {
        this.category = ExpenseCategory.FOOD;
        this.name = ExpenseCategory.FOOD.toString();
        this.limit = limit;
    }
}
