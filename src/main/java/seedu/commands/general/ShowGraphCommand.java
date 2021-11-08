
package seedu.commands.general;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.StonksGraph;
import seedu.utility.Ui;

import java.time.LocalDate;

/**
 * The command that shows a graph based on the current year, it is an inherited class of the Command class.
 */
public class ShowGraphCommand extends Command {
    private int year;


    /**
     * Constructor of the ShowGraphCommand that initialises the current year as its attribute.
     */
    public ShowGraphCommand() {
        this.year = currentYear();
    }

    //@@author KZQ1999
    private int currentYear() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear();
    }
    //@@author KZQ1999


    /**
     * Prints the graph representing the financial tracker of the current year.
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     * @param currencyManager The currency manager that manages the currency state of the financial tracker. 
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        StonksGraph stonksGraph = new StonksGraph(finances, year);
        ui.printGraph(stonksGraph);
    }
}


