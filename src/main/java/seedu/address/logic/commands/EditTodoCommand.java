package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TODOS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.Todo;

/**
 * Edits the details of an existing Todo.
 */
public class EditTodoCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the todo identified "
            + "by the index number used in the displayed todo list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Read The Intelligent Investor ";

    public static final String MESSAGE_EDIT_TODO_SUCCESS = "Edited Todo: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in Advyze.";

    private final Index index;
    private final EditTodoDescriptor editTodoDescriptor;

    /**
     * @param index of the todo in the filtered todo list to edit
     * @param editTodoDescriptor details to edit the todo with
     */
    public EditTodoCommand(Index index, EditTodoDescriptor editTodoDescriptor) {
        requireNonNull(index);
        requireNonNull(editTodoDescriptor);

        this.index = index;
        this.editTodoDescriptor = new EditTodoDescriptor(editTodoDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Todo> lastShownList = model.getFilteredTodoList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Todo todoToEdit = lastShownList.get(index.getZeroBased());
        Todo editedTodo = createEditedTodo(todoToEdit, editTodoDescriptor);

        if (!todoToEdit.equals(editedTodo) && model.hasTodo(editedTodo)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO);
        }

        model.setTodo(todoToEdit, editedTodo);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODOS);
        return new CommandResult(String.format(MESSAGE_EDIT_TODO_SUCCESS, editedTodo));
    }

    /**
     * Creates and returns a {@code Todo} with the details of {@code todoToEdit}
     * edited with {@code editTodoDescriptor}.
     */
    private static Todo createEditedTodo(Todo todoToEdit, EditTodoDescriptor editTodoDescriptor) {
        assert todoToEdit != null;

        String updatedDescription = editTodoDescriptor.getDescription()
                .orElse(todoToEdit.getDescription());
        Set<Tag> updatedTags = editTodoDescriptor.getTags().orElse(todoToEdit.getTags());
        boolean isDone = todoToEdit.isDone();

        return new Todo(updatedDescription, updatedTags, isDone);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTodoCommand)) {
            return false;
        }

        // state check
        EditTodoCommand e = (EditTodoCommand) other;
        return index.equals(e.index)
                && editTodoDescriptor.equals(e.editTodoDescriptor);
    }

    /**
     * Stores the details to edit the todo with. Each non-empty field value will replace the
     * corresponding field value of the todo.
     */
    public static class EditTodoDescriptor {
        private String description;
        private Set<Tag> tags;

        public EditTodoDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTodoDescriptor(EditTodoDescriptor toCopy) {
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, tags);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTodoDescriptor)) {
                return false;
            }

            // state check
            EditTodoDescriptor e = (EditTodoDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
