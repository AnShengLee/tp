package seedu.commands.general;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.StonksGraph;
import seedu.utility.Ui;

import java.time.LocalDate;

public class ShowGraphByYearCommand extends Command {

    private int year;
    
    public ShowGraphByYearCommand(LocalDate year) {
        this.year = year.getYear();
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        StonksGraph stonksGraph = new StonksGraph(finances,year);
        ui.printGraph(stonksGraph);
    }
}
