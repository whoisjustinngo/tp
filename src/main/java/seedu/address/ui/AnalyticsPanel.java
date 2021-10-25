package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Analytics;
import seedu.address.model.customGoal.CustomGoal;

import java.util.logging.Logger;

/**
 * Panel containing the list of CustomGoals.
 */
public class AnalyticsPanel extends UiPart<Region> {
    private static final String FXML = "AnalyticsPanel.fxml"; 
    private final Logger logger = LogsCenter.getLogger(AnalyticsPanel.class);

    @FXML
    private Label freshCount;

    @FXML
    private Label approachedCount;

    @FXML
    private Label pitchedCount;

    @FXML
    private Label negotiatedCount;

    @FXML
    private Label closedCount;
    
    private final Analytics analytics;
    
    /**
     * Creates a {@code AnalyticsPanel} that observes values in the provided {@code Analytics} object.
     */
    public AnalyticsPanel(Analytics analytics) {
        super(FXML);
        this.analytics = analytics;
        // TODO WATCH VALUES
    }
}
