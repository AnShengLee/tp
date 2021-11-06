package seedu.utility;


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
    private static final int MAX_NUMBER_OF_DIGITS = 30;
    private final char[][] grid = new char [ROWS][COLS];
    private static final char BORDER_CHAR = 'x';
    private static final char NON_BORDER_CHAR = ' ';

    /**
     * Constructor for the StonksGraph which firsts sets the border, then the balance and finally the report itself.
     */
    public StonksGraph(FinancialTracker finances, int year) {
        setBorder();
        setBalance(finances.calculateBalance());
        drawReport(finances, year);
    }

    @Override
    public String toString() {
        return convertGridToString();
    }
    
    private void setBorder() {
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

    private boolean isBorder(int x, int y) {
        return x == 0 || y == 0 || x == ROWS_OFFSET || y == COLS_OFFSET;
    }

    private void setBalance(double amount) {
        String stringAmount = String.format("$%.2f", amount);
        if (stringAmount.length() > MAX_NUMBER_OF_DIGITS) {
            stringAmount = "Too Large!";
        }
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
     * @param monthIncomeBreakdowns ArrayList containing all the totalIncomes for each month of the year.
     * @param monthExpenseBreakdowns ArrayList containing all the totalExpenses for each month of the year.
     */
    private void drawCurrentMonth(ArrayList<Double> monthIncomeBreakdowns, ArrayList<Double> monthExpenseBreakdowns) {
        Month currentMonth = currentMonth();
        int currentMonthInIndex = currentMonthInIndex();
        double currentMonthExpense = monthExpenseBreakdowns.get(currentMonthInIndex);
        double currentMonthIncome = monthIncomeBreakdowns.get(currentMonthInIndex);

        String stringCurrentMonthExpense = String.format("$%.2f", currentMonthExpense);
        String stringCurrentMonthIncome = String.format("$%.2f", currentMonthIncome);

        if (stringCurrentMonthExpense.length() > MAX_NUMBER_OF_DIGITS) {
            stringCurrentMonthExpense = "Too Large!";
        }

        if (stringCurrentMonthIncome.length() > MAX_NUMBER_OF_DIGITS) {
            stringCurrentMonthIncome = "Too Large!";
        }


        String currentExpenseString = "Current month (" + currentMonth + ") total expense: ";
        String currentIncomeString = "Current month (" + currentMonth + ") total income: ";
        writeToGraph(3,4, currentExpenseString);
        writeToGraph(3, 44, stringCurrentMonthExpense);
        writeToGraph(4,4, currentIncomeString);
        writeToGraph(4, 43, stringCurrentMonthIncome);

    }

    /**
     * Returns true it's within the rows where the barchart is suppose to be.
     */
    private boolean isWithinRowsConsistingOfBarGraph(int x) {
        return x >= 7 && x < 17;
    }

    private void drawLegend() {
        writeToGraph(2, 75, "Legend:");
        writeToGraph(3, 80, " # is Expense");
        writeToGraph(4, 80, " o is Income ");
    }


    /**
     * Returns true if position is that of each month's expense bar.
     */
    private boolean isExpenseBar(int y) {
        return y == 4 || y == 12 || y == 20 || y == 28 || y == 36 || y == 44 || y == 52 || y == 60
                || y == 68 || y == 76 || y == 84 || y == 92;
    }

    /**
     * Returns true if position is that of each month's income bar.
     */
    private boolean isIncomeBar(int y) {
        return y == 5 || y == 13 || y == 21 || y == 29 || y == 37 || y == 45 || y == 53 || y == 61
                || y == 69 || y == 77 || y == 85 || y == 93;
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

    private void setIncomeBar(int x, int y, int incomeBar) {
        if (x >= 17 - incomeBar && x < 17) {
            grid[x][y] = INCOME_BAR;
        }
    }

    private void setExpenseBar(int x, int y, int expenseBar) {
        if (x >= 17 - expenseBar && x < 17) {
            grid[x][y] = EXPENSE_BAR;
        }
    }
    
    private int currentMonthInIndex() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonthValue() - 1;
    }
    
    private Month currentMonth() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonth();
    }

    private void drawBar(int x, int y, int noOfIncomeBar, int noOfExpenseBar) {
        if (isWithinRowsConsistingOfBarGraph(x)) {
            if (isExpenseBar(y)) {
                setExpenseBar(x, y, noOfExpenseBar);
            } else if (isIncomeBar(y)) {
                setIncomeBar(x, y, noOfIncomeBar);
            }
        }
    }



    /**
     * Draws the yearly report shown in show_graph command.
     * Which includes the legend, current month totals , monthly breakdown, axes.
     *
     * @param finances The financial tracker with all the various entries.
     */
    private void drawReport(FinancialTracker finances, int year) {
        writeToGraph(5,4, "Your Yearly Report");
        drawSeparator();
        drawLegend();
        drawXAxisLabels();
        drawXAxis();
        ArrayList<Double> monthlyIncomeBreakdowns = finances.getMonthlyIncomeBreakdown(year);
        ArrayList<Double> monthlyExpenseBreakdowns = finances.getMonthlyExpenseBreakdown(year);
        
        ArrayList<Double> values = new ArrayList<>();
        values.addAll(monthlyExpenseBreakdowns);
        values.addAll(monthlyIncomeBreakdowns);
        double max = Collections.max(values);
        assert (max <= 100000000000.00);

        double barValue = determineBarValue(max);

        drawCurrentMonth(monthlyIncomeBreakdowns, monthlyExpenseBreakdowns);
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                int monthIndex = getMonth(y) - 1;
                int noOfIncomeBar = (int)(monthlyIncomeBreakdowns.get(monthIndex) / barValue);
                int noOfExpenseBar = (int)(monthlyExpenseBreakdowns.get(monthIndex) / barValue);
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
        while (totalValue >= 1) {
            totalValue = totalValue /= 10;
            noOfDigits++;
        }
        double barValue = Math.pow(10, noOfDigits - 1);
        
        
        writeToGraph(5, 75, "Unit: " + barValue);
        return barValue;
    }
    
}
