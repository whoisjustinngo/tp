package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Policy;
import seedu.address.model.person.Relationship;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Changes the status of a client in the address book.
 */
public class EditStatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of a client. "
            + "Available statuses: " + Arrays.asList(Status.values()) + "\n"
            + "Example: `" + COMMAND_WORD + " 1 " + "approach`";

    public static final String MESSAGE_SUCCESS = "Status changed for %s: %s";

    private final Status newStatus;
    private final Index targetIndex;

    /**
     * Creates an EditStatusCommand to change the {@code Status} of specified {@code Person}
     */
    public EditStatusCommand(Index targetIndex, Status newStatus) {
        requireNonNull(targetIndex);
        requireNonNull(newStatus);
        this.targetIndex = targetIndex;
        this.newStatus = newStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToChangeStatus = lastShownList.get(targetIndex.getZeroBased());
        Person personWithNewStatus = createClientWithNewStatus(personToChangeStatus, newStatus);
        model.setPerson(personToChangeStatus, personWithNewStatus);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personWithNewStatus.getName().fullName,
                personWithNewStatus.getStatus()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditStatusCommand // instanceof handles nulls
                && newStatus.equals(((EditStatusCommand) other).newStatus)
                && targetIndex.equals(((EditStatusCommand) other).targetIndex));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code clientToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createClientWithNewStatus(Person clientToEdit, Status newStatus) throws CommandException {
        assert clientToEdit != null;

        Name name = clientToEdit.getName();
        Relationship relationship = clientToEdit.getRelationship();
        Phone phone = clientToEdit.getPhone();
        Email email = clientToEdit.getEmail();
        Address address = clientToEdit.getAddress();
        Set<Tag> tags = clientToEdit.getTags();
        Set<Policy> policies = clientToEdit.getPolicies();
        //new status
        Status status = newStatus;
        String notes = clientToEdit.getNotes();

        return new Person(name, relationship, phone, email, address, tags,
                policies, status, notes);
    }

}
