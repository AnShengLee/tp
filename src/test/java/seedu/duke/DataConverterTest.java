package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.currency.CurrencyType;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidAmountException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidSettingsDataFormatException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.utility.storage.DataConverter.convertDataToBudgetSettings;
import static seedu.utility.storage.DataConverter.convertDataToCurrencySetting;
import static seedu.utility.storage.DataConverter.convertDataToExpense;
import static seedu.utility.storage.DataConverter.convertDataToIncome;
import static seedu.utility.storage.DataConverter.convertExpenseToData;
import static seedu.utility.storage.DataConverter.convertIncomeToData;
import static seedu.utility.storage.DataConverter.convertSettingsToData;

public class DataConverterTest {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATA_SEPARATOR = ",";
    private static final int TOTAL_EXPENSE_CATEGORY = 7;
    
    @Test
    public void convertExpenseToData_validExpense_correctDataOutput() {
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        Expense testExpense = new Expense("buy book", 12.33, ExpenseCategory.FOOD, date);
        String testData = convertExpenseToData(testExpense);
        assertEquals("E" + DATA_SEPARATOR + "buy book" + DATA_SEPARATOR + 12.33 + DATA_SEPARATOR + "FOOD"
                + DATA_SEPARATOR + "11/11/2121", testData);
    }

    @Test
    public void convertIncomeToData_validIncome_correctDataOutput() {
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        Income testIncome = new Income("job", 1233.0, IncomeCategory.ADHOC, date);
        String testData = convertIncomeToData(testIncome);
        assertEquals("I" + DATA_SEPARATOR + "job" + DATA_SEPARATOR + 1233.0 + DATA_SEPARATOR + "ADHOC"
                + DATA_SEPARATOR + "11/11/2121", testData);
    }

    @Test
    public void convertDataToExpense_validExpenseData_outputExpense() throws InputException,
            InvalidExpenseDataFormatException {
        Expense testExpense = convertDataToExpense("E,sfa,12,food,11/11/2121");
        assertEquals("sfa", testExpense.getDescription());
        assertEquals(12, testExpense.getValue());
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithBlankDescription_throwsException() {
        assertThrows(InvalidExpenseDataFormatException.class, () -> convertDataToExpense("E, ,"));
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithInvalidAmount_throwsException() {
        assertThrows(InvalidAmountException.class,
            () -> convertDataToExpense("E, asd, 12a, qwe, 21-11-11"));
    }

    @Test
    public void convertDataToIncome_validIncomeData_outputIncome() throws InputException,
            InvalidIncomeDataFormatException, DateTimeException {
        Income testIncome = convertDataToIncome("I" + DATA_SEPARATOR + "pay" + DATA_SEPARATOR
                + 1000 + DATA_SEPARATOR + "SALARY" + DATA_SEPARATOR + "11/11/2121");
        assertEquals("pay", testIncome.getDescription());
        assertEquals(1000.0, testIncome.getValue());
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithBlankDescription_throwsException() {
        assertThrows(InputException.class,
            () -> convertDataToIncome("I, , 12, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidAmount_throwsException() {
        assertThrows(InvalidAmountException.class,
            () -> convertDataToIncome("I, asd, 12a, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidMarker_throwsException() {
        assertThrows(InvalidIncomeDataFormatException.class,
            () -> convertDataToIncome("E, asd, 12a, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidSeparator_throwsException() {
        assertThrows(InvalidIncomeDataFormatException.class,
            () -> convertDataToIncome("I" + DATA_SEPARATOR + "pay" + DATA_SEPARATOR + 1000 + DATA_SEPARATOR
                        + "SALARY" + "|" + "11/11/2121"));
    }

    @Test
    public void convertDataToBudgetSettings_validData_validBudgets() throws InvalidSettingsDataFormatException {
        String testData = "SGD,0.1,12.0,1.0,1.0,1.0,1.0,1.0,1.0";
        ArrayList<Double> testBudgets = convertDataToBudgetSettings(testData);
        for (int i = 0; i < TOTAL_EXPENSE_CATEGORY; i++) {
            if (i == 0) {
                assertEquals(12, testBudgets.get(i));
            } else {
                assertEquals(1, testBudgets.get(i));
            }
        }
    }

    @Test
    public void convertDataToCurrencySetting_validData_validCurrency() throws InvalidCurrencyTypeException,
            InvalidSettingsDataFormatException, BlankCurrencyTypeException {
        String testData = "SGD,0.0,12.0,12.0,12.0,12.0,12.0,12.0,12";
        CurrencyType currency = convertDataToCurrencySetting(testData);
        assertEquals(currency.toString(), "SGD");
    }

    @Test
    public void convertSettingsToData_validSettings_validData() {
        BudgetManager testBudgetManager = new BudgetManager();
        CurrencyManager currencyManager = new CurrencyManager();
        FinancialTracker financialTracker = new FinancialTracker(currencyManager);
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category == ExpenseCategory.NULL) {
                break;
            }
            if (category == ExpenseCategory.OVERALL) {
                testBudgetManager.setBudget(12, category, financialTracker.getExpenses());
            } else {
                testBudgetManager.setBudget(1, category, financialTracker.getExpenses());
            }
        }
        testBudgetManager.setThreshold(0.2);
        String testData = convertSettingsToData(testBudgetManager, currencyManager);
        assertEquals("SGD,0.2,12.0,1.0,1.0,1.0,1.0,1.0,1.0", testData);

    }
}
