package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Expense is an object that stores the description, value, date and category of an expense entry added by the user.
 */
public class Expense extends Entry {
    private ExpenseCategory category;

    /**
     * Constructor for Expense that initializes description, value and category of the expense entry.
     * Date is set to current date.
     * @param description Description of the expense entry
     * @param value Dollar value of the expense entry
     * @param category Category of the expense. It must be one of the 7 valid pre-programmed expense categories.
     */
    public Expense(String description, double value, ExpenseCategory category) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    /**
     * Constructor for Expense that initializes description, value, date and category fo the expense entry.
     * @param description Description of the expense entry
     * @param value Dollar value of the expense entry
     * @param category Category of the expense. It must be one of the 7 valid pre-programmed expense categories.
     * @param date Date that the expense entry was made.
     */
    public Expense(String description, double value, ExpenseCategory category, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    /**
     * Returns the category of the expense.
     * @return Category of the expense
     */
    @Override
    public ExpenseCategory getCategory() {
        return category;
    }

    /**
     * Returns the expense entry as a string to be printed.
     * @return String format of the expense
     */
    @Override
    public String toString() {
        String expenseDate = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return String.format("[E] %s - $%.2f (%s)", description, value, expenseDate);
    }
}
