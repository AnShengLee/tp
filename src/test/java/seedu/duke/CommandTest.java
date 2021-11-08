package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.commands.expense.AddExpenseCommand;
import seedu.commands.income.AddIncomeCommand;
import seedu.commands.general.ClearAllEntriesCommand;
import seedu.commands.expense.DeleteExpenseCommand;
import seedu.commands.income.DeleteIncomeCommand;
import seedu.commands.expense.TotalExpenseCommand;
import seedu.commands.income.TotalIncomeCommand;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CommandTest {
    private CurrencyManager currencyManager = new CurrencyManager();
    private FinancialTracker testTracker = new FinancialTracker(currencyManager);
    private Ui testUi = new Ui();
    private BudgetManager budgetManager = new BudgetManager();

    @Test
    public void addExpenseCommand_validExpense_validExpensesSize() {
        Expense testExpense = new Expense("Bubble Tea", 4.80, ExpenseCategory.FOOD);
        AddExpenseCommand testCommand = new AddExpenseCommand(testExpense);
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(1, testTracker.getExpenseSize());
    }

    @Test
    public void addIncomeCommand_validIncome_validIncomeSize() {
        Income testIncome = new Income("Pocket Money", 100, IncomeCategory.ALLOWANCE);
        AddIncomeCommand testCommand = new AddIncomeCommand(testIncome);
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(1, testTracker.getIncomeSize());
    }

    @Test
    public void deleteExpenseCommand_validIndex_validIncomeRemoved() {
        DeleteExpenseCommand testCommand = new DeleteExpenseCommand(1);
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertTrue(testTracker.isExpensesEmpty());
    }

    @Test
    public void deleteIncomeCommand_validIndex_validExpenseRemoved() {
        DeleteIncomeCommand testCommand = new DeleteIncomeCommand(1);
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertTrue(testTracker.isIncomesEmpty());
    }

    @Test
    public void clearAllEntriesCommand_validEntry_validArrayListSize() 
            throws IncomeOverflowException, ExpenseOverflowException {
        Income testIncome1 = new Income("Pocket Money", 100, IncomeCategory.ALLOWANCE);
        Income testIncome2 = new Income("Pocket Money", 100, IncomeCategory.ALLOWANCE);
        Expense testExpense1 = new Expense("Bubble Tea", 4.80, ExpenseCategory.FOOD);
        Expense testExpense2 = new Expense("Bubble Tea", 4.80, ExpenseCategory.FOOD);
        testTracker.addIncome(testIncome1);
        testTracker.addIncome(testIncome2);
        testTracker.addExpense(testExpense1);
        testTracker.addExpense(testExpense2);
        ClearAllEntriesCommand testCommand = new ClearAllEntriesCommand();
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(0, testTracker.getIncomeSize());
        assertEquals(0, testTracker.getExpenseSize());
    }

    @Test
    public void totalExpenseCommand_validExpenseEntry_validTotal() {
        TotalExpenseCommand testCommand = new TotalExpenseCommand();
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(0, testTracker.getTotalIncome());
        Expense testExpense1 = new Expense("Bubble Tea", 4.80, ExpenseCategory.FOOD);
        Expense testExpense2 = new Expense("Chicken Rice", 3.00, ExpenseCategory.FOOD);
        AddExpenseCommand testCommand2 = new AddExpenseCommand(testExpense1);
        AddExpenseCommand testCommand3 = new AddExpenseCommand(testExpense2);
        testCommand2.execute(testTracker, testUi, budgetManager, currencyManager);
        testCommand3.execute(testTracker, testUi, budgetManager, currencyManager);
        TotalExpenseCommand testCommand4 = new TotalExpenseCommand();
        testCommand4.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(7.80, testTracker.getTotalExpense());
    }

    @Test
    public void totalIncomeCommand_validIncomeEntry_validTotal() {
        TotalIncomeCommand testCommand = new TotalIncomeCommand();
        testCommand.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(0, testTracker.getTotalExpense());
        Income testIncome1 = new Income("Pocket Money", 100, IncomeCategory.ALLOWANCE);
        Income testIncome2 = new Income("Chicken Rice", 2500, IncomeCategory.SALARY);
        AddIncomeCommand testCommand2 = new AddIncomeCommand(testIncome1);
        AddIncomeCommand testCommand3 = new AddIncomeCommand(testIncome2);
        testCommand2.execute(testTracker, testUi, budgetManager, currencyManager);
        testCommand3.execute(testTracker, testUi, budgetManager, currencyManager);
        TotalIncomeCommand testCommand4 = new TotalIncomeCommand();
        testCommand4.execute(testTracker, testUi, budgetManager, currencyManager);
        assertEquals(2600, testTracker.getTotalIncome());
    }

}

