package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

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
        temp1.add("gf dxcfvgbuhinjomidpov,bpnmojgnbdruyefvtcrtfgy8huijpoxerctyhujgvcrdftgyhbvgfcrftgyhubfcdrftkn");
        temp1.add("kmnjbhgvcrxtf78g79h80j9kopojihugytrtyghj90koiggvhb gvyhjbhgvyhujnhyhunbvtcf67ty8jinbuy");
        temp1.add("klj hgvyguhijpok[pojihuy ceuvyfbihojpek[fojidafsghfjgkhljjhftgdrfesrgdhtfjygeh");
        temp1.add("jnbhsvyburhej9k0flpkirnubyfv0g89[eh09]j-0k-]lv=[[3456789uytrfghjuytrfdfgtrdflpk");
        temp1.add("njbhgvtyuybiunoimpo,poiuytfgy7h8j9k0sfgdhfjygytrgfdvfbgnhuk76y5etrewfadsvfdbgyho");
        temp2.add("crxtf78g79h80j9kopojihugytrtyghj90komknjbhgfcrdfgyvgcfrfthubgfcrtgyhubgvt");
        temp2.add("crxtf78g79h80j9kopojihugytrtyghj90korgehrtggfcrtfg7nbhgvcfft6y7hubgvftgyhur");
        temp2.add("mopinubyvtcy8u9hi0jokojih7g6f7879y809-nbvtcrfty7u8i90oijuhgytfy78u9iokijbvcft67y8u9iu8y76tfr0");
        temp2.add("jbvgrtcytuyiuhoinuytrfyg7h89konbyg7y8uijnbhvyghujinbhyhujpok[pl][;");
        temp2.add("miubyvtcrtf68g79h809oinbyvg7y8hubhvgcfrftg6hybgvfcdrdpoiolkjiokjkjhgyujbvftyhftyufgy");

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
