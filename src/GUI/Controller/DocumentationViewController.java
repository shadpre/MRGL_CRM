package GUI.Controller;

import BE.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentationViewController extends BaseController implements Initializable {
    @FXML
    private StackPane sketchPane, førBilledePane, efterBilledePane, billagPane;

    @FXML
    private Button benAddComment;

    @FXML
    private Button btnAddDocumentation;

    @FXML
    private Button btnAddSketch;

    @FXML
    private TableView<User> tbwAllUsers;
    @FXML
    private TableColumn<User, String> columnLastName;
    @FXML
    private TableColumn<User, String> columnFirstName;
    @FXML
    private TableColumn<User, String> columnEmail;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnForrigeSide, btnNæsteSide;

    @FXML
    private Label lblOrderNbr;

    @FXML
    private Pane paneAddComments;

    @FXML
    private Pane paneAddDocumentation;

    @FXML
    private Pane paneAddSketch;

    @FXML
    private Pane paneUploadPictures;

    public void handleDocumenation(ActionEvent actionEvent) {
        billagPane.setVisible(false);
        btnNæsteSide.setVisible(true);
        btnForrigeSide.setVisible(true);
        sketchPane.setVisible(true);
    }

    public void handleDone(ActionEvent actionEvent) {
    }

    public void handleLast(ActionEvent actionEvent) {
        if (sketchPane.isVisible() == true){
            efterBilledePane.setVisible(true);
            sketchPane.setVisible(false);
        }
        else if (førBilledePane.isVisible() == true) {
            sketchPane.setVisible(true);
            førBilledePane.setVisible(false);
        }
        else if (efterBilledePane.isVisible() == true) {
            førBilledePane.setVisible(true);
            efterBilledePane.setVisible(false);

        }
    }

    public void handleNext(ActionEvent actionEvent) {
        if (sketchPane.isVisible() == true){
            førBilledePane.setVisible(true);
            sketchPane.setVisible(false);
        }
        else if (førBilledePane.isVisible() == true) {
            efterBilledePane.setVisible(true);
            førBilledePane.setVisible(false);
        }
        else if (efterBilledePane.isVisible() == true) {
            sketchPane.setVisible(true);
            efterBilledePane.setVisible(false);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sketchPane.setVisible(false);
        førBilledePane.setVisible(false);
        efterBilledePane.setVisible(false);
        btnForrigeSide.setVisible(false);
        btnNæsteSide.setVisible(false);
        billagPane.setVisible(false);
    }

    public void handleAttachment(ActionEvent actionEvent) {
        sketchPane.setVisible(false);
        førBilledePane.setVisible(false);
        efterBilledePane.setVisible(false);
        btnForrigeSide.setVisible(false);
        btnNæsteSide.setVisible(false);

        billagPane.setVisible(true);

    }

    public void handleExit(ActionEvent actionEvent) {

        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
