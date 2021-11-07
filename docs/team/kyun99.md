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
  - Managed release v2.0 on GitHub
  - Ensured forking workflow
  - Brainstormed for features and ideas as a project


- **Enhancements to existing features**: (TO BE UPDATED)
  - Fixed PE-D bugs. [#195](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/195/files), [#200](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/200/files)
  - Wrote additional tests for existing features. [#166](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/116), [#202](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/202)
  - Added Categories and Date fields to Expense and Income.[#56](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/56)
  - Added method to get data for printing in graph command. [#66](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/66)
  - Refactored financial tracker class to use 2 array list instead of 1, making the code cleaner and more readable. [#45](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/45) 
  - Refactored parser code to use exceptions. [#97](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/97)

- **Documentation**: (TO BE UPDATED)
  - User Guide:
    - Supplemented total_in, total_ex, btw_in, btw_ex commands. [#217](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/217)
    - Supplemented add_in, add_ex commands. [#208](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/208)
    - Added FAQ. [#105](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/105)
  - Developer Guide:
    - Add Financial Tracker component
    - Add StonksXD sequence diagram
    - Add Command component sequence diagram


- **Community**: 
  - PRs reviewed including but not limited to: [#49](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/49), [213](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/213), [#64](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/64)
  

