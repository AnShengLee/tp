# Nirmaleshwar Sathiya Moorthi
## Project Portfolio

---
## Overview
Stonks XD is an expense managing software that aims to simplify the process of keeping track of one's finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
The app is able to track your daily expenses, set and adjust your spending limits and give advice based on daily expenses.
It is also able to give visual representations of financial data through bar graphs with currency conversion capabilities.
---

### Summary of Contributions

* **Classes:** `Currency Conversion` & `Currency Manager`
<br></br>
    * **Purpose:** Handles all currency related functions that support the execution of commands such as `list_curr`, `set_curr` and `check_curr`.
<br></br>
    * **Justifications:** Allows users to use Stonks XD in their native currency thus, giving them a better grasp of their finances and ensuring a more personalised experience.
<br></br>
    * **Significance:** This enhancement requires proper understanding of what needs to be shown, the indexing, different enums, format specifiers like 2 decimal place for money related entries.

  <br>

* **Feature:** `check_curr`
<br></br>
  * **Purpose:** Allow users to check the current currency mode of Stonks XD.
<br></br>
  * **Justifications:** Sometimes user can forget what currency mode the entries are in e.g. when user re-opens Stonks XD. This commands allows the user to check the currency type at any point during execution. 
<br></br>
  * **Significance:** Requires tracking the currency state at all times, even when switching to other modes or after saving the program.

  <br>

* **Feature:** `list_curr`
<br></br>
  * **Purpose:** Allow users to list available currency conversion modes.
<br></br>
  * **Justifications:** When using Stonks XD for the first time, users can check the available types at their convenience without referring to the UG. 
<br></br>
  * **Significance:** Used an array store and loop through enum class of currency types and print them out. If newer types are added, changes have to be made only to the enum class.

  <br>

* **Feature:** `set_curr`
<br></br>
  * **Purpose:** Converts all entries in Stonks XD to the given currency type.
<br></br>
  * **Justifications:** Users can visualize their expenses or income in their native currency. Since the purpose of Stonks XD is to not only track but also to save money for users, converting to a currency they are familiar with will enable them to achieve the aforementioned goals.
<br></br>
  * **Significance:** The currency types are stored in an enum class and when a user requests a change, it obtains all the entries from `income`, `expense`, `budget`, `balance` and multiplies them all with the correct exchange rate. If returning to default (SGD), then the original entries are returned.

  <br>

* **Feature:** `add_ex` & `add_in` commands.
<br></br>
    * **Purpose** Adds user expense and income entries to their appropriate lists.
<br></br>
    * **Justifications:** Users can manage two separate lists and interact with them by adding items.
<br></br>
    * **Significance:** It is the most instrumental function to the tracker, so it involves managing the different attributes that the user can parse.

  <br>

* **Feature:** `del_ex`& `del_in` commands.
<br></br>
    * **Purpose:** Allows users to delete entries from their appropriate lists.
<br></br>
    * **Justifications:** Users might have unwanted or erroneous entries that they might wish to delete.
<br></br>
    * **Significance:** Similar to the add function, it is instrumental to the tracker. Ensuring the deletion of the correct entry is a bit more crucial. For this purpose, `parser` is made a lot more strict in what is acceptable as a proper index for deletion.

  <br>

* **Link to code contribution:** [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Nirmaleshwar&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Nirmaleshwar&tabRepo=AY2122S1-CS2113T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
---
###Enhancement to existing features:

* Added JUnit testing for command class [#92](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/92)
* Added JUnit testing for currencyManager class [#276](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/276)
* Refactored Currency commands into separate classes [#213](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/213)
* Refactored `Parser` to include separate case handling for currency commands [#213](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/213)
* Added Javadoc comments to improve code readability [#270](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/270)

<br>

###Contributions to the UG:

* Created the entire UG for v1.0 release [#76](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/76/files)
* Implemented drop-down box style descriptions [#76](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/76/files)
* Included guides for currency related commands for v2.0 release [#137](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/137/files)
* Made improvements to the structuring and readability of UG [#137](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/137/files)

<br>

###Contributions to the DG:

* Created section pertaining to command class for v1.0 release [#83](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/83)
* Created entire manual testing section for v1.0 release [#83](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/83)
* Created section for Currency Conversion class [#276](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/276)
* Included both class and sequence diagrams [#276](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/276)

<br>

###Contributions to team-based tasks:

  * Worked together with teammates for the UG, DG and features for v1.0.
  * Discussed with teammates regularly on ideas for the development of the product.
  * Involved in creating releases for `v1.0` & `v2.0`
  * Handled issues, user stories and PR's during project development.
  * Facilitated Zoom calls by creating rooms whenever required and taking meeting minutes.
  * Tested extensively and fixed bugs before final release.

<br>

###Review/mentoring contributions:

* Links to PR's reviewed by me:
  * [#20](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/20)
  * [#27](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/27)
  * [#45](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/45)
  * [#49](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/49)
  * [#86](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/86)
  * [#122](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/122)
  * [#134](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/134)
  * [#143](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/143)
  * [#212](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/212)
  * [#213](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/213)
  * [#215](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/215)

<br>

### Other notable contributions not listed above:

* Created the skeleton of entire command class for Stonks XD before refactoring [#20](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/20)
* Fixed the loss of precision when converting between currencies when they ae of type double [#279](https://github.com/AY2122S1-CS2113T-T12-3/tp/pull/137)

  