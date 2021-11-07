package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceCommandTest {
    private CurrencyManager currencyManager = new CurrencyManager();
    private FinancialTracker testTracker = new FinancialTracker(currencyManager);
    
    @Test
    public void execute_twoValidDateInputs_validTotalSum() throws IncomeOverflowException, ExpenseOverflowException {
        Income testIncome = new Income("Salary", 400.00, IncomeCategory.SALARY);
        Expense testExpense = new Expense("Burger", 500.00, ExpenseCategory.FOOD);
        testTracker.addIncome(testIncome);
        testTracker.addExpense(testExpense);
        assertEquals(-100.00, testTracker.calculateBalance());
    }
}
