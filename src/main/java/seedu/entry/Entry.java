package seedu.entry;

import java.time.LocalDate;

/**
 * Entry is the parent class that Expense and Income inherits from.
 */
public abstract class Entry {
    protected String description;
    protected double value;
    protected LocalDate date;
    protected static final String DATE_FORMAT = "dd/MM/yyyy";

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

    /**
     * Converts the object into a string to be printed.
     * 
     * @return A string of information associated to the entry.
     */
    public abstract String toString();
}
