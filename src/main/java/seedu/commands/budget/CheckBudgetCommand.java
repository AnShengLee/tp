package seedu.commands.budget;

import seedu.commands.Command;
import seedu.entry.ExpenseCategory;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * Class representing the check budget command.
 * Checks the current limit of the specified budget.
 */
public class CheckBudgetCommand extends Command {
    ExpenseCategory category;

    /**
     * Constructor that initializes the category of the budget to be checked.
     * @param category Category of budget to be checked
     */
    public CheckBudgetCommand(ExpenseCategory category) {
        this.category = category;
    }

    /**
     * Obtains the budget limit of the specified budget and prints it to the Ui with an appropriate message.
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     * @param currencyManager The currency manager that manages the currency conversion related operations.
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        double budgetLimit = budgetManager.getBudget(category);
        ui.printBudget(category, budgetLimit);
    }

}
