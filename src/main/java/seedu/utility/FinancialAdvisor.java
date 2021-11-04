package seedu.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * FinancialAdvisor offers a finance related tip to the user at the end when the program terminates.
 */
public class FinancialAdvisor {
    private static FinancialAdvisor financialAdvisor = null;
    private final Random random;
    
    private static final String ADVICE_1 = "Try using the 50/30/20 rule to budget."
            + " 50% for needs, 30% for wants and 20% for savings";
    private static final String ADVICE_2 = "Do you have an emergency fund? "
            + "It should cover at least 3 to 6 months of your expense";
    private static final String ADVICE_3 = "The Best Time To Invest Was Yesterday,"
            + " The Second Best Time Is Today";
    private static final String ADVICE_4 = "If you have any debts," 
            + " try to pay off the one with the highest interest first.";
    private static final String ADVICE_5 = "If there is something you really want to buy"
            + ", make sure you have the price of it times 10 in your savings first";
    private static final List<String> ADVICES = Arrays.asList(ADVICE_1,ADVICE_2,ADVICE_3,ADVICE_4,ADVICE_5);
    
    private FinancialAdvisor() {
        this.random = new Random();
    }

    /**
     * Method that follows the singleton pattern to instantiate. If an instant does not exist, this method will call the
     * FinancialAdvisor constructor else it will simply return the existing instance.
     * 
     * @return A FinancialAdvisor object is returned.
     */
    public static FinancialAdvisor getInstance() {
        if (financialAdvisor == null) {
            financialAdvisor = new FinancialAdvisor();
        }
        return financialAdvisor;
    }

    /**
     * Generates a random advice.
     * 
     * @return A string that reads a financial advice.
     */
    public String getRandomAdvice() {
        int bound = ADVICES.size();
        if (isValidRandomRange(bound)) {
            int adviceIndex = random.nextInt(bound);
            return ADVICES.get(adviceIndex);
        }
        return Messages.DISPLAY_ADVICE_ERROR;
    }

    private boolean isValidRandomRange(int bound) {
        return bound > 0;
    }
}
