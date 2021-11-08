package seedu.commands.general;

import seedu.commands.Command;
import seedu.entry.Entry;
import seedu.utility.BudgetManager;
import seedu.entry.ExpenseCategory;
import seedu.entry.IncomeCategory;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static seedu.utility.tools.DateOperator.DATE_FORMAT;

public class FindCommand extends Command {
    protected String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    /**
     * Filters through both the expense list and income list to search for entries with matching search keywords.
     *
     * @param finances The financial tracker containing all the entries.
     * @param ui The user interface which provide feedback to the user.
     * @param budgetManager The budgeting manager that manages all the budget related operations.
     */
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager,
                        CurrencyManager currencyManager) {
        ArrayList<Entry> entries = finances.getEntries();
        ArrayList<Entry> filteredEntries = new ArrayList<>();
        
        try {
            filterByDate(entries, filteredEntries);
        } catch (DateTimeParseException e) {
            filterByKeyword(entries, filteredEntries);
        }
        
        ui.listFind(filteredEntries);
    }

    private void filterByDate(ArrayList<Entry> entries, ArrayList<Entry> filteredEntries) {
        LocalDate localDate = LocalDate.parse(keyword, DateTimeFormatter.ofPattern(DATE_FORMAT));
        for (Entry entry: entries) {
            if (entry.getDate().isEqual(localDate)) {
                filteredEntries.add(entry);
            }
        }
    }

    private void filterByKeyword(ArrayList<Entry> entries, ArrayList<Entry> filteredEntries) {
        for (Entry entry: entries) {
            String valueAsString = Double.toString(entry.getValue());
            if (entry.getDescription().contains(keyword)) {
                filteredEntries.add(entry);
            } else if (valueAsString.contains(keyword)) {
                filteredEntries.add(entry);
            } else {
                Enum filterCategory = determineCategory();
                if (entry.getCategory().equals(filterCategory)) {
                    filteredEntries.add(entry);
                }
            }
        }
    }

    private Enum determineCategory() {
        Enum filterCategory;
        switch (keyword.toUpperCase()) {
        case "FOOD":
            filterCategory = ExpenseCategory.FOOD;
            break;
        case "TRANSPORT":
            filterCategory = ExpenseCategory.TRANSPORT;
            break;
        case "MEDICAL":
            filterCategory = ExpenseCategory.MEDICAL;
            break;
        case "BILLS":
            filterCategory = ExpenseCategory.BILLS;
            break;
        case "ENTERTAINMENT":
            filterCategory = ExpenseCategory.ENTERTAINMENT;
            break;
        case "MISC":
            filterCategory = ExpenseCategory.MISC;
            break;
        case "SALARY":
            filterCategory = IncomeCategory.SALARY;
            break;
        case "ALLOWANCE":
            filterCategory = IncomeCategory.ALLOWANCE;
            break;
        case "ADHOC":
            filterCategory = IncomeCategory.ADHOC;
            break;
        case "OTHERS":
            filterCategory = IncomeCategory.OTHERS;
            break;
        default:
            filterCategory = ExpenseCategory.NULL;
        }
        return filterCategory;
    }
}