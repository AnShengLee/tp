package seedu.commands.expense;

import seedu.commands.Command;
import seedu.entry.Expense;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.util.List;

public class TotalExpenseBetweenCommand extends Command {
    private LocalDate start;
    private LocalDate end;
    
    public TotalExpenseBetweenCommand(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        double totalExpenseBetween = finances.getExpenseBetween(start,end);
        ui.printTotalExpenseBetween(totalExpenseBetween,start,end);
    }
}

