package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Income is an object that stores the description, value, date, and category of an income entry inputted by the user.
 */
public class Income extends Entry {
    IncomeCategory category;

    /**
     * Constructor that initializes description, value and category of the income entry.
     * Date is set to current date
     * @param description Description of the income entry
     * @param value Dollar value of the income entry
     * @param category Category of the income entry. It must be one of the 4 valid pre-programmed expense categories.
     */
    public Income(String description, double value, IncomeCategory category) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    /**
     * Constructor that initializes description, value, category and date of the income entry.
     * @param description Description of the income entry
     * @param value Dollar value of the income entry
     * @param category Category of the income entry. It must be one of the 4 valid pre-programmed expense categories.
     * @param date Date that the income entry was made.
     */
    public Income(String description, double value, IncomeCategory category, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    /**
     * Returns the category of the income entry.
     * @return Category of the incoem entry
     */
    @Override
    public IncomeCategory getCategory() {
        return category;
    }

    /**
     * Returns the income entry as a string to be printed.
     * @return String format of the income entry
     */
    @Override
    public String toString() {
        String incomeDate = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return String.format("[I] %s - $%.2f (%s)", description, value, incomeDate);
    }
}
