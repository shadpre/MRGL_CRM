package GUI.Controller;


import BE.DBEnteties.Device;
import BE.DBEnteties.Installation;
import BE.DBEnteties.Network;
import BE.DBEnteties.WiFi;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;
import BLL.Managers.DeviceManager;
import BLL.Managers.ImageManager;
import BLL.Managers.NetworkManager;
import BLL.Managers.WiFiManager;
import GUI.Model.*;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DocumentationViewController extends BaseController implements Initializable {
    @FXML
    private ImageView canvasImageView, billagBillede;
    @FXML
    private StackPane  paneSketch, paneWiFi, paneNetwork, paneAttachment, paneDevice;
    @FXML
    private Button btnExit, btnDeviceShow, btnAddDevice, btnShowSketch, btnSave, btnCreateWiFi, btnShowWiFi, btnSaveNetwork, btnShowNetwork;
    @FXML
    private Button  btnSaveSketch, billagSaveUpdate, btnShowAttachment, billagUpload, btnUploadSketch;
    @FXML
    private TextArea txtDeviceDescription, billagKommentar, txtAreaSketch, txtAreaWiFi, txtAreaNetwork;
    @FXML
    private TextField txtDevicePassword, txtDeviceSubnet, txtDeviceUsername, txtDeviceIp, txtDeviceName, txtTitleSketch, txtBillagNavn;
    @FXML
    private TextField txtWiFiPassword, txtWiFiSSID, txtWiFiName, txtNetworkIP, txtNetworkName, txtNetworkDefault, txtNetworkSubnet;
    @FXML CheckBox txtDevicePOE, networkPOE;
    @FXML
    private TableView<WiFi> tableWiFi;
    @FXML
    private TableColumn<WiFi, String> columnWiFiSSID, columnWiFiName, columnWiFiPassword;
    @FXML
    private TableView<Network> tableNetwork;
    @FXML
    private TableColumn<Network, String> columnNetworkSubnet, columnNetworkName, columnNetworkIP, columnNetworkDefault;
    @FXML
    private TableColumn<Network, Boolean> columnNetworkPOE;
    @FXML
    private TableView<BE.DBEnteties.Image> tableSketch, tableBillag;
    @FXML
    private TableColumn<BE.DBEnteties.Image, String> columnSketchTitle, columnBillagNavn;
    @FXML
    private TableColumn<BE.DBEnteties.Image, Integer> columnSketchID, columnBillagID;
    @FXML
    private TableView<Device> tableDevice;
    @FXML
    private TableColumn<Device, String> columnDeviceIP, columnDevicePassword, columnDeviceUsername, columnDeviceSubnet, columnDeviceName;
    private DeviceManager deviceManager;
    private DeviceModel deviceModel;
    private NetworkModel networkModel;
    private ImageModel imageModel;
    private WiFiModel wiFiModel;

    private Network network;
    private InstallationModel installationModel;
    private Installation selectedInstallation;

    private File imgFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUpDocu(Installation selectedInstallation, InstallationModel installationModel){

        this.selectedInstallation = selectedInstallation;
        this.installationModel = installationModel;

        handleSketch(null);
        setSketchTable();
    }

    public void handleNetwork(ActionEvent actionEvent) {
        turnOffPanes();
        paneNetwork.setVisible(true);
        setTableNetwork();
    }

    public void handleWiFi(ActionEvent actionEvent) {
        turnOffPanes();
        paneWiFi.setVisible(true);
        setTableWiFi();
    }

    public void handleAttachment(ActionEvent actionEvent) {
        turnOffPanes();
        paneAttachment.setVisible(true);
        setTableBillag();
    }

    public void handleSketch(ActionEvent actionEvent) {
        turnOffPanes();
        paneSketch.setVisible(true);
        setSketchTable();
    }

    public void handleDevice(ActionEvent actionEvent) {
        turnOffPanes();
        paneDevice.setVisible(true);
        setTableDevice();
    }

    public void handleExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void handleBillagSaveUpdate(ActionEvent actionEvent) throws IOException {

        if (billagSaveUpdate.getText().equals("Gem Billag")) {

            createBillag();

            billagBillede.setImage(null);
            billagKommentar.setText("");
            txtBillagNavn.setText("");


        } else if (billagSaveUpdate.getText().equals("Opdater Billag")) {

            updateBillag();

            txtBillagNavn.setText("");
            billagKommentar.setText("");
            billagBillede.setImage(null);

            billagSaveUpdate.setText("Gem Billag");
            btnShowAttachment.setText("Vis Billag");
        }
    }

    public void handleBillagUpload(ActionEvent actionEvent) {
        uploadImage();
    }

    public void handleCloseBillag(ActionEvent actionEvent) {
        handleSketch(null);

        tableBillag.getSelectionModel().clearSelection();

        billagKommentar.setText("");
        billagBillede.setImage(null);
        imgFile = null;
        txtBillagNavn.setText("");
        billagSaveUpdate.setText("Gem Billag");
        btnShowAttachment.setText("Vis Billag");
    }

    public void handleShowBillag(ActionEvent actionEvent) {

        txtAreaSketch.setWrapText(true);

        BE.DBEnteties.Image selectedImage = tableBillag.getSelectionModel().getSelectedItem();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(selectedImage.getData());

        Image billagsBillede = new Image(inputStream);

        billagBillede.setImage(billagsBillede);

        txtBillagNavn.setText(selectedImage.getDescription());
        billagKommentar.setText(selectedImage.getRemarks());

        updateFieldsBillag();
        imgFile = null;
    }

    public void handleDeleteBillag(ActionEvent actionEvent) throws ImageNotFoundExeption {

        BE.DBEnteties.Image selectedImage = tableBillag.getSelectionModel().getSelectedItem();

        try{
            ImageManager.deleteImage(selectedImage.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ImageNotFoundExeption e) {
            throw new RuntimeException(e);
        }
        setTableBillag();

    }
    public void handleAddDevice(ActionEvent actionEvent) {

        if (btnAddDevice.getText().equals("Gem Enhed")) {

            creatingDevice();
            turnOffDeviceFields();

        } else if (btnAddDevice.getText().equals("Opdater Enhed")) {

            updatingDevice();
            turnOffDeviceFields();
            btnAddDevice.setText("Gem Enhed");
            btnDeviceShow.setText("Vis Enhed");
        }

    }
    public void handleDeviceShow(ActionEvent actionEvent) {

                // Retrieve the selected device
                Device selectedDevice = tableDevice.getSelectionModel().getSelectedItem();

                turnOffDeviceFields();

                if (selectedDevice.isPOE() == true) {
                    txtDevicePOE.setSelected(true);
                } else if (selectedDevice.isPOE() == false) {
                    txtDevicePOE.setSelected(false);
                }

            updateButtonAndFieldsDevice();
    }

    public void handleCancelDevice(ActionEvent actionEvent) {
        handleSketch(null);

        tableDevice.getSelectionModel().clearSelection();

        turnOffDeviceFields();
        btnAddDevice.setText("Gem Enhed");
        btnDeviceShow.setText("Vis Enhed");
    }

    public void handleDeleteDevice(ActionEvent actionEvent) throws DeviceNotFoundExeption {

        Device selectedDevice = tableDevice.getSelectionModel().getSelectedItem();

        try{
            deviceManager.deleteDevice(selectedDevice.getId());
        } catch (SQLException e) {
            throw new DeviceNotFoundExeption("device not found");
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

    public void handleUploadSketch(ActionEvent actionEvent) {uploadSketch();}

    public void handleFinishSketch(ActionEvent actionEvent) {
        setSketchTable();
        txtAreaSketch.setText("");
        txtTitleSketch.setText("");
        canvasImageView.setImage(null);
    }

    public void handleSaveSketch(ActionEvent actionEvent) throws IOException {


    if(btnSaveSketch.getText().equals("Gem Tegning")) {

        saveSketch();
        setSketchTable();
    }
    else if(btnSaveSketch.getText().equals("Opdater Tegning")){
        updateSketch();
        setSketchTable();
        btnSaveSketch.setText("Gem Tegning");
        btnShowSketch.setText("Vis Tegning");
    }
    }

    public void handleShowSketch(ActionEvent actionEvent) {
        
        imgFile = null;

        txtAreaSketch.setWrapText(true);

        BE.DBEnteties.Image selectedImage = tableSketch.getSelectionModel().getSelectedItem();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(selectedImage.getData());

        Image sketchForShow = new Image(inputStream);

        canvasImageView.setImage(sketchForShow);

        txtTitleSketch.setText(selectedImage.getDescription());
        txtAreaSketch.setText(selectedImage.getRemarks());

        updateButtonsSketch();
    }

    public void handleRemoveSketch(ActionEvent actionEvent)  throws ImageNotFoundExeption{

        BE.DBEnteties.Image selectedImage = tableSketch.getSelectionModel().getSelectedItem();

        try{
            ImageManager.deleteImage(selectedImage.getId());

        } catch (SQLException e) {

            throw new RuntimeException(e);
        } catch (ImageNotFoundExeption e)
        {
            throw new RuntimeException(e);
        }
        setSketchTable();
    }
    public void handleCreateWiFi(ActionEvent actionEvent) {

        if (btnCreateWiFi.getText().equals("Gem WiFi")) {

            createWiFi();

            txtWiFiName.setText("");
            txtAreaWiFi.setText("");
            txtWiFiPassword.setText("");
            txtWiFiSSID.setText("");


        } else if (btnCreateWiFi.getText().equals("Opdater WiFi")) {

            updateWiFi();

            txtWiFiSSID.setText("");
            txtWiFiName.setText("");
            txtWiFiPassword.setText("");
            txtAreaWiFi.setText("");

            btnCreateWiFi.setText("Gem Wifi");
            btnShowWiFi.setText("Vis WiFi");
        }
    }

    public void handleCancelWiFi(ActionEvent actionEvent) {
        handleSketch(null);

        tableWiFi.getSelectionModel().clearSelection();

        txtWiFiName.setText("");
        txtAreaWiFi.setText("");
        txtWiFiPassword.setText("");
        txtWiFiSSID.setText("");
        btnCreateWiFi.setText("Gem WiFi");
        btnShowWiFi.setText("Vis WiFi");
    }

    public void handleShowWiFi(ActionEvent actionEvent) {
        WiFi selectedWiFi = tableWiFi.getSelectionModel().getSelectedItem();

        // Populate the UI elements with the selected device's data
        txtWiFiSSID.setText(selectedWiFi.getSSID());
        txtWiFiName.setText(selectedWiFi.getDescription());
        txtWiFiPassword.setText(selectedWiFi.getPSK());
        txtAreaWiFi.setText(selectedWiFi.getRemarks());


        updateFieldsWiFi();
    }

    public void handleDeleteWiFi(ActionEvent actionEvent) throws WiFiNotFoundExeption{
        WiFi selectedWiFi = tableWiFi.getSelectionModel().getSelectedItem();

        try{
            WiFiManager.deleteWiFi(selectedWiFi.getId());
        } catch (WiFiNotFoundExeption e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setTableWiFi();
    }

    public void handleSaveNetwork(ActionEvent actionEvent) {
        if (btnSaveNetwork.getText().equals("Gem Netværk")) {

            createNetwork();
            turnOffNetworkFields();

        } else if (btnSaveNetwork.getText().equals("Opdater Netværk")) {

            updateNetwork();
            turnOffNetworkFields();
            btnSaveNetwork.setText("Gem Netværk");
            btnShowNetwork.setText("Vis Netværk");
        }

    }

    public void handleCancelNetwork(ActionEvent actionEvent) {
        handleSketch(null);
        tableNetwork.getSelectionModel().clearSelection();
        turnOffNetworkFields();
        btnSaveNetwork.setText("Gem Netværk");
        btnShowNetwork.setText("Vis Netværk");
    }

    public void handleShowNetwork(ActionEvent actionEvent) {
        Network selectedNetwork = tableNetwork.getSelectionModel().getSelectedItem();

        // Populate the UI elements with the selected network's data
        txtNetworkIP.setText(selectedNetwork.getNetworkIP());
        txtNetworkName.setText(selectedNetwork.getDescription());
        txtNetworkDefault.setText(selectedNetwork.getDefaultGateway());
        txtNetworkSubnet.setText(selectedNetwork.getSubnetMask());
        networkPOE.setSelected(selectedNetwork.isHasPOE());

        updateFieldsNetwork();
    }

    public void handleDeleteNetwork(ActionEvent actionEvent) throws NetworkNotFoundExeption {
        Network selectedNetwork = tableNetwork.getSelectionModel().getSelectedItem();

        try {
            NetworkManager.deleteNetwork(selectedNetwork.getId());
        } catch (NetworkNotFoundExeption e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTableNetwork();
    }

    public void createBillag() throws IOException {

        billagKommentar.setWrapText(true);

        int installationId = selectedInstallation.getId();
        String description = txtBillagNavn.getText();
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

    setTableBillag();

    }

    public void updateBillag() throws IOException {

        billagKommentar.setWrapText(true);
        BE.DBEnteties.Image selectedImage = tableBillag.getSelectionModel().getSelectedItem();

        String description = txtBillagNavn.getText();
        String remarks = billagKommentar.getText();
        int imageType = selectedImage.getImageType();
        byte[] data = selectedImage.getData();

        if (imgFile != null) {
            BufferedImage bImage = ImageIO.read(imgFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            data = bos.toByteArray();
        }

        BE.DBEnteties.Image image = new BE.DBEnteties.Image(selectedImage.getId(), selectedImage.getInstallationId(), description, remarks, data, imageType);

        try {

            ImageManager.updateImage(image);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }


        billagKommentar.setText("");

        billagBillede.setImage(null);


    setTableBillag();
    }

    public void updateFieldsBillag(){

        if (btnShowAttachment.getText().equals("Vis Billag")) {

            // Run current method
            billagSaveUpdate.setText("Opdater Billag");
            btnShowAttachment.setText("Stop Visning");

        }

        else if (btnShowAttachment.getText().equals("Stop Visning")) {

            tableBillag.getSelectionModel().clearSelection();

            txtBillagNavn.setText("");
            billagKommentar.setText("");

            billagBillede.setImage(null);

            billagSaveUpdate.setText("Gem Billag");
            btnShowAttachment.setText("Vis Billag");
        }

    }

    public void setTableBillag(){

        ImageModel imageModel = new ImageModel();
        this.imageModel = imageModel;

        columnBillagID.setCellValueFactory(new PropertyValueFactory<BE.DBEnteties.Image, Integer>("Id"));
        columnBillagNavn.setCellValueFactory(new PropertyValueFactory<BE.DBEnteties.Image, String>("Description"));

        ObservableList<BE.DBEnteties.Image> allImages = imageModel.getImageList(selectedInstallation.getId());
        ObservableList<BE.DBEnteties.Image> filteredImages = FXCollections.observableArrayList();

        for (BE.DBEnteties.Image imageListing: allImages){
            if (imageListing.getImageType() == 1){
                filteredImages.add(imageListing);
            }
        }
        try {
            tableBillag.setItems(filteredImages);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void creatingDevice(){

        // Get Device Information
        String description = txtDeviceName.getText();
        String remarks = txtDeviceDescription.getText();
        String IP = txtDeviceIp.getText();
        String password = txtDevicePassword.getText();
        String subnetMask = txtDeviceSubnet.getText();
        int installationID = selectedInstallation.getId();
        String userName = txtDeviceUsername.getText();
        Boolean isPOE = false;

        if (txtDevicePOE.isSelected() == true) {
             isPOE = true;
        }

        Device device = new Device(0, installationID, description, remarks, IP, subnetMask, userName, password, isPOE);


        try {

            deviceManager.createDevice(device);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        setTableDevice();
    }
    public void setTableDevice(){

        DeviceModel deviceModel = new DeviceModel();
        this.deviceModel = deviceModel;

        columnDeviceIP.setCellValueFactory(new PropertyValueFactory<Device, String>("IP"));
        columnDeviceSubnet.setCellValueFactory(new PropertyValueFactory<Device, String>("SubnetMask"));
        columnDeviceUsername.setCellValueFactory(new PropertyValueFactory<Device, String>("UserName"));
        columnDevicePassword.setCellValueFactory(new PropertyValueFactory<Device, String>("Password"));
        columnDeviceName.setCellValueFactory(new PropertyValueFactory<Device, String>("Description"));

        try {
            tableDevice.setItems(DeviceModel.getDeviceList(selectedInstallation.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updatingDevice(){

        // Get Device Information

        Device selectedDevice = tableDevice.getSelectionModel().getSelectedItem();

        String description = txtDeviceName.getText();
        String remarks = txtDeviceDescription.getText();
        String IP = txtDeviceIp.getText();
        String password = txtDevicePassword.getText();
        String subnetMask = txtDeviceSubnet.getText();
        int installationID = selectedDevice.getInstallationId();
        String userName = txtDeviceUsername.getText();
        Boolean isPOE = false;
        int Id = selectedDevice.getId();

        if (txtDevicePOE.isSelected()) {
             isPOE = true;
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

            turnOffDeviceFields();
            btnAddDevice.setText("Gem Enhed");
            btnDeviceShow.setText("Vis Enhed");
        }
    }

    public void updateButtonsSketch(){


            System.out.println("Button Text: " + btnSaveSketch.getText()); // Debug output
            if (btnShowSketch.getText().equals("Vis Tegning")) {
                // Run current method
                btnSaveSketch.setText("Opdater Tegning");
                btnShowSketch.setText("Stop Visning");
            } else if (btnShowSketch.getText().equals("Stop Visning")) {
                tableSketch.getSelectionModel().clearSelection();
                txtTitleSketch.setText("");
                txtAreaSketch.setText("");
                canvasImageView.setImage(null);
                btnSaveSketch.setText("Gem Tegning");
                btnShowSketch.setText("Vis Tegning");
            }

    }
    public void saveSketch() {
        if (canvasImageView != null) {

            txtAreaSketch.setWrapText(true);

            int installationId = selectedInstallation.getId();
            String description = txtTitleSketch.getText();
            String remarks = txtAreaSketch.getText();
            int imageType = 2;

            javafx.scene.image.Image fxImage = canvasImageView.getImage();

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            System.out.println("Image dimensions: " + width + "x" + height);

            byte[] data;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                if (!ImageIO.write(bufferedImage, "png", outputStream)) {
                    throw new IOException("Failed to write the image as Png");
                }
                data = outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            if (data.length == 0) {
                System.out.println("No data found in the converted image.");
                return;
            }

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
    }



    public void updateSketch() throws IOException {

        txtAreaSketch.setWrapText(true);
        BE.DBEnteties.Image selectedImage = tableSketch.getSelectionModel().getSelectedItem();

        String description = txtTitleSketch.getText();
        String remarks = txtAreaSketch.getText();
        int imageType = selectedImage.getImageType();
        javafx.scene.image.Image fxImage = canvasImageView.getImage();

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        System.out.println("Image dimensions: " + width + "x" + height);

        byte[] data;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            if (!ImageIO.write(bufferedImage, "png", outputStream)) {
                throw new IOException("Failed to write the image as Png");
            }
            data = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (imgFile != null) {

            BufferedImage bImage = ImageIO.read(imgFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            data = bos.toByteArray();
        }

        BE.DBEnteties.Image image = new BE.DBEnteties.Image(selectedImage.getId(), selectedImage.getInstallationId(), description, remarks, data, imageType);

        try {

            ImageManager.updateImage(image);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }


        txtAreaSketch.setText("");
        txtTitleSketch.setText("");

        canvasImageView.setImage(null);


        setSketchTable();
        imgFile = null;
    }

    public void setSketchTable(){

        ImageModel imageModel = new ImageModel();
        this.imageModel = imageModel;

        columnSketchID.setCellValueFactory(new PropertyValueFactory<BE.DBEnteties.Image, Integer>("Id"));
        columnSketchTitle.setCellValueFactory(new PropertyValueFactory<BE.DBEnteties.Image, String>("Description"));

        ObservableList<BE.DBEnteties.Image> allImages = imageModel.getImageList(selectedInstallation.getId());
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

    public void uploadImage() {

        FileChooser fc = new FileChooser();
        Stage stage = (Stage) billagBillede.getScene().getWindow();
        imgFile = fc.showOpenDialog(stage);

        if (imgFile != null) {
            javafx.scene.image.Image image = new Image(imgFile.getAbsolutePath());
            billagBillede.setImage(image);
        }
    }

    public void uploadSketch(){
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) btnUploadSketch.getScene().getWindow();
        imgFile = fc.showOpenDialog(stage);

        if (imgFile != null) {
            javafx.scene.image.Image image = new Image(imgFile.getAbsolutePath());
            canvasImageView.setImage(image);
        }
    }
    public void createWiFi(){

        // Get User Information
        String description = txtWiFiName.getText();
        String remarks = txtAreaWiFi.getText();
        String PSK = txtWiFiPassword.getText();
        String SSID = txtWiFiSSID.getText();
        int installationID = selectedInstallation.getId();



        WiFi wifi = new WiFi(0, installationID, description, remarks, SSID, PSK);

        try {

            WiFiManager.createWiFi(wifi);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        setTableWiFi();

    }
    public void updateWiFi(){

        WiFi selectedWiFi = tableWiFi.getSelectionModel().getSelectedItem();

        String description = txtWiFiName.getText();
        String remarks = txtAreaWiFi.getText();
        String PSK = txtWiFiPassword.getText();
        String SSID = txtWiFiSSID.getText();
        int installationID = selectedWiFi.getInstallationId();
        int Id = selectedWiFi.getId();

        WiFi wifi = new WiFi(Id, installationID, description, remarks, SSID, PSK);


        try {

            WiFiManager.updateWiFi(wifi);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        setTableWiFi();
    }

    public void updateFieldsWiFi(){

        if (btnShowWiFi.getText().equals("Vis WiFi")) {
            // Run current method
            btnCreateWiFi.setText("Opdater WiFi");
            btnShowWiFi.setText("Stop Visning");

        } else if (btnShowWiFi.getText().equals("Stop Visning")) {

            tableDevice.getSelectionModel().clearSelection();

            txtAreaWiFi.setText("");
            txtWiFiPassword.setText("");
            txtWiFiName.setText("");
            txtWiFiSSID.setText("");;
            btnCreateWiFi.setText("Gem WiFi");
            btnShowWiFi.setText("Vis WiFi");
        }

    }

    public void setTableWiFi(){


        WiFiModel wiFiModel = new WiFiModel();
        this.wiFiModel = new WiFiModel();


        columnWiFiName.setCellValueFactory(new PropertyValueFactory<WiFi, String>("Description"));
        columnWiFiPassword.setCellValueFactory(new PropertyValueFactory<WiFi, String>("PSK"));
        columnWiFiSSID.setCellValueFactory(new PropertyValueFactory<WiFi, String>("SSID"));

        try {
            tableWiFi.setItems(WiFiModel.getWiFis(selectedInstallation.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void createNetwork(){

        String description = txtNetworkName.getText();
        String remarks = txtAreaNetwork.getText();
        String networkIP = txtNetworkIP.getText();
        String subnetMask = txtNetworkSubnet.getText();
        String defaultGateway = txtNetworkDefault.getText();
        boolean hasPOE = networkPOE.isSelected();
        int installationID = selectedInstallation.getId();

        Network network = new Network(0, installationID, description, remarks, networkIP, subnetMask, defaultGateway, hasPOE);

        try {
            NetworkManager.createNetwork(network);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setTableNetwork();

    }
    public void updateNetwork(){

            Network selectedNetwork = tableNetwork.getSelectionModel().getSelectedItem();

            String description = txtNetworkName.getText();
            String remarks = txtAreaNetwork.getText();
            String networkIP = txtNetworkIP.getText();
            String subnetMask = txtNetworkSubnet.getText();
            String defaultGateway = txtNetworkDefault.getText();
            int installationID = selectedNetwork.getInstallationId();
            int id = selectedNetwork.getId();
            Boolean hasPOE = networkPOE.isSelected();

            Network network = new Network(id, installationID, description, remarks, networkIP, subnetMask, defaultGateway, hasPOE);

            try {
                NetworkManager.updateNetwork(network);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            setTableNetwork();
    }

    public void updateFieldsNetwork(){

            if (btnShowNetwork.getText().equals("Vis Netværk")) {
                // Run current method
                btnSaveNetwork.setText("Opdater Netværk");
                btnShowNetwork.setText("Stop Visning");
            } else if (btnShowNetwork.getText().equals("Stop Visning")) {
                tableNetwork.getSelectionModel().clearSelection();
                turnOffNetworkFields();
                btnSaveNetwork.setText("Gem Netværk");
                btnShowNetwork.setText("Vis Netværk");
            }
    }


    public void setTableNetwork() {

            NetworkModel networkModel = new NetworkModel();
            this.networkModel = new NetworkModel();

            columnNetworkName.setCellValueFactory(new PropertyValueFactory<Network, String>("Description"));
            columnNetworkIP.setCellValueFactory(new PropertyValueFactory<Network, String>("NetworkIP"));
            columnNetworkSubnet.setCellValueFactory(new PropertyValueFactory<Network, String>("SubnetMask"));
            columnNetworkDefault.setCellValueFactory(new PropertyValueFactory<Network, String>("DefaultGateway"));
            columnNetworkPOE.setCellValueFactory(new PropertyValueFactory<Network, Boolean>("hasPOE"));

            try {
                tableNetwork.setItems(NetworkModel.getNetworks(selectedInstallation.getId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void turnOffPanes(){
        paneAttachment.setVisible(false);
        paneDevice.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneSketch.setVisible(false);
        }

        public void turnOffNetworkFields(){
            txtAreaNetwork.setText("");
            txtNetworkIP.setText("");
            txtNetworkName.setText("");
            txtNetworkDefault.setText("");
            txtNetworkSubnet.setText("");
            networkPOE.setSelected(false);
        }

        public void turnOffDeviceFields(){
            txtDeviceDescription.setText("");
            txtDeviceIp.setText("");
            txtDevicePassword.setText("");
            txtDeviceSubnet.setText("");
            txtDeviceUsername.setText("");
            txtDeviceName.setText("");
            txtDevicePOE.setSelected(false);
        }

}
