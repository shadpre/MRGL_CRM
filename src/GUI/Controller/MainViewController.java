package GUI.Controller;

import BLL.UserManager;
import DAL.db.UserDAO_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController {
    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnCreateOrder;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnShowAllOrders;

    @FXML
    private Button btnShowAllUsers;

    @FXML
    private Button btnShowCurrent;

    @FXML
    private Button btnShowFinished;

    @FXML
    private Button btnShowUpcoming;

    @FXML
    private Button btnUpdateOrder;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private StackPane stackpane;

    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<?> tbwAllUsers;
    private UserDAO_DB userDAO_db;
    private UserManager userManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            userManager = new UserManager();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnHandleAddUser(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/View/AddUser.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tilføj en bruger");
            stage.show();

            //setting controller and model for new window.

            BaseController controller = loader.getController();
            controller.(userMnanger);


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "");
            alert.showAndWait();
        }

    }

    @FXML
    void btnHandleCreateOrder(ActionEvent event) {

    }

    @FXML
    void btnHandleDeleteUser(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllOrders(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllUsers(ActionEvent event) {

    }

    @FXML
    void btnHandleShowCurrent(ActionEvent event) {

    }

    @FXML
    void btnHandleShowFinished(ActionEvent event) {

    }

    @FXML
    void btnHandleShowUpcoming(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateOrder(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateUser(ActionEvent event) {

    }
}
