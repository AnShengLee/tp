# Lim Kay Yun's Project Portfolio Page

## Project: StonksXD

Stonks XD - It is an expense managing software that aims to simplify the process of keeping track of ones' s finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
By simplifying the commands, we made logging financial information easy, lowering the barrier of entry for users to build
positive financial habits


Given below are my contributions to the project.

- **New Feature**: Added StonksXD class.
  - What it does: It is the main class of the entire program which interacts with all the main components of the program
  - Justification: This feature acts as the central processor of the program 
  - Highlights: The components used in the program used this class a point of entry. New components are added accordingly in this class.
    Good understanding of OOP is required, and the overall structure of the program.
  - Credits: Structure of code is adapted from [addressbook-level2](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java)  


- **New Feature**: Added command and exit command class
  - What is does: Command is the parent class which all other commands inherit from and ExitCommand is the only class that inherits
     overrides isExit method of Command, which is used to terminate the program. It provides the framework for other existing command classes.
  - Justification: Command specifies the structure of the other command classes so that they can integrate well with the main class.
    Only the exit command should terminate the program. Other classes which inherits from Command should not be able to do so.
  - Highlights: All commands used in the program inherits from the Command class. If there is any change in behavior require to be done for 
    all commands, it should be done in Command. Good understanding of OOP is required. 


- **New Feature**: Added commands to get the total expense/income entries.
  - What it does: It calculates the sum of all the amounts in each expense/income entry.  
  - Justification: Provides insights on the expense/income of the user
  - Highlights: This feature is used as a foundation for another command that gets total expense/income given a specified date range.


- **New Feature**: Added commands to get the total expense/income entries between 2 dates.
  - What it does: It calculates the sum of all the amounts in each expense/income entry based on a pair of dates.
  - Justification: Provides insights on the expense/income of the user
  - Highlights: This feature is uses streams and LocalDate heavily so understanding of Java 8 streams is necessary.

- **New Feature**: Added optional year parameter that will show graph according to the year given by user
  - What is does: The program shows a graph according to all the months in the year, given by the user
  - Justification: Since the program allows user to input entries by date, they should also be able to view entries of that year
  - Highlights: This feature required good understanding of DateTimeFormatter since it does not accept year-only formats. 

- **New Feature**: Added Financial Advisor that will offer finance tips to the user when the program terminates
  - What is does: The program prints a random message everytime the user ends the program
  - Justification: It is part of our user story, to offer tips to the user, so he can manage their finances better.
  - Highlights: It utilizes Random class which deals with random number generators. 
  
- **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=t12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=kyun99&tabRepo=AY2122S1-CS2113T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false&until=2021-11-07)


- **Project management**:
  - Managed release v2.0 on GitHub
  - Ensured forking workflow
  - Brainstormed for features and ideas as a project


- **Enhancements to existing features**: (TO BE UPDATED)
  - Set limit to the sum of total expense and income to prevent rounding-off error.
  - Set limit to amount user can add for each entry.
  - Added multiple helper class that 
  - Refactored Parser class using SLAP
  - Wrote additional tests for existing features. [#166](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/116), [#202](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/202)
  - Added Categories and Date fields to Expense and Income.[#56](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/56)
  - Added method to get data for printing in graph command. [#66](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/66)
  - Refactored financial tracker class to use 2 array list instead of 1, making the code cleaner and more readable. [#45](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/45) 
  - Refactored parser code to use exceptions. [#97](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/97)

- **Documentation**:
  - User Guide:
    - Supplemented total_in, total_ex, btw_in, btw_ex commands. [#217](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/217)
    - Supplemented add_in, add_ex commands. [#208](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/208)
    - Added FAQ. [#105](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/105)
  - Developer Guide:
    - Add Financial Tracker component [#253](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/253)
    - Add StonksXD sequence diagram
    - Add Command component sequence diagram
  
- **Community**: 
  - PRs reviewed: 
    [#49](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/49)
    , [213](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/213) 
    , [#64](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/64)
    , [#48](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/48)
    , [#32](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/32)
  - Helped with reading through and debugging code.
  
  

