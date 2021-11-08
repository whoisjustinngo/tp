---
layout: page
title: Bryan Tee's Project Portfolio Page
---

### Project: Advyze

Advyze is a desktop app for tech savvy student financial advisors to keep track of their clients and their busy school schedule. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=spdpnd98&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

* **New Features**: 
  * Implemented Tab Command
    * What it does: Allows user to switch between tabs via commands
    * Justification: These commands are required for the application to be fully mouse-free, and for users to only use the keyboard, allowing a better fit for our target users.
    * Highlights: Each Tab names are consolidated into enums, so that it is easier to keep track when programming, and also allows better extensibility for the application in the future. In an earlier iteration, static strings are scattered everywhere, including the parser and tests. Having enums allows a better abstraction, reduces code duplication, and also prevents misuse of code by programmers in the future. Not only that, To add this implementation, it is somewhat linked to the UI, and as such require careful planning to place this command at a correct location within the MVC.
  
  * Implemented Convenience Command:
    * What it does: Allows user to add context to the commands they want to run. Eg `/todos add d/a todo` in the Dashboard tab.
    * Justification: This addition reduces the time it takes to logically link to other tabs. As such it streamlines the process of adding `Todos`, `Schedules`, `Contacts`, and `Details`, making Advyze more user-friendly.
    * Highlight: The tab ids' implementation should be hidden from the user, but users have to enter a tab name! As such, functions to convert relevant strings to enums need to be written. Next, there were many implementation that came to mind when thinking about this portion of the program. To simplify the program, the convenience commands are implemented by internally calling the tab command. This makes the program less error-prone as when the tab command implementation changes, so should the implementation of convinience function. Identifying this level of coupling was not easy, and required extensive reading of the AB3 code to notice. This addition was done in a way that was not only invisible to the user, but also to the other developers, meaning that their code required _no_ modification at all upon complete implementation.

  * Implemented import command:
    * What it does: Allows uesr to import their schedules into the application
    * Justification: Our team went back and forth on whether this was an important enough feature to implement. This feature is important because many 3rd party applications support an export of `.ics` files, and as such the introduction with Advyze will be a lot smoother.
    * Highlights: Many rounds of experimentation was done, including investigating the structure of `.ics` files, writing custom parsers, before finally noticing that `.ics` files have some sort of standard, and a library was found that followed the [RFC2445](http://www.faqs.org/rfcs/rfc2445.html) specification. The library `iCal4j` was not well documented, but was the only complete library that could support this feature. The documentations are scattered across different sites. Eventually, the feature was implemented somewhat cleanly in a separate project, before finally integrating it into Advyze. Furthermore, import `.ics` also makes use of the Schedule classes, as they should fundamentally represent the same object. Within Advyze. Making use of Schedule class reduces code duplication, and makes the program easily readable and extensible in the future.

* **Contribution to team-based tasks**:
  * Reviewed most of the PRs (some exceptions were made due to time constraints and relevancy to other members) to ensure relatively high consistency among team member's codes.
  * Tested merging all the PRs locally before merging in the team repo. This process reduces the likelihood of accidentally introducing an application breaking bug.

* **Documentation**:
  * Logic:...
  * Implementation of new features....