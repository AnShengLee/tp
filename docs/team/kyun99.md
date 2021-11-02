# Lim Kay Yun's Project Portfolio Page

## Project: AddressBook Level 3

Stonks XD - It is an expense managing software that aims to simplify the process of keeping track of ones' s finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
The app is able to track your daily expenses, set and adjust your spending limits and give advice based on daily expenses.
It is also able to give visual representations of financial data through bar graphs with currency conversion capabilities.


Given below are my contributions to the project.

- **New Feature**: Added StonksXD class.
  - What it does: It is the main class of the entire program which interacts with all the main components of the program
  - Justification: This feature acts as the central processor of the program 
  - Highlights: The components used in the program used this class a point of entry. New components are added accordingly in this class
  - Credits: Structure of code is adapted from [addressbook-level2](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java)  


- **New Feature**: Added command and exit command class
  - What is does: Command is the parent class which all other commands inherit from and ExitCommand is the only class that inherits
     overrides isExit method of Command, which is used to terminate the program
  - Justification: Command specifies the structure of the other command classes so that they can integrate well with the main class.
    Only the exit command should terminate the program. Other classes which inherits from Command should not be able to do so.
  - Highlights: All commands used in the program inherits from the Command class. If there is any change in behavior require to be done for 
    all commands, it should be done in Command.


- **New Feature**: Added commands to get the total expense/income entries.
  - What it does: It calculates the sum of all the amounts in each expense/income entry.  
  - Justification: Provides insights on the expense/income of the user
  - Highlights: This feature is used as a foundation for another command that gets total expense/income given a specified date range.


- **New Feature**: Added commands to get the total expense/income entries between 2 dates.
  - What it does: It calculates the sum of all the amounts in each expense/income entry based on a pair of dates.
  - Justification: Provides insights on the expense/income of the user
  - Highlights: This feature is used as a foundation for methods used to extract information to be printed on the graph using the graph command.


- **Code contributed**: https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=kyun99


- **Project management**:
  - Managed releases v2.0 on GitHub


- **Enhancements to existing features**: (TO BE UPDATED)
  - Updated the GUI color scheme (Pull requests #33, #34)
  - Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests #36, #38)


- **Documentation**: (TO BE UPDATED)
  - User Guide:
    - Added documentation for the features delete and find #72
    - Did cosmetic tweaks to existing documentation of features clear, exit: #74
  - Developer Guide:
    - Added implementation details of the delete feature.


- **Community**: (TO BE UPDATED)
  - PRs reviewed (with non-trivial review comments): #12, #32, #19, #42
  - Contributed to forum discussions (examples: 1, 2, 3, 4)
  - Reported bugs and suggestions for other teams in the class (examples: 1, 2, 3)
  - Some parts of the history feature I added was adopted by several other class mates (1, 2)


- **Tools**: (TO BE UPDATED)
  - Integrated a third party library (Natty) to the project (#42)
  - Integrated a new Github plugin (CircleCI) to the team repo

