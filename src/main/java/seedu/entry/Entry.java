package seedu.entry;

import seedu.commands.currency.CurrencyType;

import java.time.LocalDate;

/**
 * Entry is the parent class that Expense and Income inherits from.
 */
public abstract class Entry {
    protected String description;
    protected double value;
    protected LocalDate date;
    protected static final String DATE_FORMAT = "dd/MM/yyyy";
    protected CurrencyType originalCurrency;
    protected double originalValue;

    /**
     * Gets the description of the entry.
     * 
     * @return A String storing information on the entry.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the value of the entry.
     * 
     * @return A double storing the value of the entry.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Sets a value for an entry.
     * 
     * @param newValue A double that will replace the original value.
     */
    public void setValue(double newValue) {
        this.value = newValue;
    }

    /**
     * Get the date information of the entry.
     * 
     * @return A LocalDate which is associated with the entry.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Get the category associated with the entry.
     * 
     * @return A enum which the entry is categorised under.
     */
    public abstract Enum getCategory();
    
    public double getOriginalValue() {
        return this.originalValue;
    }

    public void setCurrentDetails(double amount, CurrencyType originalCurrency) {
        this.originalCurrency = originalCurrency;
        this.originalValue = amount;
    }

    /**
     * Converts the object into a string to be printed.
     * 
     * @return A string of information associated to the entry.
     */
    public abstract String toString();
}
