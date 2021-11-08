package seedu.commands.general;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.StonksGraph;
import seedu.utility.Ui;

import java.time.LocalDate;


/**
 * The command that shows a graph based on the input year, it is an inherited class of the Command class.
 */
public class ShowGraphByYearCommand extends Command {

    private int year;


    /**
     * Constructor of the ShowGraphByYearCommand that initialises the input year as its attribute.
     * @param year The input year given by the user.
     */
    public ShowGraphByYearCommand(LocalDate year) {
        this.year = year.getYear();
    }


    /**
     * Prints the graph representing the financial tracker of the input year given by the user.
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     * @param currencyManager The currency manager that manages the currency state of the financial tracker. 
     */
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        StonksGraph stonksGraph = new StonksGraph(finances,year);
        ui.printGraph(stonksGraph);
    }
}
