package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Policy;

/**
 * Panel containing the list of CustomGoals.
 */
public class PolicyListPanel extends UiPart<Region> {
    private static final String FXML = "PolicyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PolicyListPanel.class);

    @FXML
    private ListView<Policy> customGoalListView;

    /**
     * Creates a {@code PolicyPanel} with the given {@code ObservableList}.
     */
    public PolicyListPanel(ObservableList<Policy> policies) {
        super(FXML);
        customGoalListView.setItems(policies);
        customGoalListView.setCellFactory(listView -> new PolicyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the details of a {@code Policy} using a {@code PolicyListCard}.
     */
    class PolicyListViewCell extends ListCell<Policy> {
        @Override
        protected void updateItem(Policy customGoal, boolean empty) {
            super.updateItem(customGoal, empty);

            if (empty || customGoal == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PolicyListCard(customGoal, getIndex() + 1).getRoot());
            }
        }
    }

}
