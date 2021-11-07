package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.exceptions.ExpenseOverflowException;
import seedu.reminder.BudgetReminder;
import seedu.reminder.BudgetSetReminder;
import seedu.reminder.DoubleExceededBudgetReminder;
import seedu.reminder.DoubleNearingBudgetReminder;
import seedu.reminder.ExceededBudgetNearingOverallReminder;
import seedu.reminder.SingleExceededReminder;
import seedu.reminder.SingleNearingReminder;
import seedu.reminder.SingleReminder;
import seedu.reminder.UnableToSetBudgetReminder;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetManagerTest {

    private static final String currentMonth =
            LocalDate.now().getMonth().toString();

    private final BudgetManager budgetManager = new BudgetManager();
    private final FinancialTracker finances = new FinancialTracker();

    @Test
    public void setBudget_validEntry_correctBudget() {
        BudgetReminder reminder = budgetManager.setBudget(2000.50, ExpenseCategory.OVERALL, finances.getExpenses());
        BudgetReminder expectedReminder = new BudgetSetReminder("OVERALL", 2000.50);
        assertEquals(2000.50, budgetManager.getBudget(ExpenseCategory.OVERALL));
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    public void setBudget_overallBudgetLessThanBudget_warningGiven() {
        BudgetReminder reminder = budgetManager.setBudget(2000.50, ExpenseCategory.TRANSPORT,
                finances.getExpenses());
        BudgetReminder expectedReminder = new UnableToSetBudgetReminder("TRANSPORT", 0,
                0, 2000.50, 2000.50);
        assertEquals(0, budgetManager.getBudget(ExpenseCategory.TRANSPORT));
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    public void setBudget_budgetLessThanExpense_warningGiven() throws ExpenseOverflowException {
        budgetManager.setBudget(20, ExpenseCategory.OVERALL, finances.getExpenses());
        finances.addExpense(new Expense("mcdonalds", 15, ExpenseCategory.FOOD));
        BudgetReminder reminder = budgetManager.setBudget(10, ExpenseCategory.FOOD, finances.getExpenses());
        BudgetReminder expectedReminder = new UnableToSetBudgetReminder("FOOD", 15,
                20, 10, 15);
        assertEquals(0, budgetManager.getBudget(ExpenseCategory.FOOD));
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    @Test
    public void setThreshold_validEntry_correctThreshold() {
        budgetManager.setThreshold(0.85);
        assertEquals(0.85, budgetManager.getThreshold());
    }


    @Test
    public void handleBudget_overallNotExceededBudgetNotExceeded_DoubleNearingBudgetReminder()
            throws ExpenseOverflowException {
        budgetManager.setBudget(13, ExpenseCategory.OVERALL, finances.getExpenses());
        budgetManager.setBudget(12, ExpenseCategory.FOOD, finances.getExpenses());
        budgetManager.setThreshold(0.8);
        finances.addExpense(new Expense("mcdonalds", 5, ExpenseCategory.FOOD));
        Expense testExpense = new Expense("dinner", 6, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        BudgetReminder reminder = budgetManager.handleBudget(testExpense, finances.getExpenses(), LocalDate.now());
        BudgetReminder expectedReminder = new DoubleNearingBudgetReminder(currentMonth,
                "FOOD", 11, 12, 11, 13, 12);
        assertEquals(expectedReminder.toString(), reminder.toString());

    }

    @Test
    public void handleBudget_overallNotExceededBudgetExceeded_ExceededBudgetNearingOverallReminder()
            throws ExpenseOverflowException {
        budgetManager.setBudget(12, ExpenseCategory.OVERALL, finances.getExpenses());
        budgetManager.setBudget(4, ExpenseCategory.FOOD, finances.getExpenses());
        budgetManager.setThreshold(0.9);
        Expense testExpense = new Expense("breakfast", 11, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        BudgetReminder reminder = budgetManager.handleBudget(testExpense, finances.getExpenses(), LocalDate.now());
        BudgetReminder expectedReminder = new ExceededBudgetNearingOverallReminder(currentMonth,
                "FOOD", 11, 4, 11,12, 11);
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    @Test
    public void handleBudget_overallExceededBudgetExceeded_DoubleExceededBudgetReminder()
            throws ExpenseOverflowException {
        budgetManager.setBudget(12, ExpenseCategory.OVERALL, finances.getExpenses());
        budgetManager.setBudget(4, ExpenseCategory.FOOD, finances.getExpenses());
        budgetManager.setThreshold(0.9);
        Expense testExpense = new Expense("breakfast", 15, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        BudgetReminder reminder = budgetManager.handleBudget(testExpense, finances.getExpenses(), LocalDate.now());
        BudgetReminder expectedReminder = new DoubleExceededBudgetReminder(currentMonth,
                "FOOD", 15, 4, 15,12, 15);
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    @Test
    public void handleBudget_overallNoWarningBudgetExceeded_SingleExceededReminder()
            throws ExpenseOverflowException {
        budgetManager.setBudget(12, ExpenseCategory.OVERALL, finances.getExpenses());
        budgetManager.setBudget(4, ExpenseCategory.FOOD, finances.getExpenses());
        budgetManager.setThreshold(0.9);
        Expense testExpense = new Expense("breakfast", 5, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        BudgetReminder reminder = budgetManager.handleBudget(testExpense, finances.getExpenses(), LocalDate.now());
        BudgetReminder expectedReminder = new SingleExceededReminder(currentMonth,
                "FOOD", 5, 4);
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    @Test
    public void handleBudget_overallNoWarningBudgetNearing_SingleNearingReminder()
            throws ExpenseOverflowException {
        budgetManager.setBudget(12, ExpenseCategory.OVERALL, finances.getExpenses());
        budgetManager.setBudget(4, ExpenseCategory.FOOD, finances.getExpenses());
        budgetManager.setThreshold(0.9);
        Expense testExpense = new Expense("breakfast", 3.9, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        BudgetReminder reminder = budgetManager.handleBudget(testExpense, finances.getExpenses(), LocalDate.now());
        BudgetReminder expectedReminder = new SingleNearingReminder(currentMonth,
                "FOOD", 3.9, 4);
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

    @Test
    public void handleBudget_overallNoWarningBudgetNotNearing_SingleBudgetReminder()
            throws ExpenseOverflowException {
        budgetManager.setBudget(12, ExpenseCategory.OVERALL, finances.getExpenses());
        budgetManager.setBudget(4, ExpenseCategory.FOOD, finances.getExpenses());
        budgetManager.setThreshold(0.9);
        Expense testExpense = new Expense("breakfast", 1.20, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        BudgetReminder reminder = budgetManager.handleBudget(testExpense, finances.getExpenses(), LocalDate.now());
        BudgetReminder expectedReminder = new SingleReminder(currentMonth,
                "FOOD", 1.20, 4);
        assertEquals(expectedReminder.toString(), reminder.toString());
    }

}
