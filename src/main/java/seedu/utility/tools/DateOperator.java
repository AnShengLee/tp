package seedu.utility.tools;

import seedu.entry.Entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.function.Predicate;

/**
 * DateOperator is stores useful date manipulation methods which are used for processing data of FinancialTracker.java.
 */
public abstract class DateOperator {
    /**
     * Checks if the date of a given entry is within a valid date range, 
     * where startDate is earlier than or the same as endDate.
     * 
     * @param startDate The lower bound of the valid date range.
     * @param endDate The upper bound of the valid date range.
     * @param item The entry which date is of interest.
     * @return True if the date of the item lies within a valid range else returns false.
     */
    public static boolean isBetweenStartAndEndDates(LocalDate startDate, LocalDate endDate, Entry item) {
        LocalDate date = item.getDate();
        return ((date.isAfter(startDate) || date.isEqual(startDate))
                && (date.isBefore(endDate) || date.isEqual(endDate)));
    }

    /**
     * Check if 2 dates form a valid date range.
     * 
     * @param startDate The lower bound of the valid date range.
     * @param endDate The upper bound of the valid date range.
     * @return True if the startDate is earlier than endDate.
     */
    public static boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return ((startDate.isBefore(endDate) || startDate.isEqual(endDate)));
    }

    /**
     * Check if the year associated with the item matches the input year.
     * 
     * @param inputYear Year used to compare item year with
     * @param item The item whose year we want to compare
     * @return True if year of both inputYear and item's year are the same, false if different.
     */
    public static boolean isSameYear(int inputYear, Entry item) {
        return item.getDate().getYear() == inputYear;
    }

    /**
     * Check if the month associated with the item matches the input year.
     *
     * @param inputMonth Year used to compare item month with
     * @param item The item whose month we want to compare
     * @return True if year of both inputMonth and item's month are the same, false if different.
     */
    public static boolean isSameMonth(int inputMonth, Entry item) {
        return item.getDate().getMonthValue() == inputMonth;
    }

    /**
     * Create a DateTime format that only accounts for year. It is used to compare with user input
     * 
     * @return DateTimeFormatter object that compares year.
     */
    public static DateTimeFormatter getYearFormat() {
        return new DateTimeFormatterBuilder().appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
    }

    /**
     * Creates a lambda that compares if the date associated to an item falls between 2 given dates.
     * 
     * @param startDate The lower bound of the valid date range.
     * @param endDate The upper bound of the valid date range.
     * @return A lambda that can compare item's date to a valid date range.
     */
    public static Predicate<Entry> entryDateInRange(LocalDate startDate, LocalDate endDate) {
        return item -> isBetweenStartAndEndDates(startDate, endDate, item);
    }

    /**
     * Creates a lambda that checks if the month associated to an item is the same as an input month.
     * 
     * @param inputMonth The month which we compare the item month to.
     * @return A lambda that can compare if item's month and input month is the same.
     */
    public static Predicate<Entry> sameEntryMonth(int inputMonth) {
        return item -> isSameMonth(inputMonth, item);
    }

    /**
     * Creates a lambda that checks if the year associated to an item is the same as an input year.
     *
     * @param inputYear The month which we compare the item month to.
     * @return A lambda that can compare if item's year and input year is the same.
     */
    public static Predicate<Entry> sameEntryYear(int inputYear) {
        return item -> isSameYear(inputYear, item);
    }
}
