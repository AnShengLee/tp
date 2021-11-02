package seedu.commands.currency;

import seedu.commands.Command;
import seedu.exceptions.SameCurrencyTypeException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class CurrencyConversionCommand extends Command {
    protected CurrencyType from = CurrencyType.SGD;
    protected CurrencyType to;

    public CurrencyConversionCommand(CurrencyType to) {
        this.to = to;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager, CurrencyManager currencyManager) {
        try {
            currencyManager.CurrencyConversion(from, to, finances, budgetManager);
            ui.printCurrencyChangedConfirmation(to);
        } catch (SameCurrencyTypeException e) {
           ui.printSameCurrencyTypeMessage(to);
        }
    }
}

