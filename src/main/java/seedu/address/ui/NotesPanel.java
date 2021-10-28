package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of CustomGoals.
 */
public class NotesPanel extends UiPart<Region> {
    private static final String FXML = "NotesPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PolicyListPanel.class);
    @FXML
    private TextArea notesDisplay;
    private ObservableList<Person> selectedPersons;

    /**
     * Creates a {@code NotesPanel} with the given {@code selectedPersons}.
     */
    public NotesPanel(ObservableList<Person> selectedPersons) {
        super(FXML);
        this.selectedPersons = selectedPersons;
        this.selectedPersons.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateValues();
            }
        });
        updateValues();
    }
    private void updateValues() {
        notesDisplay.setText(selectedPersons.get(0).getNotes());
    }

}
