package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Panel displaying the dashboard sections.
 */
public class DashboardPanel extends UiPart<Region> {
    private static final String FXML = "DashboardPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DashboardPanel.class);

    @FXML
    private VBox sections;

    /**
     * Creates a {@code DashboardPanel}.
     */
    public DashboardPanel() {
        super(FXML);
        
        // stub just for testing purposes
        List<String> temp1 = new ArrayList<String>();
        List<String> temp2 = new ArrayList<String>();
        temp1.add("hello 1");
        temp1.add("hello 2");
        temp1.add("hello 3");
        temp1.add("hello 4");
        temp1.add("hello 5");
        temp2.add("hello 1");
        temp2.add("hello 2");
        temp2.add("hello 3");
        temp2.add("hello 4");
        temp2.add("hello 5");
        
        sections.getChildren().add(new DashboardCard("Here is your schedule for today:", temp1).getRoot());
        sections.getChildren().add(new DashboardCard("Here are some of your uncompleted ToDos:", temp2).getRoot());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
