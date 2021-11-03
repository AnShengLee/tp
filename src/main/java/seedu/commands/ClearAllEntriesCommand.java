package seedu.commands;

import seedu.budget.Budget;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * Clears all expense and income entries. This is so that users can start afresh.
 * Also allows us developers to have easier time testing data saving capabilities.
 */
public class ClearAllEntriesCommand extends Command {

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        finances.clearAllEntries();
        ui.printAllEntriesCleared();
    }
}
