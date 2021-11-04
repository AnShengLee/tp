package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.exceptions.ExpenseOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetManagerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String newLine = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static final String SEPARATOR_LINE = "-------------------------------------------------------------------"
            + "----------------------------------";
    private static final String currentMonth =
            LocalDate.now().getMonth().toString();

    private final Ui testUi = new Ui();
    private final BudgetManager budgetManager = new BudgetManager();
    private final FinancialTracker finances = new FinancialTracker();

    @Test
    public void setBudget_validEntry_correctBudget() {
        budgetManager.setBudget(2000.50, ExpenseCategory.TRANSPORT);
        assertEquals(2000.50, budgetManager.getBudget(ExpenseCategory.TRANSPORT));
    }

    @Test
    public void setThreshold_validEntry_correctThreshold() {
        budgetManager.setThreshold(0.85);
        assertEquals(0.85, budgetManager.getThreshold());
    }


    @Test
        public void handleBudget_overallNotExceededBudgetNotExceeded_directlyAdjustBudgetReminder() 
            throws ExpenseOverflowException {
        budgetManager.setBudget(20, ExpenseCategory.OVERALL);
        budgetManager.setBudget(12, ExpenseCategory.FOOD);
        budgetManager.setThreshold(0.9);
        finances.addExpense(new Expense("mcdonalds", 5, ExpenseCategory.FOOD));
        Expense testExpense = new Expense("dinner", 6, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        budgetManager.handleBudget(testExpense, finances.getExpenses(), testUi);

        String expectedOutput = "You are almost reaching the " + currentMonth + " FOOD budget: $11.00/$12.00" + newLine
                + "Since you have not yet exceeded your " + currentMonth + " OVERALL budget: $11.00/$20.00" + newLine
                + "You can directly increase your " + currentMonth + " FOOD budget up to $21.00!" + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void handleBudget_overallNotExceededBudgetExceeded_directlyAdjustBudgetReminder() 
            throws ExpenseOverflowException {
        budgetManager.setBudget(12, ExpenseCategory.OVERALL);
        budgetManager.setBudget(4, ExpenseCategory.FOOD);
        budgetManager.setThreshold(0.9);
        Expense testExpense = new Expense("breakfast", 10, ExpenseCategory.FOOD);
        finances.addExpense(testExpense);
        budgetManager.handleBudget(testExpense, finances.getExpenses(), testUi);

        String expectedOutput = "You have exceeded the " + currentMonth + " FOOD budget: $10.00/$4.00" + newLine
                + "Since you have not yet exceeded your " + currentMonth + " OVERALL budget: $10.00/$12.00" + newLine
                + "You can directly increase your " + currentMonth + " FOOD budget up to $12.00!"
                + newLine + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }


}
