package seedu.address.model;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Tab Switch Command
 */
public class TabSwitch {
    public enum Tab { //used to index tabs
        DASHBOARD("dashboardTab", 0),
        CONTACTS("contactsTab", 1),
        SCHEDULE("scheduleTab", 2),
        TODOS("todosTab", 3);

        private String label;
        private int index;
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

        /**
         * Converts index into Tab enum
         * @param index parsed index
         * @return Tab enum
         * @throws ParseException when TabId does not exist
         */
        public static Tab toEnum(Index index) throws ParseException {
            switch (index.getZeroBased()) {
            case 0:
                return DASHBOARD;
            case 1:
                return CONTACTS;
            case 2:
                return SCHEDULE;
            case 3:
                return TODOS;
            default:
                throw new ParseException("Tab does not exist!");
            }
        }
    }
}
