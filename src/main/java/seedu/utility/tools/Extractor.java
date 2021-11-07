package seedu.utility.tools;

import seedu.commands.InvalidCommand;
import seedu.entry.ExpenseCategory;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankExpenseCategoryException;
import seedu.exceptions.BlankIncomeCategoryException;
import seedu.exceptions.InvalidBudgetAmountException;
import seedu.exceptions.InvalidDescriptionException;
import seedu.exceptions.InvalidAmountException;
import seedu.exceptions.InvalidExpenseCategoryException;
import seedu.exceptions.InvalidIncomeCategoryException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.EntryAmountExceedLimitException;
import seedu.exceptions.InvalidThresholdValueException;
import seedu.utility.Messages;

import java.util.regex.Matcher;

public abstract class Extractor {
    private static final double ENTRY_AMOUNT_LIMIT = 1000000;
    private static final double BUDGET_AMOUNT_LIMIT = 100000000000.00;

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

    public static String extractDescription(Matcher matcher) throws
            InvalidDescriptionException {
        String description = matcher.group("description").trim();
        if (description.isBlank()) {
            throw new InvalidDescriptionException(Messages.BLANK_DESCRIPTION_MESSAGE);
        }
        return description;
    }

    public static IncomeCategory extractIncomeCategory(Matcher matcher) throws
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

    public static ExpenseCategory extractExpenseCategory(Matcher matcher) throws
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
