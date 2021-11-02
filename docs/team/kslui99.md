---
layout: page
title: Kai Siang's Project Portfolio Page
---

### Project: Advyze

Advyze is a desktop app for tech savvy student financial advisors to keep track of their clients and their busy school schedule. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=kslui99&tabRepo=AY2122S1-CS2103-T14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements Implemented**: 
    * Updated the parser to recognise commands from different tabs (PR [\#43](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/43))


* **New Features**: Implemented the Todos tab
    * What it does: functions as a to-do-list that allows the user to `add`, `list`, `edit`, `delete`, and `filter` Todos. Users can also mark Todos as `done`.
    * Justification: These features improve the product significantly because a user can keep track of all the tasks that he needs to do in one convenient location, which complements the rest of the app.
    * Highlights: The implementation of the Todos tab affects commands related to the Todos tab that may be added in future and thus its design required an in-depth consideration. The implementation needed meticulous handling to comply with the existing design patterns implemented in AB3, such as the Observable pattern and the MVC pattern, so that these existing patterns would not be broken. In particular, implementing the Todos tab required the Todo model to be created, and care had to be taken to integrate it into the existing architecture of AB3. This meant that the existing Model, Storage and Logic components had to be extended at the correct levels of abstraction within the components to keep the overall architecture intact.


* **Contributions to team-based tasks**:
    * Kept track of deadlines and deliverables (examples: created PRs [\#39](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/39), [\#41](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/41) to ensure deliverables are met)
    * Added milestone `v1.4` on GitHub
    * Morphed the product to recognize commands coming from different tabs


* **Documentation**:
    * User Guide:
        * Added documentation for the features related to the Todos tab: `add` Todo, `list` Todos, `edit` Todo, `done`, `filter` Todos, `delete` Todo (PRs [\#21](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/21), [\#103](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/103))
    * Developer Guide:
        * Added implementation details of the `done` feature.


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#47](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/47), [\#105](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/105), [\#109](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/109)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S1-CS2103T-T09-3/tp/issues/195), [2](https://github.com/AY2122S1-CS2103T-T09-3/tp/issues/191), [3](https://github.com/AY2122S1-CS2103T-T09-3/tp/issues/184), [4](https://github.com/AY2122S1-CS2103T-T09-3/tp/issues/160))
    * Suggested design alternatives for teammate's implementations (example: [\#52](https://github.com/AY2122S1-CS2103-T14-4/tp/issues/52))
