//@@author Nirmaleshwar

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

    /**
     * Constructor for add expense command.
     * @param expense object to be added
     */
    public AddExpenseCommand(Expense expense) {
        this.expense = expense;
    }

    /**
     * Executes adding of expense item.
     * @param finances object from FinancialTracker
     * @param ui object from Ui
     * @param budgetManager object from BudgetManager
     * @param currencyManager object from CurrencyManager
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        try {
            finances.addExpense(expense);
            ui.printExpenseAdded(expense);
            BudgetReminder reminder = budgetManager.handleBudget(expense, finances.getExpenses());
            ui.printBudgetReminder(reminder);
        } catch (ExpenseOverflowException e) {
            ui.printError(e.getMessage());
        }
    }
}
