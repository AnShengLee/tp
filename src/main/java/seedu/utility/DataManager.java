package seedu.utility;

import seedu.commands.general.CurrencyType;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeOverflowException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidSettingsDataException;
import seedu.exceptions.InvalidThresholdValueException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Saves entries (expenses and incomes) Stonks XD is tracking into a csv file.
 * Also saves Stonks XD settings into another csv file. Settings include: currency state, amount for each budget 
 * category and the reminder threshold value.
 */
public class DataManager {

    private static final String NEWLINE = System.lineSeparator();
    private static final String ENTRIES_FILE_NAME = "./StonksXD_Entries.csv";
    private static final String ENTRIES_CSV_HEADER = "entry_type,entry_description,amount,category,date";
    private static final String SETTINGS_FILE_NAME = "./StonksXD_Settings.csv";
    private static final String SETTINGS_CSV_HEADER = "currency,threshold,food,transport,medical,bills,entertainment," 
            + "misc,overall";
    private final Parser parser;
    private final FinancialTracker financialTracker;
    private final Ui ui;
    private final BudgetManager budgetManager;

    /**
     * Constructs a instance of DataManager.
     * 
     * @param parser The parser will be used to convert settings and entries to data, vice verse.
     * @param financialTracker The finantialTracker will provide all the entries Stonks XD is tracking currently as 
     *                         well as the currency setting.
     * @param ui The ui will be used to print out any warnings or messages to the user.
     * @param budgetManager The budgetManager will provide all the budget settings to be saved / loaded.
     */
    public DataManager(Parser parser, FinancialTracker financialTracker, Ui ui, BudgetManager budgetManager) {
        this.parser = parser;
        this.financialTracker = financialTracker;
        this.ui = ui;
        this.budgetManager = budgetManager;
    }

    /**
     * Saves all entries and settings. 
     * This method will be used more frequently as we typically want to save both entries and settings together.
     */
    public void saveAll() {
        saveEntries();
        saveSettings();
    }

    /**
     * Loads all entries and settings.
     * This method will be used more frequently as we typically want to load both entries and settings together.
     */
    public void loadAll() {
        loadEntries();
        loadSettings();
    }

    /**
     * Saves all entries StonksXD is currently tracking into a csv file.
     * This allows users to not lose all their entries when program closes.
     */
    private void saveEntries() {
        try {
            FileWriter writer = new FileWriter(ENTRIES_FILE_NAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            
            buffer.write(ENTRIES_CSV_HEADER);
            buffer.write(NEWLINE);
            writeExpenses(buffer);
            writeIncomes(buffer);
            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_ENTRIES_MESSAGE);
        }
    }

    private void writeIncomes(BufferedWriter buffer) throws IOException {
        String data;
        ArrayList<Income> incomes = financialTracker.getIncomes();
        for (Income income : incomes) {
            data = parser.convertIncomeToData(income);
            buffer.write(data);
            buffer.write(NEWLINE);
        }
    }

    private void writeExpenses(BufferedWriter buffer) throws IOException {
        String data;
        ArrayList<Expense> expenses = financialTracker.getExpenses();
        for (Expense expense : expenses) {
            data = parser.convertExpenseToData(expense);
            buffer.write(data);
            buffer.write(NEWLINE);
        }
    }

    /**
     * Loads all entries from StonksXD_Data.csv into StonksXD.
     * This allows users to not lose all their entries when the previous instance of StonksXD closed.
     */
    private void loadEntries() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(ENTRIES_FILE_NAME);
        } catch (FileNotFoundException e) {
            ui.printLoadingError(Messages.UNABLE_TO_FIND_DATA_FILE);
            return;
        }
        boolean hasCorruptedLines = false;
        Scanner sc = new Scanner(fis);
        checkForEntriesFileHeader(sc);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            if (data.isBlank()) {
                continue;
            }
            try {
                loadAsExpense(data);
            } catch (InputException | InvalidExpenseDataFormatException | DateTimeParseException 
                    | ExpenseOverflowException e) {
                try {
                    loadAsIncome(data);
                } catch (InputException | InvalidIncomeDataFormatException | DateTimeParseException 
                        | IncomeOverflowException ie) {
                    hasCorruptedLines = true;
                }
            }
        }
        if (hasCorruptedLines) {
            ui.printLoadingError(Messages.HAS_CORRUPTED_DATA_ENTRIES);
        }
    }

    private void loadAsIncome(String data) throws InputException, InvalidIncomeDataFormatException, 
            IncomeOverflowException {
        Income income = parser.convertDataToIncome(data);
        financialTracker.addIncome(income);
    }

    private void loadAsExpense(String data) throws InputException, InvalidExpenseDataFormatException, 
            ExpenseOverflowException {
        Expense expense = parser.convertDataToExpense(data);
        financialTracker.addExpense(expense);
    }

    private void checkForEntriesFileHeader(Scanner sc) {
        String data;
        try {
            data = sc.nextLine();
        } catch (NoSuchElementException e) {
            ui.printLoadingError(Messages.INCOMPLETE_ENTRIES_CSV_HEADER_MESSAGE);
            return;
        }
        
        if (!data.equals(ENTRIES_CSV_HEADER)) {
            ui.printLoadingError(Messages.INCOMPLETE_ENTRIES_CSV_HEADER_MESSAGE);
        }
    }

    private void checkForSettingsFileHeader(Scanner sc) {
        String data;
        try {
            data = sc.nextLine();
        } catch (NoSuchElementException e) {
            ui.printLoadingError(Messages.INCOMPLETE_SETTINGS_CSV_HEADER_MESSAGE);
            return;
        }

        if (!data.equals(SETTINGS_CSV_HEADER)) {
            ui.printLoadingError(Messages.INCOMPLETE_SETTINGS_CSV_HEADER_MESSAGE);
        }
    }

    /**
     * Saves all settings into a csv file.
     * This allows users to not lose all their settings when program closes.
     */
    private void saveSettings() {
        try {
            FileWriter writer = new FileWriter(SETTINGS_FILE_NAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            
            buffer.write(SETTINGS_CSV_HEADER);
            buffer.write(NEWLINE);
            writeSettings(buffer);
            buffer.write(NEWLINE);
            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_SETTINGS_MESSAGE);
        }
    }

    private void writeSettings(BufferedWriter buffer) throws IOException {
        String data;
        data = parser.convertSettingsToData(financialTracker, budgetManager);
        buffer.write(data);
    }

    /**
     * Loads all settings from StonksXD_Settings.csv into StonksXD.
     * This allows users to not lose all their settings when the previous instance of 
     * StonksXD closed.
     */
    private void loadSettings() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(SETTINGS_FILE_NAME);
        } catch (FileNotFoundException e) {
            ui.printLoadingError(Messages.UNABLE_TO_FIND_SETTINGS_FILE);
            return;
        }

        Scanner sc = new Scanner(fis);
        try {
            checkForSettingsFileHeader(sc);
            String data = sc.nextLine();
            ArrayList<Double> budgetSettings = parser.convertDataToBudgetSettings(data);
            loadBudgetSettings(budgetSettings);
            CurrencyType currency = parser.convertDataToCurrencySetting(data);
            loadCurrencySetting(currency);
            double thresholdValue = parser.convertDataToThresholdSetting(data);
            loadThresholdSettings(thresholdValue);
        } catch (NullPointerException | NumberFormatException | InvalidSettingsDataException 
                | InvalidCurrencyTypeException | BlankCurrencyTypeException | NoSuchElementException 
                | InvalidThresholdValueException e) {
            ui.printLoadingError(Messages.HAS_CORRUPTED_SETTINGS);
        }
    }

    private void loadCurrencySetting(CurrencyType currency) {
        financialTracker.setCurrency(currency);
    }

    private void loadBudgetSettings(ArrayList<Double> budgetSettings) {
        int budgetIndex = 0;
        for (ExpenseCategory category : ExpenseCategory.values()) {
            // Not expected to have a budget related to NULL
            if (category == ExpenseCategory.NULL) {
                break;
            }
            budgetManager.setBudget(budgetSettings.get(budgetIndex), category);
            budgetIndex += 1;
        }
    }
    
    private void loadThresholdSettings(double thresholdValue) {
        budgetManager.setThreshold(thresholdValue);
    }
}
