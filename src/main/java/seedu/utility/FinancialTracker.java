package seedu.utility;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.exceptions.IncomeOverflowException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static seedu.utility.tools.DateOperator.entryDateInRange;
import static seedu.utility.tools.DateOperator.sameEntryYear;
import static seedu.utility.tools.FinancialCalculator.getSumOfEntries;
import static seedu.utility.tools.FinancialCalculator.sortEntriesByMonth;


/**
 * A Financial tracker that contains 2 separate list of income and expense entries and a net balance.
 */
public class FinancialTracker {
    private final ArrayList<Expense> expenses;
    private final ArrayList<Income> incomes;
    private final CurrencyManager currencyManager;

    public static final double TOTAL_ENTRIES_LIMIT = 100000000000.00;

    /**
     * Constructor for financial tracker initialises two empty ArrayList, one for expenses and one for incomes.
     */
    public FinancialTracker(CurrencyManager currencyManager) {
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
        this.currencyManager = currencyManager;
    }

    /**
     * Returns balance of the financial tracker which is totalincome - totalexpense.
     *
     * @return Balance of the financial tracker.
     */
    public double calculateBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    /**
     * Adds an Expense object into the expenses ArrayList of FinancialTracker.
     *
     * @param expense Expense object we want to add into the ArrayList.
     * @throws ExpenseOverflowException Thrown if the sum of expense exceeds a fixed limit. 
     */
    public void addExpense(Expense expense) throws ExpenseOverflowException {
        int expenseSize = 0;
        assert (expenseSize = expenses.size()) >= 0;
        if (isOverflowedExpense(expense)) {
            throw new ExpenseOverflowException(Messages.EXPENSE_OVERFLOW_ERROR);
        }
        //so now each expense/income have their og value&curr stored
        expense.setCurrentDetails(expense.getValue(), currencyManager.getCurrency());

        expenses.add(expense);

        assert !expenses.isEmpty();
        assert expenses.size() > expenseSize;
    }

    private boolean isOverflowedExpense(Expense expense) {
        return expense.getValue() + getTotalExpense() > TOTAL_ENTRIES_LIMIT;
    }

    /**
     * Adds an Income object into the income ArrayList of FinancialTracker.
     * 
     * @param income Income object we want to add into the ArrayList.
     * @throws IncomeOverflowException Thrown if the sum of income exceeds a fixed limit.
     */
    public void addIncome(Income income) throws IncomeOverflowException {
        int incomeSize = 0;
        assert (incomeSize = incomes.size()) >= 0;
        if (isOverflowedIncome(income)) {
            throw new IncomeOverflowException(Messages.INCOME_OVERFLOW_ERROR);
        }
        incomes.add(income);
        assert !incomes.isEmpty();
        assert incomes.size() > incomeSize;
    }

    private boolean isOverflowedIncome(Income income) {
        return income.getValue() + getTotalIncome() > TOTAL_ENTRIES_LIMIT;
    }

    private int indexOffset(int index) {
        return index - 1;
    }

    /**
     * Removes an expense entry based on its index.
     *
     * @param expenseIndex Index of deleted expense entry.
     * @return Deleted expense object.
     * @throws ExpenseEntryNotFoundException Thrown if no entry can be found in the given index.
     */
    public Expense removeExpense(int expenseIndex) throws ExpenseEntryNotFoundException {
        try {
            Expense removedExpense =  expenses.remove(indexOffset(expenseIndex));
            assert expenses.stream().noneMatch(expense -> expense == removedExpense);
            return removedExpense;
        } catch (IndexOutOfBoundsException e) {
            throw new ExpenseEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
    }

    /**
     * Removes an income entry based on its index.
     * 
     * @param incomeIndex Index of deleted income entry.
     * @return Deleted income object.
     * @throws IncomeEntryNotFoundException Thrown if no entry can be found in the given index.
     */
    public Income removeIncome(int incomeIndex) throws IncomeEntryNotFoundException {
        try {
            Income removedIncome = incomes.remove(indexOffset(incomeIndex));
            assert incomes.stream().noneMatch(expense -> expense == removedIncome);
            return removedIncome;
        } catch (IndexOutOfBoundsException e) {
            throw new IncomeEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
    }

    /**
     * Returns an ArrayList called expenses from FinancialTracker.
     * 
     * @return Returns ArrayList with only expense entries inside.
     */
    public ArrayList<Expense> getExpenses() {
        assert expenses != null;
        return expenses;
    }

    /**
     * Returns an ArrayList called incomes from FinancialTracker.
     *
     * @return Returns ArrayList with only income entries inside.
     */
    public ArrayList<Income> getIncomes() {
        assert incomes != null;
        return incomes;
    }

    /**
     * Returns an ArrayList of Entry elements, which include all incomes and expenses entries in the financial tracker.
     *
     * @return ArrayList of Entry elements.
     */
    public ArrayList<Entry> getEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.addAll(getExpenses());
        entries.addAll(getIncomes());
        return entries;
    }

    /**
     * Returns the total expense of all expenses in the financial tracker.
     *
     * @return Total expense of all expenses in the financial tracker.
     */
    public double getTotalExpense() {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            assert expense.getValue() >= 0;
            totalExpense += expense.getValue();
        }
        assert totalExpense >= 0;
        assert totalExpense <= TOTAL_ENTRIES_LIMIT;
        return totalExpense;
    }
    
    /**
     * Returns the total income of all incomes in the financial tracker.
     *
     * @return Total income of all incomes in the financial tracker.
     */
    public double getTotalIncome() {
        double totalIncome = 0;
        for (Income income : incomes) {
            assert income.getValue() >= 0;
            totalIncome += income.getValue();
        }
        assert totalIncome >= 0;
        assert totalIncome <= TOTAL_ENTRIES_LIMIT;
        return totalIncome;
    }

    /**
     * Returns total expense between two given dates.
     *
     * @param startDate Starting date (Left boundary).
     * @param endDate End Date (Right boundary).
     * @return Total expense between two given dates.
     */
    public double getExpenseBetween(LocalDate startDate, LocalDate endDate) {
        List<Entry> accumulatedExpense = expenses.stream()
                .filter(entryDateInRange(startDate, endDate))
                .collect(toList());
        return getSumOfEntries(accumulatedExpense);
    }
    
    /**
     * Returns an ArrayList of size 12, where each element stores the total expense of that month in the given year.
     *
     * @param inputYear Year which the monthly breakdown is based on.
     * @return ArrayList of elements representing total expense in each month.
     */
    public ArrayList<Double> getMonthlyExpenseBreakdown(int inputYear) {
        List<Entry> yearlyAccumulatedExpense = expenses.stream()
                .filter(sameEntryYear(inputYear))
                .collect(toList());
        return sortEntriesByMonth(yearlyAccumulatedExpense);
    }

    /**
     * Returns total income between two given dates.
     *
     * @param startDate Starting date (Left boundary).
     * @param endDate End Date (Right boundary).
     * @return Total income between two given dates.
     */
    public double getIncomeBetween(LocalDate startDate, LocalDate endDate) {
        List<Entry> accumulatedIncome = incomes.stream()
                .filter(entryDateInRange(startDate, endDate))
                .collect(toList());
        return getSumOfEntries(accumulatedIncome);
    }

    /**
     * Returns an ArrayList of size 12, where each element stores the total income of that month in the given year.
     *
     * @param inputYear Year which the monthly breakdown is based on.
     * @return ArrayList of elements representing total income in each month.
     */
    public ArrayList<Double> getMonthlyIncomeBreakdown(int inputYear) {
        List<Entry> yearlyAccumulatedIncome = incomes.stream()
                .filter(sameEntryYear(inputYear))
                .collect(toList());
        return sortEntriesByMonth(yearlyAccumulatedIncome);
    }

    /**
     * Returns the size of the expenses ArrayList.
     *
     * @return Size of the expenses array list.
     */
    public int getExpenseSize() {
        return expenses.size();
    }

    /**
     * Returns the size of the incomes ArrayList.
     *
     * @return Size of the incomes array list.
     */
    public int getIncomeSize() {
        return incomes.size();
    }

    /**
     * Returns true if expenses list of financial tracker is empty.
     *
     * @return Whether the expense list is empty.
     */
    public boolean isExpensesEmpty() {
        return expenses.isEmpty();
    }

    /**
     * Returns true if incomes list of financial tracker is empty.
     *
     * @return Whether the income list is empty.
     */
    public boolean isIncomesEmpty() {
        return incomes.isEmpty();
    }

    /**
     * Delete all entries from both expenses and income list in financial tracker and set balance to zero.
     */
    public void clearAllEntries() {
        expenses.clear();
        incomes.clear();
    }
}
