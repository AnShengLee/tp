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

    public DeleteExpenseCommand(int expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

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
