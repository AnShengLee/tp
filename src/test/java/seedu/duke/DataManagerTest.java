package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.utility.BudgetManager;
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
    public void saveEntries_validEntries_correctDataFileContent() {
        FinancialTracker financialTracker = new FinancialTracker();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        financialTracker.addExpense(new Expense("qwe", 12.5, ExpenseCategory.FOOD, date));
        financialTracker.addExpense(new Expense("qwe", .5, ExpenseCategory.FOOD, date));
        financialTracker.addIncome(new Income("qwe", 12.5, IncomeCategory.ALLOWANCE, date));
        financialTracker.addIncome(new Income("qwe", 12.5, IncomeCategory.ALLOWANCE, date));
        Parser parser = new Parser();
        Ui ui = new Ui();
        BudgetManager budgetManager = new BudgetManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager);
        dataManager.saveEntries();
    }

    @Test
    public void loadEntries_validDataFileContent_correctEntries() {
        saveEntries_validEntries_correctDataFileContent();
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        BudgetManager budgetManager = new BudgetManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager);
        dataManager.loadEntries();
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
    public void loadEntries_invalidDataFileContent_detectInvalidDataEntriesAndOutputWarningMessages() {
        FinancialTracker financialTracker = new FinancialTracker();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        financialTracker.addExpense(new Expense("qwe", 12.5, ExpenseCategory.FOOD, date));
        financialTracker.addIncome(new Income("qwe", 12.5, IncomeCategory.ALLOWANCE, date));
        financialTracker.addIncome(new Income("", 12.5, IncomeCategory.ALLOWANCE, date));
        Ui ui = new Ui();
        Parser parser = new Parser();
        BudgetManager budgetManager = new BudgetManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager);
        dataManager.saveEntries();
        dataManager.loadEntries();
    }

    @Test
    public void saveBudget_validBudgets_validBudgetData() {
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        Parser parser = new Parser();
        BudgetManager budgetManager = new BudgetManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager);
        int i = 0;
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category == ExpenseCategory.NULL) {
                break;
            }
            budgetManager.setBudget(i, category);
            i += 1;
        }
        dataManager.saveBudgetSettings();
        String testData = parser.convertBudgetSettingsToData(budgetManager);
        String expectedData = "0.0,1.0,2.0,3.0,4.0,5.0,6.0";
        assertEquals(expectedData, testData);
    }

    @Test
    public void loadBudget_validBudgetData_validBudgets() {
        saveBudget_validBudgets_validBudgetData();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        Parser parser = new Parser();
        BudgetManager budgetManager = new BudgetManager();
        DataManager dataManager = new DataManager(parser, financialTracker, ui, budgetManager);
        dataManager.loadBudgetSettings();
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
