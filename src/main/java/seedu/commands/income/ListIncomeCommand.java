package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Income;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class ListIncomeCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        ArrayList<Income> incomes = finances.getIncomes(); 
        ui.listIncome(incomes);
    }
}
