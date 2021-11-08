package seedu.commands.currency;

import seedu.commands.Command;
import seedu.exceptions.SameCurrencyTypeException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class CurrencyConversionCommand extends Command {
    protected CurrencyType from;
    protected CurrencyType to;

    /**
     * Constructor used to assign old and new currency types.
     * @param to new currency type that user wishes to convert to
     */
    public CurrencyConversionCommand(CurrencyType to) {
        this.to = to;
        this.from = CurrencyType.SGD;
    }


    /**
     * Executes conversion of all entries into given currency type.
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        try {
            currencyManager.currencyConvertor(from, to, finances, budgetManager);
            ui.printCurrencyChangedConfirmation(to);
        } catch (SameCurrencyTypeException e) {
            ui.printSameCurrencyTypeMessage(to);
        }
    }
}

