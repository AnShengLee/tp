package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Income;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class AddIncomeCommand extends Command {
    private Income income;

    public AddIncomeCommand(Income income) {
        this.income = income;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        try {
            finances.addIncome(income);
            ui.printIncomeAdded(income);
        } catch (IncomeOverflowException e) {
            ui.printError(e.getMessage());
        }
        
    }
}
