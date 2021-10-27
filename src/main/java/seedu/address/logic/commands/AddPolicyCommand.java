package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Policy;
import seedu.address.model.person.Relationship;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to portfolio of client. "
            + "Typing `add policy` will begin the process of adding policy";

    public static final String MESSAGE_SUCCESS = "Policy added: %1$s";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy number already and insurer already exists";

    private final Index targetIndex;
    private final Policy policyToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPolicyCommand(Index targetIndex, Policy policyToAdd) {
        requireNonNull(policyToAdd);
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.policyToAdd = policyToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddPolicy = lastShownList.get(targetIndex.getZeroBased());

        if (!personToAddPolicy.getRelationship().value.equals("client")) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        Client clientToAddPolicy = (Client) personToAddPolicy;
        if (!model.clientHasPolicy(clientToAddPolicy, policyToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();


        //actually an edit because person is immutable object
        Client clientWithNewPolicy = createClientWithNewPolicy(clientToAddPolicy, editClientDescriptor);

        Policy newPolicy = new Policy(insurer, number, name, commission);
        Client editedClient = createEditedPerson(personToAddPolicy, newPolicy);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Client createClientWithNewPolicy(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Relationship updatedRelationship = editClientDescriptor.getRelationship()
                .orElse(clientToEdit.getRelationship());
        Phone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Email updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        Set<Tag> updatedTags = editClientDescriptor.getTags().orElse(clientToEdit.getTags());
        Set<Policy> updatedPolicies = editClientDescriptor.getPolicies().orElse(clientToEdit.getPolicies());
        Status status = editClientDescriptor.getStatus().orElse(clientToEdit.getStatus());
        String notes = editClientDescriptor.getNotes().orElse(clientToEdit.getNotes());

        return new Client(updatedName, updatedRelationship, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor extends EditCommand.EditPersonDescriptor {
        private Set<Policy> policies = new HashSet<>();
        private Status status;
        private String notes;
        private LocalDateTime lastUpdated;

        /**
         * Creates editClientDescriptor to recreate immutable Clients
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setRelationship(toCopy.relationship);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Creates editClientDescriptor to recreate immutable Clients
         */
        public EditClientDescriptor() {
            super();
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public void setLastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }


        public Optional<String> getNotes() {
            return Optional.ofNullable(notes);
        }

        public Optional<LocalDateTime> getLastUpdated() {
            return Optional.ofNullable(lastUpdated);
        }

        /**
         * Sets {@code policies} to this object's {@code policies}.
         * A defensive copy of {@code policies} is used internally.
         */
        public void setPolicies(Set<Policy> policies) {
            this.policies = (policies != null) ? new HashSet<>(policies) : null;
        }

        /**
         * Returns an unmodifiable policy set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code policis} is null.
         */
        public Optional<Set<Policy>> getPolicies() {
            return (policies != null) ? Optional.of(Collections.unmodifiableSet(policies)) : Optional.empty();
        }

    }
}
