package seedu.utility;

import java.util.Arrays;
import java.util.List;

public class CommandFormat {
    private static final String HELP_FORMAT = "List Out All Commands: help";
    private static final String ADD_EXPENSE_FORMAT = "Adding Expense (Date Format: DD/MM/YYYY): add_ex d/DESCRIPTION " 
            + "a/AMOUNT c/CATEGORY [D/DATE]";
    private static final String DEL_EXPENSE_FORMAT = "Deleting Expense: del_ex i/INDEX";
    private static final String LIST_EXPENSE_FORMAT = "Listing Expense: list_ex";
    private static final String TOTAL_EXPENSE_FORMAT = "Show Total Expense: total_ex";
    private static final String ADD_INCOME_FORMAT = "Adding Income (Date Format: DD/MM/YYYY): add_in d/DESCRIPTION " 
            + "a/AMOUNT c/CATEGORY [D/DATE]";
    private static final String DEL_INCOME_FORMAT = "Deleting Income: del_in i/INDEX";
    private static final String LIST_INCOME_FORMAT = "Listing Income: list_in";
    private static final String TOTAL_INCOME_FORMAT = "Show Total Income: total_in";
    private static final String EXPENSE_BETWEEN_FORMAT = "Show Total Expense between 2 dates (Date Format: DD/MM/YYYY)"
            + ": btw_ex s/START_DATE e/END_DATE";
    private static final String INCOME_BETWEEN_FORMAT = "Show Total Income between 2 dates (Date Format: DD/MM/YYYY)"
            + ": btw_in s/START_DATE e/END_DATE";
    private static final String END_FORMAT = "To Terminate The Program: end";
    private static final String FIND_FORMAT = "To Find Using Date: find DD/MM/YYYY\n"
            + "To Find Based On Keyword: find KEYWORD";
    private static final String BALANCE_FORMAT = "To Display Total Balance: balance";
    private static final String SET_BUDGET_FORMAT = "To Set Budgets: set_budget c/CATEGORY a/AMOUNT";
    private static final String CHECK_BUDGET_FORMAT = "To Check Budgets: check_budget c/CATEGORY";
    private static final String SET_THRESHOLD_FORMAT = "To Set Threshold Value for Reminders: "
            + "set_threshold t/THRESHOLD";
    private static final String CLEAR_FORMAT = "To Clear All Expense And Income Entries: clear_all_entries";
    private static final String GRAPH_FORMAT = "To View Your Yearly Report (Year format: YYYY): show_graph [Y/YEAR]";
    private static final String CONVERT_CURRENCY_FORMAT
            = "To change entries into a different currency: set_curr c/CURRENCY";
    private static final String CHECK_CURRENCY_FORMAT
            = "To check the currency that entries are currently in: check_curr";
    private static final String LIST_CURRENCY_TYPES_FORMAT
            =  "Lists available currency types for conversion: list_curr";
    
    public static final List<String> commands = Arrays.asList(HELP_FORMAT, ADD_EXPENSE_FORMAT, DEL_EXPENSE_FORMAT,
        ADD_INCOME_FORMAT, DEL_INCOME_FORMAT, LIST_EXPENSE_FORMAT, LIST_INCOME_FORMAT, TOTAL_EXPENSE_FORMAT,
        TOTAL_INCOME_FORMAT, BALANCE_FORMAT, EXPENSE_BETWEEN_FORMAT, INCOME_BETWEEN_FORMAT, FIND_FORMAT,
        SET_BUDGET_FORMAT, CHECK_BUDGET_FORMAT, SET_THRESHOLD_FORMAT, CONVERT_CURRENCY_FORMAT, CHECK_CURRENCY_FORMAT,
            LIST_CURRENCY_TYPES_FORMAT, GRAPH_FORMAT, CLEAR_FORMAT, END_FORMAT);
}
