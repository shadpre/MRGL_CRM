package GUI.Controller;


import BE.DBEnteties.Device;
import BE.DBEnteties.Interfaces.*;
import BE.DBEnteties.Network;
import BE.DBEnteties.WiFi;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundException;
import BE.Exptions.NotFoundExeptions.ImageNotFoundException;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundException;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundException;
import BLL.Interfaces.IValidationResult;
import GUI.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    CheckBox txtDevicePOE, networkPOE;
    @FXML
    private ImageView canvasImageView, billagBillede;
    @FXML
    private StackPane paneSketch, paneWiFi, paneNetwork, paneAttachment, paneDevice;
    @FXML
    private Button btnExit, btnDeviceShow, btnAddDevice, btnShowSketch, btnSave, btnCreateWiFi, btnShowWiFi, btnSaveNetwork, btnShowNetwork;
    @FXML
    private Button btnSaveSketch, billagSaveUpdate, btnShowAttachment, billagUpload, btnUploadSketch;
    @FXML
    private TextArea txtDeviceDescription, billagKommentar, txtAreaSketch, txtAreaWiFi, txtAreaNetwork;
    @FXML
    private TextField txtDevicePassword, txtDeviceSubnet, txtDeviceUsername, txtDeviceIp, txtDeviceName, txtTitleSketch, txtBillagNavn;
    @FXML
    private TextField txtWiFiPassword, txtWiFiSSID, txtWiFiName, txtNetworkIP, txtNetworkName, txtNetworkDefault, txtNetworkSubnet;
    @FXML
    private TableView<IWiFi> tableWiFi;
    @FXML
    private TableColumn<IWiFi, String> columnWiFiSSID, columnWiFiName, columnWiFiPassword;
    @FXML
    private TableView<INetwork> tableNetwork;
    @FXML
    private TableColumn<INetwork, String> columnNetworkSubnet, columnNetworkName, columnNetworkIP, columnNetworkDefault;
    @FXML
    private TableColumn<INetwork, Boolean> columnNetworkPOE;
    @FXML
    private TableView<IImage> tableSketch, tableBillag;
    @FXML
    private TableColumn<IImage, String> columnSketchTitle, columnBillagNavn;
    @FXML
    private TableColumn<IImage, Integer> columnSketchID, columnBillagID;
    @FXML
    private TableView<IDevice> tableDevice;
    @FXML
    private TableColumn<IDevice, String> columnDeviceIP, columnDevicePassword, columnDeviceUsername, columnDeviceSubnet, columnDeviceName;
    private final DeviceModel deviceModel = new DeviceModel();
    private final NetworkModel networkModel = new NetworkModel();
    private final ImageModel imageModel = new ImageModel();
    private final WiFiModel wiFiModel = new WiFiModel();
    private final InstallationModel installationModel = new InstallationModel();
    private final ValidationModel validationModel = new ValidationModel();
    private IInstallation selectedInstallation;
    private File imgFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUpDocu(IInstallation selectedInstallation, InstallationModel installationModel) {

        this.selectedInstallation = selectedInstallation;

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
        IImage selectedImage = tableBillag.getSelectionModel().getSelectedItem();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(selectedImage.getData());

        Image billagsBillede = new Image(inputStream);

        billagBillede.setImage(billagsBillede);

        txtBillagNavn.setText(selectedImage.getDescription());
        billagKommentar.setText(selectedImage.getRemarks());

        updateFieldsBillag();
        imgFile = null;
    }

    public void handleDeleteBillag(ActionEvent actionEvent) throws ImageNotFoundException {
        IImage selectedImage = tableBillag.getSelectionModel().getSelectedItem();
        try {
            imageModel.deleteImage(selectedImage.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ImageNotFoundException e) {
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
        IDevice selectedDevice = tableDevice.getSelectionModel().getSelectedItem();
        turnOffDeviceFields();
        txtDevicePOE.setSelected(selectedDevice.isPOE());
        updateButtonAndFieldsDevice();
    }

    public void handleCancelDevice(ActionEvent actionEvent) {
        handleSketch(null);
        tableDevice.getSelectionModel().clearSelection();
        turnOffDeviceFields();
        btnAddDevice.setText("Gem Enhed");
        btnDeviceShow.setText("Vis Enhed");
    }

    public void handleDeleteDevice(ActionEvent actionEvent) throws DeviceNotFoundException {

        IDevice selectedDevice = tableDevice.getSelectionModel().getSelectedItem();
        try {
            deviceModel.deleteDevice(selectedDevice.getId());
        } catch (SQLException e) {
            throw new DeviceNotFoundException("device not found");
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

    public void setCanvasImage(Image canvasImage) {
        canvasImageView.setImage(canvasImage);
    }

    public void handleUploadSketch(ActionEvent actionEvent) {
        uploadSketch();
    }

    public void handleFinishSketch(ActionEvent actionEvent) {
        setSketchTable();
        txtAreaSketch.setText("");
        txtTitleSketch.setText("");
        canvasImageView.setImage(null);
    }

    public void handleSaveSketch(ActionEvent actionEvent) throws IOException {

        if (btnSaveSketch.getText().equals("Gem Tegning")) {
            saveSketch();
            setSketchTable();
        } else if (btnSaveSketch.getText().equals("Opdater Tegning")) {
            updateSketch();
            setSketchTable();
            btnSaveSketch.setText("Gem Tegning");
            btnShowSketch.setText("Vis Tegning");
        }
    }

    public void handleShowSketch(ActionEvent actionEvent) {

        imgFile = null;
        txtAreaSketch.setWrapText(true);

        IImage selectedImage = tableSketch.getSelectionModel().getSelectedItem();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(selectedImage.getData());

        Image sketchForShow = new Image(inputStream);

        canvasImageView.setImage(sketchForShow);

        txtTitleSketch.setText(selectedImage.getDescription());
        txtAreaSketch.setText(selectedImage.getRemarks());

        updateButtonsSketch();
    }

    public void handleRemoveSketch(ActionEvent actionEvent) throws ImageNotFoundException {

        IImage selectedImage = tableSketch.getSelectionModel().getSelectedItem();

        try {
            imageModel.deleteImage(selectedImage.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ImageNotFoundException e) {
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
        IWiFi selectedWiFi = tableWiFi.getSelectionModel().getSelectedItem();

        // Populate the UI elements with the selected device's data
        txtWiFiSSID.setText(selectedWiFi.getSSID());
        txtWiFiName.setText(selectedWiFi.getDescription());
        txtWiFiPassword.setText(selectedWiFi.getPSK());
        txtAreaWiFi.setText(selectedWiFi.getRemarks());

        updateFieldsWiFi();
    }

    public void handleDeleteWiFi(ActionEvent actionEvent) throws WiFiNotFoundException {
        IWiFi selectedWiFi = tableWiFi.getSelectionModel().getSelectedItem();

        try {
            wiFiModel.deleteWiFi(selectedWiFi.getId());
        } catch (WiFiNotFoundException e) {
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
        INetwork selectedNetwork = tableNetwork.getSelectionModel().getSelectedItem();

        // Populate the UI elements with the selected network's data
        txtNetworkIP.setText(selectedNetwork.getNetworkIP());
        txtNetworkName.setText(selectedNetwork.getDescription());
        txtNetworkDefault.setText(selectedNetwork.getDefaultGateway());
        txtNetworkSubnet.setText(selectedNetwork.getSubnetMask());
        networkPOE.setSelected(selectedNetwork.isHasPOE());

        updateFieldsNetwork();
    }

    public void handleDeleteNetwork(ActionEvent actionEvent) throws NetworkNotFoundException {
        INetwork selectedNetwork = tableNetwork.getSelectionModel().getSelectedItem();

        try {
            networkModel.deleteNetwork(selectedNetwork.getId());
        } catch (NetworkNotFoundException e) {
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
        ImageIO.write(bImage, "jpg", bos);
        byte[] data = bos.toByteArray();

        IImage image = new BE.DBEnteties.Image(0, installationId, description, remarks, data, imageType);

        try {
            IValidationResult vr = validate(image);
            if (!vr.hasNoError()) return;
            imageModel.createImage(image);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        billagKommentar.setText("");
        billagBillede.setImage(null);
        setTableBillag();
    }

    public void updateBillag() throws IOException {

        billagKommentar.setWrapText(true);
        IImage selectedImage = tableBillag.getSelectionModel().getSelectedItem();

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

        IImage image = new BE.DBEnteties.Image(selectedImage.getId(), selectedImage.getInstallationId(), description, remarks, data, imageType);

        try {
            IValidationResult vr = validate(image);
            if (!vr.hasNoError()) return;
            imageModel.updateImage(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        billagKommentar.setText("");
        billagBillede.setImage(null);
        setTableBillag();
    }

    public void updateFieldsBillag() {

        if (btnShowAttachment.getText().equals("Vis Billag")) {
            // Run current method
            billagSaveUpdate.setText("Opdater Billag");
            btnShowAttachment.setText("Stop Visning");

        } else if (btnShowAttachment.getText().equals("Stop Visning")) {
            tableBillag.getSelectionModel().clearSelection();

            txtBillagNavn.setText("");
            billagKommentar.setText("");

            billagBillede.setImage(null);

            billagSaveUpdate.setText("Gem Billag");
            btnShowAttachment.setText("Vis Billag");
        }
    }

    public void setTableBillag() {

        columnBillagID.setCellValueFactory(new PropertyValueFactory<IImage, Integer>("Id"));
        columnBillagNavn.setCellValueFactory(new PropertyValueFactory<IImage, String>("Description"));

        ObservableList<IImage> allImages = imageModel.getImageList(selectedInstallation.getId());
        ObservableList<IImage> filteredImages = FXCollections.observableArrayList();

        for (IImage imageListing : allImages) {
            if (imageListing.getImageType() == 1) {
                filteredImages.add(imageListing);
            }
        }
        try {
            tableBillag.setItems(filteredImages);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void creatingDevice() {

        // Get Device Information
        String description = txtDeviceName.getText();
        String remarks = txtDeviceDescription.getText();
        String IP = txtDeviceIp.getText();
        String password = txtDevicePassword.getText();
        String subnetMask = txtDeviceSubnet.getText();
        int installationID = selectedInstallation.getId();
        String userName = txtDeviceUsername.getText();
        boolean isPOE = txtDevicePOE.isSelected();

        IDevice device = new Device(0, installationID, description, remarks, IP, subnetMask, userName, password, isPOE);

        try {
            IValidationResult vr = validate(device);
            if (!vr.hasNoError()) return;
            deviceModel.createDevice(device);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setTableDevice();
    }

    public void setTableDevice() {

        columnDeviceIP.setCellValueFactory(new PropertyValueFactory<IDevice, String>("IP"));
        columnDeviceSubnet.setCellValueFactory(new PropertyValueFactory<IDevice, String>("SubnetMask"));
        columnDeviceUsername.setCellValueFactory(new PropertyValueFactory<IDevice, String>("UserName"));
        columnDevicePassword.setCellValueFactory(new PropertyValueFactory<IDevice, String>("Password"));
        columnDeviceName.setCellValueFactory(new PropertyValueFactory<IDevice, String>("Description"));

        try {
            tableDevice.setItems(deviceModel.getDeviceList(selectedInstallation.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatingDevice() {

        // Get Device Information
        IDevice selectedDevice = tableDevice.getSelectionModel().getSelectedItem();

        String description = txtDeviceName.getText();
        String remarks = txtDeviceDescription.getText();
        String IP = txtDeviceIp.getText();
        String password = txtDevicePassword.getText();
        String subnetMask = txtDeviceSubnet.getText();
        int installationID = selectedDevice.getInstallationId();
        String userName = txtDeviceUsername.getText();
        boolean isPOE = false;
        int Id = selectedDevice.getId();

        if (txtDevicePOE.isSelected()) {
            isPOE = true;
        }

        IDevice device = new Device(Id, installationID, description, remarks, IP, subnetMask, userName, password, isPOE);

        try {
            IValidationResult vr = validate(device);
            if (!vr.hasNoError()) return;
            deviceModel.updateDevice(device);
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

    public void updateButtonsSketch() {
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
                IValidationResult vr = validate(image);
                if (!vr.hasNoError()) return;
                imageModel.createImage(image);
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
        IImage selectedImage = tableSketch.getSelectionModel().getSelectedItem();

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

        IImage image = new BE.DBEnteties.Image(selectedImage.getId(), selectedImage.getInstallationId(), description, remarks, data, imageType);

        try {
            IValidationResult vr = validate(image);
            if (!vr.hasNoError()) return;
            imageModel.updateImage(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        txtAreaSketch.setText("");
        txtTitleSketch.setText("");

        canvasImageView.setImage(null);

        setSketchTable();
        imgFile = null;
    }

    public void setSketchTable() {

        columnSketchID.setCellValueFactory(new PropertyValueFactory<IImage, Integer>("Id"));
        columnSketchTitle.setCellValueFactory(new PropertyValueFactory<IImage, String>("Description"));

        ObservableList<IImage> allImages = imageModel.getImageList(selectedInstallation.getId());
        ObservableList<IImage> filteredImages = FXCollections.observableArrayList();

        for (IImage imageListing : allImages) {
            if (imageListing.getImageType() == 2) {
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

    public void uploadSketch() {
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) btnUploadSketch.getScene().getWindow();
        imgFile = fc.showOpenDialog(stage);

        if (imgFile != null) {
            javafx.scene.image.Image image = new Image(imgFile.getAbsolutePath());
            canvasImageView.setImage(image);
        }
    }

    public void createWiFi() {

        // Get User Information
        String description = txtWiFiName.getText();
        String remarks = txtAreaWiFi.getText();
        String PSK = txtWiFiPassword.getText();
        String SSID = txtWiFiSSID.getText();
        int installationID = selectedInstallation.getId();

        IWiFi wifi = new WiFi(0, installationID, description, remarks, SSID, PSK);

        try {
            IValidationResult vr = validate(wifi);
            if (!vr.hasNoError()) return;
            wiFiModel.createWiFi(wifi);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setTableWiFi();
    }

    public void updateWiFi() {

        IWiFi selectedWiFi = tableWiFi.getSelectionModel().getSelectedItem();

        String description = txtWiFiName.getText();
        String remarks = txtAreaWiFi.getText();
        String PSK = txtWiFiPassword.getText();
        String SSID = txtWiFiSSID.getText();
        int installationID = selectedWiFi.getInstallationId();
        int Id = selectedWiFi.getId();

        WiFi wifi = new WiFi(Id, installationID, description, remarks, SSID, PSK);


        try {
            IValidationResult vr = validate(wifi);
            if (!vr.hasNoError()) return;

            wiFiModel.updateWiFi(wifi);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setTableWiFi();
    }

    public void updateFieldsWiFi() {

        if (btnShowWiFi.getText().equals("Vis WiFi")) {
            // Run current method
            btnCreateWiFi.setText("Opdater WiFi");
            btnShowWiFi.setText("Stop Visning");

        } else if (btnShowWiFi.getText().equals("Stop Visning")) {

            tableDevice.getSelectionModel().clearSelection();

            txtAreaWiFi.setText("");
            txtWiFiPassword.setText("");
            txtWiFiName.setText("");
            txtWiFiSSID.setText("");
            btnCreateWiFi.setText("Gem WiFi");
            btnShowWiFi.setText("Vis WiFi");
        }
    }

    public void setTableWiFi() {
        columnWiFiName.setCellValueFactory(new PropertyValueFactory<IWiFi, String>("Description"));
        columnWiFiPassword.setCellValueFactory(new PropertyValueFactory<IWiFi, String>("PSK"));
        columnWiFiSSID.setCellValueFactory(new PropertyValueFactory<IWiFi, String>("SSID"));

        try {
            tableWiFi.setItems(wiFiModel.getWiFis(selectedInstallation.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createNetwork() {

        String description = txtNetworkName.getText();
        String remarks = txtAreaNetwork.getText();
        String networkIP = txtNetworkIP.getText();
        String subnetMask = txtNetworkSubnet.getText();
        String defaultGateway = txtNetworkDefault.getText();
        boolean hasPOE = networkPOE.isSelected();
        int installationID = selectedInstallation.getId();

        Network network = new Network(0, installationID, description, remarks, networkIP, subnetMask, defaultGateway, hasPOE);

        try {
            IValidationResult vr = validate(network);
            if (!vr.hasNoError()) return;
            networkModel.createNetwork(network);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setTableNetwork();
    }

    public void updateNetwork() {

        INetwork selectedNetwork = tableNetwork.getSelectionModel().getSelectedItem();

        String description = txtNetworkName.getText();
        String remarks = txtAreaNetwork.getText();
        String networkIP = txtNetworkIP.getText();
        String subnetMask = txtNetworkSubnet.getText();
        String defaultGateway = txtNetworkDefault.getText();
        int installationID = selectedNetwork.getInstallationId();
        int id = selectedNetwork.getId();
        boolean hasPOE = networkPOE.isSelected();

        Network network = new Network(id, installationID, description, remarks, networkIP, subnetMask, defaultGateway, hasPOE);

        try {
            IValidationResult vr = validate(network);
            if (!vr.hasNoError()) return;
            networkModel.updateNetwork(network);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setTableNetwork();
    }

    public void updateFieldsNetwork() {

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

        columnNetworkName.setCellValueFactory(new PropertyValueFactory<INetwork, String>("Description"));
        columnNetworkIP.setCellValueFactory(new PropertyValueFactory<INetwork, String>("NetworkIP"));
        columnNetworkSubnet.setCellValueFactory(new PropertyValueFactory<INetwork, String>("SubnetMask"));
        columnNetworkDefault.setCellValueFactory(new PropertyValueFactory<INetwork, String>("DefaultGateway"));
        columnNetworkPOE.setCellValueFactory(new PropertyValueFactory<INetwork, Boolean>("hasPOE"));

        try {
            tableNetwork.setItems(networkModel.getNetworks(selectedInstallation.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void turnOffPanes() {
        paneAttachment.setVisible(false);
        paneDevice.setVisible(false);
        paneWiFi.setVisible(false);
        paneNetwork.setVisible(false);
        paneSketch.setVisible(false);
    }

    public void turnOffNetworkFields() {
        txtAreaNetwork.setText("");
        txtNetworkIP.setText("");
        txtNetworkName.setText("");
        txtNetworkDefault.setText("");
        txtNetworkSubnet.setText("");
        networkPOE.setSelected(false);
    }

    public void turnOffDeviceFields() {
        txtDeviceDescription.setText("");
        txtDeviceIp.setText("");
        txtDevicePassword.setText("");
        txtDeviceSubnet.setText("");
        txtDeviceUsername.setText("");
        txtDeviceName.setText("");
        txtDevicePOE.setSelected(false);
    }

    private IValidationResult validate(IImage image) {
        IValidationResult result = validationModel.validate(image);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Description":
                        txtBillagNavn.getStyleClass().add("invalid");
                        break;
                    case "Remarks":
                        billagKommentar.getStyleClass().add("invalid");
                        break;
                    case "ImageType":
                        throw new RuntimeException("Wrong ImageType");
                }
            }
        }
        return result;
    }

    private IValidationResult validate(IDevice device) {
        IValidationResult result = validationModel.validate(device);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Description":
                        txtDeviceName.getStyleClass().add("invalid");
                        break;
                    case "Remarks":
                        txtDeviceDescription.getStyleClass().add("invalid");
                        break;
                    case "IP":
                        txtDeviceIp.getStyleClass().add("invalid");
                        break;
                    case "SubnetMask":
                        txtDeviceSubnet.getStyleClass().add("invalid");
                        break;
                    case "UserName":
                        txtDeviceUsername.getStyleClass().add("invalid");
                        break;
                    case "Password":
                        txtDevicePassword.getStyleClass().add("invalid");
                        break;
                }
            }
        }
        return result;
    }

    private IValidationResult validate(IWiFi wiFi) {
        IValidationResult result = validationModel.validate(wiFi);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Description":
                        txtWiFiName.getStyleClass().add("invalid");
                        break;
                    case "Remarks":
                        txtAreaWiFi.getStyleClass().add("invalid");
                        break;
                    case "SSID":
                        txtWiFiSSID.getStyleClass().add("invalid");
                        break;
                    case "PSK":
                        txtWiFiPassword.getStyleClass().add("invalid");
                        break;
                }
            }
        }
        return result;
    }

    private IValidationResult validate(INetwork network) {
        IValidationResult result = validationModel.validate(network);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Description":
                        txtNetworkName.getStyleClass().add("invalid");
                        break;
                    case "Remarks":
                        txtAreaNetwork.getStyleClass().add("invalid");
                        break;
                    case "NetworkIP":
                        txtNetworkIP.getStyleClass().add("invalid");
                        break;
                    case "SubnetMask":
                        txtNetworkSubnet.getStyleClass().add("invalid");
                        break;
                    case "DefaultGateway":
                        txtNetworkDefault.getStyleClass().add("invalid");
                        break;
                }
            }
        }
        return result;
    }
}
