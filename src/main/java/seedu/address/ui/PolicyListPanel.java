package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ClientState;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;

/**
 * Panel containing the list of CustomGoals.
 */
public class PolicyListPanel extends UiPart<Region> {
    private static final String FXML = "PolicyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PolicyListPanel.class);
    @FXML
    private ListView<Policy> policiesListView;
    private ObservableList<Person> selectedPersons;
    private ObservableList<Policy>observablePolicies;
    private List<Policy> policies;

    /**
     * Creates a {@code PolicyPanel} with the given {@code selectedList}.
     */
    public PolicyListPanel(ObservableList<Person> selectedPersons) {
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
        this.policies = new ArrayList<>(selectedPersons.get(0).getPolicies());
        this.observablePolicies = FXCollections.observableList(policies);
        policiesListView.setItems(observablePolicies);
        policiesListView.setCellFactory(listView -> new PolicyListViewCell());
    }
    /**
     * Custom {@code ListCell} that displays the details of a {@code Policy} using a {@code PolicyListCard}.
     */
    class PolicyListViewCell extends ListCell<Policy> {
        @Override
        protected void updateItem(Policy policy, boolean empty) {
            super.updateItem(policy, empty);
            if (empty || policy == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PolicyListCard(policy, getIndex() + 1).getRoot());
            }
        }
    }

}
