package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;

import java.util.Comparator;
import java.util.List;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DashboardCard extends UiPart<Region> {

    private static final String FXML = "DashboardCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-redu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label sectionLabel;
    
    @FXML
    private VBox contents;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DashboardCard(String label, List<String> entries) {
        super(FXML);
        sectionLabel.setText(label);
        int i = 1;
        for (String s: entries) {
            Text text = new Text(i + ": " + s + "\n");
            text.setFont(Font.font("verdana", 12)); // find a way to not hard code the font lol
            contents.getChildren().add(text);
            i++;
        }
        
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DashboardCard)) {
            return false;
        }

        // state check
        DashboardCard card = (DashboardCard) other;
        return false;
    }
}
