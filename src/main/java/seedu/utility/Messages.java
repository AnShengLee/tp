package seedu.utility;

/**
 * Contains all the warning messages to be sent to the user when an invalid input is given.
 */
public class Messages {
    public static final String INVALID_COMMAND_MESSAGE =
            "Invalid command. Use \"help\" to show the list of possible commands.";
    public static final String NON_NUMERIC_AMOUNT_MESSAGE = "Only numeric inputs are allowed for amount.";
    public static final String NON_NUMERIC_THRESHOLD_MESSAGE = "Threshold value given is either out of range or " 
            + "non-numeric. Please try again.";
    public static final String NON_POSITIVE_AMOUNT_MESSAGE = "Only positive values are allowed for amount.";
    public static final String NON_POSITIVE_INTEGER_INDEX_MESSAGE = "Only positive integers are allowed for index.";
    public static final String BLANK_AMOUNT_MESSAGE = "No amount inputted!";
    public static final String UNABLE_TO_DELETE_MESSAGE = "Entry not deleted because entry not found!";
    public static final String BLANK_DESCRIPTION_MESSAGE = "Your description is empty!";
    public static final String HAS_CORRUPTED_DATA_ENTRIES = "StonksXD_Entries.csv has corrupted entries, " 
            + "those corrupted entries will be discarded and not be loaded.";
    public static final String HAS_CORRUPTED_SETTINGS = "StonksXD_Settings.csv has corrupted settings, "
            + "all settings will be reset.";
    public static final String UNABLE_TO_FIND_DATA_FILE = "Unable to find StonksXD_Entries.csv, a new one " 
            + "has been made.";
    public static final String UNABLE_TO_FIND_SETTINGS_FILE = "Unable to find StonksXD_Settings.csv, a new one " 
            + "has been made.";
    public static final String SEARCH_NO_MATCH_MESSAGE = "Your search did not match any of the entries!";
    public static final String PROMPTING_MESSAGE = "Would you like to set your budget before you begin?"
            + " You can use the set budget commands shown in the help command!";
    public static final String HELP_COMMAND_MESSAGE = "This is a list of commands and their format!";
    public static final String LISTING_EXPENSE_MESSAGE = "Below is a list of all of your recent spending!";
    public static final String LISTING_INCOME_MESSAGE = "Below is a list of all of your recent earnings!";
    public static final String EMPTY_INCOME_MESSAGE = "You have not entered any income!";
    public static final String EMPTY_EXPENSE_MESSAGE = "You have not spent anything!";
    public static final String FOUND_LIST_MESSAGE = "Below is a list of all your findings!";
    public static final String BLANK_CATEGORY_MESSAGE = "Your category is empty!";
    public static final String DATE_FORMAT_MESSAGE = "Your start and end dates must be in a DD/MM/YYYY format!";
    public static final String ALL_DATA_CLEARED = "All your entries have been cleared!";
    public static final String INVALID_EXPENSE_CATEGORY_MESSAGE = "Input only 1 of these 6 categories: Food, " 
            + "Transport, Bills, Medical, Entertainment or Misc.";
    public static final String INVALID_INCOME_CATEGORY_MESSAGE = "Input only 1 of these 4 categories: Salary, " 
            + "Allowance, Adhoc, or Others.";
    public static final String INVALID_BUDGET_CATEGORY_MESSAGE = "Input only 1 of these 6 categories: Food, "
            + "Transport, Bills, Medical, Entertainment or Overall";
    public static final String INVALID_THRESHOLD_MESSAGE = "Threshold value should between 0 and 1.";
    public static final String INVALID_CURRENCY_TYPE_MESSAGE = "Please enter a valid currency for conversion!";
    public static final String BLANK_CURRENCY_TYPE_MESSAGE = "You have not entered any currency type!";
    public static final String SAME_CURRENCY_TYPE_MESSAGE = "Your lists are already in the requested currency type!";
    public static final String AVAILABLE_CURRENCIES_MESSAGE
            = "Here is a list of available currencies you can convert to!";

    public static final String SEPARATOR_MESSAGE = "----------------------------------------------------------------"
            + "-------------------------------------";

    public static final String BYE_MESSAGE = "██████  ██    ██ ███████        ██  \n"
            + "██   ██  ██  ██  ██          ██  ██ \n"
            + "██████    ████   █████           ██ \n"
            + "██   ██    ██    ██          ██  ██ \n"
            + "██████     ██    ███████        ██ ";

    public static final String LOGO_MESSAGE = "███████ ████████  ██████  ███    ██ ██   ██ ███████"
            + "     ██   ██ ██████  \n██         ██    ██    ██ ████   ██ ██  ██  ██           ██ ██  ██   ██ \n"
            + "███████    ██    ██    ██ ██ ██  ██ █████   ███████       ███   ██   ██ \n"
            + "     ██    ██    ██    ██ ██  ██ ██ ██  ██       ██      ██ ██  ██   ██ \n"
            + "███████    ██     ██████  ██   ████ ██   ██ ███████     ██   ██ ██████  ";

    public static final String TIP_HEADER = "Here's our tip for the day: ";
    public static final String DISPLAY_ADVICE_ERROR = "Sorry there is no advice for you at this moment >.<";
    public static final String CURRENT_CURRENCY_MESSAGE = "You currency setting currently: ";
    public static final String INVALID_DATE_RANGE_MESSAGE = "Make sure your start date is before your end date";
    public static final String PARAMETERS_ERROR_MESSAGE = "You have missing or invalid parameters. Use help to view " 
            + "commands again!";
    public static final String INVALID_EXPENSE_VALUE = "Wow! That's a lot of money. "
            + "Are you sure you have spent that much?";
    public static final String INVALID_BUDGET_VALUE = "Wow! That's a lot of money."
            + "I don't think we can handle that much money";
    public static final String INCOMPLETE_ENTRIES_CSV_HEADER_MESSAGE = "The CSV header for StonksXD_Entries.csv " 
            + "seems to be incomplete or missing.\nDo not worry, we will put back the CSV header.\nNote: If you " 
            + "replaced the CSV header with an entry, that entry will not be loaded.";
    public static final String INCOMPLETE_SETTINGS_CSV_HEADER_MESSAGE = "The CSV header for StonksXD_Settings.csv "
            + "seems to be incomplete or missing.\nDo not worry, we will put back the CSV header.\nNote: If you " 
            + "replaced the CSV header with the settings, those settings will not be loaded.";
    public static final String EXPENSE_OVERFLOW_ERROR = "Your total expense will exceed $100000000000. " 
            + "Are you sure you have spent this much?";
    public static final String INCOME_OVERFLOW_ERROR = "Your total income will exceed $100000000000. "
            + "Are you sure you have gotten this much?";
    public static final String ERROR_SAVING_ENTRIES_MESSAGE = "There is trouble saving entries into " 
            + "StonksXD_Entries.csv, some or all entries maybe lost.";
    public static final String ERROR_SAVING_SETTINGS_MESSAGE = "There is trouble saving entries into "
            + "StonksXD_Settings.csv, some or all settings maybe lost.";
    public static final String TOO_MANY_DP_MESSAGE = "Only 2 or less decimal places are allowed!";
    public static final String INVALID_DATE_FORMAT = "Your date format is invalid. Please use DD/MM/YYYY";
    public static final String INVALID_INDEX_MESSAGE = "Index given is either out of range or not an integer.";
    public static final String INVALID_YEAR_MESSAGE = "The given year must be in the YYYY format.";
}
