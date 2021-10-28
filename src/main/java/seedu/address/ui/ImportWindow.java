package seedu.address.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class ImportWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "ImportWindow.fxml";
    private File icsFile;

    @FXML
    private AnchorPane importWindowAnchorPane;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ImportWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ImportWindow() {
        this(new Stage());
    }

    /**
     * Gets the file opened
     */
    public File getIcsFile() {
        return icsFile;
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing import schedule page.");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import a Schedule");
        Stage stage = (Stage) importWindowAnchorPane.getScene().getWindow();

        icsFile = fileChooser.showOpenDialog(stage);
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
