//@@author Nirmaleshwar

package seedu.commands.expense;

import seedu.commands.Command;
import seedu.entry.Expense;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class DeleteExpenseCommand extends Command {
    private int expenseNumber;

    /**
     * Constructor for delete expense command.
     * @param expenseNumber index of item to be deleted
     */
    public DeleteExpenseCommand(int expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    /**
     * Executes deletion of expense item.
     * @param finances object from FinancialTracker
     * @param ui object from Ui
     * @param budgetManager object from BudgetManager
     * @param currencyManager object from CurrencyManager
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        try {
            Expense deletedExpense = finances.removeExpense(expenseNumber);
            ui.printExpenseDeleted(deletedExpense);
        } catch (ExpenseEntryNotFoundException e) {
            ui.printError(e.getMessage());
        }
    }
}
