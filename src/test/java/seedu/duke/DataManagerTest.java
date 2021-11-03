package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.currency.CurrencyType;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Parser;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataManagerTest {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    @Test
    public void saveEntries_validEntries_correctDataFileContent()
            throws ExpenseOverflowException, IncomeOverflowException {
        FinancialTracker financialTracker = new FinancialTracker();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        financialTracker.addExpense(new Expense("qwe", 12.5, ExpenseCategory.FOOD, date));
        financialTracker.addExpense(new Expense("qwe", .5, ExpenseCategory.FOOD, date));
        financialTracker.addIncome(new Income("qwe", 12.5, IncomeCategory.SALARY, date));
        financialTracker.addIncome(new Income("qwe", 12.5, IncomeCategory.ALLOWANCE, date));
        Parser parser = new Parser();
        Ui ui = new Ui();
        BudgetManager budgetManager = new BudgetManager();
        CurrencyManager currencyManager = new CurrencyManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager, currencyManager);
        dataManager.saveAll();
    }

    @Test
    public void loadEntries_validDataFileContent_correctEntries()
            throws ExpenseOverflowException, IncomeOverflowException {
        saveEntries_validEntries_correctDataFileContent();
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        BudgetManager budgetManager = new BudgetManager();
        CurrencyManager currencyManager = new CurrencyManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager, currencyManager);
        dataManager.loadAll();
        assertEquals(12.5, financialTracker.getExpenses().get(0).getValue());
        assertEquals("qwe", financialTracker.getExpenses().get(0).getDescription());
        assertEquals(ExpenseCategory.FOOD, financialTracker.getExpenses().get(0).getCategory());

        assertEquals(.5, financialTracker.getExpenses().get(1).getValue());
        assertEquals("qwe", financialTracker.getExpenses().get(1).getDescription());

        assertEquals(12.5, financialTracker.getIncomes().get(0).getValue());
        assertEquals("qwe", financialTracker.getIncomes().get(0).getDescription());

        assertEquals(12.5, financialTracker.getIncomes().get(1).getValue());
        assertEquals("qwe", financialTracker.getIncomes().get(1).getDescription());
    }

    @Test
    public void loadEntries_invalidDataFileContent_detectInvalidDataEntriesAndOutputWarningMessages()
            throws ExpenseOverflowException, IncomeOverflowException {
        FinancialTracker financialTracker = new FinancialTracker();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        financialTracker.addExpense(new Expense("qwe", 12.5, ExpenseCategory.FOOD, date));
        financialTracker.addIncome(new Income("qwe", 12.5, IncomeCategory.ALLOWANCE, date));
        financialTracker.addIncome(new Income("", 12.5, IncomeCategory.ALLOWANCE, date));
        Ui ui = new Ui();
        Parser parser = new Parser();
        BudgetManager budgetManager = new BudgetManager();
        CurrencyManager currencyManager = new CurrencyManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager, currencyManager);
        dataManager.saveAll();
        dataManager.loadAll();
    }

    @Test
    public void saveSettings_validSettings_validSettingData() {
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        Parser parser = new Parser();
        BudgetManager budgetManager = new BudgetManager();
        CurrencyManager currencyManager = new CurrencyManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager, currencyManager);
        int i = 0;
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category == ExpenseCategory.NULL) {
                break;
            }
            budgetManager.setBudget(i, category);
            i += 1;
        }
        budgetManager.setThreshold(0.5);
        dataManager.saveAll();
        String testData = parser.convertSettingsToData(budgetManager, currencyManager);
        String expectedData = "SGD,0.5,0.0,1.0,2.0,3.0,4.0,5.0,6.0";
        assertEquals(expectedData, testData);
    }

    @Test
    public void loadAll_validBudgetData_validBudgets() {
        saveSettings_validSettings_validSettingData();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        Parser parser = new Parser();
        BudgetManager budgetManager = new BudgetManager();
        CurrencyManager currencyManager = new CurrencyManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager, currencyManager);
        dataManager.loadAll();
        int i = 0;
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category == ExpenseCategory.NULL) {
                break;
            }
            assertEquals(budgetManager.getBudget(category), i);
            i += 1;
        }
    }
}
