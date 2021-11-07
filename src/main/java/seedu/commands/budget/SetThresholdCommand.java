package seedu.commands.budget;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

/**
 * Class representing the set threshold command.
 * Sets the threshold value to the specified value.
 */
public class SetThresholdCommand extends Command {
    double threshold;

    /**
     * Constructor initializing the threshold value to be set.
     * @param threshold Threshold value to be set
     */
    public SetThresholdCommand(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Sets the threshold value and prints a confirmation to the Ui.
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     * @param currencyManager The currency manager that manages the currency conversion related operations.
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        budgetManager.setThreshold(threshold);
        ui.printThresholdConfirmation(threshold);
    }
}
