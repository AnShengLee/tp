package seedu.utility.tools;

import seedu.entry.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static seedu.utility.FinancialTracker.TOTAL_ENTRIES_LIMIT;


/**
 * This class abstracts out more complication calculations used in FinancialTracker.
 */
public abstract class FinancialCalculator {
    private static final int[] MONTHS = IntStream.range(1,13).toArray();
    
    /**
     * Sorts an entire year's entries according to the month they are associated with.
     * 
     * @param yearlyAccumulatedEntries A List of entries that all share the same associated year.
     * @return A sorted ArrayList where index 1 - 12 contains the total entry corresponding to the month Jan to Dec.
     */
    public static ArrayList<Double> sortEntriesByMonth(List<Entry> yearlyAccumulatedEntries) {
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int month : MONTHS) {
            double entryForTheMonth = getMonthlyEntries(month, yearlyAccumulatedEntries);
            monthlyBreakdown.add(entryForTheMonth);
        }
        return monthlyBreakdown;
    }
    
    private static double getMonthlyEntries(int inputMonth, List<Entry> yearlyEntries) {
        List<Entry> monthlyAccumulatedEntries = yearlyEntries.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getSumOfEntries(monthlyAccumulatedEntries);
    }

    /**
     * Calculate the total amount associated to all the entries in the list.
     * 
     * @param accumulatedEntries A list containing all the entries we want to sum the values of.
     * @return The sum of all the entries stored as a double.
     */
    public static double getSumOfEntries(List<Entry> accumulatedEntries) {
        double totalEntry = 0;
        for (Entry entry : accumulatedEntries) {
            totalEntry += entry.getValue();
        }
        assert totalEntry < TOTAL_ENTRIES_LIMIT;
        return totalEntry;
    }
}
