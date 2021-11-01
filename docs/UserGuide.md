# Stonks XD User Guide

![](https://melmagazine.com/wp-content/uploads/2019/07/Stonks_Meme.jpg)

## Introduction

Stonks XD is your go-to smart money management application that is able to: 
- track your daily expenses 
- set/adjust your spending limits
- provide you with helpful financial tips and insights

It is optimised as a daily journal, so you can key in your entries while you unwind at the end of the day. 
It also allows you to view your financial records in different currencies through both text and graphs,
so you can use this application anywhere around the world!

With this guide, you will be able to learn how to use all the functionalities of this application through step-by-step instructions 
without having to learn how to write a single line of code.

Before you learn how to use the app, take a look at the [quickstart](#quick-start) guide on how to install and prepare the application for use.

---

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)

   2.1 Help 
   * [View all possible commands: `help`](#view-all-possible-commands-help)
 
   2.2 Add / Delete Entries
   * [Create expense entry: `add_ex`](#create-expense-entry-add_ex)
   * [Delete expense entry: `del_ex`](#delete-expense-entry-del_ex)
   * [Create income entry: `add_in`](#create-income-entry-add_in)
   * [Delete income entry: `del_in`](#delete-income-entry-del_in)

   2.3 List / View information
   * [List all expense entries: `list_ex`](#list-all-expense-entries-list_ex)
   * [List all income entries: `list_in`](#list-all-income-entries-list_in)
   * [View total expense: `total_ex`](#view-total-expense-total_ex)
   * [View total income: `total_in`](#view-total-income-total_in)
   * [View total balance: `balance`](#view-total-balance-balance)
   * [Show total expense between 2 dates: `btw_ex s/START_DATE e/END_DATE`](#show-total-expense-between-2-dates-btw_ex)
   * [Show total income between 2 dates: `btw_in s/START_DATE e/END_DATE`](#show-total-income-between-2-dates-btw_in)

   2.4 Finding Entries
   * [Find entry using date: `find DD/MM/YYYY`](#find-entry-using-date-find-ddmmyyyy)
   * [Find entry using keyword: `find KEYWORD`](#find-entry-using-keyword-find-keyword)

   2.5 Budget Setting 
   * [Set budget: `set_budget`](#set-budget-set_budget)
   * [Check budget: `check_budget`](#check-budget-check_budget)
   * [Set_threshold: `set_threshold`](#set-threshold-set_threshold)

   2.6 Currency Conversion
   * [Set currency: `set_curr`](#set-currency-set_curr)
   * [Check current currency: `check_curr`](#check-current-currency-check_curr)

   2.7 Graphing
   * [View yearly report: `show_graph`](#view-yearly-report-show_graph)

   2.8 Clear All Entries
   * [Clear all entries: `clear_all_entries`](#clear-all-entries-clear_all_entries)
    
   2.9 Terminating Program
   * [Terminate program: `end`](#terminate-program-end)

   2.10 Saving of Data
   * [Saving of data](#saving-of-data)
   
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

---

## 1. Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `StonksXD.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your `Stonks XD`.
4. Open the Command-Line interface (CLI) and navigate to the directory where you saved the `.jar` file and run 
`java -jar StonksXD.jar` in the command line. `Stonks XD` will start up.
5. In `Stonks XD`, type the command in the CLI and press `Enter` on your keyboard to execute it. (Tip: type `help` 
to show all available commands and their format.)
6. Use the format `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY` to add expense entries to `Stonks XD`.
7. Use the format `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY` to add income entries to `Stonks XD`.
8. Type `balance` to view your net saving or type `show_graph` to see an overview of your finances!
9. Refer to the [Features](#features) below for details of each command.

---

## 2. Features 

### Notes

- Words in `UPPER_CASE` are the parameters to be supplied by you, the user.
  e.g. in add `a/AMOUNT`, `AMOUNT` is a parameter which can be typed as `a/12.30`.


- Most features below have a collapsible section that allows you to see the run time output. Do check them out if you want to visualize what the product looks like!

---
### 2.1 Help

### View all possible commands: `help`
This shows a list of all possible commands. Use this command when you are lost while using the app!

Format: `help`


<details>
<summary> ▼ Expected output in run window (Click to expand!)</summary>
<pre>
-----------------------------------------------------------------------------------------------------
This is a list of commands and their format!
-----------------------------------------------------------------------------------------------------
List Out All Commands: help
Adding Expense: add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY
Deleting Expense: del_ex i/INDEX
Adding Income: add_in d/DESCRIPTION a/AMOUNT c/CATEGORY
Deleting Income: del_in i/INDEX
Listing Expense: list_ex
Listing Income: list_in
Show Total Expense: total_ex
Show Total Income: total_in
To Display Total Balance: balance
Show Total Expense between 2 dates (Date Format: DD/MM/YYYY): btw_ex s/START_DATE e/END_DATE
Show Total Income between 2 dates (Date Format: DD/MM/YYYY): btw_in s/START_DATE e/END_DATE
To Find Using Date: find DD/MM/YYYY
To Find Based On Keyword: find KEYWORD
To Set Budgets: set_budget c/CATEGORY a/AMOUNT
To Check Budgets: check_budget c/CATEGORY
To Set Threshold Value for Reminders: set_threshold t/THRESHOLD
To change entries into a different currency: set_curr c/CURRENCY
To check the currency that entries are currently in: check_curr
To View Your Yearly Report: show_graph
To Clear All Expense And Income Entries: clear_all_entries
To Terminate The Program: end
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---

### 2.2 Add / Delete Entries



### Create expense entry: `add_ex`
This command adds an expense entry to your list. Use this to keep track of your daily expenses!

Format: `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `food`, `transport`, `bills`, `medical`, `entertainment`, or `misc`.

Note:

- The default date of the added expense will be the date in which the expense is added.
- Each expense entry can only have a maximum value of 1,000,000,000 (1Billion).
- The sum of all your entries cannot be more than 100,000,000,000 (100Billion).

Examples:

- `add_ex d/KFC lunch a/10.20 c/food` Adds an expense entry regarding lunch that costs $10.20.

<details>
<summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent spending: 
[E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>

Note: Budget reminders of different kinds might also appear when expenses are added! 
They might look something like this.

<pre>-----------------------------------------------------------------------------------------------------
You are almost reaching the OCTOBER OVERALL budget: $48.40/$50.00
Consider readjusting your OCTOBER OVERALL budget!
-----------------------------------------------------------------------------------------------------
You have exceeded the OCTOBER FOOD budget: $30.40/$30.00
Since you have not yet exceeded your OCTOBER OVERALL budget: $48.40/$50.00
You can directly increase your OCTOBER FOOD budget up to $32.00!
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Delete expense entry: `del_ex`

This deletes an unwanted expense entry by providing the index of said entry. 
Index can be found via the `list_ex` command below.

Format: `del_ex i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_ex i/1` Deletes the 1st entry from the expense list.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
Before deletion the expense list is as follows:
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent spending!
-----------------------------------------------------------------------------------------------------
1: [E] pillow - $500.00 (18/10/2021)
2: [E] bought cookies - $500.00 (18/01/2021)
3: [E] bought home - $555.00 (18/07/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
When command <code>del_ex i/1</code> is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
You removed this: 
[E] pillow - $500.00 (18/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
After deletion, we see that the list has removed the previous first entry!
<pre>
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### Create income entry: `add_in`

This adds an income entry to your list. Use this to keep track of your income!

Format: `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `salary`, `allowance`, `others` or `adhoc`.

Note:

- The default date of the added income will be the date in which the income is added.
- Each income entry can only have a maximum value of 1,000,000,000 (1Billion).
- The sum of all your entries cannot be more than 100,000,000,000 (100Billion).

Examples:

- `add_in d/lunch money a/1000 c/allowance` Adds an income entry regarding an allowance of $1000.

<details>
  <summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] lunch money - $1000.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>

</details>
<br>

### Delete income entry: `del_in`

This deletes an unwanted income entries by providing the index of said entry.
The index can be found using the `list_in` command found below.

Format: `del_in i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_in i/1` Deletes the 1st entry from the income list.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
Before deletion the income list is as follows:
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent earnings!
-----------------------------------------------------------------------------------------------------
1: [I] rob a bank - $800.00 (18/10/2021)
2: [I] rob a church - $300.00 (18/11/2021)
3: [I] rob a car - $400.00 (18/12/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
When command <code>del_in i/1</code> is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
You removed this: 
[I] rob a bank - $800.00 (18/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
After deletion, we see that the list has removed the previous first entry!
<pre>
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18/11/2021)
2: [I] rob a car - $400.00 (18/12/2021)
-----------------------------------------------------------------------------------------------------
</pre>

</details>
<br>

---
### 2.3 List / View information

### List all expense entries: `list_ex`

This displays the previously added expense entries in the form of a list.
Use this to find the index of an entry you want to delete.

Format: `list_ex`

<details>
<summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent spending!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
3: [E] bought car - $4777.00 (18/06/2021)
4: [E] bought condo - $87654888878.00 (18/05/2021)
5: [E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### List all income entries: `list_in`

This displays the previously added income entries in the form of a list. 
Use this to find the index of the entries you want to delete.

Format: `list_in`

<details>
<summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent earnings!
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18/11/2021)
2: [I] rob a car - $400.00 (18/12/2021)
3: [I] rob a home - $500.00 (18/09/2021)
4: [I] rob a child - $600.00 (18/08/2021)
5: [I] lunch money - $1000.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### View total expense: `total_ex`

This displays the total amount of all expenses in your list.
This is great for giving you a quick snapshot of how much you've spent!

Format: `total_ex`

<details>
<summary> ▼ Expected output in run window </summary>
<br>
Here we have a simple expense list with three items:
<pre>
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $100.00 (18/01/2021)
2: [E] bought home - $200.50 (18/07/2021)
3: [E] bought car - $300.00 (18/06/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
If we give the command <code>total_ex</code>, it will display the total expenditure:
<pre>
-----------------------------------------------------------------------------------------------------
Your total expense is: $600.50
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### View total income: `total_in`

This displays the total amount of all income entries in your list.
This is great for giving you a quick snapshot of how much you've earned!


Format: `total_in`
<details>
<summary> ▼ Expected output in run window </summary>
<br>
Here we have a simple income list with three items:
<pre>
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18/11/2021)
2: [I] rob a car - $400.00 (18/12/2021)
3: [I] rob a home - $500.00 (18/09/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
If we give the command <code>total_in</code>, it will display the total income:
<pre>
-----------------------------------------------------------------------------------------------------
Your total income is: $2800.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### View total balance: `balance`

This shows the net balance you have after subtracting your expenses from your incomes.
Use this to get a snapshot of whether you're in the red!

Format: `balance`
<details>
  <summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your current balance is: $-87654891720.20
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Show total expense between 2 dates: `btw_ex`

This shows the total expense you had accumulated between a given time period.
This is great for checking your weekly or monthly expenses!

Format: `btw_ex s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` must be in the DD/MM/YYYY format.
- `START_DATE` & `END_DATE` have to be valid and non-empty
- `START_DATE` must be before or the same as `END_DATE`

Examples:
- <code>btw_ex s/06/12/1987 e/21/11/1999</code> will return the total income of all entries between and inclusive of
  the given dates.
<details>
<summary> ▼ Expected output in run window </summary>
<br>
If you wish to find your income between 10th Aug 2021 and 23rd Oct 2021
<br>
<ul><li>Give the command <code>btw_ex s/10/07/2021 e/23/10/2021</code></li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Your total expense between 10 Jul 2021 and 23 Oct 2021 is : $2300.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Show total income between 2 dates: `btw_in`

This shows the total income you have accumulated between a given time period.
This is great for checking your weekly or monthly income!

Format: `btw_in s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` must be in the DD/MM/YYYY format.
- `START_DATE` & `END_DATE` have to be valid and non-empty
- `START_DATE` must be before or the same as `END_DATE`

Examples:
<ul><li><code>btw_in s/06/12/1987 e/21/11/1999</code> will return the sum of all income entries between and inclusive of
the given dates.</li></ul>
<details>
<summary> ▼ Expected output in run window </summary>
<br>
If you wish to find your income between 10th Aug 2021 and 23rd Oct 2021
<ul><li>Give the command <code>btw_in s/10/07/2021 e/23/10/2021</code></li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Your total income between 10 Jul 2021 and 23 Oct 2021 is : $2300.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.4 Finding Entries


### Find entry using date: `find DD/MM/YYYY`

You can use this command to find and display the income or expense entries with the given date. 

Format: `find DD/MM/YYYY`

- If the date given is not in the recognised format, a keyword search would be done instead.

Examples:

- `find 19/10/2021` returns income and expense entries with the given date.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
If you enter <code>find 19/10/2021</code>, it will find the entry recorded on that date:
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] Birthday Money! - $200.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Find entry using keyword: `find KEYWORD`

This finds and displays the income or expense entries that contain the given keyword.

Format: `find KEYWORD`

- `KEYWORD` has to be a non-empty
- `KEYWORD` can be any alpha-numeric string

Examples:

- `find FOOD` returns income and expense entries that contain the keyword `FOOD` in their description or categories.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
If you wish to search based on category, for e.g. all <code>food</code> expenses:
<br>
<ul><li>Give the command <code>find food</code> and it will return the following:</li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
If you wish to search based on description, for e.g. all entries that contain the keyword <code>bought</code>:
<ul><li>Give the command <code>find bought</code> and it will return the following:</li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
3: [E] bought car - $4777.00 (18/06/2021)
4: [E] bought condo - $87654888878.00 (18/05/2021)
-----------------------------------------------------------------------------------------------------
</pre>
If you wish to search based on value, for e.g. all entries that contain the value <code>5</code>:
<ul><li>Give the command <code>find 5</code> and it will return the following:</li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
3: [E] bought condo - $87654888878.00 (18/05/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.5 Budget Setting

### Set budget: `set_budget`

This sets a budget for one of the many preset expense categories. 
Reminders will be given when your spending approaches the budget limit!

Format: `set_budget c/CATEGORY a/AMOUNT`

- `CATEGORY` has to be one of `food`, `transport`, `bills`, `medical`, `entertainment`, `misc` or `overall`.
- `AMOUNT` has to be a valid non-negative number.
- TIP: Setting `AMOUNT` to 0 deactivates the budget warnings for that category!

Examples:

- `set_budget c/bills a/100` Sets the `bills` budget to $100.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
When command <code>set_budget c/bills a/100</code> is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
BILLS budget has been set to $100.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### Check budget: `check_budget`

This checks the budget limit of one of the many preset expense categories.
Use this when you forget your budget limits!

Format: `check_budget c/CATEGORY`

- `CATEGORY` has to be one of `food`, `transport`, `bills`, `medical`, `entertainment`, `misc` or `overall`.
- TIP: Setting `AMOUNT` to 0 deactivates the budget warnings for that category!

Examples:

- `check_budget c/bills` checks the budget limit of the `bills` budget.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
When command <code>check_budget c/bills</code> is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
Current BILLS limit is $100.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Set threshold: `set_threshold`

This sets the threshold beyond which reminders will be given when approaching the budget limit.

Format: `set_threshold t/THRESHOLD`

- `THRESHOLD` has to be a value between 0 and 1.
- Setting `THRESHOLD` to 0.1 produces reminders when you have used up more than 90% of your budget!

Examples:

- `set_threshold t/0.2` sets the threshold value of all budget categories to 80%.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
When command <code>set_threshold t/0.2</code> is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
Threshold for budget reminders set to 0.2
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.6 Currency Conversion

### Set currency: `set_curr`

This allows you to see everything money-related in a different currency. Any money-related amount you key in from now 
onwards will be treated as the new currency set.

Format: `set_curr c/CURRENCY`

- As of v2.0, Stonks XD supports 2 different currencies: SGD and USD.
- If you try to set currency to currency you're already using, a warning will be shown.

<details>
<summary> ▼ Expected output in run window </summary>
<br>
Let's take the following budget limit for <code>FOOD</code> expenses as an example:
<pre>
-----------------------------------------------------------------------------------------------------
Current FOOD limit is $10.00
-----------------------------------------------------------------------------------------------------
</pre>
<br>
If you wish to convert the above (and all entries) to USD, simply enter <code>set_curr c/USD</code>. You will receive the following confirmation message:
<pre>
-----------------------------------------------------------------------------------------------------
All entries have been converted to USD!
-----------------------------------------------------------------------------------------------------
</pre>
<br>
If we check our budget limit once again, we see that it has now been converted to the appropriate value in USD!
<pre>
-----------------------------------------------------------------------------------------------------
Current FOOD limit is $7.40
-----------------------------------------------------------------------------------------------------
</pre>
<br>
To convert back to SGD, just enter <code>set_curr c/SGD</code> and all entries will revert back to their original denominations.
</details>
<br>

### Check current currency: `check_curr`

This shows you what currency setting you are currently on.

Format: `check_curr`

<details>
<summary> ▼ Expected output in run window </summary>
<br>
If you are unsure what currency your values are in, just enter <code>check_curr</code> and it will show the following message:
<pre>
-----------------------------------------------------------------------------------------------------
You currency setting currently: SGD
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.7 Graphing

### View yearly report: `show_graph`

This shows the monthly breakdown of the finances in a Yearly Report which also
includes current month spending and earnings.
We recommend using this function after your daily logging of expenses for a one-stop check-in on the state of your finances!

Format: `show_graph`

<details>
<summary> ▼ Expected output in run window </summary>
<pre>
show_graph
-----------------------------------------------------------------------------------------------------
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
x                                                                                                  x
x   Account Balance: $-398.2                                               Legend:                 x
x   Current month (OCTOBER) total expense: $410.20                               # is Expense      x
x   Current month (OCTOBER) total income: $0.00                                  o is Income       x
x   Your Yearly Report                                                                             x
x ------------------------------------------------------------------------------------------------ x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                           #                      x
x                                                                           #                      x
x ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ x
x   Jan     Feb     Mar     Apr     May     Jun     Jul     Aug     Sept    Oct     Nov     Dec    x
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.8 Clear all entries

### Clear all entries: `clear_all_entries`

Clears all the income and expense entries Stonks XD is currently keeping track of.
Use this when you want to start Stonks XD afresh. Be careful when you use it though!
This cannot be undone!

Format: `clear_all_entries`

<details>
<summary> ▼ Expected output in run window </summary>
<br>
If you wish to clear all your entries and start afresh:
<br>
<ul><li> Give the command <code>clear_all_entries</code></li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
All your entries have been cleared!
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.9 Terminating Program

### Terminate program: `end`

This exits the program when you are done using it.

Format: `end`
<details>
<summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
██████  ██    ██ ███████        ██  
██   ██  ██  ██  ██          ██  ██ 
██████    ████   █████           ██ 
██   ██    ██    ██          ██  ██ 
██████     ██    ███████        ██ 
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.10 Saving of Data

### Saving of Data

Stonks XD will save / load data from `StonksXD_Entries.csv` and `StonksXD_Settings.csv`. 

- `StonksXD_Entries.csv` will store all the expense and income entries StonksXD is currently tracking.
- `StonksXD_Settings.csv` will store all the budget values as well as the currency setting.

The reason for data files to be in `.csv` format is so that you can have an easier time editing those data in Excel 
when not using the program.

#### Note

1. Stonks XD expects the dates in `StonksXD_Entries.csv` to be in `dd/MM/yyyy` format i.e., `11/12/2021` when loading data. 
When opening `StonksXD_Entries.csv` in Excel, Excel might change the format of the dates. Do ensure Excel's date format 
is in `dd/MM/yyyy` when dealing with `StonksXD_Entries.csv`. Entries with a different date format will be considered 
corrupted and not be loaded into Stonks XD.
2. Changing the currency setting in `StonksXD_Settings.csv` is almost never recommended. This is because this will 
cause all your entries and budgets to be recognised as a different currency. 
3. `.csv` files should not be open concurrently while Stonks XD is running. This would cause the data to not be accurately saved. 

---

## 4. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Your data is saved in 2 `.csv` files called `StonksXD_Entries.csv` and `StonksXD_Budget.csv`. To transfer the data, make a copy of these files and paste them in the same directory as the `.jar` file on your new machine. 

**Q**: What if my program terminates unexpectedly?

**A**: All data will be stored inside the respective `.csv` files.

---

## 5. Command Summary

| Action | Format | Examples |
| ------------ | ------------- | ------------- |
| View all possible commands | `help` | - |
| Create expense entry | `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY`  |  `add_ex d/KFC lunch a/10.20 c/food` |
| Delete expense entry | `del_ex i/INDEX` | `del_ex i/3` |
| List all expense entries | `list_ex` | - |
| View total expense | `total_ex` | - |
| Create income entry | `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY`  | `add_in d/work a/3200 c/salary` |
| Delete income entry | `del_in i/INDEX` | `del_in i/2` |
| List all income entries | `list_in` | - |
| View total income | `total_in` | - |
| Find entry using date | `find YYYY-MM-DD` | `find 19/10/2021` |
| Find entry using keyword | `find KEYWORD` | `find food`<br>`find 3` |
| View total balance | `balance` | - |
| Show total expense between 2 dates | `btw_ex s/START_DATE e/END_DATE` | `btw_ex s/10/07/2021 e/23/10/2021` |
| Show total income between 2 dates | `btw_in s/START_DATE e/END_DATE` | `btw_in s/10/07/2021 e/23/10/2021`  |
| Clear all entries | `clear_all_entries` | - |
| View Yearly Report | `show_graph` | - |
| Set currency | `set_curr c/CURRENCY` | `set_curr c/usd` |
| Check current currency | `check_curr` | - |
| To terminate program | `end` | - |


