package seedu.commands.budget;

import seedu.commands.Command;
import seedu.entry.ExpenseCategory;
import seedu.reminder.BudgetReminder;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class SetBudgetCommand extends Command {
    ExpenseCategory category;
    double amount;

    public SetBudgetCommand(ExpenseCategory category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        BudgetReminder reminder = budgetManager.setBudget(amount, category, finances.getExpenses());
        ui.printSetBudgetReminder(reminder);
    }
}
