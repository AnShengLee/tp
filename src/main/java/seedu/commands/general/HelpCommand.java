package seedu.commands.general;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * The command that shows all possible commands and their formats, it is an inherited class of the Command class.
 */
public class HelpCommand extends Command {

    /**
     * Prints the list of commands and their formats to the output.
     * 
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     * @param currencyManager The currency manager that manages the currency state of the financial tracker. 
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        ui.printHelp();
    }
}
