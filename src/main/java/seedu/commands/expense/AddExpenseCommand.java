package seedu.commands.expense;

import seedu.commands.Command;
import seedu.entry.Expense;
import seedu.exceptions.ExpenseOverflowException;
import seedu.reminder.BudgetReminder;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;

public class AddExpenseCommand extends Command {
    private Expense expense;

    public AddExpenseCommand(Expense expense) {
        this.expense = expense;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        try {
            finances.addExpense(expense);
            ui.printExpenseAdded(expense);
            BudgetReminder reminder = budgetManager.handleBudget(expense, finances.getExpenses(), LocalDate.now());
            ui.printBudgetReminder(reminder);
        } catch (ExpenseOverflowException e) {
            ui.printError(e.getMessage());
        }
    }
}
