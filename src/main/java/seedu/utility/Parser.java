package seedu.utility;

import seedu.commands.currency.ListCurrencyTypesCommand;
import seedu.commands.expense.AddExpenseCommand;
import seedu.commands.currency.CheckCurrentCurrencyCommand;
import seedu.commands.general.ClearAllEntriesCommand;
import seedu.commands.general.ExitCommand;
import seedu.commands.general.FindCommand;
import seedu.commands.general.HelpCommand;
import seedu.commands.general.ShowGraphByYearCommand;
import seedu.commands.general.ShowGraphCommand;
import seedu.commands.income.AddIncomeCommand;
import seedu.commands.budget.CheckBudgetCommand;
import seedu.commands.Command;
import seedu.commands.currency.CurrencyConversionCommand;
import seedu.commands.currency.CurrencyType;
import seedu.commands.expense.DeleteExpenseCommand;
import seedu.commands.income.DeleteIncomeCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.expense.ListExpenseCommand;
import seedu.commands.income.ListIncomeCommand;
import seedu.commands.budget.SetBudgetCommand;
import seedu.commands.budget.SetThresholdCommand;
import seedu.commands.expense.TotalExpenseBetweenCommand;
import seedu.commands.expense.TotalExpenseCommand;
import seedu.commands.income.TotalIncomeBetweenCommand;
import seedu.commands.income.TotalIncomeCommand;
import seedu.commands.budget.BalanceCommand;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.InvalidSettingsDataException;
import seedu.exceptions.InvalidThresholdValueException;
import seedu.utility.tools.DateRange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.utility.CommandKeywords.ADD_EXPENSE_KEYWORD;
import static seedu.utility.CommandKeywords.ADD_EXPENSE_WITH_DATE_KEYWORD;
import static seedu.utility.CommandKeywords.ADD_INCOME_KEYWORD;
import static seedu.utility.CommandKeywords.ADD_INCOME_WITH_DATE_KEYWORD;
import static seedu.utility.CommandKeywords.BALANCE_KEYWORD;
import static seedu.utility.CommandKeywords.CHECK_BUDGET_KEYWORD;
import static seedu.utility.CommandKeywords.CHECK_CURRENT_CURRENCY_KEYWORD;
import static seedu.utility.CommandKeywords.CLEAR_ALL_ENTRIES_KEYWORD;
import static seedu.utility.CommandKeywords.CONVERT_CURRENCY_KEYWORD;
import static seedu.utility.CommandKeywords.DELETE_EXPENSE_KEYWORD;
import static seedu.utility.CommandKeywords.DELETE_INCOME_KEYWORD;
import static seedu.utility.CommandKeywords.EXIT_KEYWORD;
import static seedu.utility.CommandKeywords.EXPENSE_RANGE_KEYWORD;
import static seedu.utility.CommandKeywords.FIND_KEYWORD;
import static seedu.utility.CommandKeywords.HELP_COMMAND_KEYWORD;
import static seedu.utility.CommandKeywords.INCOME_RANGE_KEYWORD;
import static seedu.utility.CommandKeywords.LIST_CURRENCY_TYPES_KEYWORD;
import static seedu.utility.CommandKeywords.LIST_EXPENSE_KEYWORD;
import static seedu.utility.CommandKeywords.LIST_INCOME_KEYWORD;
import static seedu.utility.CommandKeywords.SET_BUDGET_KEYWORD;
import static seedu.utility.CommandKeywords.SET_THRESHOLD_KEYWORD;
import static seedu.utility.CommandKeywords.SHOW_GRAPH_KEYWORD;
import static seedu.utility.CommandKeywords.TOTAL_EXPENSE_KEYWORD;
import static seedu.utility.CommandKeywords.TOTAL_INCOME_KEYWORD;
import static seedu.utility.tools.DateOperator.extractDate;
import static seedu.utility.tools.DateOperator.extractStartAndEndDate;
import static seedu.utility.tools.DateOperator.getYearFormat;
import static seedu.utility.tools.DateOperator.isValidDateRange;
import static seedu.utility.tools.Extractor.extractAmount;
import static seedu.utility.tools.Extractor.extractDescription;
import static seedu.utility.tools.Extractor.extractExpenseCategory;
import static seedu.utility.tools.Extractor.extractIncomeCategory;
import static seedu.utility.tools.Extractor.extractIndex;

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
            Pattern.compile("^(?= [cda]/)(?=.* d/(?<description>.+?)( [ca]/|$))"
                    + "(?=.* a/(?<amount>.+?)( [dc]/|$))"
                    + "(?=.* c/(?<category>.+?)( [da]/|$)).*$");

    private static final Pattern ADD_EXPENSE_ARGUMENT_FORMAT_WITH_DATE =
            Pattern.compile("^(?= [cdaD]/)(?=.* d/(?<description>.+?)( [caD]/|$))"
                    + "(?=.* a/(?<amount>.+?)( [dcD]/|$))"
                    + "(?=.* c/(?<category>.+?)( [daD]/|$))"
                    + "(?=.* D/(?<date>.+?)( [dac]/|$)).*$");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("^(?= [cda]/)(?=.* d/(?<description>.+?)( [ca]/|$))"
                    + "(?=.* a/(?<amount>.+?)( [dc]/|$))"
                    + "(?=.* c/(?<category>.+?)( [da]/|$)).*$");

    private static final Pattern ADD_INCOME_ARGUMENT_FORMAT_WITH_DATE =
            Pattern.compile("^(?= [cdaD]/)(?=.* d/(?<description>.+?)( [caD]/|$))"
                    + "(?=.* a/(?<amount>.+?)( [dcD]/|$))"
                    + "(?=.* c/(?<category>.+?)( [daD]/|$))"
                    + "(?=.* D/(?<date>.+?)( [dac]/|$)).*$");
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
            Pattern.compile("^(?= [se]/)(?=.* s/(?<start>.+?)( [e]/|$))"
                    + "(?=.* e/(?<end>.+?)( [s]/|$)).*$");

    private static final Pattern SET_BUDGET_ARGUMENT_FORMAT =
            Pattern.compile("^(?= [ca]/)(?=.* c/(?<category>.+?)( [a]/|$))"
                    + "(?=.* a/(?<amount>.+?)( [c]/|$)).*$");

    private static final Pattern CHECK_BUDGET_ARGUMENT_FORMAT =
            Pattern.compile("c/(?<category>[^/]+)");

    private static final Pattern SET_THRESHOLD_ARGUMENT_FORMAT =
            Pattern.compile("t/(?<threshold>[^/]+)");

    private static final Pattern CURRENCY_CONVERSION_FORMAT =
            Pattern.compile("c/(?<currency>.+)");

    private static final Pattern SHOW_GRAPH_BY_YEAR_FORMAT =
            Pattern.compile("Y/(?<year>.+)");


    private static final String DATA_SEPARATOR = ",";
    private static final Pattern EXPENSE_DATA_FORMAT
            = Pattern.compile("E" + DATA_SEPARATOR + "(?<description>.+)" + DATA_SEPARATOR
            + "(?<amount>.+)" + DATA_SEPARATOR + "(?<category>.+)" + DATA_SEPARATOR + "(?<date>.+)");
    private static final Pattern INCOME_DATA_FORMAT
            = Pattern.compile("I" + DATA_SEPARATOR + "(?<description>.+)" + DATA_SEPARATOR
            + "(?<amount>.+)" + DATA_SEPARATOR + "(?<category>.+)" + DATA_SEPARATOR + "(?<date>.+)");
    private static final Pattern SETTINGS_DATA_FORMAT = Pattern.compile("(?<currency>.+)" + DATA_SEPARATOR
            + "(?<threshold>.+)" + DATA_SEPARATOR + "(?<overall>.+)" + DATA_SEPARATOR + "(?<food>.+)"
            + DATA_SEPARATOR + "(?<transport>.+)" + DATA_SEPARATOR + "(?<medical>.+)"
            + DATA_SEPARATOR + "(?<bills>.+)" + DATA_SEPARATOR + "(?<entertainment>.+)"
            + DATA_SEPARATOR + "(?<misc>.+)");

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final double BUDGET_AMOUNT_LIMIT = 100000000000.00;

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
        String arguments = matcher.group("arguments").trim();

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
                || commandWord.equals(EXPENSE_RANGE_KEYWORD) || commandWord.equals(ADD_EXPENSE_WITH_DATE_KEYWORD));
    }

    private boolean isIncomeRelatedCommand(String commandWord) {
        return (commandWord.equals(ADD_INCOME_KEYWORD) || commandWord.equals(DELETE_INCOME_KEYWORD)
                || commandWord.equals(LIST_INCOME_KEYWORD) || commandWord.equals(TOTAL_INCOME_KEYWORD)
                || commandWord.equals(INCOME_RANGE_KEYWORD) || commandWord.equals(ADD_INCOME_WITH_DATE_KEYWORD));
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
            return prepareAddExpenseWithoutDate(arguments);
        case ADD_EXPENSE_WITH_DATE_KEYWORD:
            return prepareAddExpenseWithDate(arguments);
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
            return prepareAddIncomeWithoutDate(arguments);
        case ADD_INCOME_WITH_DATE_KEYWORD:
            return prepareAddIncomeWithDate(arguments);
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
        try {
            final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(" " + arguments);
            if (!matcher.matches()) {
                return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
            }
            DateRange dateRange = extractStartAndEndDate(matcher);
            if (isValidDateRange(dateRange)) {
                return new TotalIncomeBetweenCommand(dateRange);
            }
            return new InvalidCommand(Messages.INVALID_DATE_RANGE_MESSAGE);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }

    private Command prepareExpenseRange(String arguments) {
        try {
            final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(" " + arguments);
            if (!matcher.matches()) {
                return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
            }
            DateRange dateRange = extractStartAndEndDate(matcher);
            if (isValidDateRange(dateRange)) {
                return new TotalExpenseBetweenCommand(dateRange);
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
    private Command prepareAddExpenseWithoutDate(String arguments) {
        final Matcher matcher = ADD_EXPENSE_ARGUMENT_FORMAT.matcher(" " + arguments);
        if (matcher.matches()) {
            try {
                double expenseAmount = extractAmount(matcher);
                String expenseDescription = extractDescription(matcher);
                ExpenseCategory expenseCategory = extractExpenseCategory(matcher);
                Expense expense = new Expense(expenseDescription, expenseAmount, expenseCategory);
                assert !expenseCategory.equals(ExpenseCategory.NULL);
                return new AddExpenseCommand(expense);
            } catch (InputException e) {
                return new InvalidCommand(e.getMessage());
            }
        } else {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }
    }

    private Command prepareAddExpenseWithDate(String arguments) {
        final Matcher matcher = ADD_EXPENSE_ARGUMENT_FORMAT_WITH_DATE.matcher(" " + arguments);
        if (matcher.matches()) {
            try {
                double expenseAmount = extractAmount(matcher);
                String expenseDescription = extractDescription(matcher);
                ExpenseCategory expenseCategory = extractExpenseCategory(matcher);
                LocalDate expenseDate = extractDate(matcher);
                Expense expense = new Expense(expenseDescription, expenseAmount, expenseCategory, expenseDate);
                assert !expenseCategory.equals(ExpenseCategory.NULL);
                return new AddExpenseCommand(expense);
            } catch (InputException e) {
                return new InvalidCommand(e.getMessage());
            } catch (DateTimeParseException de) {
                return new InvalidCommand(Messages.INVALID_DATE_FORMAT);
            }
        } else {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }
    }

    private Command prepareAddIncomeWithoutDate(String arguments) {
        final Matcher matcher = ADD_INCOME_ARGUMENT_FORMAT.matcher(" " + arguments);
        if (matcher.matches()) {
            try {
                double incomeAmount = extractAmount(matcher);
                String incomeDescription = extractDescription(matcher);
                IncomeCategory incomeCategory = extractIncomeCategory(matcher);
                Income income = new Income(incomeDescription, incomeAmount, incomeCategory);
                assert !incomeCategory.equals(IncomeCategory.NULL);
                return new AddIncomeCommand(income);
            } catch (InputException e) {
                return new InvalidCommand(e.getMessage());
            }
        } else {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }
    }

    private Command prepareAddIncomeWithDate(String arguments) {
        final Matcher matcher = ADD_INCOME_ARGUMENT_FORMAT_WITH_DATE.matcher(" " + arguments);
        if (matcher.matches()) {
            try {
                double incomeAmount = extractAmount(matcher);
                String incomeDescription = extractDescription(matcher);
                IncomeCategory incomeCategory = extractIncomeCategory(matcher);
                LocalDate incomeDate = extractDate(matcher);
                Income income = new Income(incomeDescription, incomeAmount, incomeCategory, incomeDate);
                assert !incomeCategory.equals(IncomeCategory.NULL);
                return new AddIncomeCommand(income);
            } catch (InputException e) {
                return new InvalidCommand(e.getMessage());
            } catch (DateTimeParseException de) {
                return new InvalidCommand(Messages.INVALID_DATE_FORMAT);
            }
        } else {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }
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
            deleteExpenseIndex = extractIndex(userGivenIndex);
        } catch (InvalidIndexException e) {
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
            deleteIncomeIndex = extractIndex(userGivenIndex);
        } catch (InvalidIndexException e) {
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
        final Matcher matcher = SHOW_GRAPH_BY_YEAR_FORMAT.matcher(arguments);
        if (matcher.matches()) {
            try {
                DateTimeFormatter yearFormat = getYearFormat();
                String userGivenYear = matcher.group("year").trim();
                LocalDate year = LocalDate.parse(userGivenYear, yearFormat);
                return new ShowGraphByYearCommand(year);
            } catch (DateTimeParseException e) {
                return new InvalidCommand(Messages.INVALID_YEAR_MESSAGE);
            }
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

    private Command prepareSetBudget(String arguments) {
        final Matcher matcher = SET_BUDGET_ARGUMENT_FORMAT.matcher(" " + arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.PARAMETERS_ERROR_MESSAGE);
        }

        String dataAmount = matcher.group("amount").trim();
        if (dataAmount.isBlank()) {
            return new InvalidCommand(Messages.BLANK_AMOUNT_MESSAGE);
        } else if (hasMoreThanTwoDecimalPlaces(dataAmount)) {
            return new InvalidCommand(Messages.TOO_MANY_DP_MESSAGE);
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
        } else if (budgetAmount > BUDGET_AMOUNT_LIMIT) {
            return new InvalidCommand(Messages.INVALID_BUDGET_VALUE);
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
        if (matcher.matches()) {
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
            String expenseDescription = extractDescription(matcher);
            double expenseAmount = extractAmount(matcher);
            ExpenseCategory expenseCategory = extractExpenseCategory(matcher);
            LocalDate expenseDate = extractDate(matcher);
            assert expenseAmount > 0;
            assert !expenseDescription.isBlank();
            return new Expense(expenseDescription, expenseAmount, expenseCategory, expenseDate);
        } else {
            throw new InvalidExpenseDataFormatException();
        }
    }

    public Income convertDataToIncome(String data) throws InputException, InvalidIncomeDataFormatException,
            DateTimeParseException {
        final Matcher matcher = INCOME_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            String incomeDescription = extractDescription(matcher);
            double incomeAmount = extractAmount(matcher);
            IncomeCategory incomeCategory = extractIncomeCategory(matcher);
            LocalDate incomeDate = extractDate(matcher);
            assert incomeAmount > 0;
            assert !incomeDescription.isBlank();
            return new Income(incomeDescription, incomeAmount, incomeCategory, incomeDate);
        } else {
            throw new InvalidIncomeDataFormatException();
        }
    }

    public String convertSettingsToData(BudgetManager budgetManager, CurrencyManager currencyManager) {
        CurrencyType currency = currencyManager.getCurrency();
        StringBuilder data = new StringBuilder(currency.toString() + ",");
        data.append(budgetManager.getThreshold()).append(",");
        for (ExpenseCategory category : ExpenseCategory.values()) {
            // NULL is the category after MISC. We do not expect NULL to have a value thus we break here.
            if (category == ExpenseCategory.MISC) {
                data.append(budgetManager.getBudget(category));
                break;
            }
            data.append(budgetManager.getBudget(category));
            data.append(DATA_SEPARATOR);
        }
        return data.toString();
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
}
