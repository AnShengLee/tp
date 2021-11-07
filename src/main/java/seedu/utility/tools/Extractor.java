package seedu.utility.tools;

import seedu.entry.ExpenseCategory;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankExpenseCategoryException;
import seedu.exceptions.BlankIncomeCategoryException;
import seedu.exceptions.InvalidDescriptionException;
import seedu.exceptions.InvalidExpenseCategoryException;
import seedu.exceptions.InvalidIncomeCategoryException;
import seedu.exceptions.InvalidIndexException;
import seedu.utility.Messages;

import java.util.regex.Matcher;

public abstract class Extractor {
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
}
