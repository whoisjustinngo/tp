package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Client;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;

import java.util.List;

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

        if (!model.clientHasPolicy((Client) personToAddPolicy, policyToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

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
}
