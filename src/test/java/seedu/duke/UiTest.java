package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.commands.general.FindCommand;
import seedu.entry.Entry;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.entry.ExpenseCategory;
import seedu.entry.Expense;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Messages;
import seedu.utility.StonksGraph;
import seedu.utility.Ui;
import seedu.utility.BudgetManager;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
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
    private static final String currentDate =
            "(" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    
    private final Ui testUI = new Ui();
    private CurrencyManager currencyManager = new CurrencyManager();
    private final FinancialTracker financialTracker = new FinancialTracker(currencyManager);
    private BudgetManager budgetManager = new BudgetManager();

    public void initialiseFinancialTracker() throws IncomeOverflowException, ExpenseOverflowException {
        financialTracker.addIncome(new Income("Paycheck August", 25.0, IncomeCategory.SALARY));
        financialTracker.addExpense(new Expense("Bought a game", 19.73, ExpenseCategory.FOOD));
        financialTracker.addExpense(new Expense("Bought cookies", 5.0, ExpenseCategory.FOOD));
        financialTracker.addExpense(new Expense("Bought cakes", 7.0, ExpenseCategory.FOOD));
        financialTracker.addIncome(new Income("Rob a bank", 2000.0, IncomeCategory.ADHOC));
        financialTracker.addIncome(new Income("Paycheck July", 25.0, IncomeCategory.SALARY));
    }
    
    @Test
        public void listExpense_validFinancialTracker_filteredExpenses() 
            throws ExpenseOverflowException, IncomeOverflowException {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all of your recent spending!" + newLine 
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + "2: [E] Bought cookies - $5.00 " + currentDate + newLine 
                + "3: [E] Bought cakes - $7.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        
        testUI.listExpense(financialTracker.getExpenses());

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listIncome_validFinancialTracker_filteredIncomes() 
            throws ExpenseOverflowException, IncomeOverflowException {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine 
                + "Below is a list of all of your recent earnings!" + newLine
                + SEPARATOR_LINE + newLine 
                + "1: [I] Paycheck August - $25.00 " + currentDate + newLine
                + "2: [I] Rob a bank - $2000.00 " + currentDate + newLine
                + "3: [I] Paycheck July - $25.00 " + currentDate + newLine
                + SEPARATOR_LINE;

        testUI.listIncome(financialTracker.getIncomes());

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listFind_givenFilteredList_printFilteredList() 
            throws ExpenseOverflowException, IncomeOverflowException {
        initialiseFinancialTracker();
        String expectedOutput = SEPARATOR_LINE + newLine
                + Messages.FOUND_LIST_MESSAGE + newLine
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + "2: [E] Bought cookies - $5.00 " + currentDate + newLine
                + "3: [E] Bought cakes - $7.00 " + currentDate + newLine
                + "4: [I] Paycheck August - $25.00 " + currentDate + newLine
                + "5: [I] Rob a bank - $2000.00 " + currentDate + newLine
                + "6: [I] Paycheck July - $25.00 " + currentDate + newLine
                + SEPARATOR_LINE;

        testUI.listFind(financialTracker.getEntries());
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void printExpenseAdded_addedOneItem_expenseAddedFeedback() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your most recent spending: " + newLine
                + "[E] Bought cookies - $5.00 " + currentDate + newLine
                + SEPARATOR_LINE;

        testUI.printExpenseAdded(new Expense("Bought cookies", 5.0, ExpenseCategory.FOOD));
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printIncomeAdded_addedOneItem_incomeAddedFeedback() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your most recent earning: " + newLine
                + "[I] Salary August - $5.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        testUI.printIncomeAdded(new Income("Salary August", 5.0, IncomeCategory.SALARY));
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printBalance_oneExpenseOneIncome_printNetBalance() 
            throws ExpenseOverflowException, IncomeOverflowException {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your current balance is: $2018.27" + newLine
                + SEPARATOR_LINE;
        
        testUI.printBalance(financialTracker.calculateBalance());
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listExpense_emptyFinancialTracker_emptyExpenseListMessage() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "You have not spent anything!" + newLine
                + SEPARATOR_LINE;

        testUI.listExpense(financialTracker.getExpenses());

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listIncome_emptyFinancialTracker_emptyIncomeListMessage() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "You have not entered any income!" + newLine
                + SEPARATOR_LINE;

        testUI.listIncome(financialTracker.getIncomes());

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listFind_emptyFilteredList_notFoundMessage() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your search did not match any of the entries!" + newLine
                + SEPARATOR_LINE;
        ArrayList<Entry> entries = new ArrayList<>();
        testUI.listFind(entries);

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }


    @Test
    public void printTotalExpense_doubleExpense_totalExpenseMessage() {
        double totalExpense = 98.72923;
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Your total expense is: $98.73" + newLine
                + SEPARATOR_LINE;
        testUI.printTotalExpense(totalExpense);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printTotalIncome_doubleIncome_totalIncomeMessage() {
        double totalIncome = 98.72923;
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Your total income is: $98.73" + newLine
                + SEPARATOR_LINE;
        testUI.printTotalIncome(totalIncome);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void expenseDeleted_oneExpenseDeleted_deletedExpenseMessage() 
            throws ExpenseOverflowException, IncomeOverflowException {
        initialiseFinancialTracker();
        String expectedOutput = SEPARATOR_LINE + newLine
                + "You removed this: " + newLine
                + "[E] chocolate - $56.12 " + currentDate + newLine
                + SEPARATOR_LINE;
        Expense toBeDeletedExpense = new Expense("chocolate", 56.12, ExpenseCategory.FOOD);
        testUI.printExpenseDeleted(toBeDeletedExpense);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void incomeDeleted_oneIncomeDeleted_deletedIncomeMessage() 
            throws ExpenseOverflowException, IncomeOverflowException {
        initialiseFinancialTracker();
        String expectedOutput = SEPARATOR_LINE + newLine
                + "You removed this: " + newLine
                + "[I] august paycheck - $567.12 " + currentDate + newLine
                + SEPARATOR_LINE;
        Income toBeDeletedIncome = new Income("august paycheck", 567.12, IncomeCategory.SALARY);
        testUI.printIncomeDeleted(toBeDeletedIncome);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printTotalExpenseBetween_noExpenseBetween_printNoExpenseBetweenMessage() {
        String expectedOutput = SEPARATOR_LINE + newLine
                + "You do not have any expense between 30/08/2090 and 30/08/2092" + newLine
                + SEPARATOR_LINE;
        LocalDate testDate1 = LocalDate.of(2090,8,30);
        LocalDate testDate2 = LocalDate.of(2092,8,30);

        double totalExpense = 0;
        testUI.printTotalExpenseBetween(totalExpense, testDate1, testDate2);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printTotalExpenseBetween_gotExpenseBetween_printTotalExpenseBetweenMessage() {
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Your total expense between 30/08/2090 and 30/08/2092 is $7512.00" + newLine
                + SEPARATOR_LINE;
        LocalDate testDate1 = LocalDate.of(2090,8,30);
        LocalDate testDate2 = LocalDate.of(2092,8,30);

        double totalExpense = 7512;
        testUI.printTotalExpenseBetween(totalExpense, testDate1, testDate2);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printTotalIncomeBetween_noIncomeBetween_printNoIncomeBetweenMessage() {
        String expectedOutput = SEPARATOR_LINE + newLine
                + "You do not have any income between 30/08/2090 and 30/08/2092" + newLine
                + SEPARATOR_LINE;
        LocalDate testDate1 = LocalDate.of(2090,8,30);
        LocalDate testDate2 = LocalDate.of(2092,8,30);

        double totalIncome = 0;
        testUI.printTotalIncomeBetween(totalIncome, testDate1, testDate2);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printTotalIncomeBetween_gotIncomeBetween_printTotalIncomeBetweenMessage() {
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Your total income between 30/08/2090 and 30/08/2092 is $988.10" + newLine
                + SEPARATOR_LINE;
        LocalDate testDate1 = LocalDate.of(2090,8,30);
        LocalDate testDate2 = LocalDate.of(2092,8,30);

        double totalIncome = 988.1;
        testUI.printTotalIncomeBetween(totalIncome, testDate1, testDate2);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void printGraph_validStonksGraph_printCorrectGraph() {
        //empty financialtracker
        StonksGraph stonksGraph = new StonksGraph(financialTracker,LocalDate.now().getYear());
        String expectedOutput = SEPARATOR_LINE
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                + "x                                                                                                  x"
                + "x   Account Balance: $0.00                                                 Legend:                 x"
                + "x   Current month total expense: $0.00                                # is Expense      x"
                + "x   Current month total income: $0.00                                 o is Income       x"
                + "x   Your Year Report                                                       Unit: 0.01              x"
                + "x ------------------------------------------------------------------------------------------------ x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x                                                                                                  x"
                + "x ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ x"
                + "x   Jan     Feb     Mar     Apr     May     Jun     Jul     Aug     Sept    Oct     Nov     Dec    x"
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                + SEPARATOR_LINE;
        testUI.printGraph(stonksGraph);

        String fullOutput = outputStreamCaptor.toString().trim();
        String fullOutputWithoutNewLine = fullOutput.replace(System.lineSeparator(),"");
        String outputToBeTested = fullOutputWithoutNewLine.replaceAll("h.*?t","h t");

        assertEquals(expectedOutput, outputToBeTested);
    }

    @Test
    public void printBudget_givenBudget_printBudgetMsg() {
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Current FOOD limit is $58.71" + newLine
                + SEPARATOR_LINE;
        testUI.printBudget(ExpenseCategory.FOOD, 58.71);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printThresholdConfirmation_validThreshold_printThresholdMsg() {
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Threshold for budget reminders set to 0.58" + newLine
                + "We'll warn you when you spend 58.0% of your budget!" + newLine
                + SEPARATOR_LINE;
        testUI.printThresholdConfirmation(0.58);
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void filterByKeyword_testFood_printOnlyFoodEntries() 
            throws ExpenseOverflowException, IncomeOverflowException {
        FindCommand testFindCommand = new FindCommand("food");
        initialiseFinancialTracker();
        testFindCommand.execute(financialTracker, testUI, budgetManager, currencyManager);
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all your findings!" + newLine
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + "2: [E] Bought cookies - $5.00 " + currentDate + newLine
                + "3: [E] Bought cakes - $7.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void filterByKeyword_testWordCasing_printFoodEntries() 
            throws ExpenseOverflowException, IncomeOverflowException {
        FindCommand testFindCommand = new FindCommand("FOod");
        initialiseFinancialTracker();
        testFindCommand.execute(financialTracker, testUI, budgetManager, currencyManager);
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all your findings!" + newLine
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + "2: [E] Bought cookies - $5.00 " + currentDate + newLine
                + "3: [E] Bought cakes - $7.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void filterByDate_dateGotMatch_printOnlyEntriesOfThatDate() 
            throws IncomeOverflowException, ExpenseOverflowException {
        String currDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        FindCommand testFindCommand = new FindCommand(currDate);
        initialiseFinancialTracker();
        LocalDate date = LocalDate.parse("11/11/2121", DateTimeFormatter.ofPattern(DATE_FORMAT));
        Income incomeWithDiffDate = new Income("Paycheck August", 25.0, IncomeCategory.SALARY, date);
        financialTracker.addIncome(incomeWithDiffDate);
        testFindCommand.execute(financialTracker, testUI, budgetManager, currencyManager);
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all your findings!" + newLine
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + "2: [E] Bought cookies - $5.00 " + currentDate + newLine
                + "3: [E] Bought cakes - $7.00 " + currentDate + newLine
                + "4: [I] Paycheck August - $25.00 " + currentDate + newLine
                + "5: [I] Rob a bank - $2000.00 " + currentDate + newLine
                + "6: [I] Paycheck July - $25.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void filterByDate_dateNoMatch_printNoEntryFound() 
            throws ExpenseOverflowException, IncomeOverflowException {
        FindCommand testFindCommand = new FindCommand("25/10/2099");
        initialiseFinancialTracker();
        testFindCommand.execute(financialTracker, testUI, budgetManager, currencyManager);
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Your search did not match any of the entries!" + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void filterByKeyword_matchInDescription_printEntriesFound() 
            throws ExpenseOverflowException, IncomeOverflowException {
        FindCommand testFindCommand = new FindCommand("game");
        initialiseFinancialTracker();
        testFindCommand.execute(financialTracker, testUI, budgetManager, currencyManager);
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all your findings!" + newLine
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void filterByKeyword_matchInAmount_printEntriesFound() 
            throws ExpenseOverflowException, IncomeOverflowException {
        FindCommand testFindCommand = new FindCommand("19.73");
        initialiseFinancialTracker();
        testFindCommand.execute(financialTracker, testUI, budgetManager, currencyManager);
        String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all your findings!" + newLine
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + SEPARATOR_LINE;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}

