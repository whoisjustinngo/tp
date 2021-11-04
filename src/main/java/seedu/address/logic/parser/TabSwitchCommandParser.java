package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB_NAME;

import seedu.address.commons.core.Tab;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TabSwitch;

public class TabSwitchCommandParser implements Parser<TabCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public TabCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            return new TabCommand(Tab.toEnum(args.trim()));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_TAB_NAME, args),
                    pe
            );
        }
    }
}
