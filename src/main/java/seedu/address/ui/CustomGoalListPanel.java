package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.customGoal.CustomGoal;

import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class CustomGoalListPanel extends UiPart<Region> {
    private static final String FXML = "CustomGoalPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomGoalListPanel.class);

    @FXML
    private ListView<CustomGoal> customGoalListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CustomGoalListPanel(ObservableList<CustomGoal> customGoalList) {
        super(FXML);
        customGoalListView.setItems(customGoalList);
        customGoalListView.setCellFactory(listView -> new CustomGoalListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class CustomGoalListViewCell extends ListCell<CustomGoal> {
        @Override
        protected void updateItem(CustomGoal customGoal, boolean empty) {
            super.updateItem(customGoal, empty);

            if (empty || customGoal == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CustomGoalCard(customGoal, getIndex() + 1).getRoot());
            }
        }
    }

}
