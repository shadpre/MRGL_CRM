package GUI.Controller;

import BE.DBEnteties.Customer;
import BE.DBEnteties.User;
import GUI.Model.CustomerModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView2Controller extends BaseController implements Initializable {
    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnBeginTask;

    @FXML
    private ImageView btnDeleteCustomer;

    @FXML
    private ImageView btnDeleteUser;

    @FXML
    private Button btnShowAllCustomers;

    @FXML
    private Button btnShowAllCustomersSales;

    @FXML
    private Button btnShowAllFinishedTasksSales;

    @FXML
    private Button btnShowAllMyTasksPManager;

    @FXML
    private Button btnShowAllMyTasksTech;

    @FXML
    private Button btnShowAllTasks;

    @FXML
    private Button btnShowAllUsers;

    @FXML
    private ImageView btnUpdateCustomer;

    @FXML
    private Button btnUpdateTask;

    @FXML
    private Button btnUpdateTaskPManager;

    @FXML
    private ImageView btnUpdateUser;

    @FXML
    private StackPane stackPaneAddCustomerCeo;

    @FXML
    private StackPane stackPaneAddUserCeo;

    @FXML
    private StackPane stackPaneAllUsersCeo;

    @FXML
    private StackPane stackPaneCeoBtn;

    @FXML
    private StackPane stackPaneTechBtn;

    @FXML
    private StackPane stackPaneSalesBtn;

    @FXML
    private StackPane stackPanePManagerBtn;

    @FXML
    private StackPane stackPaneViewAllCustomersCeo;

    @FXML
    private StackPane stackPaneViewAllCustomersSales;

    @FXML
    private StackPane stackPaneBtnEditCustomer;

    @FXML
    private StackPane stackPaneBtnEditUser;

    @FXML
    private TableView<?> tableViewAllCustomersCeo1;

    @FXML
    private TableView<?> tableViewAllTasksCeo;

    @FXML
    private TableView<User> tableViewAllUsersCeo;
    @FXML
    private TableColumn<User, String> columnLastName;
    @FXML
    private TableColumn<User, String> columnFirstName;
    @FXML
    private TableColumn<User, String> columnEmail;

    @FXML
    private TableView<Customer> tableViewAllCustomersCeo;
    @FXML
    private TableColumn<Customer, String> columnCustomerName;
    @FXML
    private TableColumn<Customer, String> columnCustomerAddress;
    @FXML
    private TableColumn<Customer, String> columnCustomerPhone;
    @FXML
    private TableColumn<Customer, String> columnCustomerZipCode;
    @FXML
    private TableColumn<Customer, String> columnCustomerCity;


    @FXML
    private TextField txtSearch;

    private UserModel userModel;
    private CustomerModel customerModel;



    @FXML
    void btnHandleAddCustomer(ActionEvent event) {

        stackPaneAllUsersCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(true);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPaneViewAllCustomersSales.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);

    }

    @FXML
    void btnHandleAddUser(ActionEvent event) {

        stackPaneAllUsersCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneCeoBtn.setVisible(true);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneAddUserCeo.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPaneViewAllCustomersSales.setVisible(false);
        stackPaneTechBtn.setVisible(false);

    }

    @FXML
    void btnHandleBeginTask(ActionEvent event) {

    }

    @FXML
    void btnHandleDeleteCustomer(MouseEvent event) {

    }

    @FXML
    void btnHandleDeleteUser(MouseEvent event) {

    }

    @FXML
    void btnHandleShowAllCustomers(ActionEvent event) {


        stackPaneAllUsersCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(true);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPaneViewAllCustomersSales.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(true);

        CustomerModel customerModel = new CustomerModel();
        this.customerModel = customerModel;

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        columnCustomerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address"));
        columnCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("Phone"));
        columnCustomerZipCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("ZipCode"));
        columnCustomerCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("City"));


        try {
            tableViewAllCustomersCeo.setItems(CustomerModel.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleShowAllCustomersSales(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllFinishedTasksSales(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllMyTasksPManager(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllMyTasksTech(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllTasks(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllUsers(ActionEvent event) {

        stackPaneAllUsersCeo.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPaneViewAllCustomersSales.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        tableViewAllUsersCeo.setVisible(true);

        UserModel userModel = new UserModel();
        this.userModel = userModel;

        columnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("EMail"));


        try {
            tableViewAllUsersCeo.setItems(userModel.getAllUsers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleUpdateCustomer(MouseEvent event) {

    }

    @FXML
    void btnHandleUpdateTask(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateTaskPManager(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateUser(MouseEvent event) {

    }

@Override
    public void initialize(URL location, ResourceBundle resources) {

        stackPaneCeoBtn.setVisible(true);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersSales.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);

    }

}
