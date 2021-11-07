package seedu.utility;

import seedu.budget.Budget;
import seedu.commands.currency.CurrencyType;
import seedu.entry.Entry;
import seedu.exceptions.SameCurrencyTypeException;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrencyManager {

    protected double exchangeRate;
    private CurrencyType currency = CurrencyType.SGD;
    private final ArrayList<CurrencyType> currencyTypes = new ArrayList<>();

    public void currencyConvertor(CurrencyType from, CurrencyType to, FinancialTracker finances,
                                   BudgetManager budgetManager) throws SameCurrencyTypeException {
        if (getCurrency() == to) {
            throw new SameCurrencyTypeException(Messages.SAME_CURRENCY_TYPE_MESSAGE);
        } else if (to == CurrencyType.SGD) {
            convertOriginal(finances.getEntries());
        } else {
            ArrayList<Entry> entries = finances.getEntries();
            ArrayList<Budget> budgets = budgetManager.getBudgets();
            convertEntries(entries, from, to);
            convertBudgets(budgets, from, to);
        }
    }

    public CurrencyType getCurrency() {
        assert currency != null;
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public ArrayList<CurrencyType> getCurrencyTypes() {
        currencyTypes.addAll(Arrays.asList(CurrencyType.values()));
        return currencyTypes;
    }

    public double determineExchangeRate(CurrencyType to) {
        switch (to) {
        case USD:
            return exchangeRate = 5.00;
        case INR:
            return exchangeRate = 2.00;
        case SGD:
            return exchangeRate = 1.00;
        default:
            return 0;
        }
    }

    public boolean isBaseCurrency(CurrencyType from) {
        return !from.equals(CurrencyType.SGD);
    }

    public void convertOriginal(ArrayList<Entry> entries) {
        for (Entry entry : entries) {
            entry.setValue(entry.getOriginalValue());
        }
        setCurrency(CurrencyType.SGD);
    }

    public void convertEntries(ArrayList<Entry> entries, CurrencyType from, CurrencyType to) {
        for (Entry entry : entries) {
            double newValue = convertItem(from, to, entry.getValue());
            assert newValue >= 0;
            entry.setValue(newValue);
        }
        setCurrency(to);
    }

    public void convertBudgets(ArrayList<Budget> budgets, CurrencyType from, CurrencyType to) {
        for (Budget budget : budgets) {
            double newValue = convertItem(from, to, budget.getLimit());
            budget.setLimit(newValue);
        }
        setCurrency(to);
    }

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
