package seedu.utility.tools;

import java.time.LocalDate;

/**
 * Class that stores 2 LocalDate attributes which is used for date operations.
 */
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
}
