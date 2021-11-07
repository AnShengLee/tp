package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;
import seedu.utility.tools.DateRange;

import java.time.LocalDate;

public class TotalIncomeBetweenCommand extends Command {
    private LocalDate start;
    private LocalDate end;
    
    public TotalIncomeBetweenCommand(DateRange dateRange) {
        this.start = dateRange.getStartDate();
        this.end = dateRange.getEndDate();
    }
    
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        double totalIncomeBetween = finances.getIncomeBetween(start,end);
        ui.printTotalIncomeBetween(totalIncomeBetween,start,end);
    }
}

