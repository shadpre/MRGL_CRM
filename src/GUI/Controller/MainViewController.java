package GUI.Controller;


import BLL.UserManager;
import DAL.db.UserDAO_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {

    private UserModel userModel;
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
    private TableView<User> tbwAllUsers;
    @FXML
    private TableColumn<User, String> columnLastName;
    @FXML
    private TableColumn<User, String> columnFirstName;
    @FXML
    private TableColumn<User, String> columnEmail;
    
    private UserDAO_DB userDAO_db;
    private UserManager userManager;
    private AddUserController addUserController;


    @FXML
    void btnHandleAddUser(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AddUser.fxml"));
            //AddUserController + new AddUserController(); // create an instance of your controller class
            loader.setController(addUserController); // set the controller for the loader
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tilføj en bruger");
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "");
            alert.showAndWait();
        }

    }

    @FXML
    void btnHandleCreateOrder(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView2.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("adm");
        stage.show();



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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UserModel userModel = getModel();
        this.userModel = userModel;

    }

}
