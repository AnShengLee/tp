package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.budget.BalanceCommand;
import seedu.commands.budget.SetThresholdCommand;
import seedu.commands.expense.AddExpenseCommand;
import seedu.commands.general.ClearAllEntriesCommand;
import seedu.commands.general.ShowGraphByYearCommand;
import seedu.commands.general.ShowGraphCommand;
import seedu.commands.income.AddIncomeCommand;
import seedu.commands.income.DeleteIncomeCommand;
import seedu.commands.general.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.expense.ListExpenseCommand;
import seedu.commands.income.ListIncomeCommand;
import seedu.commands.income.TotalIncomeCommand;
import seedu.utility.Messages;
import seedu.utility.Parser;

public class ParserTest {
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
        assertSame("Index given is either out of range or not an integer.", test.getMessage());
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
        assertSame("Index given is either out of range or not an integer.", test.getMessage());
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
    public void parseCommand_expenseInputWithDate_validCommand() {
        Parser testParser = new Parser();
        String userInput = "add_ex_d D/11/11/2121 d/asf a/10 c/food";
        Command testCommand = testParser.parseCommand(userInput);
        assertEquals(testCommand.getClass(), AddExpenseCommand.class);
    }

    @Test
    public void parseCommand_incomeInputWithoutDate_validCommand() {
        Parser testParser = new Parser();
        String userInput = "add_in c/salary d/a/g/adg/ad/gd/fag/ a/10";
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
        Command underTest = testParser.parseCommand("add_ex d/food a/1000000.001 c/food");
        assertSame(InvalidCommand.class, underTest.getClass());
    }

    @Test
    public void parseAddIncomeCommand_invalidIncomeValue_invalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_in d/salary a/1000000.0011 c/salary");
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

    @Test
    public void parseCommand_validYearInputShowGraphCommand_correctCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("show_graph Y/   2021    ");
        assertSame(ShowGraphByYearCommand.class, underTest.getClass());
    }

    @Test
    public void parseCommand_invalidYearInputShowGraphCommand_correctCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("show_graph Y/2023 12as d v ");
        assertEquals(Messages.INVALID_YEAR_MESSAGE,((InvalidCommand)underTest).getMessage());
    }
    
    @Test
    public void parseCommand_validExpenseInputD_C_A_validCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d//fa/gd/ff/s/f/sf/s/f/ c/food a/100");
        assertEquals(AddExpenseCommand.class, underTest.getClass());
    }

    @Test
    public void parseCommand_validExpenseInputC_D_A_validCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex c/food d//fa/gd/ff/s/f/sf/s/f/ a/100");
        assertEquals(AddExpenseCommand.class, underTest.getClass());
    }

    @Test
    public void parseCommand_validExpenseInputA_C_D_validCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex a/100 c/food d//fa/gd/ff/s/f/sf/s/f/");
        assertEquals(AddExpenseCommand.class, underTest.getClass());
    }
    
    @Test
    public void parseCommand_validBalanceCommand_validBalanceCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("balance");
        assertEquals(BalanceCommand.class, underTest.getClass());
    }
}
