package seedu.commands;

import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public abstract class Command {
    public abstract void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                                 CurrencyManager currencyManager);

    public boolean isExit() {
        return false;
    }
}
