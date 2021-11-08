package seedu.utility.tools;

import seedu.commands.currency.CurrencyType;
import seedu.entry.ExpenseCategory;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankCategoryException;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.EntryAmountExceedLimitException;
import seedu.exceptions.InvalidAmountException;
import seedu.exceptions.InvalidBudgetAmountException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidDescriptionException;
import seedu.exceptions.InvalidExpenseCategoryException;
import seedu.exceptions.InvalidIncomeCategoryException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.InvalidThresholdValueException;
import seedu.utility.Messages;

import java.util.regex.Matcher;

/**
 * Converts Strings given to a variable format that the program understands and can do meaningful work on it.
 */
public abstract class Extractor {

    private static final double ENTRY_AMOUNT_LIMIT = 1000000;
    private static final double BUDGET_AMOUNT_LIMIT = 100000000000.00;

    /**
     * Converts the String given to an index which is an int.
     *
     * @param userGivenIndex The String that should contain user-specified index.
     * @return An int which represents the index user specify.
     * @throws InvalidIndexException If the String cannot be parsed into an int.
     */
    public static int extractIndex(String userGivenIndex) throws InvalidIndexException {
        try {
            int deleteIndex = Integer.parseInt(userGivenIndex);
            if (deleteIndex <= 0) {
                throw new InvalidIndexException(Messages.NON_POSITIVE_INTEGER_INDEX_MESSAGE);
            }
            return deleteIndex;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Messages.INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Checks if the String given for Entry description is valid. Returns the String if it's valid.
     *
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The description as a String.
     * @throws InvalidDescriptionException If the description is blank.
     */
    public static String extractDescription(Matcher matcher) throws
            InvalidDescriptionException {
        String description = matcher.group("description").trim();
        if (description.isBlank()) {
            throw new InvalidDescriptionException(Messages.BLANK_DESCRIPTION_MESSAGE);
        }
        return description;
    }

    /**
     * Converts the String given to IncomeCategory.
     *
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The IncomeCategory.
     * @throws BlankCategoryException   If the supposed IncomeCategory is blank.
     * @throws InvalidIncomeCategoryException If the IncomeCategory given is not expected / not supported.
     */
    public static IncomeCategory extractIncomeCategory(Matcher matcher) throws
            BlankCategoryException, InvalidIncomeCategoryException {
        String incomeCategory = matcher.group("category").trim();
        if (incomeCategory.isBlank()) {
            throw new BlankCategoryException(Messages.BLANK_CATEGORY_MESSAGE);
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

    /**
     * Converts the given String to ExpenseCategory.
     *
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The ExpenseCategory.
     * @throws BlankCategoryException   If the supposed ExpenseCategory is blank.
     * @throws InvalidExpenseCategoryException If the ExpenseCategory given is not expected / not supported.
     */
    public static ExpenseCategory extractExpenseCategory(Matcher matcher) throws
            BlankCategoryException, InvalidExpenseCategoryException {
        String expenseCategory = matcher.group("category").trim();
        if (expenseCategory.isBlank()) {
            throw new BlankCategoryException(Messages.BLANK_CATEGORY_MESSAGE);
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

    /**
     * Converts the given String to the budget amount which is a double.
     *
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The budget amount in double format.
     * @throws InvalidBudgetAmountException If the budget amount does not fit the expected rules.
     *                                      For example, if budget amount given is more than the limit.
     */
    public static double extractBudgetAmount(Matcher matcher) throws InvalidBudgetAmountException {

        String dataAmount = matcher.group("amount").trim();
        if (dataAmount.isBlank()) {
            throw new InvalidBudgetAmountException(Messages.BLANK_AMOUNT_MESSAGE);
        } else if (hasMoreThanTwoDecimalPlaces(dataAmount)) {
            throw new InvalidBudgetAmountException(Messages.TOO_MANY_DP_MESSAGE);
        }
        double budgetAmount;
        try {
            budgetAmount = Double.parseDouble(dataAmount);
        } catch (NumberFormatException e) {
            throw new InvalidBudgetAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }

        if (budgetAmount < 0) {
            throw new InvalidBudgetAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        } else if (Double.isInfinite(budgetAmount) || Double.isNaN(budgetAmount)) {
            throw new InvalidBudgetAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        } else if (budgetAmount > BUDGET_AMOUNT_LIMIT) {
            throw new InvalidBudgetAmountException(Messages.INVALID_BUDGET_VALUE);
        }
        return budgetAmount;
    }

    /**
     * Converts the given String to amount which is a double.
     * 
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The amount, in double format.
     * @throws InvalidAmountException If the amount given does not match the expected guidelines.
     * @throws EntryAmountExceedLimitException If the amount given exceeds the limit.
     */
    public static double extractAmount(Matcher matcher) throws InvalidAmountException,
            EntryAmountExceedLimitException {
        String userGivenAmount = matcher.group("amount").trim();
        double amount = parseAmount(userGivenAmount);
        if (amount > ENTRY_AMOUNT_LIMIT) {
            throw new EntryAmountExceedLimitException(Messages.INVALID_EXPENSE_VALUE);
        }
        return amount;
    }
    
    private static double parseAmount(String userGivenAmount) throws InvalidAmountException {
        double amount;
        try {
            amount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (hasMoreThanTwoDecimalPlaces(userGivenAmount)) {
            throw new InvalidAmountException(Messages.TOO_MANY_DP_MESSAGE);
        } else if (amount <= 0) {
            throw new InvalidAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        } else if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        assert amount > 0;
        return amount;
    }

    private static boolean hasMoreThanTwoDecimalPlaces(String userGivenAmount) {
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

    /**
     * Converts the String given to the CurrencyType.
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The CurrencyType.
     * @throws BlankCurrencyTypeException If CurrencyType given is empty.
     * @throws InvalidCurrencyTypeException the CurrencyType given is not expected / not supported.
     */
    public static CurrencyType extractCurrencyType(Matcher matcher)
            throws BlankCurrencyTypeException, InvalidCurrencyTypeException {
        String newCurrency = matcher.group("currency").trim();
        if (newCurrency.isBlank()) {
            throw new BlankCurrencyTypeException(Messages.BLANK_CURRENCY_TYPE_MESSAGE);
        }
        switch (newCurrency.toUpperCase()) {
        case "RMB":
            return CurrencyType.RMB;
        case "SGD":
            return CurrencyType.SGD;
        default:
            throw new InvalidCurrencyTypeException(Messages.INVALID_CURRENCY_TYPE_MESSAGE);
        }
    }

    /**
     * Converts given String to threshold value.
     * 
     * @param matcher An engine that performs match operations on a character sequence by interpreting a Pattern.
     * @return The threshold value.
     * @throws InvalidThresholdValueException If the threshold value given is unexpected.
     */
    public static double extractThresholdValue(Matcher matcher) throws InvalidThresholdValueException {
        String userGivenThreshold = matcher.group("threshold").trim();
        return parseThresholdValue(userGivenThreshold);
    }

    private static double parseThresholdValue(String userGivenThreshold) throws InvalidThresholdValueException {
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
}
