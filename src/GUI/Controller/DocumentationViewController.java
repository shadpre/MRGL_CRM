package GUI.Controller;


import BE.DBEnteties.Device;
import BLL.Managers.DeviceManager;
import BLL.Managers.ImageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocumentationViewController extends BaseController implements Initializable {

    private File imgFile;
    @FXML
    private StackPane  paneSketch, paneWiFi, paneNetwork, paneAttachment, paneDevice;

    @FXML
    private Button btnExit, billagUpload;

    @FXML
    private TextArea txtDeviceDescription, billagKommentar;

    @FXML
    private TextField txtDeviceIp;

    @FXML
    private CheckBox txtDevicePOE;

    @FXML
    private TextField txtDevicePassword;

    @FXML
    private TextField txtDeviceSubnet;

    @FXML
    private TextField txtDeviceUsername;

    @FXML
    private ImageView billagBillede;



    private DeviceManager deviceManager;



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

    public void handleAddDevice(ActionEvent actionEvent) {
        // Get User Information
        String description = txtDeviceDescription.getText();
        String remarks = "txtFieldFirstName.getText();";
        String IP = txtDeviceIp.getText();
        String password = txtDevicePassword.getText();
        String subnetMask = txtDeviceSubnet.getText();
        int installationID = 1;
        String userName = txtDeviceUsername.getText();
        Boolean isPOE = false;

        if (txtDevicePOE.isSelected()) {
            Boolean isPoe = true;
        }

        Device device = new Device(0, installationID, description, remarks, IP, subnetMask, userName, password, isPOE);
        try {

            deviceManager.createDevice(device);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void handleBillagSaveUpdate(ActionEvent actionEvent) throws IOException {
        billagKommentar.setWrapText(true);

        int installationId = 1;
        String description = "testing";
        String remarks = billagKommentar.getText();
        BufferedImage bImage = ImageIO.read(imgFile);
        int imageType = 1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage,"jpg", bos);
        byte[] data = bos.toByteArray();


        BE.DBEnteties.Image image = new BE.DBEnteties.Image(0, installationId, description, remarks, data, imageType);

        try {

            ImageManager.createImage(image);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }


        billagKommentar.setText("");

        billagBillede.setImage(null);
    }

    public void handleBillagUpload(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        Stage stage = (Stage) billagBillede.getScene().getWindow();
        imgFile = fc.showOpenDialog(stage);
        javafx.scene.image.Image image = new Image(imgFile.getAbsolutePath());
        billagBillede.setImage(image);

    }
}
