---
layout: page
title: Ricky's Project Portfolio Page
---

# Project: Advyze

Advyze is a desktop address book application is designed for tech savvy student or financial advisors who prefer CLI over GUI, and have a lot of clients and a busy school schedule to keep track of.

Given below are my contributions to the project.

* **New Feature**: Implemented the Schehdule Tab
  * What does it do: This is a schedule manager, which allows user to add in their schedules and events in a timeline. Allows user to have an better overview on their upcoming timetable.

    * This scheduler will also help to check whether the Event which is going to be added by the user have any clashes with the current Events which are already added. If the Event which is going to be added has a clash with the added Events, the new Event will not be added until clashes is resolved. Otherwise, the new Event will be added into the schedule itself.

    * In addition, Events which are added into the schedule are sorted according to their date and time in an ascedning order. By deafult, past Events will not be displayed in the dashboard or the Schedule tabs unless the user wants to view the past Events.
  
  * This Schedule tab allows the user to:
    1. `add` - to add an Event into the schedule.
        * Allows user to: add tags and recurring Events.
    1. `edit` - to edit the Events which have been previously added into schedule.
    1. `list` - to view all past and upcoming Events.
    1. `filter` - to filter out Events which have the given keywords in the respective attributes.
    1. `find` - to find Events which contains the given keyword in the description attribute.
    1. `delete` - to delete an Event which was added into the schedule.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=rickyaandrew&tabRepo=AY2122S1-CS2103-T14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Updated the header GUI for Schedule to match what we need (Pull requests [\#58](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/58)).
  * Bugs fixes on the application (Pull requests [\#88](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/88), [\#185](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/185)).
  * To display only upcoming Events right after start up (Pull requests [\#111](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/111), [\#174](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/174)).
  * Events to be sorted all the time (before or after adding of mew Events) (Pull requests [\#58](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/58)).
  * Ensure that Events are not added if there is a clash in the schedule (Pull requests [\#46](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/46), [\#55](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/55)).

* **Documentation**:
  * User Guide:
    * Added documentation for the features which are pertaining to Schedule Tab: `add`, `list` and `delete` documentation for Schedule Tab (Pull requests [\#108](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/108), [\#185](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/185)).
  * Developer Guide:
    * Added implementation for `add` Event into the schedule (Pull requests [\#90](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/90))
    * Added implementation for `find` Event, `edit` Event, Add tags in Events, `filter` Events and Add recurring Events (Pull requests [\#108](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/108)).
    * Added implementation viewing past and upcoming Events only (Pull requests [\#196](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/196)).
    * Added implementation for `Storage` (Pull requests [\#200](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/200)).

* **Community**:
  * Included some test cases and remove unused files (Pull requests [\#195](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/195)). Increased coverage by 4.13%.
  * Resolved various bug issues (Pull requests [\#185](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/185)).
