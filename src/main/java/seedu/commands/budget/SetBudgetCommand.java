package seedu.commands.budget;

import seedu.commands.Command;
import seedu.entry.ExpenseCategory;
import seedu.reminder.BudgetReminder;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * Class representing the set budget command.
 * Sets the limit of the specified budget if the new limit is valid.
 * If invalid, an appropriate reminder is returned, with advice on how to handle the budget.
 */
public class SetBudgetCommand extends Command {
    ExpenseCategory category;
    double amount;

    /**
     * Constructor that initializes the category and the amount of the budget to be set.
     * @param category Category of the budget
     * @param amount New budget limit to be set
     */
    public SetBudgetCommand(ExpenseCategory category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    /**
     * Sets the limit of the specified budget if the new limit is valid.
     * If invalid, an appropriate reminder is printed to the Ui.
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     * @param currencyManager The currency manager that manages the currency conversion related operations.
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        BudgetReminder reminder = budgetManager.setBudget(amount, category, finances.getExpenses());
        ui.printSetBudgetReminder(reminder);
    }
}
