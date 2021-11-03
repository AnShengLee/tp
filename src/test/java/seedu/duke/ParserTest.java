package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.budget.SetThresholdCommand;
import seedu.commands.expense.AddExpenseCommand;
import seedu.commands.general.ClearAllEntriesCommand;
import seedu.commands.currency.CurrencyType;
import seedu.commands.general.ShowGraphCommand;
import seedu.commands.income.AddIncomeCommand;
import seedu.commands.income.DeleteIncomeCommand;
import seedu.commands.general.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.expense.ListExpenseCommand;
import seedu.commands.income.ListIncomeCommand;
import seedu.commands.income.TotalIncomeCommand;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidSettingsDataException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParserTest {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATA_SEPARATOR = ",";
    private static final int TOTAL_EXPENSE_CATEGORY = 7;

    @Test
    public void parseCommand_validHelpCommand_returnHelpCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("help");
        assertSame(underTest.getClass(), HelpCommand.class);
    }

    @Test
    public void parseCommand_invalidListCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in           d");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_validListIncomeCommand_returnListIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in ");
        assertSame(underTest.getClass(), ListIncomeCommand.class);
    }

    @Test
    public void parseCommand_validListExpenseCommand_returnListIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_ex");
        assertSame(underTest.getClass(), ListExpenseCommand.class);
    }

    @Test
    public void parseCommand_invalidCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("tfshsdfh");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidAddExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/tfshsdfh a/12a c/qewew");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidAddExpenseCommandWithEmptyDescription_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/a/12a");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_validAddExpenseCommand_returnInvalidExpenseCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex    d/      tfshsdfh     a/          123  c/2wq2");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidDeleteIncomeCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/12a              ");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidDeleteExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_ex      i/12a              ");
        InvalidCommand test = (InvalidCommand) underTest; 
        assertSame("Only numeric inputs are allowed for index", test.getMessage());
    }

    @Test
    public void parseCommand_validDeleteIncomeCommand_returnDeleteIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/   12              ");
        assertSame(underTest.getClass(), DeleteIncomeCommand.class);
    }

    @Test
    public void parseCommand_invalidTotalIncomeCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in                 fddgf");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidTotalExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_ex                 fddgf");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_validTotalIncomeCommand_returnTotalIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in           ");
        assertSame(underTest.getClass(), TotalIncomeCommand.class);
    }

    @Test
    public void parseCommand_invalidInput_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("gfsbsfbgfsbfgs");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Invalid command. Use \"help\" to show the list of possible commands.", test.getMessage());
    }

    @Test
    public void parseCommand_invalidDeleteIncomeCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in i/aa");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Only numeric inputs are allowed for index", test.getMessage());
    }

    @Test
    public void parseCommand_invalidAddIncomeCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_in d/buy book a/aa c/qwqe");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Only numeric inputs are allowed for amount.", test.getMessage());
    }

    @Test
    public void parseCommand_invalidExitCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("bye");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Invalid command. Use \"help\" to show the list of possible commands.", test.getMessage());
    }

    @Test
    public void convertExpenseToData_validExpense_correctDataOutput() {
        Parser testParser = new Parser();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        Expense testExpense = new Expense("buy book", 12.33, ExpenseCategory.FOOD, date);
        String testData = testParser.convertExpenseToData(testExpense);
        assertEquals("E" + DATA_SEPARATOR + "buy book" + DATA_SEPARATOR + 12.33 + DATA_SEPARATOR + "FOOD" 
                + DATA_SEPARATOR + "11/11/2121", testData);
    }

    @Test
    public void convertIncomeToData_validIncome_correctDataOutput() {
        Parser testParser = new Parser();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        Income testIncome = new Income("job", 1233.0, IncomeCategory.ADHOC, date);
        String testData = testParser.convertIncomeToData(testIncome);
        assertEquals("I" + DATA_SEPARATOR + "job" + DATA_SEPARATOR + 1233.0 + DATA_SEPARATOR + "ADHOC" 
                + DATA_SEPARATOR + "11/11/2121", testData);
    }

    @Test
    public void convertDataToExpense_validExpenseData_outputExpense() throws InputException,
            InvalidExpenseDataFormatException {
        Parser testParser = new Parser();
        Expense testExpense = testParser.convertDataToExpense("E,sfa,12,food,11/11/2121");
        assertEquals("sfa", testExpense.getDescription());
        assertEquals(12, testExpense.getValue());
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithBlankDescription_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidExpenseDataFormatException.class, () -> testParser.convertDataToExpense("E, ,"));
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithInvalidAmount_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidExpenseAmountException.class,
            () -> testParser.convertDataToExpense("E, asd, 12a, qwe, 21-11-11"));
    }

    @Test
    public void convertDataToIncome_validIncomeData_outputIncome() throws InputException, 
            InvalidIncomeDataFormatException, DateTimeException {
        Parser testParser = new Parser();
        Income testIncome = testParser.convertDataToIncome("I" + DATA_SEPARATOR + "pay" + DATA_SEPARATOR 
                + 1000 + DATA_SEPARATOR + "SALARY" + DATA_SEPARATOR + "11/11/2121");
        assertEquals("pay", testIncome.getDescription());
        assertEquals(1000.0, testIncome.getValue());
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithBlankDescription_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InputException.class, 
            () -> testParser.convertDataToIncome("I, , 12, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidAmount_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeAmountException.class, 
            () -> testParser.convertDataToIncome("I, asd, 12a, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidMarker_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, 
            () -> testParser.convertDataToIncome("E, asd, 12a, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidSeparator_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, 
            () -> testParser.convertDataToIncome("I" + DATA_SEPARATOR + "pay" + DATA_SEPARATOR + 1000 + DATA_SEPARATOR 
                    + "SALARY" + "|" + "11/11/2121"));
    }
    
    @Test
    public void convertSettingsToData_validSettings_validData() {
        BudgetManager testBudgetManager = new BudgetManager();
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category == ExpenseCategory.NULL) {
                break;
            }
            testBudgetManager.setBudget(12, category);
        }
        testBudgetManager.setThreshold(0.2);
        Parser testParser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        CurrencyManager currencyManager = new CurrencyManager();
        String testData = testParser.convertSettingsToData(financialTracker, testBudgetManager, currencyManager);
        assertEquals("SGD,0.2,12.0,12.0,12.0,12.0,12.0,12.0,12.0", testData);
        
    }
    
    @Test
    public void convertDataToBudgetSettings_validData_validBudgets() throws InvalidSettingsDataException {
        String testData = "SGD,0.1,12.0,12.0,12.0,12.0,12.0,12.0,12";
        Parser parser = new Parser();
        ArrayList<Double> testBudgets = parser.convertDataToBudgetSettings(testData);
        for (int i = 0; i < TOTAL_EXPENSE_CATEGORY; i++) {
            assertEquals(12, testBudgets.get(i));
        }
    }
    
    @Test 
    public void convertDataToCurrencySetting_validData_validCurrency() throws InvalidCurrencyTypeException, 
            InvalidSettingsDataException, BlankCurrencyTypeException {
        String testData = "SGD,0.0,12.0,12.0,12.0,12.0,12.0,12.0,12";
        Parser parser = new Parser();
        CurrencyType currency = parser.convertDataToCurrencySetting(testData);
        assertEquals(currency.toString(), "SGD");
    }
    
    @Test
    public void addExpenseWithDate_validInput_validCommand() {
        Parser testParser = new Parser();
        String userInput = "add_ex d/asf a/10 c/food D/11/11/2021";
        Command testCommand = testParser.parseCommand(userInput);
        assertEquals(testCommand.getClass(), AddExpenseCommand.class);
    }

    @Test
    public void addIncomeWithDate_validInput_validCommand() {
        Parser testParser = new Parser();
        String userInput = "add_in d/asf a/10 c/salary D/11/11/2100";
        Command testCommand = testParser.parseCommand(userInput);
        assertEquals(testCommand.getClass(), AddIncomeCommand.class);
    }

    @Test
    public void betweenIncome_invalidDateRange_invalidCommand() {
        Parser testParser = new Parser();
        String userInput = "btw_in s/11/12/2100 e/11/11/2100";
        Command testCommand = testParser.parseCommand(userInput);
        assertEquals(testCommand.getClass(), InvalidCommand.class);
    }

    @Test
    public void betweenExpense_invalidDateRange_invalidCommand() {
        Parser testParser = new Parser();
        String userInput = "btw_ex s/11/12/2100 e/11/11/2100";
        Command testCommand = testParser.parseCommand(userInput);
        assertEquals(testCommand.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseAddExpenseCommand_invalidExpenseValue_invalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/food a/1000000000.001 c/food");
        assertSame(InvalidCommand.class, underTest.getClass());
    }

    @Test
    public void parseAddIncomeCommand_invalidIncomeValue_invalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_in d/salary a/1000000000.0011 c/salary");
        assertSame(InvalidCommand.class, underTest.getClass());
    }
    
    @Test
    public void parseCommand_validThresholdInput_correctCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("set_threshold t/0.5");
        assertSame(SetThresholdCommand.class, underTest.getClass());
    }

    @Test
    public void parseCommand_validClearAllCommand_correctCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("clear_all_entries");
        assertSame(ClearAllEntriesCommand.class, underTest.getClass());
    }

    @Test
    public void parseCommand_validShowGraphCommand_correctCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("show_graph");
        assertSame(ShowGraphCommand.class, underTest.getClass());
    }
    
}
