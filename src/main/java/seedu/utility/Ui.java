package seedu.utility;

import seedu.commands.currency.CurrencyType;
import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.reminder.BudgetReminder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.utility.tools.DateOperator.DATE_FORMAT;
import static seedu.utility.tools.FinancialAdvisor.getRandomAdvice;

/**
 * Represents a user interface where feedbacks are given in response to user input.
 */
public class Ui {
    private final Scanner in;
    private static final String NEW_LINE = System.lineSeparator();


    /**
     * Constructor for Ui of the program with a Scanner object that is able to read user inputs.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads a new command from the user through the standard input and trim trailing spaces at the back.
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints the welcoming message for users that have entered the program.
     */
    public void printWelcome() {
        printLine();
        System.out.println(Messages.LOGO_MESSAGE);
        printLine();
        System.out.println(Messages.PROMPTING_MESSAGE);
    }

    private void printLine() {
        System.out.println(Messages.SEPARATOR_MESSAGE);
    }

    /**
     * Prints the given list of expenses to the standard output, else if its empty print the empty expense list message.
     *
     * @param expenses An ArrayList of expense elements.
     */
    public void listExpense(ArrayList<Expense> expenses) {
        printLine();
        if (expenses.isEmpty()) {
            printEmptyExpenseListMessage();
        } else {
            printExpenseList(expenses);
        }
        printLine();
    }

    /**
     * Prints the given list of incomes else if its empty print the empty income list message.
     * 
     * @param incomes An ArrayList of income elements.
     */
    public void listIncome(ArrayList<Income> incomes) {
        printLine();
        if (incomes.isEmpty()) {
            printEmptyIncomeListMessage();
        } else {
            printIncomeList(incomes);
        }
        printLine();
    }


    /**
     * Prints the given list of entries else if its empty print the no match found message.
     *
     * @param filteredEntries The entries that got filtered out from searching through the financial tracker.
     */
    public void listFind(ArrayList<Entry> filteredEntries) {
        printLine();
        if (filteredEntries.isEmpty()) {
            printNoMatchFoundMessage();
        } else {
            printFilteredList(filteredEntries);
        }
        printLine();
    }

    private void printNoMatchFoundMessage() {
        System.out.println(Messages.SEARCH_NO_MATCH_MESSAGE);
    }

    private void printEmptyIncomeListMessage() {
        System.out.println(Messages.EMPTY_INCOME_MESSAGE);
    }

    private void printEmptyExpenseListMessage() {
        System.out.println(Messages.EMPTY_EXPENSE_MESSAGE);
    }

    private void printFilteredList(ArrayList<Entry> filteredEntries) {
        assert filteredEntries.size() > 0;
        System.out.println(Messages.FOUND_LIST_MESSAGE);
        printLine();

        int i = 1;
        for (Entry entry : filteredEntries) {
            System.out.print(i + ": " + entry + NEW_LINE);
            i++;
        }
    }
    
    private void printIncomeList(ArrayList<Income> incomes) {
        assert incomes.size() > 0;
        System.out.println(Messages.LISTING_INCOME_MESSAGE);
        printLine();

        int i = 1;
        for (Income income : incomes) {
            System.out.print(i + ": " + income + NEW_LINE);
            i++;
        }
    }

    
    
    private void printExpenseList(ArrayList<Expense> expenses) {
        assert expenses.size() > 0;
        System.out.println(Messages.LISTING_EXPENSE_MESSAGE);
        printLine();

        int i = 1;
        for (Expense expense : expenses) {
            System.out.print(i + ": " + expense + NEW_LINE);
            i++;
        }
    }
    
    /**
     * Prints the total expense of all expenses in the financial tracker to the standard output.
     *
     * @param totalExpense The total value of the expenses in the financial tracker.
     */
    public void printTotalExpense(double totalExpense) {
        assert totalExpense >= 0;
        printLine();
        System.out.printf("Your total expense is: $%.2f" + NEW_LINE, totalExpense);
        printLine();
    }

    /**
     * Prints the total income of the financial tracker to the standard output.
     *
     * @param totalIncome The total value of the incomes in the financial tracker.
     */
    public void printTotalIncome(double totalIncome) {
        assert totalIncome >= 0;
        printLine();
        System.out.printf("Your total income is: $%.2f" + NEW_LINE, totalIncome);
        printLine();
    }

    /**
     * Prints the balance of the financial tracker to the standard output.
     *
     * @param balance The balance which is the net value of totalIncome and totalExpense in the financial tracker.
     */
    public void printBalance(double balance) {
        printLine();
        System.out.printf("Your current balance is: $%.2f" + NEW_LINE, balance);
        printLine();
    }
    
    /**
     * Prints the feedback message for adding an expense to the financial tracker.
     *
     * @param expense The expense to be added to the financial tracker.
     */
    public void printExpenseAdded(Expense expense) {
        printLine();
        System.out.println("Your most recent spending: " + NEW_LINE + expense);
        printLine();
    }

    /**
     * Prints the feedback message for deleting an expense from the financial tracker.
     *
     * @param expense The expense to be deleted from the financial tracker.
     */
    public void printExpenseDeleted(Expense expense) {
        printLine();
        System.out.println("You removed this: " + NEW_LINE + expense);
        printLine();
    }

    /**
     * Prints the feedback message for adding an income to the financial tracker.
     *
     * @param income The income to be added to the financial tracker.
     */
    public void printIncomeAdded(Income income) {
        printLine();
        System.out.println("Your most recent earning: " + NEW_LINE + income);
        printLine();
    }

    /**
     * Prints the feedback message for deleting an income from the financial tracker.
     *
     * @param income The income to be deleted from the financial tracker.
     */
    public void printIncomeDeleted(Income income) {
        printLine();
        System.out.println("You removed this: " + NEW_LINE + income);
        printLine();
    }

    /**
     * Prints the total expense between two dates.
     * If totalExpense is 0 print the no expense between two dates message.
     *
     * @param totalExpense The total value of the expenses in the financial tracker.
     * @param start The starting date (Left boundary).
     * @param end The ending date (Right boundary).
     */
    public void printTotalExpenseBetween(double totalExpense, LocalDate start, LocalDate end) {
        printLine();
        if (totalExpense == 0) {
            printNoExpenseBetweenMessage(start, end);
        } else {
            printExpenseBetweenMessage(totalExpense, start, end);
        }
        printLine();
    }

    /**
     * Prints the total income between two dates.
     * If totalIncome is 0 print the no income between two dates message.
     *
     * @param totalIncome The total value of the incomes in the financial tracker.
     * @param start The starting date (Left boundary).
     * @param end The ending date (Right boundary).
     */
    public void printTotalIncomeBetween(double totalIncome, LocalDate start, LocalDate end) {
        printLine();
        if (totalIncome == 0) {
            printNoIncomeBetweenMessage(start, end);
        } else {
            printIncomeBetweenMessage(totalIncome, start, end);
        }
        printLine();
    }

    private void printExpenseBetweenMessage(double totalExpense, LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String endString = end.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.printf("Your total expense between %s and %s is $%.2f", startString, endString, totalExpense);
        System.out.print(NEW_LINE);
    }

    private void printNoExpenseBetweenMessage(LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String endString = end.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.printf("You do not have any expense between %s and %s", startString, endString);
        System.out.print(NEW_LINE);
    }

    private void printIncomeBetweenMessage(double totalExpense, LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String endString = end.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.printf("Your total income between %s and %s is $%.2f", startString, endString, totalExpense);
        System.out.print(NEW_LINE);
    }

    private void printNoIncomeBetweenMessage(LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String endString = end.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.printf("You do not have any income between %s and %s", startString, endString);
        System.out.print(NEW_LINE);

    }

    /**
     * Prints the list of commands and their respective format to the standard output.
     */
    public void printHelp() {
        printLine();
        System.out.println(Messages.HELP_COMMAND_MESSAGE);
        printLine();
        for (String command:CommandFormat.commands) {
            System.out.println(command);
        }
        printLine();
    }

    /**
     * Prints the termination message of the STONKS XD program.
     *
     */
    public void printBye() {
        printLine();
        System.out.println(Messages.BYE_MESSAGE);
        System.out.println(NEW_LINE);
        System.out.println(Messages.TIP_HEADER + getRandomAdvice());
        printLine();
    }

    /**
     * Prints the error message as feedback through the standard output.
     *
     * @param errorMessage The error message to be printed out due to certain exceptions or invalid inputs.
     */
    public void printError(String errorMessage) {
        printLine();
        System.out.println(errorMessage);
        printLine();
    }

    /**
     * Prints the error message as feedback through the standard output, specifically for the loading of data process.
     *
     * @param errorMessage The error message to be printed out due to certain exceptions or invalid inputs.
     */
    public void printLoadingError(String errorMessage) {
        printLine();
        System.out.println(errorMessage);
    }

    /**
     * Prints a message to user through standard output confirming all entries have been cleared.
     */
    public void printAllEntriesCleared() {
        printLine();
        System.out.println(Messages.ALL_DATA_CLEARED);
        printLine();
    }

    /**
     * Prints the graph representing the yearly report.
     *
     * @param stonksGraph The graph representing the yearly report of the financial tracker.
     */
    public void printGraph(StonksGraph stonksGraph) {
        printLine();
        System.out.print(stonksGraph);
        printLine();
    }

    /**
     * Prints the budget reminder after an expense is added.
     * @param reminder BudgetReminder object containing appropriate reminder.
     */
    public void printBudgetReminder(BudgetReminder reminder) {
        System.out.println(reminder);
        printLine();
    }

    /**
     * Prints the budget setting advice after an attempt is made to set a new budget.
     * @param reminder BudgetReminder object containing appropriate advice
     */
    public void printSetBudgetReminder(BudgetReminder reminder) {
        printLine();
        System.out.println(reminder);
        printLine();
    }

    /**
     * Prints the budget for the given category.
     *
     * @param category The category of expense.
     * @param budgetLimit The budget limit for the given category.
     */
    public void printBudget(ExpenseCategory category, double budgetLimit) {
        printLine();
        System.out.printf("Current %s limit is $%.2f", category.toString(), budgetLimit);
        System.out.print(NEW_LINE);
        printLine();
    }

    /**
     * Prints the threshold set feedback for setting budgets.
     *
     * @param threshold The threshold for the budget.
     */
    public void printThresholdConfirmation(double threshold) {
        printLine();
        System.out.println("Threshold for budget reminders set to " + threshold);
        System.out.printf("We'll warn you when you spend %.1f%% of your budget!", threshold * 100);
        System.out.print(NEW_LINE);
        printLine();
    }

    /**
     * Prints confirmation message upon successful conversion of entries.
     * @param currency tracks currency type of entries
     */
    public void printCurrencyChangedConfirmation(CurrencyType currency) {
        printLine();
        System.out.println(Messages.CURRENCY_CONVERSION_SUCCESSFUL_MESSAGE + currency + "!");
        printLine();
    }


    /**
     * Prints error message if trying to convert again to the same currency type.
     * @param currency tracks currency type of entries
     */
    public void printSameCurrencyTypeMessage(CurrencyType currency) {
        printLine();
        System.out.println(Messages.SAME_CURRENCY_TYPE_MESSAGE + "- " + currency);
        printLine();
    }

    /**
     * Prints the current currency type of all entries.
     * @param currency tracks currency type of entries
     */
    public void printCurrentCurrency(CurrencyType currency) {
        printLine();
        System.out.println(Messages.CURRENT_CURRENCY_MESSAGE + currency.toString());
        printLine();
    }

    /**
     * Prints out list of available currency conversions.
     * @param currencyTypes enum of all allowed currency types
     */
    public void printCurrencyTypes(ArrayList<CurrencyType> currencyTypes) {
        printLine();
        System.out.println(Messages.AVAILABLE_CURRENCIES_MESSAGE);
        int i = 0;
        for (CurrencyType currencyType : currencyTypes) {
            System.out.println(i + 1 + ". " + currencyType);
            i++;
        }
        printLine();
    }
}
