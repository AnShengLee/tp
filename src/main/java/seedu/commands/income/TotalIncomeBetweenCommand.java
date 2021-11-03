package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.util.List;

public class TotalIncomeBetweenCommand extends Command {
    private LocalDate start;
    private LocalDate end;
    
    public TotalIncomeBetweenCommand(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        double totalIncomeBetween = finances.getIncomeBetween(start,end);
        ui.printTotalIncomeBetween(totalIncomeBetween,start,end);
    }
}

