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
    private StackPane  paneSketch, paneWiFi, paneNetwork, paneAttachment, paneDevice;

    @FXML
    private Button btnExit;



    public void handleNetwork(ActionEvent actionEvent) {
        paneSketch.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(true);
        paneAttachment.setVisible(false);
        paneDevice.setVisible(false);
    }

    public void handleWiFi(ActionEvent actionEvent) {
        paneSketch.setVisible(false);
        paneWiFi.setVisible(true);
        paneNetwork.setVisible(false);
        paneDevice.setVisible(false);
        paneAttachment.setVisible(false);
    }

    public void handleDone(ActionEvent actionEvent) {
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneSketch.setVisible(true);
        paneDevice.setVisible(false);
        paneAttachment.setVisible(false);
    }

    public void handleSketch(ActionEvent actionEvent) {
        paneSketch.setVisible(true);
        paneNetwork.setVisible(false);
        paneWiFi.setVisible(false);
        paneAttachment.setVisible(false);
        paneDevice.setVisible(false);
    }

    public void handleExit(ActionEvent actionEvent) {

        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void handleAttachment(ActionEvent actionEvent) {
        paneSketch.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneAttachment.setVisible(true);
        paneDevice.setVisible(false);
    }

    public void handleDevice(ActionEvent actionEvent) {
        paneSketch.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneAttachment.setVisible(false);
        paneDevice.setVisible(true);
    }
}
