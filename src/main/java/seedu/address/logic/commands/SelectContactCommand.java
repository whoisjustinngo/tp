package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class SelectContactCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects a specific contact to find client details\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + "1";

    private final Index index;

    /**
     *  Creates a SelectContactCommand class that takes in an index to select a contact
     * @param index
     */
    public SelectContactCommand(Index index) {
        //find the contact and filter exactly for that
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToSelect = lastShownList.get(index.getZeroBased());
        Predicate<Person> predicate = person -> person.equals(personToSelect);
        model.updateSelectedPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getSelectedPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectContactCommand // instanceof handles nulls
                && index.equals(((SelectContactCommand) other).index)); // state check
    }
}
