package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TabSwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TabSwitch;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_TAB_INDEX_ERROR;

public class TabSwitchCommandParser implements Parser<TabSwitchCommand>{
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public TabSwitchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index = ParserUtil.parseIndex(args);
        try {
            return new TabSwitchCommand(TabSwitch.Tab.toEnum(index));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_TAB_INDEX_ERROR, index.getOneBased()),
                    pe
            );
        }
    }
}
