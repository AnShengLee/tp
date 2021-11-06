package seedu.duke;

import seedu.commands.Command;

import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;

import seedu.utility.Parser;
import seedu.utility.Ui;

/**
 * A command line interfaced program that can store your financial entries and provide other insights and analytical 
 * services.
 */
public class StonksXD {
    private Ui ui;
    private FinancialTracker finances;
    private Parser parser;
    private DataManager dataManager;
    private BudgetManager budgetManager;
    private CurrencyManager currencyManager;

    /**
     * Constructor for StonksXD. It instantiates all the components used and are crucial to the functioning of the 
     * program.
     */
    public StonksXD() {
        this.ui = new Ui();
        this.finances = new FinancialTracker();
        this.parser = new Parser();
        this.budgetManager = new BudgetManager();
        this.currencyManager = new CurrencyManager();
        
        this.dataManager = new DataManager(parser, finances, ui, budgetManager, currencyManager);
        dataManager.loadAll();
    }

    /**
     * This method handles the lifecycle and the general logic of the program. It reads users input and performs actions
     * based on it.
     */
    public void run() {
        ui.printWelcome();
        boolean isNonTerminatingCommand = true;
        while (isNonTerminatingCommand) {
            String fullCommand = ui.readCommand();
            Command command = parser.parseCommand(fullCommand);
            command.execute(finances, ui, budgetManager, currencyManager);
            if (command.isExit()) {
                isNonTerminatingCommand = false;
            }
            dataManager.saveAll();
        }
        //ui.printBye(getRandomAdvice());
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
