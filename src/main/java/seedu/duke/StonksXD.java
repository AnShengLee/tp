package seedu.duke;

import seedu.commands.Command;

import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;
import seedu.utility.FinancialAdvisor;

import seedu.utility.Parser;
import seedu.utility.Ui;


public class StonksXD {
    private Ui ui;
    private FinancialTracker finances;
    private Parser parser;
    private DataManager dataManager;
    private BudgetManager budgetManager;
    private CurrencyManager currencyManager;
    private FinancialAdvisor financialAdvisor;
    private String advice;

    public StonksXD() {
        this.ui = new Ui();
        this.finances = new FinancialTracker();
        this.parser = new Parser();
        this.budgetManager = new BudgetManager();
        this.currencyManager = new CurrencyManager();
        
        this.dataManager = new DataManager(parser, finances, ui, budgetManager, currencyManager);
        dataManager.loadAll();
        
        this.financialAdvisor = new FinancialAdvisor();
        this.advice = financialAdvisor.getRandomAdvice();
    }
    
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
        //ui.printBye(advice);
    }

    

    public static void main(String[] args) {
        new StonksXD().run();
    }
}
