package seedu.utility;

import seedu.commands.currency.ListCurrencyTypesCommand;
import seedu.commands.expense.AddExpenseCommand;
import seedu.commands.currency.CheckCurrentCurrencyCommand;
import seedu.commands.income.AddIncomeCommand;
import seedu.commands.budget.CheckBudgetCommand;
import seedu.commands.general.ClearAllEntriesCommand;
import seedu.commands.Command;
import seedu.commands.currency.CurrencyConversionCommand;
import seedu.commands.currency.CurrencyType;
import seedu.commands.expense.DeleteExpenseCommand;
import seedu.commands.income.DeleteIncomeCommand;
import seedu.commands.general.ExitCommand;
import seedu.commands.general.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.expense.ListExpenseCommand;
import seedu.commands.income.ListIncomeCommand;
import seedu.commands.budget.SetBudgetCommand;
import seedu.commands.budget.SetThresholdCommand;
import seedu.commands.general.ShowGraphCommand;
import seedu.commands.expense.TotalExpenseBetweenCommand;
import seedu.commands.expense.TotalExpenseCommand;
import seedu.commands.income.TotalIncomeBetweenCommand;
import seedu.commands.income.TotalIncomeCommand;
import seedu.commands.general.FindCommand;
import seedu.commands.budget.BalanceCommand;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.BlankExpenseCategoryException;
import seedu.exceptions.BlankIncomeCategoryException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseCategoryException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidExpenseDescriptionException;
import seedu.exceptions.InvalidExpenseIndexException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeCategoryException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidIncomeDescriptionException;
import seedu.exceptions.InvalidIncomeIndexException;
import seedu.exceptions.InvalidInputAmountValueException;
import seedu.exceptions.InvalidSettingsDataException;
import seedu.exceptions.InvalidThresholdValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.utility.datetools.DateOperator.isValidDateRange;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_EXPENSE_ARGUMENT_FORMAT =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)");

    private static final Pattern ADD_EXPENSE_ARGUMENT_FORMAT_WITH_DATE =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)"
                    + " D/(?<date>.+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)");

    private static final Pattern ADD_INCOME_ARGUMENT_FORMAT_WITH_DATE =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)"
                    + " D/(?<date>.+)");
    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern DELETE_EXPENSE_ARGUMENT_FORMAT =
            Pattern.compile("i/(?<index>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern DELETE_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("i/(?<index>[^/]+)");

    private static final Pattern DATE_RANGE_ARGUMENT_FORMAT =
            Pattern.compile("s/(?<start>.+)"
                    + " e/(?<end>.+)");

    private static final Pattern SET_BUDGET_ARGUMENT_FORMAT =
            Pattern.compile("c/(?<category>[^/]+)"
                    + " a/(?<amount>[^/]+)");

    private static final Pattern CHECK_BUDGET_ARGUMENT_FORMAT =
            Pattern.compile("c/(?<category>[^/]+)");

    private static final Pattern SET_THRESHOLD_ARGUMENT_FORMAT =
            Pattern.compile("t/(?<threshold>[^/]+)");

    private static final Pattern CURRENCY_CONVERSION_FORMAT =
            Pattern.compile("c/(?<currency>.+)");

    private static final String HELP_COMMAND_KEYWORD = "help";
    private static final String ADD_EXPENSE_KEYWORD = "add_ex";
    private static final String ADD_INCOME_KEYWORD = "add_in";
    private static final String DELETE_EXPENSE_KEYWORD = "del_ex";
    private static final String DELETE_INCOME_KEYWORD = "del_in";
    private static final String LIST_EXPENSE_KEYWORD = "list_ex";
    private static final String LIST_INCOME_KEYWORD = "list_in";
    private static final String TOTAL_EXPENSE_KEYWORD = "total_ex";
    private static final String TOTAL_INCOME_KEYWORD = "total_in";
    private static final String FIND_KEYWORD = "find";
    private static final String BALANCE_KEYWORD = "balance";
    private static final String EXIT_KEYWORD = "end";
    private static final String EXPENSE_RANGE_KEYWORD = "btw_ex";
    private static final String INCOME_RANGE_KEYWORD = "btw_in";
    private static final String SHOW_GRAPH_KEYWORD = "show_graph";
    private static final String CLEAR_ALL_ENTRIES_KEYWORD = "clear_all_entries";
    private static final String SET_BUDGET_KEYWORD = "set_budget";
    private static final String CHECK_BUDGET_KEYWORD = "check_budget";
    private static final String SET_THRESHOLD_KEYWORD = "set_threshold";
    private static final String CONVERT_CURRENCY_KEYWORD = "set_curr";
    private static final String CHECK_CURRENT_CURRENCY_KEYWORD = "check_curr";
    private static final String LIST_CURRENCY_TYPES_KEYWORD = "list_curr";
    
    private static final String DATA_SEPARATOR = ",";
    private static final Pattern EXPENSE_DATA_FORMAT
            = Pattern.compile("E" + DATA_SEPARATOR + "(?<description>.+)" + DATA_SEPARATOR
            + "(?<amount>.+)" + DATA_SEPARATOR + "(?<category>.+)" + DATA_SEPARATOR + "(?<date>.+)");
    private static final Pattern INCOME_DATA_FORMAT
            = Pattern.compile("I" + DATA_SEPARATOR + "(?<description>.+)" + DATA_SEPARATOR
            + "(?<amount>.+)" + DATA_SEPARATOR + "(?<category>.+)" + DATA_SEPARATOR + "(?<date>.+)");
    private static final Pattern SETTINGS_DATA_FORMAT = Pattern.compile("(?<currency>.+)" + DATA_SEPARATOR
            + "(?<threshold>.+)" + DATA_SEPARATOR + "(?<food>.+)" + DATA_SEPARATOR + "(?<transport>.+)" 
            + DATA_SEPARATOR + "(?<medical>.+)" + DATA_SEPARATOR + "(?<bills>.+)" + DATA_SEPARATOR 
            + "(?<entertainment>.+)" + DATA_SEPARATOR + "(?<misc>.+)" + DATA_SEPARATOR + "(?<overall>.+)");

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final double INPUT_AMOUNT_LIMIT = 1000000000;

    /**
     * Parses user input into command for execution.
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();
        
        if (isExpenseRelatedCommand(commandWord)) {
            return prepareExpenseRelatedCommand(commandWord, arguments);
        } else if (isIncomeRelatedCommand(commandWord)) {
            return prepareIncomeRelatedCommand(commandWord, arguments);
        } else if (isGeneralRelatedCommand(commandWord)) {
            return prepareGeneralRelatedCommand(commandWord, arguments);
        } else if (isBudgetRelatedCommand(commandWord)) {
            return prepareBudgetRelatedCommand(commandWord, arguments);
        } else if (isCurrencyRelatedCommand(commandWord)) {
            return prepareCurrencyRelatedCommand(commandWord, arguments);
        } else {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private boolean isExpenseRelatedCommand(String commandWord) {
        return (commandWord.equals(ADD_EXPENSE_KEYWORD) || commandWord.equals(DELETE_EXPENSE_KEYWORD)
                || commandWord.equals(LIST_EXPENSE_KEYWORD) || commandWord.equals(TOTAL_EXPENSE_KEYWORD)
                || commandWord.equals(EXPENSE_RANGE_KEYWORD));
    }

    private boolean isIncomeRelatedCommand(String commandWord) {
        return (commandWord.equals(ADD_INCOME_KEYWORD) || commandWord.equals(DELETE_INCOME_KEYWORD)
                || commandWord.equals(LIST_INCOME_KEYWORD) || commandWord.equals(TOTAL_INCOME_KEYWORD)
                || commandWord.equals(INCOME_RANGE_KEYWORD));
    }

    private boolean isGeneralRelatedCommand(String commandWord) {
        return (commandWord.equals(HELP_COMMAND_KEYWORD) || commandWord.equals(FIND_KEYWORD)
                || commandWord.equals(EXIT_KEYWORD) || commandWord.equals(SHOW_GRAPH_KEYWORD)
                || commandWord.equals(CLEAR_ALL_ENTRIES_KEYWORD));
    }

    private boolean isBudgetRelatedCommand(String commandWord) {
        return (commandWord.equals(BALANCE_KEYWORD) || commandWord.equals(SET_BUDGET_KEYWORD)
                || commandWord.equals(CHECK_BUDGET_KEYWORD) || commandWord.equals(SET_THRESHOLD_KEYWORD));
    }

    private boolean isCurrencyRelatedCommand(String commandWord) {
        return (commandWord.equals(CHECK_CURRENT_CURRENCY_KEYWORD) || commandWord.equals(CONVERT_CURRENCY_KEYWORD)
                || commandWord.equals(LIST_CURRENCY_TYPES_KEYWORD));
    }

    private Command prepareExpenseRelatedCommand(String commandWord, String arguments) {
        switch (commandWord) {
        case ADD_EXPENSE_KEYWORD:
            final Matcher matcherWithoutDate = ADD_EXPENSE_ARGUMENT_FORMAT.matcher(arguments);
            final Matcher matcherWithDate = ADD_EXPENSE_ARGUMENT_FORMAT_WITH_DATE.matcher(arguments);
            if (matcherWithoutDate.matches()) {
                return prepareAddExpenseWithoutDate(matcherWithoutDate);
            } else if (matcherWithDate.matches()) {
                return prepareAddExpenseWithDate(matcherWithDate);
            } else {
                return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
            }
        case DELETE_EXPENSE_KEYWORD:
            return prepareDeleteExpense(arguments);
        case LIST_EXPENSE_KEYWORD:
            return prepareListExpense(arguments);
        case TOTAL_EXPENSE_KEYWORD:
            return prepareTotalExpense(arguments);
        case EXPENSE_RANGE_KEYWORD:
            return prepareExpenseRange(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareIncomeRelatedCommand(String commandWord, String arguments) {
        switch (commandWord) {
        case ADD_INCOME_KEYWORD:
            final Matcher matcherWithoutDate = ADD_INCOME_ARGUMENT_FORMAT.matcher(arguments);
            final Matcher matcherWithDate = ADD_INCOME_ARGUMENT_FORMAT_WITH_DATE.matcher(arguments);
            if (matcherWithoutDate.matches()) {
                return prepareAddIncomeWithoutDate(matcherWithoutDate);
            } else if (matcherWithDate.matches()) {
                return prepareAddIncomeWithDate(matcherWithDate);
            } else {
                return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
            }
        case DELETE_INCOME_KEYWORD:
            return prepareDeleteIncome(arguments);
        case LIST_INCOME_KEYWORD:
            return prepareListIncome(arguments);
        case TOTAL_INCOME_KEYWORD:
            return prepareTotalIncome(arguments);
        case INCOME_RANGE_KEYWORD:
            return prepareIncomeRange(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareBudgetRelatedCommand(String commandWord, String arguments) {
        switch (commandWord) {
        case BALANCE_KEYWORD:
            return prepareBalance(arguments);
        case SET_BUDGET_KEYWORD:
            return prepareSetBudget(arguments);
        case CHECK_BUDGET_KEYWORD:
            return prepareCheckBudget(arguments);
        case SET_THRESHOLD_KEYWORD:
            return prepareSetThreshold(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareCurrencyRelatedCommand(String commandWord, String arguments) {
        switch (commandWord) {
        case CHECK_CURRENT_CURRENCY_KEYWORD:
            return prepareCheckCurrentCurrency(arguments);
        case CONVERT_CURRENCY_KEYWORD:
            return prepareConvertCurrency(arguments);
        case LIST_CURRENCY_TYPES_KEYWORD:
            return prepareListCurrencyTypes(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareGeneralRelatedCommand(String commandWord, String arguments) {
        switch (commandWord) {
        case HELP_COMMAND_KEYWORD:
            return prepareHelp(arguments);
        case FIND_KEYWORD:
            return prepareFind(arguments);
        case EXIT_KEYWORD:
            return prepareExit(arguments);
        case SHOW_GRAPH_KEYWORD:
            return prepareShowGraph(arguments);
        case CLEAR_ALL_ENTRIES_KEYWORD:
            return prepareClearAllEntries(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareIncomeRange(String arguments) {
        final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }
        try {
            String start = matcher.group("start").trim();
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern(DATE_FORMAT));
            String end = matcher.group("end").trim();
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern(DATE_FORMAT));
            if (isValidDateRange(startDate,endDate)) {
                return new TotalIncomeBetweenCommand(startDate, endDate);
            }
            return new InvalidCommand(Messages.INVALID_DATE_RANGE_MESSAGE);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }

    private Command prepareExpenseRange(String arguments) {
        final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }
        try {
            String start = matcher.group("start").trim();
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern(DATE_FORMAT));
            String end = matcher.group("end").trim();
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern(DATE_FORMAT));
            if (isValidDateRange(startDate,endDate)) {
                return new TotalExpenseBetweenCommand(startDate, endDate);
            } 
            return new InvalidCommand(Messages.INVALID_DATE_RANGE_MESSAGE);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }

    private Command prepareBalance(String arguments) {
        if (arguments.isBlank()) {
            return new BalanceCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareFind(String arguments) {
        if (arguments.isBlank()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        return new FindCommand(arguments);
    }

    private Command prepareHelp(String arguments) {
        if (arguments.isBlank()) {
            return new HelpCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareAddExpenseWithoutDate(Matcher matcher) {
        try {
            double expenseAmount = extractExpenseAmount(matcher);
            String expenseDescription = extractExpenseDescription(matcher);
            ExpenseCategory expenseCategory = extractExpenseCategory(matcher);
            Expense expense = new Expense(expenseDescription, expenseAmount, expenseCategory);
            assert !expenseCategory.equals(ExpenseCategory.NULL);
            return new AddExpenseCommand(expense);
        } catch (InputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareAddExpenseWithDate(Matcher matcher) {
        try {
            double expenseAmount = extractExpenseAmount(matcher);
            String expenseDescription = extractExpenseDescription(matcher);
            ExpenseCategory expenseCategory = extractExpenseCategory(matcher);
            LocalDate expenseDate = extractExpenseDate(matcher);
            Expense expense = new Expense(expenseDescription, expenseAmount, expenseCategory, expenseDate);
            assert !expenseCategory.equals(ExpenseCategory.NULL);
            return new AddExpenseCommand(expense);
        } catch (InputException e) {
            return new InvalidCommand(e.getMessage());
        } catch (DateTimeParseException de) {
            return new InvalidCommand(Messages.INVALID_DATE_FORMAT);
        }
    }

    private boolean isMatch(Matcher matcher) {
        return matcher.matches();
    }

    private ExpenseCategory extractExpenseCategory(Matcher matcher) throws
            BlankExpenseCategoryException, InvalidExpenseCategoryException {
        String expenseCategory = matcher.group("category").trim();
        if (expenseCategory.isBlank()) {
            throw new BlankExpenseCategoryException(Messages.BLANK_CATEGORY_MESSAGE);
        }
        switch (expenseCategory.toUpperCase()) {
        case "FOOD":
            return ExpenseCategory.FOOD;
        case "TRANSPORT":
            return ExpenseCategory.TRANSPORT;
        case "MEDICAL":
            return ExpenseCategory.MEDICAL;
        case "BILLS":
            return ExpenseCategory.BILLS;
        case "ENTERTAINMENT":
            return ExpenseCategory.ENTERTAINMENT;
        case "MISC":
            return ExpenseCategory.MISC;
        default:
            throw new InvalidExpenseCategoryException(Messages.INVALID_EXPENSE_CATEGORY_MESSAGE);
        }
    }

    private String extractExpenseDescription(Matcher matcher) throws
            InvalidExpenseDescriptionException {
        String expenseDescription = matcher.group("description").trim();
        if (expenseDescription.isBlank()) {
            throw new InvalidExpenseDescriptionException(Messages.BLANK_DESCRIPTION_MESSAGE);
        }
        return expenseDescription;
    }

    private double extractExpenseAmount(Matcher matcher) 
            throws InvalidExpenseAmountException, InvalidInputAmountValueException {
        String userGivenAmount = matcher.group("amount").trim();
        double expenseAmount = parseExpenseAmount(userGivenAmount);
        if (expenseAmount > INPUT_AMOUNT_LIMIT) { 
            throw new InvalidInputAmountValueException(Messages.INVALID_EXPENSE_VALUE);
        }
        return expenseAmount;
    }

    private Command prepareAddIncomeWithoutDate(Matcher matcher) {
        try {
            double incomeAmount = extractIncomeAmount(matcher);
            String incomeDescription = extractIncomeDescription(matcher);
            IncomeCategory incomeCategory = extractIncomeCategory(matcher);
            Income income = new Income(incomeDescription, incomeAmount, incomeCategory);
            assert !incomeCategory.equals(IncomeCategory.NULL);
            return new AddIncomeCommand(income);
        } catch (InputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareAddIncomeWithDate(Matcher matcher) {
        try {
            double incomeAmount = extractIncomeAmount(matcher);
            String incomeDescription = extractIncomeDescription(matcher);
            IncomeCategory incomeCategory = extractIncomeCategory(matcher);
            LocalDate incomeDate = extractIncomeDate(matcher);
            Income income = new Income(incomeDescription, incomeAmount, incomeCategory, incomeDate);
            assert !incomeCategory.equals(IncomeCategory.NULL);
            return new AddIncomeCommand(income);
        } catch (InputException e) {
            return new InvalidCommand(e.getMessage());
        } catch (DateTimeParseException de) {
            return new InvalidCommand(Messages.INVALID_DATE_FORMAT);
        }
    }

    private IncomeCategory extractIncomeCategory(Matcher matcher) throws
            BlankIncomeCategoryException, InvalidIncomeCategoryException {
        String incomeCategory = matcher.group("category").trim();
        if (incomeCategory.isBlank()) {
            throw new BlankIncomeCategoryException(Messages.BLANK_CATEGORY_MESSAGE);
        }
        switch (incomeCategory.toUpperCase()) {
        case "ALLOWANCE":
            return IncomeCategory.ALLOWANCE;
        case "SALARY":
            return IncomeCategory.SALARY;
        case "ADHOC":
            return IncomeCategory.ADHOC;
        case "OTHERS":
            return IncomeCategory.OTHERS;
        default:
            throw new InvalidIncomeCategoryException(Messages.INVALID_INCOME_CATEGORY_MESSAGE);
        }
    }

    private String extractIncomeDescription(Matcher matcher) throws
            InvalidIncomeDescriptionException {
        String incomeDescription = matcher.group("description").trim();
        if (incomeDescription.isBlank()) {
            throw new InvalidIncomeDescriptionException(Messages.BLANK_DESCRIPTION_MESSAGE);
        }
        return incomeDescription;
    }

    private double extractIncomeAmount(Matcher matcher) 
            throws InvalidIncomeAmountException, InvalidInputAmountValueException {
        String userGivenAmount = matcher.group("amount").trim();
        double incomeAmount = parseIncomeAmount(userGivenAmount);
        if (incomeAmount > INPUT_AMOUNT_LIMIT) {
            throw new InvalidInputAmountValueException(Messages.INVALID_INCOME_VALUE);
        } 
        return incomeAmount;
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareDeleteExpense(String arguments) {
        final Matcher matcher = DELETE_EXPENSE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }

        String userGivenIndex = matcher.group("index").trim();
        int deleteExpenseIndex;
        try {
            deleteExpenseIndex = parseExpenseIndex(userGivenIndex);
        } catch (InvalidExpenseIndexException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert deleteExpenseIndex >= 1;

        return new DeleteExpenseCommand(deleteExpenseIndex);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareDeleteIncome(String arguments) {
        final Matcher matcher = DELETE_INCOME_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }

        String userGivenIndex = matcher.group("index").trim();
        int deleteIncomeIndex;
        try {
            deleteIncomeIndex = parseIncomeIndex(userGivenIndex);
        } catch (InvalidIncomeIndexException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert deleteIncomeIndex >= 1;

        return new DeleteIncomeCommand(deleteIncomeIndex);
    }

    private Command prepareListExpense(String arguments) {
        if (arguments.isBlank()) {
            return new ListExpenseCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareListIncome(String arguments) {
        if (arguments.isBlank()) {
            return new ListIncomeCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareTotalExpense(String arguments) {
        if (arguments.isBlank()) {
            return new TotalExpenseCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareTotalIncome(String arguments) {
        if (arguments.isBlank()) {
            return new TotalIncomeCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareClearAllEntries(String arguments) {
        if (arguments.isBlank()) {
            return new ClearAllEntriesCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareShowGraph(String arguments) {
        if (arguments.isBlank()) {
            return new ShowGraphCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareCheckCurrentCurrency(String arguments) {
        if (arguments.isBlank()) {
            return new CheckCurrentCurrencyCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareExit(String arguments) {
        if (arguments.isBlank()) {
            return new ExitCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private double parseExpenseAmount(String userGivenAmount) throws InvalidExpenseAmountException {
        double expenseAmount;
        try {
            expenseAmount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidExpenseAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (hasMoreThanTwoDecimalPlaces(userGivenAmount)) {
            throw new InvalidExpenseAmountException(Messages.TOO_MANY_DP_MESSAGE);
        } else if (expenseAmount <= 0) {
            throw new InvalidExpenseAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        } else if (Double.isNaN(expenseAmount) || Double.isInfinite(expenseAmount)) {
            throw new InvalidExpenseAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        assert expenseAmount > 0;
        return expenseAmount;
    }

    private double parseIncomeAmount(String userGivenAmount) throws InvalidIncomeAmountException {
        double incomeAmount;
        try {
            incomeAmount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidIncomeAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (hasMoreThanTwoDecimalPlaces(userGivenAmount)) {
            throw new InvalidIncomeAmountException(Messages.TOO_MANY_DP_MESSAGE);
        } else if (incomeAmount <= 0) {
            throw new InvalidIncomeAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        } else if (Double.isNaN(incomeAmount) || Double.isInfinite(incomeAmount)) {
            throw new InvalidIncomeAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        assert incomeAmount > 0;
        return incomeAmount;
    }
    
    private boolean hasMoreThanTwoDecimalPlaces(String userGivenAmount) {
        boolean hasDecimal = userGivenAmount.contains(".");
        if (hasDecimal) {
            int indexOfDecimal = userGivenAmount.indexOf(".");
            String decimals = userGivenAmount.substring(indexOfDecimal + 1);
            int numOfDecimalPlaces = decimals.length();
            return numOfDecimalPlaces > 2;
        } else {
            return false;
        }
    }

    private int parseExpenseIndex(String userGivenIndex) throws InvalidExpenseIndexException {
        int deleteExpenseIndex;
        try {
            deleteExpenseIndex = Integer.parseInt(userGivenIndex);
        } catch (NumberFormatException e) {
            throw new InvalidExpenseIndexException(Messages.INVALID_INDEX_MESSAGE);
        }
        if (deleteExpenseIndex <= 0) {
            throw new InvalidExpenseIndexException(Messages.NON_POSITIVE_INTEGER_INDEX_MESSAGE);
        }
        return deleteExpenseIndex;
    }

    private int parseIncomeIndex(String userGivenIndex) throws InvalidIncomeIndexException {
        int deleteIncomeIndex;
        try {
            deleteIncomeIndex = Integer.parseInt(userGivenIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIncomeIndexException(Messages.INVALID_INDEX_MESSAGE);
        }
        if (deleteIncomeIndex <= 0) {
            throw new InvalidIncomeIndexException(Messages.NON_POSITIVE_INTEGER_INDEX_MESSAGE);
        }
        return deleteIncomeIndex;
    }
    
    private double parseThresholdValue(String userGivenThreshold) throws InvalidThresholdValueException {
        double thresholdValue;
        try {
            thresholdValue = Double.parseDouble(userGivenThreshold);
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException(Messages.NON_NUMERIC_THRESHOLD_MESSAGE);
        }
        if ((thresholdValue < 0) | (thresholdValue > 1)) {
            throw new InvalidThresholdValueException(Messages.INVALID_THRESHOLD_MESSAGE);
        } else if (Double.isNaN(thresholdValue) || Double.isInfinite(thresholdValue)) {
            throw new InvalidThresholdValueException(Messages.NON_NUMERIC_THRESHOLD_MESSAGE);
        } else if (hasMoreThanTwoDecimalPlaces(userGivenThreshold)) {
            throw new InvalidThresholdValueException(Messages.TOO_MANY_DP_MESSAGE);
        }
        return thresholdValue;
    }
    
    private double extractThresholdValue(Matcher matcher) throws InvalidThresholdValueException {
        String userGivenThreshold = matcher.group("threshold").trim();
        return parseThresholdValue(userGivenThreshold);
    }

    public String convertExpenseToData(Expense expense) {
        return "E" + DATA_SEPARATOR + expense.getDescription() + DATA_SEPARATOR + expense.getValue() + DATA_SEPARATOR
                + expense.getCategory() + DATA_SEPARATOR
                + expense.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String convertIncomeToData(Income income) {
        return "I" + DATA_SEPARATOR + income.getDescription() + DATA_SEPARATOR + income.getValue() + DATA_SEPARATOR
                + income.getCategory() + DATA_SEPARATOR
                + income.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public Expense convertDataToExpense(String data) throws InputException, InvalidExpenseDataFormatException,
            DateTimeParseException {
        final Matcher matcher = EXPENSE_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            String expenseDescription = extractExpenseDescription(matcher);
            double expenseAmount = extractExpenseAmount(matcher);
            ExpenseCategory expenseCategory = extractExpenseCategory(matcher);
            LocalDate expenseDate = extractExpenseDate(matcher);
            assert expenseAmount > 0;
            assert !expenseDescription.isBlank();
            return new Expense(expenseDescription, expenseAmount, expenseCategory, expenseDate);
        } else {
            throw new InvalidExpenseDataFormatException();
        }
    }

    private LocalDate extractExpenseDate(Matcher matcher) throws DateTimeParseException {
        String date = matcher.group("date").trim();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public Income convertDataToIncome(String data) throws InputException, InvalidIncomeDataFormatException,
            DateTimeParseException {
        final Matcher matcher = INCOME_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            String incomeDescription = extractIncomeDescription(matcher);
            double incomeAmount = extractIncomeAmount(matcher);
            IncomeCategory incomeCategory = extractIncomeCategory(matcher);
            LocalDate incomeDate = extractIncomeDate(matcher);
            assert incomeAmount > 0;
            assert !incomeDescription.isBlank();
            return new Income(incomeDescription, incomeAmount, incomeCategory, incomeDate);
        } else {
            throw new InvalidIncomeDataFormatException();
        }
    }

    private LocalDate extractIncomeDate(Matcher matcher) throws DateTimeParseException {
        String date = matcher.group("date").trim();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    private Command prepareSetBudget(String arguments) {
        final Matcher matcher = SET_BUDGET_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }

        String dataAmount = matcher.group("amount").trim();
        if (dataAmount.isBlank()) {
            return new InvalidCommand(Messages.BLANK_AMOUNT_MESSAGE);
        }
        double budgetAmount;
        try {
            budgetAmount = Double.parseDouble(dataAmount);
        } catch (NumberFormatException e) {
            return new InvalidCommand(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (budgetAmount < 0) {
            return new InvalidCommand(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        } else if (Double.isInfinite(budgetAmount) || Double.isNaN(budgetAmount)) {
            return new InvalidCommand(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }

        String expenseCategory = matcher.group("category").trim().toUpperCase();
        switch (expenseCategory) {
        case "FOOD":
            return new SetBudgetCommand(ExpenseCategory.FOOD, budgetAmount);
        case "TRANSPORT":
            return new SetBudgetCommand(ExpenseCategory.TRANSPORT, budgetAmount);
        case "MEDICAL":
            return new SetBudgetCommand(ExpenseCategory.MEDICAL, budgetAmount);
        case "BILLS":
            return new SetBudgetCommand(ExpenseCategory.BILLS, budgetAmount);
        case "ENTERTAINMENT":
            return new SetBudgetCommand(ExpenseCategory.ENTERTAINMENT, budgetAmount);
        case "MISC":
            return new SetBudgetCommand(ExpenseCategory.MISC, budgetAmount);
        case "OVERALL":
            return new SetBudgetCommand(ExpenseCategory.OVERALL, budgetAmount);
        default:
            return new InvalidCommand(Messages.INVALID_BUDGET_CATEGORY_MESSAGE);
        }
    }

    private Command prepareCheckBudget(String arguments) {
        final Matcher matcher = CHECK_BUDGET_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }

        String expenseCategory = matcher.group("category").trim().toUpperCase();
        if (expenseCategory.isBlank()) {
            return new InvalidCommand(Messages.BLANK_CATEGORY_MESSAGE);
        }

        switch (expenseCategory) {
        case "FOOD":
            return new CheckBudgetCommand(ExpenseCategory.FOOD);
        case "TRANSPORT":
            return new CheckBudgetCommand(ExpenseCategory.TRANSPORT);
        case "MEDICAL":
            return new CheckBudgetCommand(ExpenseCategory.MEDICAL);
        case "BILLS":
            return new CheckBudgetCommand(ExpenseCategory.BILLS);
        case "ENTERTAINMENT":
            return new CheckBudgetCommand(ExpenseCategory.ENTERTAINMENT);
        case "MISC":
            return new CheckBudgetCommand(ExpenseCategory.MISC);
        case "OVERALL":
            return new CheckBudgetCommand(ExpenseCategory.OVERALL);
        default:
            return new InvalidCommand(Messages.INVALID_BUDGET_CATEGORY_MESSAGE);
        }
    }

    private Command prepareSetThreshold(String arguments) {
        final Matcher matcher = SET_THRESHOLD_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }

        double thresholdValue;
        try {
            thresholdValue = extractThresholdValue(matcher);
        } catch (InvalidThresholdValueException e) {
            return new InvalidCommand(e.getMessage());
        }
        
        return new SetThresholdCommand(thresholdValue);
    }

    private Command prepareConvertCurrency(String arguments) {
        final Matcher matcher = CURRENCY_CONVERSION_FORMAT.matcher(arguments);
        if (isMatch(matcher)) {
            try {
                CurrencyType newCurrencyType = extractCurrencyType(matcher);
                return new CurrencyConversionCommand(newCurrencyType);
            } catch (InputException e) {
                return new InvalidCommand(e.getMessage());
            }
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private CurrencyType extractCurrencyType(Matcher matcher)
            throws BlankCurrencyTypeException, InvalidCurrencyTypeException {
        String newCurrency = matcher.group("currency").trim();
        if (newCurrency.isBlank()) {
            throw new BlankCurrencyTypeException(Messages.BLANK_CURRENCY_TYPE_MESSAGE);
        }
        switch (newCurrency.toUpperCase()) {
        case "USD":
            return CurrencyType.USD;
        case "SGD":
            return CurrencyType.SGD;
        default:
            throw new InvalidCurrencyTypeException(Messages.INVALID_CURRENCY_TYPE_MESSAGE);
        }
    }

    private Command prepareListCurrencyTypes(String arguments) {
        if (arguments.isBlank()) {
            return new ListCurrencyTypesCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }
    
    public String convertSettingsToData(BudgetManager budgetManager, CurrencyManager currencyManager) {
        CurrencyType currency = currencyManager.getCurrency();
        StringBuilder data = new StringBuilder(currency.toString() + ",");
        data.append(budgetManager.getThreshold()).append(",");
        for (ExpenseCategory category : ExpenseCategory.values()) {
            // NULL is the category after OVERALL. We do not expect NULL to have a value thus we break here.
            if (category == ExpenseCategory.OVERALL) {
                data.append(budgetManager.getBudget(category));
                break;
            }
            data.append(budgetManager.getBudget(category));
            data.append(DATA_SEPARATOR);
        }
        return data.toString();
    }

    public ArrayList<Double> convertDataToBudgetSettings(String data) throws NumberFormatException,
            NullPointerException, InvalidSettingsDataException {
        ArrayList<Double> budgetSettings = new ArrayList<>();
        final Matcher matcher = SETTINGS_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            for (ExpenseCategory category : ExpenseCategory.values()) {
                // Not expected to have a budget related to NULL
                if (category == ExpenseCategory.NULL) {
                    break;
                }
                budgetSettings.add(Double.parseDouble(matcher.group(category.toString().toLowerCase())));
            }
            return budgetSettings;
        }
        throw new InvalidSettingsDataException();
    }

    public CurrencyType convertDataToCurrencySetting(String data) throws InvalidCurrencyTypeException,
            BlankCurrencyTypeException, InvalidSettingsDataException {
        final Matcher matcher = SETTINGS_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            return extractCurrencyType(matcher);
        }
        throw new InvalidSettingsDataException();

    }
    
    public double convertDataToThresholdSetting(String data) throws InvalidThresholdValueException, 
            InvalidSettingsDataException {
        final Matcher matcher = SETTINGS_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            return extractThresholdValue(matcher);
        }
        throw new InvalidSettingsDataException();
    }
}
