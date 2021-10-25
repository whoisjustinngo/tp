package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.analytics.ClientAnalytics;
import seedu.address.model.person.Status;

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
    
    private final ClientAnalytics clientAnalytics;
    private final ObservableList<Integer> counts;
    
    /**
     * Creates a {@code AnalyticsPanel} that observes values in the provided {@code Analytics} object.
     */
    public AnalyticsPanel(ClientAnalytics clientAnalytics) {
        super(FXML);
        this.clientAnalytics = clientAnalytics;
        this.counts = this.clientAnalytics.getValues();
        this.counts.addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> c) {
                System.out.println("list change detected");
                System.out.println(c);
            }
        });
        updateValues();
    }
    
    private void updateValues() {
        this.freshCount.setText(Integer.toString(this.clientAnalytics.getValueOfTrackedField(Status.FRESH)));
        this.approachedCount.setText(Integer.toString(this.clientAnalytics.getValueOfTrackedField(Status.APPROACHED)));
        this.pitchedCount.setText(Integer.toString(this.clientAnalytics.getValueOfTrackedField(Status.PITCHED)));
        this.negotiatedCount.setText(Integer.toString(this.clientAnalytics.getValueOfTrackedField(Status.NEGOTIATED)));
        this.closedCount.setText(Integer.toString(this.clientAnalytics.getValueOfTrackedField(Status.CLOSED)));
    }
}
