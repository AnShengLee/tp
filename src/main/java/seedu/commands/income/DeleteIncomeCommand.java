package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Income;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteIncomeCommand extends Command {
    private int incomeNumber;

    public DeleteIncomeCommand(int incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        try {
            Income deletedIncome = finances.removeIncome(incomeNumber);
            ui.printIncomeDeleted(deletedIncome);
        } catch (IncomeEntryNotFoundException e) {
            ui.printError(e.getMessage());
        }
    }
}
