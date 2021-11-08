---
layout: page
title: Bryan Tee's Project Portfolio Page
---

### Project: Advyze

Advyze is a desktop app for tech savvy student financial advisors to keep track of their clients and their busy school schedule. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

### Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=spdpnd98&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **New Features**: 
  * Implemented `Tab` Command
    * What it does: Allows user to switch between tabs via commands
    * Justification: These commands are required for the application to be fully mouse-free, and for users to only use the keyboard to navigate around the application, allowing a better fit for our target users.
    * Highlights: The `tab` command is inherently somewhat linked to the UI, and as such require careful planning to place this command at a correct location within the MVC. In an earlier iteration, static strings were scattered everywhere, including the parser and tests. To maintain code consistency and reduce code duplication, Tab names are consolidated into enums so that it is easier to keep track when programming, and allow for better extensibility for the application in the future. Having enums also allows for a better abstraction and prevents misuse of code by programmers in the future.
  
  * Implemented Convenience Command:
    * What it does: Allows user to add context to the commands they want to run. Eg `/todos add d/a todo` in the Dashboard tab.
    * Justification: It is very likely the user will want to add a schedule, contact, or todo from the dashboard. As such, many of the tabs are logically link to other tabs, and rely on each other to prodcue a complete experience to the user. This addition reduces the time it takes to run a command that can only be done in another tab. As such it streamlines the process of adding `Todos`, `Schedules`, `Contacts`, and `Details`, making Advyze more user-friendly.
    * Highlight: The tab ids' implementation should be hidden from the user, but users will need to indicate a tab name! As such, a function to convert relevant displayed tab names to tab enums need to be written. Next, there were many implementation that came to mind when thinking about this portion of the program. To simplify the program, the convenience commands are implemented by internally calling the tab command. This makes the program less error-prone as when the tab command implementation changes, so should the implementation of convenience function. Identifying this level of coupling was not easy, and required extensive reading of the AB3 code to notice. This addition was done in a way that was not only invisible to the user, but also to the other developers, meaning that most code written by other developers required _no_ modification after this feature was completed.

  * Implemented `import` command:
    * What it does: Allows user to import their schedules into the application
    * Justification: Our team went back and forth on whether this was an important enough feature to implement. This feature is important because requiring users to copy/enter their schedule one by one is very troublesome, especially if they already have an `.ics` file exported from another application. As such, this feature will smoothen the transition of users to Advyze.
    * Highlights: Many rounds of experimentation was done, including investigating the structure of `.ics` files, writing custom parsers, and reading the `.ics` file standard. In the end, a library was found that followed the [RFC2445](http://www.faqs.org/rfcs/rfc2445.html) specification. The library `iCal4j` was not well documented, but was the only complete library that could support this feature. The documentations are scattered across different sites. Eventually, the feature was implemented in a separate project, before finally integrating it into Advyze. Furthermore, import `.ics` also makes use of the Schedule classes, as they should fundamentally represent the same object. Within Advyze. Making use of Schedule class reduces code duplication, and makes the program easily readable and extensible in the future.

* **Contribution to team-based tasks**:
  * Set up github organisation, team repository and git CI workflow
  * Tested merging all the PRs locally before merging in the team repo. This process reduces the likelihood of accidentally introducing an application breaking bug.
  * Fix & deployed release v1.3.1: Relevant PR: [#110](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/110)

* **Community**:
  * Reviewed most of the critical PRs (some exceptions were made due to time constraints and relevancy to other members) to ensure relatively high consistency among team member's codes.
    * Some highlights: [#46](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/46), [#87](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/87)
    * Others: [#43](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/43), [#44](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/44), [#48](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/48), [#49](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/49), [#50](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/50), [#51](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/51), [#54](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/54), [#55](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/55), [#58](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/58), [#59](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/59), [#86](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/86), [#88](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/88), [#89](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/89), [#93](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/93), [#94](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/94), [#96](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/96), [#97](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/97), [#99](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/99), [#101](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/101), [#104](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/104), [#107](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/107), [#193](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/193)
      * Note: PED bug fixes were not reviewed and merged directly, and a final check is to be done for v1.4.

* **Documentation**:
  * Logic:
    * Updated Delete Sequence Diagram to reflect the latest implementation. Used a reference frame to reduce UML diagram clutter. [#238](https://github.com/AY2122S1-CS2103-T14-4/tp/pull/238)
    * Expanded the example to show how delete contact works.
  * Implementation of new features.
    * Documented `Tab`, `import` and Convenience Commands.
