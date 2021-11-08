//@@author Nirmaleshwar

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

    /**
     * Constructor for delete income command.
     * @param incomeNumber index of item to be deleted
     */
    public DeleteIncomeCommand(int incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    /**
     * Executes deletion of income item.
     * @param finances object from FinancialTracker
     * @param ui object from Ui
     * @param budgetManager object from BudgetManager
     * @param currencyManager object from CurrencyManager
     */
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
