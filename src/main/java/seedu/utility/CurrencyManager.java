package seedu.utility;

import seedu.budget.Budget;
import seedu.commands.currency.CurrencyType;
import seedu.entry.Entry;
import seedu.exceptions.SameCurrencyTypeException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CurrencyManager class maintains all methods required by currency related commands.
 * Performs currency conversion operations on income, expense and budget objects and tracks those changes.
 */
public class CurrencyManager {

    protected double exchangeRate;
    private CurrencyType currency = CurrencyType.SGD;
    private final ArrayList<CurrencyType> currencyTypes = new ArrayList<>();
    

    /**
     * Converts all entries and budgets into given currency type.
     * @param from base currency type
     * @param to new currency type that user wishes to convert to
     * @param finances FinancialTracker object that allows access to all stored entries
     * @param budgetManager BudgetManager object that allows access to stored budgets
     * @throws SameCurrencyTypeException throws error is user tries to convert to same currency type again
     */
    public void currencyConvertor(CurrencyType from, CurrencyType to, FinancialTracker finances,
                                   BudgetManager budgetManager) throws SameCurrencyTypeException {
        if (getCurrency() == to) {
            throw new SameCurrencyTypeException(Messages.SAME_CURRENCY_TYPE_MESSAGE);
        } else {
            ArrayList<Entry> entries = finances.getEntries();
            ArrayList<Budget> budgets = budgetManager.getBudgets();
            convertEntries(entries, from, to);
            convertBudgets(budgets, from, to);
        }
    }

    /**
     * Returns the current currency state of all entries.
     * @return currency
     */
    public CurrencyType getCurrency() {
        assert currency != null;
        return currency;
    }

    /**
     * Updates currency type to new currency.
     * @param currency tracks currency type of entries
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    /**
     * Creates arrayList with enums contained in  currencyType class.
     * @return currencyTypes
     */
    public ArrayList<CurrencyType> getCurrencyTypes() {
        currencyTypes.addAll(Arrays.asList(CurrencyType.values()));
        return currencyTypes;
    }

    /**
     * Returns exchange rate of given currency type.
     * @param to new currency type that user wishes to convert to
     * @return exchangeRate
     */
    public double determineExchangeRate(CurrencyType to) {
        switch (to) {
        case RMB:
            return exchangeRate = 5.00;
        case SGD:
            return exchangeRate = (1.00 / 5.00);
        default:
            return 0;
        }
    }

    /**
     * Checks if given currency type is equal to SGD.
     * @param from base currency type
     * @return false if equals SGD
     */
    public boolean isBaseCurrency(CurrencyType from) {
        return !from.equals(CurrencyType.SGD);
    }

    /**
     * Converts all entry objects values into equivalent value in new currency type.
     * @param entries income and expense objects stored in FinancialTracker
     * @param from base currency type
     * @param to new currency type that user wishes to convert to
     */
    public void convertEntries(ArrayList<Entry> entries, CurrencyType from, CurrencyType to) {
        for (Entry entry : entries) {
            double newValue = convertItem(from, to, entry.getValue());
            assert newValue >= 0;
            entry.setValue(newValue);
        }
        setCurrency(to);
    }

    /**
     * Converts all budget objects values into equivalent value in new currency type.
     * @param budgets budget objects stored in BudgetManager
     * @param from base currency type
     * @param to new currency type that user wishes to convert to
     */
    public void convertBudgets(ArrayList<Budget> budgets, CurrencyType from, CurrencyType to) {
        for (Budget budget : budgets) {
            double newValue = convertItem(from, to, budget.getLimit());
            budget.setLimit(newValue);
        }
        setCurrency(to);
    }

    /**
     * Returns new values of entries after multiplying with correct exchangeRate.
     * @param from base currency type
     * @param to new currency type that user wishes to convert to
     * @param value value of object being parsed
     * @return value
     */
    public double convertItem(CurrencyType from, CurrencyType to, double value) {
        if (isBaseCurrency(from)) {
            double fromRate = determineExchangeRate(from);
            assert fromRate >= 0;
            value = (value / fromRate);
        }
        double toRate = determineExchangeRate(to);
        assert toRate >= 0;
        return (value * toRate);
    }
}
