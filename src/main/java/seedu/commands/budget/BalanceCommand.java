package seedu.commands.budget;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * The command that prints the balance of the financial tracker to the output.
 */
public class BalanceCommand extends Command {

    /**
     * Prints the balance of the financial tracker to the standard output.
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations/
     * @param currencyManager The currency manager that manages the currency state of the financial tracker.
     */
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        ui.printBalance(finances.calculateBalance());
    }
}
