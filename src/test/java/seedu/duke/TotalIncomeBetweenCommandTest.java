package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalIncomeBetweenCommandTest {
    CurrencyManager currencyManager = new CurrencyManager();
    FinancialTracker testTracker = new FinancialTracker(currencyManager);

    @Test
    public void execute_twoValidDateInputs_validTotalSum() throws IncomeOverflowException {
        LocalDate startDate = LocalDate.parse("20/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("29/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Income testIncome1 = new Income("Salary", 400.00, IncomeCategory.ALLOWANCE, startDate);
        Income testIncome2 = new Income("Allowance", 400.00, IncomeCategory.ALLOWANCE, endDate);
        testTracker.addIncome(testIncome1);
        testTracker.addIncome(testIncome2);
        assertEquals(800.00, testTracker.getIncomeBetween(startDate,endDate));
    }

    @Test
    public void execute_twoValidDateInputs_TotalSumZero() throws IncomeOverflowException {
        LocalDate startDate = LocalDate.parse("28/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("29/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Income testIncome1 = new Income("Salary", 400.00, IncomeCategory.ALLOWANCE);
        Income testIncome2 = new Income("Allowance", 400.00, IncomeCategory.ALLOWANCE);
        testTracker.addIncome(testIncome1);
        testTracker.addIncome(testIncome2);
        assertEquals(0.00, testTracker.getIncomeBetween(startDate,endDate));
    }
}
