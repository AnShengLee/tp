package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FinancialTrackerTest {

    @Test
    public void addEntry_twoExpenseObjects_expectSizeOfListToBeTwo() throws ExpenseOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addExpense(new Expense("Lunch", 5.20, ExpenseCategory.FOOD));
        testTracker.addExpense(new Expense("Dinner", 20, ExpenseCategory.FOOD));
        assertEquals(2, testTracker.getExpenseSize());
    }

    @Test
    public void addIncome_twoIncomeObjects_expectSizeOfListToBeTwo() throws IncomeOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addIncome(new Income("pocket money", 5.20, IncomeCategory.ADHOC));
        testTracker.addIncome(new Income("salary", 20, IncomeCategory.ADHOC));
        assertEquals(2, testTracker.getIncomeSize());
    }

    @Test
    public void removeExpense_emptyExpenseList_expectExpenseEntryNotFoundException() throws ExpenseOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addExpense(new Expense("Lunch", 5.20, ExpenseCategory.FOOD));
        assertThrows(ExpenseEntryNotFoundException.class, () -> {
            testTracker.removeExpense(4);
        });
    }

    @Test
    public void removeIncome_emptyIncomeList_expectIncomeEntryNotFoundException() throws IncomeOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addIncome(new Income("pocket money", 5.20, IncomeCategory.ADHOC));
        assertThrows(IncomeEntryNotFoundException.class, () -> {
            testTracker.removeIncome(4);
        });
    }
    
    @Test
    public void getMonthlyIncomeBreakdown_IncomeList_expectTotalAccumulatedIncome() throws IncomeOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addIncome(new Income("pocket money", 5.20, IncomeCategory.ALLOWANCE));
        testTracker.addIncome(new Income("salary", 100, IncomeCategory.ADHOC));
        ArrayList<Double> totalIncome = testTracker.getMonthlyIncomeBreakdown(2021);
        assertEquals(105.20, totalIncome.get(LocalDate.now().getMonthValue() - 1));
    }

    @Test
    public void getMonthlyExpenseBreakdown_ExpenseList_expectTotalAccumulatedExpense() throws ExpenseOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addExpense(new Expense("lunch", 5.20, ExpenseCategory.FOOD));
        testTracker.addExpense(new Expense("dinner", 100, ExpenseCategory.FOOD));
        ArrayList<Double> totalExpense = testTracker.getMonthlyExpenseBreakdown(2021);
        System.out.println(totalExpense);
        assertEquals(105.20, totalExpense.get(LocalDate.now().getMonthValue() - 1));
    }
    
    @Test
    public void addIncome_InvalidLargeIncomeValue_expectIncomeOverflowException() throws IncomeOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addIncome(new Income("salary", 50000000000.0, IncomeCategory.SALARY));
        assertThrows(IncomeOverflowException.class, () -> {
            testTracker.addIncome(new Income("salary", 50000000001.0, IncomeCategory.SALARY));
        });
    }

    @Test
    public void addExpense_InvalidLargeExpenseValue_expectExpenseOverflowException() throws ExpenseOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addExpense(new Expense("food", 50000000000.0, ExpenseCategory.FOOD));
        assertThrows(ExpenseOverflowException.class, () -> {
            testTracker.addExpense(new Expense("food", 50000000001.0, ExpenseCategory.FOOD));
        });
    }

    @Test
    public void addExpense_LargeExpenseValue_validTotalExpense() throws ExpenseOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addExpense(new Expense("food", 50000000000.0, ExpenseCategory.FOOD));
        testTracker.addExpense(new Expense("food", 10.0, ExpenseCategory.FOOD));
        testTracker.addExpense(new Expense("food", 5.0, ExpenseCategory.FOOD));        
        assertEquals(50000000015.00, testTracker.getTotalExpense());
    }

    @Test
    public void addIncome_LargeIncomeValue_validTotalIncome() throws IncomeOverflowException {
        FinancialTracker testTracker = new FinancialTracker(new CurrencyManager());
        testTracker.addIncome(new Income("Income", 90000000000.0, IncomeCategory.SALARY));
        testTracker.addIncome(new Income("Income", 9999999998.0, IncomeCategory.SALARY));
        testTracker.addIncome(new Income("Income", 1, IncomeCategory.SALARY));
        assertEquals(99999999999.00, testTracker.getTotalIncome());
    }
}
