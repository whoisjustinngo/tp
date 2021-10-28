package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Policy;

/**
 * An UI component that displays information of a {@code Policy}.
 */
public class PolicyListCard extends UiPart<Region> {

    private static final String FXML = "PolicyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Policy policy;

    @FXML
    private HBox cardPane;
    @FXML
    private Label insurer;
    @FXML
    private Label policyName;
    @FXML
    private Label id;
    @FXML
    private Label policyNumber;
    @FXML
    private Label commission;

    /**
     * Creates a {@code PolicyCode} with the given {@code Policy} and index to display.
     */
    public PolicyListCard(Policy policy, int displayedIndex) {
        super(FXML);
        this.policy = policy;
        id.setText(displayedIndex + ". ");
        insurer.setText(policy.getInsurer().fullName);
        policyName.setText(policy.getName().fullName);
        policyNumber.setText(policy.getAsNumberString());
        commission.setText(policy.getCommissionAsString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyListCard)) {
            return false;
        }

        // state check
        PolicyListCard card = (PolicyListCard) other;
        return id.getText().equals(card.id.getText())
                && policy.equals(card.policy);
    }
}
