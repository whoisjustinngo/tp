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
 * Adds a policy to a client in the address book.
 */
public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "policy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to portfolio of client. \n"
            + "Parameters: `policy INDEX insurer/INSURER num/POLICY_ID n/POLICY_NAME comm/COMMISSION \n"
            + "Example: `policy 1 insurer/AIG num/1231 n/Critical illness comm/100`";

    public static final String MESSAGE_SUCCESS = "Policy added: %1$s";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy number and insurer already exists";

    private final Policy newPolicy;
    private final Index targetIndex;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPolicyCommand(Index targetIndex, Policy newPolicy) {
        requireNonNull(targetIndex);
        requireNonNull(newPolicy);
        this.targetIndex = targetIndex;
        this.newPolicy = newPolicy;
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
        Person personWithNewPolicy = createClientWithNewPolicy(personToAddPolicy, newPolicy);
        model.setPerson(personToAddPolicy, personWithNewPolicy);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newPolicy));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPolicyCommand // instanceof handles nulls
                && newPolicy.equals(((AddPolicyCommand) other).newPolicy)
                && targetIndex.equals(((AddPolicyCommand) other).targetIndex));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code clientToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createClientWithNewPolicy(Person clientToEdit, Policy newPolicy) throws CommandException {
        assert clientToEdit != null;

        Name name = clientToEdit.getName();
        Relationship relationship = clientToEdit.getRelationship();
        Phone phone = clientToEdit.getPhone();
        Email email = clientToEdit.getEmail();
        Address address = clientToEdit.getAddress();
        Set<Tag> tags = clientToEdit.getTags();
        Set<Policy> policies = clientToEdit.getPolicies();
        Status status = clientToEdit.getStatus();
        String notes = clientToEdit.getNotes();

        if (policies.contains(newPolicy)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        policies = addPolicy(policies, newPolicy);


        return new Person(name, relationship, phone, email, address, tags,
                policies, status, notes);
    }

    private static Set<Policy> addPolicy(Collection<Policy> policies, Policy policyToAdd) {
        assert policies != null;

        final Set<Policy> policySet = new HashSet<>();
        for (Policy policy : policies) {
            policySet.add(policy);
        }

        policySet.add(policyToAdd);
        return policySet;
    }

}
