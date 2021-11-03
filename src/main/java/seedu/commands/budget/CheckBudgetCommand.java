package seedu.commands.budget;

import seedu.commands.Command;
import seedu.entry.ExpenseCategory;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class CheckBudgetCommand extends Command {
    ExpenseCategory category;

    public CheckBudgetCommand(ExpenseCategory category) {
        this.category = category;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        double budgetLimit = budgetManager.getBudget(category);
        ui.printBudget(category, budgetLimit);
    }

}
