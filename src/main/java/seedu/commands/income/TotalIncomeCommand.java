package seedu.commands.income;

import seedu.commands.Command;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class TotalIncomeCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        double totalIncome = finances.getTotalIncome();
        ui.printTotalIncome(totalIncome);
    }
}
