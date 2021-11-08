package seedu.utility;


import seedu.utility.tools.DateOperator;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

public class StonksGraph {
    private static final int ROWS = 20;
    private static final int COLS = 100;
    private static final int ROWS_OFFSET = ROWS - 1;
    private static final int COLS_OFFSET = COLS - 1;
    private static final char INCOME_BAR = 'o';
    private static final char EXPENSE_BAR = '#';
    private static final char X_AXIS_CHAR = '~';
    private static final char SEPARATOR_CHAR = '-';
    private static final char BORDER_CHAR = 'x';
    private static final char NON_BORDER_CHAR = ' ';
    private static final double TOTAL_LIMIT = 100000000000.00;
    private final char[][] grid = new char [ROWS][COLS];

    /**
     * Constructor for the StonksGraph which firsts set the border, then the balance and finally the report itself.
     */
    public StonksGraph(FinancialTracker finances, int year) {
        drawBorder();
        drawBalance(finances.calculateBalance());
        drawReport(finances, year);
    }

    @Override
    public String toString() {
        return convertGridToString();
    }

    /**
     * Returns true it's within the rows where the barchart is supposed to be.
     */
    private boolean isWithinRowsConsistingOfBarGraph(int x) {
        return x >= 7 && x < 17;
    }

    private boolean isBorder(int x, int y) {
        return x == 0 || y == 0 || x == ROWS_OFFSET || y == COLS_OFFSET;
    }

    /**
     * Returns true if column is that of each month's expense bar.
     */
    private boolean isExpenseBar(int y) {
        return y == 4 || y == 12 || y == 20 || y == 28 || y == 36 || y == 44 || y == 52 || y == 60
                || y == 68 || y == 76 || y == 84 || y == 92;
    }

    /**
     * Returns true if column is that of each month's income bar.
     */
    private boolean isIncomeBar(int y) {
        return y == 5 || y == 13 || y == 21 || y == 29 || y == 37 || y == 45 || y == 53 || y == 61
                || y == 69 || y == 77 || y == 85 || y == 93;
    }


    private void drawBorder() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (isBorder(x, y)) {
                    grid[x][y] = BORDER_CHAR;
                } else {
                    grid[x][y] = NON_BORDER_CHAR;
                }
            }
        }
    }
    
    
    private void drawBalance(double balance) {
        String stringAmount = String.format("$%.2f", balance);

        assert (stringAmount.length() <= 30) : "Size should be <= 30";
        writeToGraph(2,4,"Account Balance: ");
        writeToGraph(2,21,  stringAmount);
    }



    private void writeToGraph(int rowCount, int colCount, String toAdd) {
        int stringLength = toAdd.length();

        int i = 0;
        while (i < stringLength) {
            grid[rowCount][colCount] = toAdd.charAt(i);
            colCount++;
            i++;
        }
        
    }

    /**
     * Converts the 2d array grid into a String.
     *
     * @return A string which represents the 2D grid in 1D form.
     */
    private String convertGridToString() {
        StringBuilder convertedString = new StringBuilder();
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                convertedString.append(grid[x][y]);
            }
            convertedString.append(System.lineSeparator());
        }
        return convertedString.toString();
    }
    

    /**
     * Returns month as an int based on which column it is at.
     *
     * @param colCount The column count of the grid.
     * @return Returns an integer that represents the month.
     */
    private int getMonth(int colCount) {
        if (colCount >= 4 && colCount <= 6) {
            return 1;
        } else if (colCount >= 12 && colCount <= 14) {
            return 2;
        }  else if (colCount >= 20 && colCount <= 22) {
            return 3;
        }  else if (colCount >= 28 && colCount <= 30) {
            return 4;
        }  else if (colCount >= 36 && colCount <= 38) {
            return 5;
        }  else if (colCount >= 44 && colCount <= 46) {
            return 6;
        }  else if (colCount >= 52 && colCount <= 54) {
            return 7;
        }  else if (colCount >= 60 && colCount <= 62) {
            return 8;
        }  else if (colCount >= 68 && colCount <= 70) {
            return 9;
        }  else if (colCount >= 76 && colCount <= 78) {
            return 10;
        }  else if (colCount >= 84 && colCount <= 86) {
            return 11;
        } else {
            return 12;
        }
    }

    /**
     * Draw the total expenses and incomes for the current month in the yearly report.
     *
     * @param currIncomeBreakdowns ArrayList containing all the totalIncomes for each month of the current year.
     * @param currExpenseBreakdowns ArrayList containing all the totalExpenses for each month of the current year.
     */
    private void drawCurrentMonth(ArrayList<Double> currIncomeBreakdowns, ArrayList<Double> currExpenseBreakdowns) {
        
        Month currentMonth = DateOperator.currentMonth();
        int currentMonthInIndex = DateOperator.currentMonthInIndex();
        String currentExpenseString = "Current month (" + currentMonth + ") total expense: ";
        String currentIncomeString = "Current month (" + currentMonth + ") total income: ";
        
        double currentMonthExpense = currExpenseBreakdowns.get(currentMonthInIndex);
        writeToGraph(3,4, currentExpenseString);
        String stringCurrentMonthExpense = String.format("$%.2f", currentMonthExpense);
        writeToGraph(3, 44, stringCurrentMonthExpense);

        double currentMonthIncome = currIncomeBreakdowns.get(currentMonthInIndex);
        writeToGraph(4,4, currentIncomeString);
        String stringCurrentMonthIncome = String.format("$%.2f", currentMonthIncome);
        writeToGraph(4, 43, stringCurrentMonthIncome);

    }

    private void drawLegendAndTitle() {
        writeToGraph(5,4, "Your Year Report");
        writeToGraph(2, 75, "Legend:");
        writeToGraph(3, 80, " # is Expense");
        writeToGraph(4, 80, " o is Income ");
    }

    private void drawXAxisLabels() {
        writeToGraph(18,4,"Jan");
        writeToGraph(18,12,"Feb");
        writeToGraph(18,20,"Mar");
        writeToGraph(18,28,"Apr");
        writeToGraph(18,36,"May");
        writeToGraph(18,44,"Jun");
        writeToGraph(18,52,"Jul");
        writeToGraph(18,60,"Aug");
        writeToGraph(18,68,"Sept");
        writeToGraph(18,76,"Oct");
        writeToGraph(18,84,"Nov");
        writeToGraph(18,92,"Dec");
    }

    private void drawXAxis() {
        for (int i = 2;i < 98; i++) {
            grid[17][i] = X_AXIS_CHAR;
        }
    }

    private void drawSeparator() {
        for (int i = 2;i < 98; i++) {
            grid[6][i] = SEPARATOR_CHAR;
        }
    }

    private void drawIncomeBar(int x, int y, int incomeBar) {
        if (x >= 17 - incomeBar && x < 17) {
            grid[x][y] = INCOME_BAR;
        }
    }

    private void drawExpenseBar(int x, int y, int expenseBar) {
        if (x >= 17 - expenseBar && x < 17) {
            grid[x][y] = EXPENSE_BAR;
        }
    }
    

    private void drawBar(int x, int y, int noOfIncomeBar, int noOfExpenseBar) {
        if (isWithinRowsConsistingOfBarGraph(x)) {
            if (isExpenseBar(y)) {
                drawExpenseBar(x, y, noOfExpenseBar);
            } else if (isIncomeBar(y)) {
                drawIncomeBar(x, y, noOfIncomeBar);
            }
        }
    }



    /**
     * Draws the yearly report shown in show_graph command depending on year user enter as input.
     * Yearly report includes the legend, current month totals , monthly breakdown, axes and the title.
     *
     * @param finances The financial tracker with all the various entries.
     * @param year The year given as input by user.                
     */
    private void drawReport(FinancialTracker finances, int year) {
        drawSeparator();
        drawLegendAndTitle();
        drawXAxisLabels();
        drawXAxis();
        ArrayList<Double> searchedIncomeBreakdowns = finances.getMonthlyIncomeBreakdown(year);
        ArrayList<Double> searchedExpenseBreakdowns = finances.getMonthlyExpenseBreakdown(year);
        
        ArrayList<Double> values = new ArrayList<>();
        values.addAll(searchedExpenseBreakdowns);
        values.addAll(searchedIncomeBreakdowns);
        double biggestTotal = Collections.max(values);
        assert (biggestTotal <= TOTAL_LIMIT) : "Total income/expense should be less than equal to 100Bil";
        double barValue = determineBarValue(biggestTotal);

        ArrayList<Double> currentIncomeBreakdowns = finances.getMonthlyIncomeBreakdown(LocalDate.now().getYear());
        ArrayList<Double> currentExpenseBreakdowns = finances.getMonthlyExpenseBreakdown(LocalDate.now().getYear());
        drawCurrentMonth(currentIncomeBreakdowns, currentExpenseBreakdowns);
        
        
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                int monthIndex = getMonth(y) - 1;
                int noOfIncomeBar = (int)(searchedIncomeBreakdowns.get(monthIndex) / barValue);
                int noOfExpenseBar = (int)(searchedExpenseBreakdowns.get(monthIndex) / barValue);
                drawBar(x, y, noOfIncomeBar, noOfExpenseBar);
            }
        }
    }

    private double determineBarValue(double totalValue) {
        boolean isBetweenZeroPointOneAndOne = totalValue >= 0.1 && totalValue < 1;
        boolean isSmallerThanZeroPointOne = totalValue < 0.1;
        
        if (isBetweenZeroPointOneAndOne) {
            writeToGraph(5, 75, "Unit: 0.1");
            return 0.1;
        } else if (isSmallerThanZeroPointOne) {
            writeToGraph(5, 75, "Unit: 0.01");
            return 0.01;
        }
        
        int noOfDigits = 0;
        //Counts no of digits on the left of decimal point
        while (totalValue >= 1) {
            totalValue = totalValue / 10;
            noOfDigits++;
        }
        double barValue = Math.pow(10, noOfDigits - 1);
        
        writeToGraph(5, 75, "Unit: " + barValue);
        return barValue;
    }
    
}
