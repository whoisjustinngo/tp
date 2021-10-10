package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB_NAME;

import seedu.address.logic.commands.TabSwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TabSwitch;

public class TabSwitchCommandParser implements Parser<TabSwitchCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public TabSwitchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            return new TabSwitchCommand(TabSwitch.Tab.toEnum(args.trim()));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_TAB_NAME, args),
                    pe
            );
        }
    }
}
