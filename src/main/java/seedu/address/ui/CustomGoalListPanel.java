package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.customGoal.CustomGoal;

/**
 * Panel containing the list of CustomGoals.
 */
public class CustomGoalListPanel extends UiPart<Region> {
    private static final String FXML = "CustomGoalPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomGoalListPanel.class);

    @FXML
    private ListView<CustomGoal> customGoalListView;

    /**
     * Creates a {@code CustomGoalPanel} with the given {@code ObservableList}.
     */
    public CustomGoalListPanel(ObservableList<CustomGoal> customGoalList) {
        super(FXML);
        customGoalListView.setItems(customGoalList);
        customGoalListView.setCellFactory(listView -> new CustomGoalListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the details of a {@code CustomGoal} using a {@code CustomGoalCard}.
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
