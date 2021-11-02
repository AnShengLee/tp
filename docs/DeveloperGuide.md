# Developer Guide

## Acknowledgements
Snippets of code from Baeldung’s guide to unit testing of system.out.println() with junit were used.

Source: https://www.baeldung.com/java-testing-system-out-println

Snippets of code from addressbook-level2’s Parser.java were used.

Source: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java

## Design & implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Architecture

![](Architecture.drawio.png)

The __Architecture Diagram__ above explains the high-level design of the StonksXD app.
Given below is a quick overview of the main components of the application and how they interact with each other:
<br>

`Ui` is the class responsible for interfacing with the user. 
It receives user input and passes it to`StonksXD`.
It also receives data from `Command` to output to the user.

`User` &harr; `Ui` &rarr; `StonksXD`

`Ui` &larr; `Command`

<br>

`StonksXD` is the main class of the app. It has 2 main functions: 
1. Upon opening the app, it loads saved data by calling `DataManager`. Before closing the app, it calls `DataManager` again to save data.
2. Runs a loop receiving new user input from `Ui` and passing it to `Parser`.

`StonksXD` &rarr; `DataManager`

`Ui` &rarr; `StonksXD` &rarr; `Parser`

<br>

`Parser` is the class responsible for interpreting the user input. 
It ensures the appropriate input format, and passes the input data to the appropriate command.

`StonksXD` &rarr; `Parser` &rarr; `Command`

<br>

`Command` is the class responsible for the execution of all commands.
It contains child classes for all possible commands.
It interacts with `FinancialTracker` and `BudgetManager` to execute commands, before sending information to `Ui` for output.

`Parser` &rarr; `Command` &harr; `FinancialTracker`

`Parser` &rarr; `Command` &harr; `BudgetManager`

`Ui` &larr; `Command`

<br>

`FinancialTracker` is the class containing and handling all income and expense entries input by the user.
It interacts with `Command` to execute tasks, and writes to `DataManager` to save its data.
It also retrieves data from `DataManager` when the program is loaded.

`Command` &harr; `FinancialTracker`

`FinancialTracker` &harr; `DataManager`

<br>

`BudgetManager` is the class containing and handling all budget information.
It interacts with `Command` to execute tasks, and writes to `DataManager` to save its data.
It also retrieves data from `DataManager` when the program is loaded.

`Command` &harr; `BudgetManager`

`BudgetManager` &harr; `DataManager`

<br>

`DataManager` is the class responsible for reading data from the `StonksXD_entries.csv` and `StonksXD_budget.csv` files upon boot up,
and writing save data to the files before terminating the program.
It interacts with `FinancialTracker` and `BudgetManager` and receives commands from `StonksXD`.

`FinancialTracker` &harr; `DataManager`

`BudgetManager` &harr; `DataManager`

`DataManager` &larr; `StonksXD_data.csv`


The sections below provide more information on the respective components.

### Ui Component
Ui contains a Scanner object that takes in user inputs from the command line.
The Ui’s main role is to provide feedback whenever the user enters a command through the form of messages. It also 
handles the indexing of each element in the listing methods before printing out to the standard output for users to see.

The image below shows how the Ui uses printing and separators to provide user feedback for listing commands.


![img_1](https://user-images.githubusercontent.com/69465661/138105673-1d21722d-0f77-4dcf-86d6-d38bffc08a40.png)


The image below illustrates the sequence diagram in the context of listing methods
which includes listExpense, listIncome and listFind.


![Untitled Diagram drawio (2)](https://user-images.githubusercontent.com/69465661/138629733-63b2a115-5405-4af5-8a74-4d18f51c8f96.png)

### Command Component

The `Command` class is a parent class that contains all the basic command features required to operate on incoming income or expense data.

Each method is abstracted into an appropriate child class (for e.g. `AddExpenseCommand`) in accordance with SLAP and OOP rules to handle only one function.

After obtaining the attributes of an entry from the `entry` class and the required command given by the user from the `parser` class, it directs the inputs to the respective methods for execution.

The image below shows the sequence diagram of how the `AddExpenseCommand` class is used and the other classes involved with it as well.

![img_2.png](AddExpenseCommandSD.drawio.PNG)

### Budget Component

The Budget component consists mainly of the `BudgetManager` class and the `Budget` class.

<br>

The `BudgetManager` class is the main class containing all methods relating to budget operations.
On the other hand, the `Budget` class is the parent class of all the budget categories. 
There are currently 7 child classes of `Budget` (i.e. 7 legal budget categories in the program).

<br>

How the Budget compoment works:
- Upon start-up, a new `BudgetManager` is initialised in `StonksXD`.
- `BudgetManager` initialises all `Budget` sub-classes with respective budget limit values loaded from `DataManager`.
- When an entry is added by the user, `BudgetManager` parses the category input by the user and calls the relevant `Budget` sub-class.
- The `handleBudget` method is performed on the `Budget` sub-class.
- The relevant budgeting information is then sent to the `Ui` class for printing.

<br>

Below is a sequence diagram of the Budget component when `handleBudget` is executed:
![](BudgetComponent.drawio.png)

_------Work in Progress------_

### Graphing Component
In the following section all coordinates will be in the form of (Row from the top, Column from the left) and coordinates mark with X is a don't care.

Description of graphing component
1. The graphing component consists mainly of the StonksGraph class which contains a 20 by 100 2d array.
2. When first initialised, the StonksGraph constructor will call setBorder() which will loop through the 2d array and set
all border characters as the given border character 'x' while keeping the others as the char blank.
3. It then calculates the balance of the financial tracker using the calculateBalance() method and write the value with its descriptor starting from coordinate (2,4).
4. Next it calls the drawReport() method, first it writes the title "Your Yearly Report" at coordinate (5,4).
Then it writes the separator at (6,X), followed by a legend at (2,75) the top right. It also writes the x-axis with its month labels.
5. It then calls the getMonthlyIncomeBreakdown(currentYear()) and getMonthlyExpenseBreakdown(currentYear()) methods to retrieve all total expenses and total incomes
for the current year when the user is using the app.
6. Using this 24 data set in total (12months for both expenses and incomes) it will calculate the scale for each bar unit
7. Then it plots the bar graph based on whichever column it looped through using the drawBar() method.

Below is a sequential diagram for the constructor of StonksGraph.

![](SequenceDiagramForStonksGraph.png)












### Data Saving Component
The saving and loading of data is handled by the `DataManager` class. Data will be saved and loaded from 
`StonksXD_Entries.csv` and `StonksXD_Budget.csv`, which are located in the same directory as the program. 

- `StonksXD_Entries.csv` will be storing users' income and expense entries.
- `StonksXD_Budget.csv` will be storing users' budget settings.

`DataManager` requires an instance of the `Parser`, `FinancialTracker`, `Ui` and `BudgetManager` at the moment of 
creation. 

- When saving data into the csv files, `DataManager` uses Java's `FileWriter` and `BufferedWriter` class to 
interact with the csv file.
- When loading data from the csv files, `DataManager` uses Java's `FileInputStream` and `Scanner` to interact with 
the csv file. 

The image below illustrates the class diagram in the context of data saving and loading.

![img_3.png](DataManagerCD.drawio.png)


`DataManager` has 2 main objectives which are to save data into csv files and load data from csv files. The sequence of
saving and loading are the same for both `StonksXD_Entries.csv` and `StonksXD_Budget.csv`. Because of this, we will 
only be showing sequence diagrams in the context of saving and loading entries.

When saving data,
1. A `FileWriter` will be created first and be used to create a `BufferedWriter`.
2. The header, that consist of all the categories, will be witten into the csv file.
3. DataManager will obtain all current entries and incomes and write them to the file line by line.

The image below illustrates the sequence diagram in the context of saving data into `StonksXD_Entries.csv`.

![img_4.png](SavingFeatureSD.drawio.png)

When loading data,
1. -Work in progress-

The image below illustrates the sequence diagram in the context of loading data from `StonksXD_Entries.csv` into the 
program.

-Work in progress-





## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... (role)| I want to ... (Function)| So that I can … (Benefit)|
|--------|----------|---------------|------------------|
|v1.0|New User|List out all possible commands|Know what I can key into the CLI interface|
|v1.0|User|Be able to record my spendings|Keep track of all my expenses|
|v1.0|User|View all expense entries|See which spending I can cut down on and better manage my finances|
|v1.0|User|Delete my expense entries|Delete wrong entries due to possible typos|
|v1.0|User|View total expense|See if I need to reduce my spending in the future|
|v1.0|User|Delete my income entries|Delete wrong entries due to possible typos|
|v1.0|Worker|Create income entries|Keep track of my total income and not spend more than that amount|
|v1.0|User|See all income entries|Have an understanding of income history|
|v1.0|User|View total income|Know what my spending limits are|
|v2.0|User|Have my entries saved into the hard disk automatically|My data would not be lost when I close the application|
|v2.0|User|Convert income / expense entries to different currencies|Do not have to manually convert currencies|
|v2.0|User|View my expenditure in the form of bar charts|Gain more useful insights on my overall financial situation|
|v2.0|Frugal spender|Set monthly budgets (overall budget and categorical budgets)|Plan my spending in advance|
|v2.0|Large spender|Receive reminders when I am about to overspend|Cut back on my spending|
|v2.0|Big spender|Get assistance readjusting my budget when I overspend|Can minimize the damage of spending too much|
|v2.0|Financially conscious user|Receive daily tips on saving, budgeting and spending|Can be more frugal and wiser with my financial decisions|
|v2.0|Long term user|See my account net balance|Can make appropriate plans / adjustments for the future|
|v2.0|User|Find expense / income entry with keyword search|Narrow down the entries I want to see|
|v2.0|User|See expenditure each month|Budget how much I need each month|
|v2.0|User|See income earned each month|Be more motivated to save|
|v2.0|User|Clear all my entries|Start afresh|


## Non-Functional Requirements

- Accessibility requirements: the application should be accessible by anyone with the `.jar` file
- Constrains: the CSV files created by the application should be able to run on different machines running the same application
- Fault tolerance requirements: the application should handle inputs with a reasonable amount of errors
- Interoperability requirements: the application should run on macOS, Windows and Linux operating systems
- Stability requirements: Application should run without internet so that user can access the application anywhere without having to connect to the internet


## Glossary

* *glossary item* - Definition

## Instructions for manual testing

###Initial start-up guide:

1. Ensure that you have Java 11 or above installed.


2. Download the latest version of `StonksXD.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-3/tp/releases).


3. Copy the file to the folder you want to use as the home folder for your `StonksXD`.


4. Open the Command-Line interface (CLI) and navigate to the directory where you saved the `.jar` file and run `java -jar tp.java` in the command line. `StonksXD` will start up.


###Testing Guide:

Below are a few types of testing that you can perform:

- Manual Testing
- JUnit Testing
- Gradle Daemon Testing
- I/O Re-direction Testing


### Manual Testing

This is a non-exhaustive list of some common manual tests that can given as commands during run-time to test code defensibility:

- ####_Adding Income/ Expense entries_
  1. Test Case: `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY`. </p>
     Expected : Adds an expense item to the list. Displays confirmation message with timestamp.
  
  <br>
  
  2. Test Case: `add_ex` but leave `d/`, `/a`, `/c` or all  empty. </p>
     Expected : No item is added. Error message displayed showing correct syntax.
  
  <br>
  
  3. Test Case: `add_ex` but give non-existent category for `/c`.
     Expected : No item added. Error message displayed showing available categories.

- ####_Delete Income/ Expense entries_
  1. Pre-requisite: List expense or income using `list_ex`/ `list_in. Must have one or more entries.
  
  <br>
  
  2. Test Case: `del_in i/1` or `del_ex i/1` </p>
     Expected : Deletes the 1st entry in Income/ Expense list. Displays confirmation message.
  
  <br>
  
  3. Test Case: `del_in i/0`, `del_in i/ABC` or `del_in i/-3`. </p>
     Expected : Displays error message saying invalid index.

  <br>

  4. Test Case: `del_in i/x` where x is larger than list size.
     Expected : Similar error message as before.

- ####_List Income/ Expense entries_
  1. Test Case: `list_ex` or `list_in` </p>
     Expected : Lists all entries added so far.
  
  <br>
  
  2. Test Case: `list_ex` or `list_in` but no items in both lists.
     Expected : Displays message saying no items in list.

### JUnit Testing 

JUnit testing modules are available in the test folder. They can be run separately or all together based on developer requirements.

Below is a list of the currently available tests:

- _CommandTest:_ Tests if commands like add, delete, list etc. are calling their respective methods and providing with the appropriate parameters.


- _DataManagerTest:_ Tests the data saving function of program.


- _DukeTest:_ Used as driver to run main().


- _ExpenseTest:_ Tests if expense entries are processed correctly into their appropriate attributes.


- _IncomeTest:_ Tests if income entries are processed into their appropriate attributes.


- _FinancialTrackerTest:_ 

### Gradle Daemon Testing

Intellij comes with an in-built Gradle Daemon that can be used to run the following test:

- `.\gradlew test` to check if all test files have passed.
- `.\graldew checkStyleTest` to check if test files comply with certain coding standards and conventions.
- `.\gradlew checkStyleMain` to check if main program complies with all JAVA coding standards.
<br>

### I/O Re-direction Testing

This form of testing involves loading sample data stored in the `text-ui-test` folder. It can be performed as follows:

1. Enter new sample data or use the pre-existing test data that can be found in the `input.txt` file.


3. Open CLI terminal and navigate to the `text-ui-test` directory using the following command - `cd /text-ui-test`


2. Run `.\runtest.bat` in CLI and see if you receive the message `"Test Passed!"`.


3. The IDE will compare the output in the `EXPECTED.TXT` and `ACTUAL.TXT` files to see if they are exactly the same to pass this test.