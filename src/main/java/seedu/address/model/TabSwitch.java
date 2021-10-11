package seedu.address.model;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Tab Switch Command
 */

public class TabSwitch {
    private static final String DASHBOARD_ALIAS = "dashboard";
    private static final String CONTACTS_ALIAS = "contacts";
    private static final String SCHEDULE_ALIAS = "schedule";
    private static final String TODOS_ALIAS = "todos";

    private static final String DOES_NOT_EXIST = " does not exist!";

    public enum Tab { //used to index tabs for TabPane
        DASHBOARD("dashboardTab", 0),
        CONTACTS("contactsTab", 1),
        SCHEDULE("scheduleTab", 2),
        TODOS("todosTab", 3);

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
            case 2:
                return SCHEDULE_ALIAS;
            case 3:
                return TODOS_ALIAS;
            default:
                return DOES_NOT_EXIST;
            }
        }

        /**
         * Converts tab name into Tab enum
         * @param tabName receivedName
         * @return Tab enum
         * @throws ParseException when TabId does not exist
         */
        public static Tab toEnum(String tabName) throws ParseException {
            switch (tabName) {
            case DASHBOARD_ALIAS:
                return DASHBOARD;
            case CONTACTS_ALIAS:
                return CONTACTS;
            case SCHEDULE_ALIAS:
                return SCHEDULE;
            case TODOS_ALIAS:
                return TODOS;
            default:
                throw new ParseException("Tab does not exist!");
            }
        }
    }
}
