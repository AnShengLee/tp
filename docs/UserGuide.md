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
1. [Quick Start](#1-quick-start)


2. [Features](#2-features)

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
   * [Show total expense between 2 dates: `btw_ex`](#show-total-expense-between-2-dates-btw_ex)
   * [Show total income between 2 dates: `btw_in `](#show-total-income-between-2-dates-btw_in)

   2.4 Finding Entries
   * [Find entry using date: `find DATE`](#find-entry-using-date-find-ddmmyyyy)
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
   
3. [FAQ](#3-faq)


4. [Command Summary](#4-command-summary)

---

## 1. Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `StonksXD.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your `Stonks XD`.
4. Open the Command-Line interface (CLI) and navigate to the directory where you saved the `.jar` file and run 
`java -jar StonksXD.jar` in the command line. `Stonks XD` will start up.

5. The image shown below is what the program should look like for first time users like yourself!   
![image](https://user-images.githubusercontent.com/69465661/140613991-7848997a-e97b-4c65-825a-1e126590b6a0.png)

6. In `Stonks XD`, type the command in the CLI and press `Enter` on your keyboard to execute it. (Tip: type `help` 
to show all available commands and their format.)
7. Use the format `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY` to add expense entries to `Stonks XD`.
8. Use the format `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY` to add income entries to `Stonks XD`.
9. Type `balance` to view your net saving or type `show_graph` to see an overview of your finances!
10. Refer to the [Features](#features) below for details of each command.

---

## 2. Features 

### Notes

- Words in `UPPER_CASE` are the parameters to be supplied by you, the user.
e.g. in add `a/AMOUNT`, `AMOUNT` is a parameter which can be typed as `a/12.30`.
- Parameters can be in any order.
e.g. if the command specifies `c/CATEGORY a/AMOUNT`, `a/AMOUNT c/CATEGORY` is also acceptable.
- If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence 
of the parameter will be taken. e.g. if you gave `a/100 a/1000`, only `a/1000` will be read in.
- Most features below have a collapsible section that allows you to see the run time output. Do check them out if you 
want to visualize what the product looks like!

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
Adding Expense (Date Format: DD/MM/YYYY): add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY [D/DATE]
Deleting Expense: del_ex i/INDEX
Adding Income (Date Format: DD/MM/YYYY): add_in d/DESCRIPTION a/AMOUNT c/CATEGORY [D/DATE]
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
To View Your Yearly Report (Year Format: YYYY): show_graph [Y/YEAR]
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

Format: `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY [D/DATE]`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount that has a maximum of 2 decimal points.
- `CATEGORY` has to be either `food`, `transport`, `bills`, `medical`, `entertainment`, or `misc`.
- `DATE` is an optional input you can add which specifies when the entry is made. It must be in the DD/MM/YYYY format.

Note:

- The default date of the added expense will be the date in which the expense is added.
- Each expense entry can only have a maximum value of 1,000,000 (1 Million).
- The sum of all your entries cannot be more than 100,000,000,000 (100 Billion).

Examples:

- `add_ex d/KFC lunch a/10.20 c/food` Adds an expense entry regarding lunch that costs $10.20.
- `add_ex d/McDonalds dinner a/7.50 c/food D/20/10/2021` Adds a dinner expense entry that costs $7.50 that is made on 20th October 2021.
<details>
<summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent spending: 
[E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent spending: 
[E] McDonalds dinner - $7.50 (20/10/2021)
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

You can delete an incorrect expense entry by providing the index of said entry. 
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

Format: `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY [D/DATE]`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `salary`, `allowance`, `others` or `adhoc`.
- `DATE` is an optional input you can add which specifies when the entry is made. It must be in the DD/MM/YYYY format.

Note:

- The default date of the added income will be the date in which the income is added.
- Each income entry can only have a maximum value of 1,000,000 (1 Million).
- The sum of all your entries cannot be more than 100,000,000,000 (100 Billion).

Examples:

- `add_in d/lunch money a/1000 c/allowance` Adds an income entry regarding a lunch allowance of $1000.
- `add_in d/december's bonus a/5000 c/salary D/26/12/2021` Adds an income entry regarding a salary bonus of $5000 for 26th December 2021.

<details>
  <summary> ▼ Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] lunch money - $1000.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] december's bonus - $5000.00 (26/12/2021)
-----------------------------------------------------------------------------------------------------
</pre>

</details>
<br>

### Delete income entry: `del_in`

You can delete an incorrect income entry by providing the index of said entry.
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

Note:

- `total_ex` command is only able to print values less than 100,000,000,000 (100Billion).

Examples:
<ol>
<li>
Let's say you have made expense entries consistently for a couple of months since you downloaded the application and you want to know the total expense you have logged.
</li>
</ol>
<details>
<summary> ▼ Expected output in run window </summary>
<br>
These are the expense entries you have made in the past couple of months. 
<pre>
-----------------------------------------------------------------------------------------------------
1: [E] Lunch with Friends - $22.00 (02/01/2021)
2: [E] Movie date - $50.00 (07/01/2021)
3: [E] ORD PARTY!! - $150.00 (12/01/2021)
4: [E] Top up my bus card for the month of Feb - $150.00 (02/02/2021)
5: [E] Roses - $30.00 (14/02/2021)
6: [E] Dinner with Friends - $37.00 (24/02/2021)
7: [E] Mum's birthday treat - $120.00 (07/03/2021)
8: [E] Grab food delivery - $45.00 (24/03/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
Simply type in the command <code>total_ex</code>, and the program will display the total expense entries you have made:
<pre>
-----------------------------------------------------------------------------------------------------
Your total income is: $604.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### View total income: `total_in`

This will give you the sum of all income entries in you have made in your list.
This is great for giving you a quick snapshot of how much you've earned!


Format: `total_in`

Note:

- `total_in` command is only able to print values less than 100,000,000,000 (100Billion).

Examples:
<ol>
<li>
Let's say you have made income entries consistently for a couple of months since you downloaded the application and you want to know the total income you have logged.
</li>
</ol>
<details>
<summary> ▼ Expected output in run window </summary>
<br>
These are the income entries you have made in the past couple of months. 
<pre>
-----------------------------------------------------------------------------------------------------
1: [I] Allowance for the month of Jan - $250.00 (02/01/2021)
2: [I] Allowance for the month of Feb - $250.00 (04/02/2021)
3: [I] Money from teaching tuition  - $800.00 (07/03/2021)
4: [I] Part time job - $1000.00 (20/04/2021)
5: [I] Part time job - $1000.00 (20/05/2021)
6: [I] Part time job - $1000.00 (20/06/2021)
7: [I] Part time job - $1000.00 (20/07/2021)
8: [I] Allowance for the month of Aug - $350.00 (01/08/2021)
9: [I] Allowance for the month of Sept - $350.00 (11/09/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
Simply type in the command <code>total_in</code>, and the program will display the total income entries you have made:
<pre>
-----------------------------------------------------------------------------------------------------
Your total income is: $6000.00
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

This shows the sum of all the expense you had accumulated between a given date range.
This is great for checking your total weekly, monthly, or yearly expenses!

Format: `btw_ex s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` must be in the DD/MM/YYYY format.
- `START_DATE` & `END_DATE` have to be valid and non-empty.
- `START_DATE` must be before or the same as `END_DATE`.

Examples:
<ol><li>Let's you have have made a bunch of expense entries in the past 2 weeks, from  10th August 2021 and 23rd August 2021, and you want to find out how much you have spent during this time period.</li>
<br><li>Simply key in two dates using the command format<code>btw_ex s/10/08/2021 e/23/08/2021</code> to get the total you have spent in those 2 weeks! </li></ol>
<details>
<summary> ▼ Expected output in run window </summary>
<br>
These are the expense entries you have made in the past couple of months. 
<pre>
-----------------------------------------------------------------------------------------------------
1: [E] Lunch with Friends - $22.00 (09/07/2021)
2: [E] Movie date - $50.00 (20/07/2021)
3: [E] ORD PARTY!! - $150.00 (12/08/2021)
4: [E] Top up my bus card for the month of Aug - $150.00 (15/08/2021)
5: [E] Hostel fees - $1873.00 (15/08/2021)
6: [E] Dinner with Friends - $37.00 (17/08/2021)
7: [E] Mum's birthday treat - $120.00 (23/08/2021)
8: [E] Grab food delivery - $45.00 (23/08/2021)
-----------------------------------------------------------------------------------------------------
</pre>
Your total expense that you have logged for the past 2 weeks will be shown:
<pre>
-----------------------------------------------------------------------------------------------------
Your total expense between 10 Aug 2021 and 23 Aug 2021 is : $2375.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Show total income between 2 dates: `btw_in`

This shows the total income you have accumulated between a given date range.
This is great for checking your total weekly, monthly, or yearly expenses!

Format: `btw_in s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` must be in the DD/MM/YYYY format.
- `START_DATE` & `END_DATE` have to be valid and non-empty.
- `START_DATE` must be before or the same as `END_DATE`.

Examples:
<ol><li>Let's you have have made a bunch of income entries in the past 2 weeks, from  10th August 2021 and 23rd August 2021, and you want to find out how much you have received during this time period.</li>
<br><li>Simply key in two dates using the command format<code>btw_in s/10/08/2021 e/23/08/2021</code> to get the total income you have gotten in those 2 weeks! </li></ol>
<details>
<summary> ▼ Expected output in run window </summary>
<br>
These are the income entries you have made in the past couple of months. 
<pre>
-----------------------------------------------------------------------------------------------------
1: [I] Allowance for the month of Jul - $350.00 (02/07/2021)
2: [I] Allowance for the month of Aug - $350.00 (04/08/2021)
3: [I] Money from teaching tuition  - $800.00 (11/08/2021)
4: [I] Part time job - $1000.00 (15/08/2021)
5: [I] Friend finally paid me back for lunch last week -.-" - $23.00 (15/08/2021)
6: [I] Mahjong ZMMT :) - $50.00 (20/08/2021)
-----------------------------------------------------------------------------------------------------
</pre>
Your total income that you have logged for the past 2 weeks will be shown:
<pre> 
-----------------------------------------------------------------------------------------------------
Your total income between 10 Aug 2021 and 23 Aug 2021 is : $1873.00
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


#### Note
1. The graph scales according to your largest monthly total, the scale currently used by the graph will be shown to you on the top right. 1.0E9 for example would mean 1,000,000,000, E stands for exponential.


2. It would be ideal not to have entries with big differences as the Stonks XD app is meant for daily logging.

Format: `show_graph [Y/YEAR]`

- `YEAR` is an optional input which you may include. It will show the graph that corresponds to the given year. It must be in the YYYY format

<details>
<summary> ▼ Expected output in run window </summary>
<pre>
show_graph
-----------------------------------------------------------------------------------------------------
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
x                                                                                                  x
x   Account Balance: $-449.50                                              Legend:                 x
x   Current month (NOVEMBER) total expense: $5454.00                             # is Expense      x
x   Current month (NOVEMBER) total income: $0.00                                 o is Income       x
x   Your Yearly Report                                                     Unit: 1000.0            x
x ------------------------------------------------------------------------------------------------ x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                   #        o     x
x                                                                                   #        o     x
x                                                                                   #        o     x
x                                                                                   #        o     x
x                                                                                   #        o     x
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

This exits the program when you are done using it. A random tip will be shown along with the "BYE" message shown below.

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
Here's our tip for the day: Try using the 50/30/20 rule to budget. 50% for needs, 30% for wants and 20% for savings
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

---
### 2.10 Saving of Data

### Saving of Data

Stonks XD will save / load your data from `StonksXD_Entries.csv` and `StonksXD_Settings.csv`. This ensures that you 
will not lose your important data when Stonks XD changes.

The 2 `csv` files will be stored in the same directory as `StonksXD.jar`.

`StonksXD_Entries.csv` will store all your entries. They are:
1. `Expense` entries.
2. `Income` entries.

`StonksXD_Settings.csv` will store all the important settings. They are:
1. Currency setting
2. Threshold setting
3. Budget settings for different expense categories.

All important fields will be separated by a `,`.

The reason for data files to be in `.csv` format is so that you can have an easier time editing those data in Excel 
when **not** using the program. It is easy to make mistakes when editing the `csv` files manually so do be careful.

#### When editing csv files

- `.csv` files **must not** be open concurrently while Stonks XD is running. In other words, never have two programs 
writing / reading the `.csv` files at the same time. This will very likely cause a saving / loading error and 
lost of data.
- Restrictions and rules for different variables are the same as how you would enter them in the Command Line 
Interface, as specified above.
  - For example, when editing an expense entry in `StonksXD_Entries.csv`, `entry_description` must not be blank.
  - Another example, when editing an income entry in `StonksXD_Entries.csv`, `amount` has to be a positive number and 
  less than 1000000.
  - Another example, when editing `threshold` in `StonksXD_Settings.csv`, ensure it is between 0 and 1. 
  - Another example, when editing an expense entry in `StonksXD_Entries.csv`, category should be the ones available and 
  specified earlier on in the user guide.
- Do not alter / delete the headers of `.csv` files. Stonks XD is able to minimise the damage if you do so but to 
ensure your data is saved / loaded properly, please do not edit anything unexpected.
- Stonks XD expects the dates in `StonksXD_Entries.csv` to be in `DD/MM/YYYY` format i.e., `11/12/2021` when loading 
data. When opening `StonksXD_Entries.csv` in Excel, Excel might change the format of the dates. Do ensure Excel's date 
format is in `DD/MM/YYYY` when dealing with `StonksXD_Entries.csv`. Entries with a different date format will be 
considered corrupted and not be loaded into Stonks XD.
- Changing the currency setting in `StonksXD_Settings.csv` is not recommended. This is because it will
cause all your entries and budgets to be recognised as a different currency.
- Do not edit the amount of your entries drastically such that they exceed the max amount per entry or the 
total limit of 100000000000 for expenses / incomes. Entries that cause you to exceed either of the 2 limit will be 
considered corrupted and not be loaded.


#### In the event of corrupted data

When you run Stonks XD, it will immediately start to load all the data from both `.csv` files. You might run into 
messages telling you that there are corrupted data, and they will not be loaded. This is likely because you have 
edited things you are not suppose to, or you have edited wrongly. There is a way to minimise this damage (This is 
optional, and you do not have to do this if you are fine with losing data). 
Here are the steps:
1. When you ended Stonks XD already but wants to edit the `.csv` files, make a copy of them first.
2. When you run Stonks XD and receive corruption errors, end the program.
3. Copy and paste the contents from your copies back into `StonksXD_Entries.csv` and `StonksXD_Settings.csv`. This 
means all your edits are gone. But this pretty much ensures that there will be no corrupted data. 


---

## 3. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Your data is saved in 2 `.csv` files called `StonksXD_Entries.csv` and `StonksXD_Budget.csv`. To transfer the data, make a copy of these files and paste them in the same directory as the `.jar` file on your new machine. 

**Q**: What if my program terminates unexpectedly?

**A**: All data will be stored inside the respective `.csv` files.

---

## 4. Command Summary

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



