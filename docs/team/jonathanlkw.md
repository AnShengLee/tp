# Lam Kai Wen Jonathan - Project Portfolio Page

---
## Overview
Stonks XD is an expense managing software that aims to simplify the process of keeping track of one's finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
The app is able to track your daily expenses, set and adjust your spending limits and give advice based on daily expenses.
It is also able to give visual representations of financial data through bar graphs with currency conversion capabilities.

---

### Summary of Contributions

- **New Feature**: Added `Entry`, `Expense` and `Income` classes.
  - What it does: These form the base class structure for all added expense and income entries.
  - Justification: Each instance of the `Expense` and `Income` class contains important information about the entries in a concise manner.
  - Highlights: This feature requires an understanding of class inheritance to implement. Furthermore, `Expense` and `Income` classes form the basis for most implementations in `FinancialTracker`.


- **New Feature**: Added `Budget` class and its child classes.
  - What it does: Stores every possible budget in the program as a child class of `Budget`.
  - Justification: Information about the respective budgets (budget limits, current spending), and their methods can be stored an accessed in an elegant manner.
  - Highlights: This feature requires an understanding of class and method inheritance in order to efficiently set up multiple budgets.


- **New Feature**: Added `BudgetManager` class.
  - What it does: This is the main processor of all budget and budget reminder operations. (Budget reminders are given when the user approaches/exceeds an active budget, and prompts are given to the user on how to refactor the budget.)
  - Justification: Contains all budget and budget reminder methods. Isolates budget operations from other operations in the program.
  - Highlights: The logic behind which budget reminders to give was quite challenging as it contained many scenarios.

- **New Feature** Added `BudgetReminder` class.
  - What it does: This class represents all the possible budget reminders given in different scenarios.
  - Justification: Organizes the large number of reminder information in a neat manner.
  - Highlights: The budget reminders had many variations and required much data from various places to print the reminders accurately.


- **New Feature**: Added `set_budget` command.
  - What it does: Receives input from the user and sets the budget limit for the appropriate budget. Budget limits of 0 represent deactivated budgets.
  - Justification: Allows user to set his budgets as a way to manage his finances.
  - Highlights: This feature required interaction between `Parser`, `Command` and `Ui` classes. Understanding of the program execution flow was required to implement correctly. Furthermore, invalid inputs by users had to be properly handled with error messages shown.


- **New Feature**: Added `check_budget` command.
  - What it does: Shows the user the current budget limit of a requested budget.
  - Justification: Allows the user to check and understand his current budget limits and plan appropriately. 
  - Highlights: This feature required interaction between `Parser`, `Command` and `Ui` classes. Understanding of the program execution flow was required to implement correctly. Furthermore, invalid budgets inputted had to be properly handled with error messages shown.


- **New Feature**: Added `set_threshold` command.
  - What it does: Receives input from the user and sets the threshold limit beyond which budget reminders will be given.
  - Justification: Allows user to customise when he wants to receive warnings.
  - Highlights: This feature required understand of the interaction between `Parser`, `Command` and `Ui` classes. Invalid inputs of more than 2dp had to be handled and inputs below zero and above 1 had to be rejected.
  
Link to code contribution: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=jonathanlkw&tabRepo=AY2122S1-CS2113T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

- **Project Management**: 
  - Managed release `v1.0` on GitHub.


- **Enhancement to existing features**: 
  - Wrote JUnit tests for `BudgetManager` class (Pull requests [#84](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/84), [#127](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/127), [#243](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/243)) 
  - Added additional JUnit tests for `Ui` class (Pull request [#127](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/127))
  - Bug fixes. (Pull requests [#42](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/42), [#44](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/44), [#135](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/135), [#254](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/254), [#284](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/284)) 
  

- **Documentation**:
  - User Guide:
    - Contributed to the writing of the introduction to StonksXD (Pull requests [#86](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/86/files), [#129](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/129))
    - Added write-up for `set_budget`, `check_budget` and `set_threshold` commands (Pull request [#129](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/129), [#281](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/281))
    - Made edits for grammar and friendlier tone (Pull request [#129](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/129)) 
  - Developer Guide:
    - Added Architecture Component write-up and architecture diagram (Pull requests [#85](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/85), [#89](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/89), [#133](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/133), [#273](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/273))
    - Added Budget Component write-up and sequence diagram (Pull request [#133](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/133), [#273](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/273))


- **Community**:
  - PRs reviewed (with non-trivial comments): [#27](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/27), [#83](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/83), [#122](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/122), [#201](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/201), [#211](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/211), [#213](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/213) 
  - Contributed to the discussion of solutions to PE-D bugs via zoom meetings.
