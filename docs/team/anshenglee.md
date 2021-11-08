# Lee An Sheng - Project Portfolio Page

## Overview
Stonks XD is an expense managing software that aims to simplify the process of keeping track of one's finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
Stonks XD allows you to:
- Add income, add daily expenses and track them.
- Set budgets for different expense categories and receive financial advices.
- Receive a snapshot of your spending in the form of bar graphs.
- See your entries in different currencies.

### Summary of Contributions

#### Code contributed

Link to code contribution: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=AnShengLee&tabRepo=AY2122S1-CS2113T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements implemented

- **New Feature**: Added `Parser` class.
  - What it does: Parses user inputs and determine the command user is trying to call.
  - Justification: This is required in order for our program to understand what the user is trying to achieve.
  - Highlights: `Parser` uses regex to do its job, verifying the format of inputs given and splitting user inputs to
    smaller chunks that the program can understand. Thus, this feature requires a decent understanding of regex, good
    understanding of `String` manipulation and also error handling especially when the user types in unexpected inputs.


- **New Feature**: Added `DataManager` class.
  - What it does: Saves and loads user's entries and settings into `csv` files.
  - Justification: This is required so that user's data (such as expenses) will not be lost when the program closes.
    When the user starts the program again, all the previously saved data will be loaded.
  - Highlights: Uses classes like `BufferedWriter` and `Scanner` to write and read files. This feature requires an
    understanding of file writing and reading libraries. Also, need to understand how other classes work as this 
    class interacts with many other classes such as `FinancialManager` and `BudgerManager`. 


- **New Feature**: Added mechanism to allow user to input their parameters in any order.
  - What it does: For a command that takes in 2 parameters, A and B, user can give A first followed by B or
    vice versa.
  - Justification: This is to increase the user-friendly factor of our product.
  - Highlights: This is implemented through regex. Requires good understanding of regex.


- **New Feature**: Added `Messages` class.
  - What it does: Stores all possible messages that the program could show to the user.
  - Justification: This is done to fit the OOP paradigm and increase readability.
  - Highlights: Good understanding of OOP.


- **New Feature**: Added `CommandKeywords` class.
  - What it does: Stores all possible commands the user can give.
  - Justification: This is done to fit the OOP paradigm and increase readability.
  - Highlights: Good understanding of OOP.


- **New Feature**: Included date mechanism to the program.
  - What it does: Allows entries entered by the user to have a date tagged to them.
  - Justification: We can group entries up by month, year, etc. This then allows us to calculate things like
    total expense in a month.
  - Highlights: This requires an understanding of the `LocalDate` and `DateTimeFormatter` libraries.


- **New Feature**: Added `ClearAllEntriesCommand` class.
  - What it does: Allows all entries to be cleared.
  - Justification: Mainly just a quality of life feature. If the user wants to start with a clear list of entries, they 
    can just use this command to start afresh. This command is useful during testing as well.
  - Highlights: Understand how other classes work as this class interacts with other classes.
  
#### Contributions to UG

- The basic UG structure when the product was in v1.0. 
  - Such as the introduction.
  - Table of contents.
  - Hyperlinks.
  - Quick start.
  - Command summary.
- The `Notes` portion under `Features`.
- Data saving feature.
- Fix typos and small format errors.

#### Contributions to DG

- Fix typos and small format errors.
- Parser component.
- Data saving component.
  - Contributed **1** class diagram and **5** sequence diagrams.
  Link to PR: [#277](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/277)
- Added user stories for v2.0.

#### Contributions to team-based tasks

- Attended all team meetings.
- Brainstormed features and user stories for v1.0 and v2.0.
- Discussed format and content for both UG and DG.
- Ensured forking workflow.
- Managed milestones. Ensured everything was done before deadlines. Adjusted milestone deadlines when needed.
- Managed issues. Ensured duplicate issues are marked. Labelled issues with the appropriate labels.
- Fixed bugs throughout the project.
- Fixed multiple bugs found during PED
  - **6** PED bugs were fixed. Here are the issue links:
  [#192](https://github.com/AY2122S1-CS2113T-T12-3/tp/issues/192)
  , [#173](https://github.com/AY2122S1-CS2113T-T12-3/tp/issues/173)
  , [#170](https://github.com/AY2122S1-CS2113T-T12-3/tp/issues/170)
  , [#167](https://github.com/AY2122S1-CS2113T-T12-3/tp/issues/167)
  , [#157](https://github.com/AY2122S1-CS2113T-T12-3/tp/issues/157)
  , [#155](https://github.com/AY2122S1-CS2113T-T12-3/tp/issues/155)

#### Review/mentoring contributions during team project

- Total PRs reviewed: 34
- Links to PRs with non-trivial reviews:
  [#210](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/210)
  , [#208](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/208)
  , [#122](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/122)
  , [#56](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/56)
  , [#49](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/49)
  , [#45](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/45)
  , [#32](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/32)
  , [#27](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/27)
  , [#20](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/20)

- Other forms of help:
  - Helped other teammates through streaming channels like Zoom and Discord.
  - Helped through telegram group chat.

#### Contributions beyond the project team

- Links to PRs reviewed during iP:
  - [[Silin Chen] iP #192](https://github.com/nus-cs2113-AY2122S1/ip/pull/192)
  - [[Swati] iP #10](https://github.com/nus-cs2113-AY2122S1/ip/pull/10)

- Links to PRs reviewed during tP:
  - [[CS2113-F11-3] TourPlanner #42](https://github.com/nus-cs2113-AY2122S1/tp/pull/42)
  - [[CS2113T-W12-4] Fridget #3](https://github.com/nus-cs2113-AY2122S1/tp/pull/3)
  