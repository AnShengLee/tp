# Lee An Sheng - Project Portfolio Page

## Overview
Stonks XD is an expense managing software that aims to simplify the process of keeping track of one's finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
The app is able to track your daily expenses, set and adjust your spending limits and give advice based on daily expenses.
It is also able to give visual representations of financial data through bar graphs with currency conversion capabilities.


### Summary of Contributions
- **New Feature**: Added `Parser` class.
    - What it does: Parses user inputs and determine the command user is trying to call. Convert important information such as users' entries to easy-to-read data, vice versa.
    - Justification: `Parser` uses regex to do its job.
    - Highlights: This feature requires a decent understanding of regex. This feature also requires good thinking at the low level.


- **New Feature**: Added `DataManager` class.
    - What it does: Save and load users' entries and settings into a `csv` file.
    - Justification: Uses classes like `BufferedWriter` and `Scanner` to write and read files.
    - Highlights: This feature requires an understanding of file writing and reading libraries. Understand things like String manipulation to convert entries to `csv` data.

    
  