package GUI.Controller;


import BE.DBEnteties.Device;
import BLL.Managers.DeviceManager;
import BLL.Managers.ImageManager;
import GUI.Model.CustomerModel;
import GUI.Model.DeviceModel;
import GUI.Model.ImageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocumentationViewController extends BaseController implements Initializable {



    @FXML
    private ImageView canvasImageView, billagBillede;

    @FXML
    private StackPane  paneSketch, paneWiFi, paneNetwork, paneAttachment, paneDevice;

    @FXML
    private Button btnExit, billagUpload,btnDeviceShow, btnAddDevice, btnShowSketch, btnSaveSketch;

    @FXML
    private TextArea txtDeviceDescription, billagKommentar, txtAreaSketch;

    @FXML
    private TextField txtDevicePassword, txtDeviceSubnet, txtDeviceUsername, txtDeviceIp;

    @FXML
    private TextField txtTitleSketch;

    @FXML CheckBox txtDevicePOE;
    @FXML
    private TableView<Device> tableDevice;

    @FXML TableView<BE.DBEnteties.Image> tableSketch;

    @FXML
    private TableColumn<BE.DBEnteties.Image, String> columnSketchTitle;

    @FXML
    private TableColumn<BE.DBEnteties.Image, Integer> columnSketchID;

    @FXML
    private TableColumn<Device, String> columnDeviceIP, columnDevicePassword, columnDeviceUsername, columnDeviceSubnet;

    private DeviceManager deviceManager;
    private DeviceModel deviceModel;
    private ImageModel imageModel;

    private File imgFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneSketch.setVisible(true);
        paneDevice.setVisible(false);
        paneAttachment.setVisible(false);

        setSketchTable();
    }

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

    public void handleAttachment(ActionEvent actionEvent) {

        paneSketch.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneAttachment.setVisible(true);
        paneDevice.setVisible(false);

    }

    public void handleSketch(ActionEvent actionEvent) {

        paneSketch.setVisible(true);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneAttachment.setVisible(false);
        paneDevice.setVisible(false);

        setSketchTable();

    }

    public void handleDevice(ActionEvent actionEvent) {

        paneSketch.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneAttachment.setVisible(false);
        paneDevice.setVisible(true);

        setTableDevice();
    }

    public void handleExit(ActionEvent actionEvent) {

        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }



    public void handleAddDevice(ActionEvent actionEvent) {

        if (btnAddDevice.getText().equals("Gem Enhed")) {

            creatingDevice();

            txtDeviceDescription.setText("");
            txtDeviceIp.setText("");
            txtDevicePassword.setText("");
            txtDeviceSubnet.setText("");
            txtDeviceUsername.setText("");
            txtDevicePOE.setSelected(false);


        } else if (btnAddDevice.getText().equals("Opdater Enhed")) {

            updatingDevice();

            txtDeviceDescription.setText("");
            txtDeviceIp.setText("");
            txtDevicePassword.setText("");
            txtDeviceSubnet.setText("");
            txtDeviceUsername.setText("");
            txtDevicePOE.setSelected(false);
            btnAddDevice.setText("Gem Enhed");
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
        uploadImage();
    }


    public void setTableDevice(){

        DeviceModel deviceModel = new DeviceModel();
        this.deviceModel = deviceModel;

        columnDeviceIP.setCellValueFactory(new PropertyValueFactory<Device, String>("IP"));
        columnDeviceSubnet.setCellValueFactory(new PropertyValueFactory<Device, String>("SubnetMask"));
        columnDeviceUsername.setCellValueFactory(new PropertyValueFactory<Device, String>("UserName"));
        columnDevicePassword.setCellValueFactory(new PropertyValueFactory<Device, String>("Password"));

        try {
            tableDevice.setItems(DeviceModel.getDeviceList(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void handleDeviceShow(ActionEvent actionEvent) {

                // Retrieve the selected device
                Device selectedDevice = tableDevice.getSelectionModel().getSelectedItem();

                // Populate the UI elements with the selected device's data
                txtDeviceDescription.setText(selectedDevice.getRemarks());
                txtDeviceIp.setText(selectedDevice.getIP());
                txtDevicePassword.setText(selectedDevice.getPassword());
                txtDeviceSubnet.setText(selectedDevice.getSubnetMask());
                txtDeviceUsername.setText(selectedDevice.getUserName());

                if (selectedDevice.isPOE() == true) {
                    txtDevicePOE.setSelected(true);
                } else {
                    txtDevicePOE.setSelected(false);
                }
            updateButtonAndFieldsDevice();
    }



    public void creatingDevice(){

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

        setTableDevice();
    }



    public void handleAddSketch(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DrawingProgram.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        DrawingProgram drawingProgram = loader.getController();
        drawingProgram.setDocumentationController(this);

        stage.setTitle("Tegne Program");
        stage.show();

    }

    public void setCanvasImage(Image canvasImage) {canvasImageView.setImage(canvasImage);}

    public void handleUploadSketch(ActionEvent actionEvent) {uploadImage();}

    public void handleFinishSketch(ActionEvent actionEvent) {
    }

    public void handleSaveSketch(ActionEvent actionEvent) throws IOException {
    if(btnSaveSketch.getText().equals("Gem Tegning"))
        saveSketch();
        setSketchTable();
    }

    public void handleShowSketch(ActionEvent actionEvent) {

        txtAreaSketch.setWrapText(true);

        BE.DBEnteties.Image selectedImage = tableSketch.getSelectionModel().getSelectedItem();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(selectedImage.getData());

        Image sketchForShow = new Image(inputStream);

        canvasImageView.setImage(sketchForShow);

        txtTitleSketch.setText(selectedImage.getDescription());
        txtAreaSketch.setText(selectedImage.getRemarks());

        updateButtonsSketch();
    }

    public void handleRemoveSketch(ActionEvent actionEvent) {
    }
    public void uploadImage() {

        FileChooser fc = new FileChooser();
        Stage stage = (Stage) billagBillede.getScene().getWindow();
        imgFile = fc.showOpenDialog(stage);

        if(imgFile != null) {
            javafx.scene.image.Image image = new Image(imgFile.getAbsolutePath());
            billagBillede.setImage(image);
        }
    }
    public void updatingDevice(){
        // Get User Information

        Device selectedDevice = tableDevice.getSelectionModel().getSelectedItem();

        String description = txtDeviceDescription.getText();
        String remarks = "txtFieldFirstName.getText();";
        String IP = txtDeviceIp.getText();
        String password = txtDevicePassword.getText();
        String subnetMask = txtDeviceSubnet.getText();
        int installationID = selectedDevice.getInstallationId();
        String userName = txtDeviceUsername.getText();
        Boolean isPOE = false;
        int Id = selectedDevice.getId();

        if (txtDevicePOE.isSelected()) {
            Boolean isPoe = true;
        }

        Device device = new Device(Id, installationID, description, remarks, IP, subnetMask, userName, password, isPOE);


        try {

            deviceManager.updateDevice(device);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        setTableDevice();
    }
    public void updateButtonAndFieldsDevice() {

        if (btnDeviceShow.getText().equals("Vis Enhed")) {
            // Run current method
            btnAddDevice.setText("Opdater Enhed");
            btnDeviceShow.setText("Stop Visning");

        } else if (btnDeviceShow.getText().equals("Stop Visning")) {

            tableDevice.getSelectionModel().clearSelection();

            txtDeviceDescription.setText("");
            txtDeviceIp.setText("");
            txtDevicePassword.setText("");
            txtDeviceSubnet.setText("");
            txtDeviceUsername.setText("");
            txtDevicePOE.setSelected(false);
            btnAddDevice.setText("Gem Enhed");
            btnDeviceShow.setText("Vis Enhed");
        }
    }

    public void updateButtonsSketch(){

        if (btnDeviceShow.getText().equals("Vis Tegning")) {

            // Run current method
            btnAddDevice.setText("Opdater Tegning");
            btnDeviceShow.setText("Stop Visning");

        }

        else if (btnDeviceShow.getText().equals("Stop Visning")) {

            tableSketch.getSelectionModel().clearSelection();

            txtTitleSketch.setText("");
            txtAreaSketch.setText("");

            canvasImageView.setImage(null);

            btnAddDevice.setText("Gem Tegning");
            btnDeviceShow.setText("Vis Tegning");
        }
    }
    public void saveSketch(){

        txtAreaSketch.setWrapText(true);

        int installationId = 1;
        String description = txtTitleSketch.getText();
        String remarks = txtAreaSketch.getText();
        int imageType = 2;

        Image imageCanvas = canvasImageView.getImage();
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageCanvas, null);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "jpg", bos);
        } catch (IOException e) {

        }

        byte[] data = bos.toByteArray();

        BE.DBEnteties.Image image = new BE.DBEnteties.Image(0, installationId, description, remarks, data, imageType);

        try {
            ImageManager.createImage(image);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        txtAreaSketch.setText("");
        txtTitleSketch.setText("");

        canvasImageView.setImage(null);
    }

    public void setSketchTable(){

        ImageModel imageModel = new ImageModel();
        this.imageModel = imageModel;

        columnSketchID.setCellValueFactory(new PropertyValueFactory<BE.DBEnteties.Image, Integer>("Id"));
        columnSketchTitle.setCellValueFactory(new PropertyValueFactory<BE.DBEnteties.Image, String>("Description"));

        ObservableList<BE.DBEnteties.Image> allImages = imageModel.getImageList(1);
        ObservableList<BE.DBEnteties.Image> filteredImages = FXCollections.observableArrayList();

        for (BE.DBEnteties.Image imageListing: allImages){
            if (imageListing.getImageType() == 2){
                filteredImages.add(imageListing);
            }

        }
        try {
            tableSketch.setItems(filteredImages);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
