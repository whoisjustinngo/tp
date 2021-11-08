---
layout: page
title: Darren's Project Portfolio Page
---

### Project: Advyze

Advyze is a desktop app for tech savvy student financial advisors to keep track of their clients and their busy school schedule. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=mokdarren&tabRepo=AY2122S1-CS2103-T14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements Implemented**:
  * Updated the contacts model to allow tracking of client related data such as `policy`, `note`, and `status` (PR [\#106](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/106))
  
* **New Features**:
* Implemented the Contacts tab (PR [\#45](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/45))
  * What it does: functions as a contact list that allows the user to `add`, `list`, `edit`, `delete`, `filter` Contacts. 
  * Justification: These features allow financial advisors to keep track of their clients, as well as friends' contacts in school, allowing student financial advisors to effectively manager their school network and client network  
  * Highlights: The implementation of contacts model was relatively smooth because it was an extension from AB3. However, it was challenging to learn JavaFX in a short span of time to create an intuitive and user friendly layout for the Contacts tab. Understanding the Model-View-Controller design pattern in this feature was also crucial step to correctly link up the data in the `Model` to the UI `View`.  
* Implemented the Details Tab (PR [\#106](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/106))
  * What it does: Gives the user a more detailed view on a single contact that shows the contacts current policies, notes and client status which are aggregated and displayed in the `dashboard` tab. User is able to view and add client related data with commands `status`, `note` and `policy` 
  * Justification: This makes it easy for Financial Advisor's to keep track of client's policy information and meeting notes  
  * Highlights: The implementation of the details tab required an extension of the `Person` model to include more detailed data such as notes and policy information. It took a lot of trial and error to finally implement a suitable OOP structure to support this additional data that works well in a Model-View-Controller design architecture.
* Implemented the `filter` command to filter contacts based on contact's attributes (PR [\#87](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/87))

* **Contributions to team-based tasks**:
  * Task management, and Issue creation for milestones v1.1, v1.2 and v1.3
  * Bug reporting and suggestions for improved `Event` class (PR [\#54](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/54))
  * Fixed tracking bug in `Dashboard` tab (Issue [#186](https://github.com/AY2122S1-CS2103-T14-4/tp/issues/186))
  
* **Documentation**:
  * User Guide:
    * Added documentation for commands `add`, `list`, `edit`, `delete`, `filter` in the Contacts tab and `select`, `status`, `policy` and `note` for the Details tab
  * Developer Guide:
    * Updated documentation for [Model](https://ay2122s1-cs2103-t14-4.github.io/tp/DeveloperGuide.html#model-component) component in the architecture
    * Added documentation for how [Filter](https://ay2122s1-cs2103-t14-4.github.io/tp/DeveloperGuide.html#filtering-persons) command is implemented for contacts

* **Community**:
  * Reported bugs for other teams in the class (examples: [1](https://github.com/mokdarren/ped/issues/1), [2](https://github.com/mokdarren/ped/issues/2), [3](https://github.com/mokdarren/ped/issues/3))

