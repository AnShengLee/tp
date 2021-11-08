package seedu.duke;

import seedu.commands.Command;

import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.storage.DataManager;
import seedu.utility.FinancialTracker;

import seedu.utility.Parser;
import seedu.utility.Ui;

/**
 * A command line interfaced program that can store your financial entries and provide other insights and analytical 
 * services.
 */
public class  StonksXD {
    private final Ui ui;
    private final FinancialTracker finances;
    private final Parser parser;
    private final DataManager dataManager;
    private final BudgetManager budgetManager;
    private final CurrencyManager currencyManager;
    private boolean isNonTerminatingCommand = true;

    /**
     * Constructor for StonksXD. It instantiates all the components used and are crucial to the functioning of the 
     * program.
     */
    public StonksXD() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.budgetManager = new BudgetManager();
        this.currencyManager = new CurrencyManager();
        this.finances = new FinancialTracker(currencyManager);
        this.dataManager = new DataManager(finances, ui, budgetManager, currencyManager);
        dataManager.loadAll();
    }

    /**
     * Handles the lifecycle and the general logic of the program. It reads users input and performs actions
     * based on it.
     */
    public void run() {
        ui.printWelcome();
        
        while (isNonTerminatingCommand) {
            String fullCommand = ui.readCommand();
            Command command = parser.parseCommand(fullCommand);
            command.execute(finances, ui, budgetManager, currencyManager);
            if (command.isExit()) {
                terminateStonksXD();
            }
            dataManager.saveAll();
        }
        //ui.printBye();
    }
    
    private void terminateStonksXD() {
        isNonTerminatingCommand = false;
    }

    /**
     * Point of entry for the program.
     * 
     * @param args No input parameters is expected.
     */
    public static void main(String[] args) {
        new StonksXD().run();
    }
}
