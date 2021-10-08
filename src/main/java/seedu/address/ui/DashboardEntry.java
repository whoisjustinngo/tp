package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DashboardEntry extends UiPart<Region> {

    private static final String FXML = "DashboardEntry.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-redu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label entry;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DashboardEntry(String label) {
        super(FXML);
        entry.setText(label);      
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DashboardEntry)) {
            return false;
        }

        // state check
        DashboardEntry card = (DashboardEntry) other;
        return false;
    }
}
