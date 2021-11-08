package seedu.commands.currency;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class ListCurrencyTypesCommand extends Command {

    /**
     * Executes listing of available currency types.
     * @param finances object from FinancialTracker
     * @param ui object from Ui
     * @param budgetManager object from BudgetManager
     * @param currencyManager object from CurrencyManager
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        ui.printCurrencyTypes(currencyManager.getCurrencyTypes());
    }
}
