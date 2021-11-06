package seedu.address.commons.core;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents Tabs on the UI
 */
public enum Tab { //used to index tabs for TabPane
    DASHBOARD(TabIds.DASHBOARD_TAB_ID, 0),
    CONTACTS(TabIds.CONTACTS_TAB_ID, 1),
    DETAILS(TabIds.DETAILS_TAB_ID, 2),
    SCHEDULE(TabIds.SCHEDULE_TAB_ID, 3),
    TODOS(TabIds.TODOS_TAB_ID, 4);

    private static final String DASHBOARD_ALIAS = "dashboard";
    private static final String CONTACTS_ALIAS = "contacts";
    private static final String DETAILS_ALIAS = "details";
    private static final String SCHEDULE_ALIAS = "schedule";
    private static final String TODOS_ALIAS = "todos";
    private static final String DOES_NOT_EXIST = "%s does not exist!";

    private final String label;
    private final int index;

    Tab(String tabName, int index) {
        this.label = tabName;
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        switch (this.index) {
        case 0:
            return DASHBOARD_ALIAS;
        case 1:
            return CONTACTS_ALIAS;
        case 3:
            return SCHEDULE_ALIAS;
        case 4:
            return TODOS_ALIAS;
        case 2:
            return DETAILS_ALIAS;
        default:
            return String.format(DOES_NOT_EXIST, this.label);
        }
    }

    /**
     * Converts tab alias into Tab enum
     *
     * @param tabName receivedName
     * @return Tab enum
     * @throws ParseException when Tab alias does not exist
     */
    public static Tab aliasToEnum(String tabName) throws ParseException {
        switch (tabName) {
        case DASHBOARD_ALIAS:
            return DASHBOARD;
        case CONTACTS_ALIAS:
            return CONTACTS;
        case SCHEDULE_ALIAS:
            return SCHEDULE;
        case TODOS_ALIAS:
            return TODOS;
        case DETAILS_ALIAS:
            return DETAILS;
        default:
            throw new ParseException(String.format(DOES_NOT_EXIST, tabName));
        }
    }

    /**
     * Converts Tab Ids to its enum equivalent
     *
     * @param tabId tab Id received
     * @return Tab enum
     * @throws ParseException when TabId does not exist
     */
    public static Tab tabIdToEnum(String tabId) throws ParseException {
        switch (tabId) {
        case TabIds.DASHBOARD_TAB_ID:
            return DASHBOARD;
        case TabIds.CONTACTS_TAB_ID:
            return CONTACTS;
        case TabIds.TODOS_TAB_ID:
            return TODOS;
        case TabIds.DETAILS_TAB_ID:
            return DETAILS;
        case TabIds.SCHEDULE_TAB_ID:
            return SCHEDULE;
        default:
            // hide the implementation details to user
            throw new ParseException(String.format(DOES_NOT_EXIST, "Internal error: Tab"));
        }
    }
}
