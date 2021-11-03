package seedu.utility;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.commands.currency.CurrencyType;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.exceptions.IncomeOverflowException;
import seedu.utility.datetools.DateOperator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A Financial tracker that contains 2 separate list of income and expense entries and a net balance.
 */
public class FinancialTracker {
    private static final double TOTAL_EXPENSE_LIMIT = 100000000000.00;
    private static final double TOTAL_INCOME_LIMIT = 100000000000.00;
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;

    /**
     * Constructor for financial tracker initialises two empty ArrayList, one for expenses and one for incomes.
     */
    public FinancialTracker() {
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
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
        expenses.add(expense);
        assert !expenses.isEmpty();
        assert expenses.size() > expenseSize;
    }

    private boolean isOverflowedExpense(Expense expense) {
        return expense.getValue() + getTotalExpense() > TOTAL_EXPENSE_LIMIT;
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
        return income.getValue() + getTotalIncome() > TOTAL_INCOME_LIMIT;
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
        return totalExpense;
    }

    private double getTotalExpense(List<Expense> accumulatedExpense) {
        double totalExpense = 0;
        for (Expense expense: accumulatedExpense) {
            totalExpense += expense.getValue();
        }
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
        return totalIncome;
    }

    private double getTotalIncome(List<Income> accumulatedIncome) {
        double totalIncome = 0;
        for (Income income: accumulatedIncome) {
            totalIncome += income.getValue();
        }
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
        List<Expense> accumulatedExpense = expenses.stream()
                .filter(DateOperator.entryDateInRange(startDate, endDate))
                .collect(Collectors.toList());
        return getTotalExpense(accumulatedExpense);
    }
    
    private double getMonthlyExpense(int inputMonth, List<Expense> yearlyExpense) {
        List<Expense> monthlyAccumulatedExpense = yearlyExpense.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getTotalExpense(monthlyAccumulatedExpense);
    }

    /**
     * Returns an ArrayList of size 12, where each element stores the total expense of that month in the given year.
     *
     * @param inputYear Year which the monthly breakdown is based on.
     * @return ArrayList of elements representing total expense in each month.
     */
    public ArrayList<Double> getMonthlyExpenseBreakdown(int inputYear) {
        List<Expense> yearlyAccumulatedExpense = expenses.stream()
                .filter(DateOperator.sameEntryYear(inputYear))
                .collect(Collectors.toList());
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthlyBreakdown.add(getMonthlyExpense(i,yearlyAccumulatedExpense));
        }
        return monthlyBreakdown;
    }

    /**
     * Returns total income between two given dates.
     *
     * @param startDate Starting date (Left boundary).
     * @param endDate End Date (Right boundary).
     * @return Total income between two given dates.
     */
    public double getIncomeBetween(LocalDate startDate, LocalDate endDate) {
        List<Income> accumulatedIncome = incomes.stream()
                .filter(DateOperator.entryDateInRange(startDate, endDate))
                .collect(Collectors.toList());
        return getTotalIncome(accumulatedIncome);
    }
    
    private double getMonthlyIncome(int inputMonth, List<Income> yearlyIncome) {
        List<Income> monthlyAccumulatedIncome = yearlyIncome.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getTotalIncome(monthlyAccumulatedIncome);
    }

    /**
     * Returns an ArrayList of size 12, where each element stores the total income of that month in the given year.
     *
     * @param inputYear Year which the monthly breakdown is based on.
     * @return ArrayList of elements representing total income in each month.
     */
    public ArrayList<Double> getMonthlyIncomeBreakdown(int inputYear) {
        List<Income> yearlyAccumulatedIncome = incomes.stream()
                .filter(DateOperator.sameEntryYear(inputYear))
                .collect(Collectors.toList());
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthlyBreakdown.add(getMonthlyIncome(i, yearlyAccumulatedIncome));
        }
        return monthlyBreakdown;
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
