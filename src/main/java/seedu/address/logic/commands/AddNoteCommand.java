package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collection;
import java.util.HashSet;
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
 * Adds a person to the address book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note for a client. "
            + "Example: `note 1 Call her back`";

    public static final String MESSAGE_SUCCESS = "Note added: %1$s";

    private final String newNote;
    private final Index targetIndex;

    /**
     * Creates an AddNoteCommand to add the specified {@code Person}
     */
    public AddNoteCommand(Index targetIndex, String newNote) {
        requireNonNull(targetIndex);
        requireNonNull(newNote);
        this.targetIndex = targetIndex;
        this.newNote = newNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddNote = lastShownList.get(targetIndex.getZeroBased());
        Person personWithNewNote = createClientWithNewNote(personToAddNote, newNote);
        model.setPerson(personToAddNote, personWithNewNote);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personWithNewNote.getNotes()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && newNote.equals(((AddNoteCommand) other).newNote)
                && targetIndex.equals(((AddNoteCommand) other).targetIndex));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createClientWithNewNote(Person personToEdit, String newNote) throws CommandException {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Relationship relationship = personToEdit.getRelationship();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        Set<Policy> policies = personToEdit.getPolicies();
        Status status = personToEdit.getStatus();
        String notes = personToEdit.getNotes();

        notes = addNote(notes, newNote);


        return new Person(name, relationship, phone, email, address, tags,
                policies, status, notes);
    }

    private static String addNote(String notes, String newNote) {
        assert notes != null;
        return notes + "\n" + newNote;
    }

}
