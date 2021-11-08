package seedu.utility.storage;

import seedu.commands.currency.CurrencyType;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidSettingsDataFormatException;
import seedu.exceptions.InvalidThresholdValueException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.utility.tools.DateOperator.DATE_FORMAT;
import static seedu.utility.tools.DateOperator.extractDate;
import static seedu.utility.tools.Extractor.extractAmount;
import static seedu.utility.tools.Extractor.extractCurrencyType;
import static seedu.utility.tools.Extractor.extractDescription;
import static seedu.utility.tools.Extractor.extractExpenseCategory;
import static seedu.utility.tools.Extractor.extractIncomeCategory;
import static seedu.utility.tools.Extractor.extractThresholdValue;

/**
 * Converts entries (expense entries and income entries) to a csv String, vice versa.
 * Also converts settings to a csv String, vice verse.
 * This allows important user information to be stored in a csv file for easy access and reading. All the
 * information will not be lost when the program closes.
 */
public abstract class DataConverter {

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

    /**
     * Converts Expense entries to a csv String. This is so that the String can be stored in a csv file.
     *
     * @param expense The Expense entry to be converted.
     * @return A csv String.
     */
    public static String convertExpenseToData(Expense expense) {
        return "E" + DATA_SEPARATOR + expense.getDescription() + DATA_SEPARATOR + expense.getValue() + DATA_SEPARATOR
                + expense.getCategory() + DATA_SEPARATOR
                + expense.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Converts Income entries to a csv String. This is so that the String can be stored in a csv file.
     *
     * @param income The Income entry to be converted.
     * @return A csv String.
     */
    public static String convertIncomeToData(Income income) {
        return "I" + DATA_SEPARATOR + income.getDescription() + DATA_SEPARATOR + income.getValue() + DATA_SEPARATOR
                + income.getCategory() + DATA_SEPARATOR
                + income.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Converts a csv String to an Expense entry. This is done during the loading of data phrase when the program
     * starts.
     *
     * @param data The csv String.
     * @return An Expense entry.
     * @throws InputException                    If any value in the csv String cannot be parsed to their expected
     *                                           variable format. For example, the amount value cannot be
     *                                           parsed to a double.
     * @throws InvalidExpenseDataFormatException If the format of csv String received is not in the expected format.
     * @throws DateTimeParseException            If the date value in the csv String cannot be parsed as a LocalDate.
     */
    public static Expense convertDataToExpense(String data) throws InputException, InvalidExpenseDataFormatException,
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

    /**
     * Converts a csv String to an Income entry. This is done during the loading of data phrase when the program
     * starts.
     *
     * @param data The csv String.
     * @return An Income entry.
     * @throws InputException                   If any value in the csv String cannot be parsed to their expected
     *                                          variable format. For example, the amount value cannot be parsed to
     *                                          a double.
     * @throws InvalidIncomeDataFormatException If the format of csv String received is not in the expected format.
     * @throws DateTimeParseException           If the date value in the csv String cannot be parsed as a LocalDate.
     */
    public static Income convertDataToIncome(String data) throws InputException, InvalidIncomeDataFormatException,
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

    /**
     * Converts user settings to a csv String.
     *
     * @param budgetManager   Provides the threshold value and the budget for the different expense category.
     * @param currencyManager Provides the currency setting also known as CurrencyType.
     * @return A csv String.
     */
    public static String convertSettingsToData(BudgetManager budgetManager, CurrencyManager currencyManager) {
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

    /**
     * Converts a csv String to CurrencyType.
     *
     * @param data The csv String.
     * @return A CurrencyType.
     * @throws InvalidCurrencyTypeException       If the CurrencyType cannot be recognised / not supported.
     * @throws BlankCurrencyTypeException         If the CurrencyType is blank.
     * @throws InvalidSettingsDataFormatException If the format of the csv String is not the same as the expected
     *                                            format.
     */
    public static CurrencyType convertDataToCurrencySetting(String data) throws InvalidCurrencyTypeException,
            BlankCurrencyTypeException, InvalidSettingsDataFormatException {
        final Matcher matcher = SETTINGS_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            return extractCurrencyType(matcher);
        }
        throw new InvalidSettingsDataFormatException();

    }

    /**
     * Converts a csv String to the threshold value.
     *
     * @param data The csv String.
     * @return The threshold value.
     * @throws InvalidThresholdValueException     If the threshold value is not acceptable. For example, it is out
     *                                            of range.
     * @throws InvalidSettingsDataFormatException If the format of the csv String is not the same as the
     *                                            expected format.
     */
    public static double convertDataToThresholdSetting(String data) throws InvalidThresholdValueException,
            InvalidSettingsDataFormatException {
        final Matcher matcher = SETTINGS_DATA_FORMAT.matcher(data.trim());
        if (matcher.matches()) {
            return extractThresholdValue(matcher);
        }
        throw new InvalidSettingsDataFormatException();
    }

    /**
     * Converts a csv String to the budget settings.
     *
     * @param data The csv String.
     * @return An ArrayList of double. Each representing a budget value for a particular ExpenseCategory.
     * @throws NumberFormatException              A threshold value in the csv String cannot be parsed as a double.
     * @throws NullPointerException               No threshold value give in the csv String.
     * @throws InvalidSettingsDataFormatException If the format of the csv String is not the same as the
     *                                            expected format.
     */
    public static ArrayList<Double> convertDataToBudgetSettings(String data) throws NumberFormatException,
            NullPointerException, InvalidSettingsDataFormatException {
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
        throw new InvalidSettingsDataFormatException();
    }
}
