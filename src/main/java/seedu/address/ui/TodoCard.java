package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.todo.Todo;

/**
 * An UI component that displays information of a {@code Todo}.
 */
public class TodoCard extends UiPart<Region> {

    private static final String FXML = "TodoListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Todo todo;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;

    /**
     * Creates a {@code TodoCard} with the given {@code Todo} and index to display.
     */
    public TodoCard(Todo todo, int displayedIndex) {
        super(FXML);
        this.todo = todo;
        id.setText(displayedIndex + ". ");
        description.setText(todo.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TodoCard)) {
            return false;
        }

        // state check
        TodoCard card = (TodoCard) other;
        return id.getText().equals(card.id.getText())
                && todo.equals(card.todo);
    }
}
