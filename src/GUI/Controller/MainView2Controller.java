package GUI.Controller;


import BE.DBEnteties.Customer;
import BE.DBEnteties.User;
import GUI.Model.CustomerModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import BLL.Managers.UserManager;

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
    private Button btnSaveUserCeo;

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

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldLoginName;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldPasswordRetype;

    @FXML
    private ChoiceBox<String> choiceBoxRoleCeo;

    private UserModel userModel;
    private UserManager userManager;
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
    void btnHandleSaveUserCeo(ActionEvent event) {

        // Get User Information
        String loginName = txtFieldLoginName.getText();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String password = txtFieldPassword.getText();
        String passwordRetype = txtFieldPasswordRetype.getText();
        String email = txtFieldEmail.getText();
        int roleValue = getRoleValue();

        User user = new User(-1, loginName, firstName, lastName, email, roleValue);
        try {

            userManager.createUser(user, password, passwordRetype );

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    private int getRoleValue() {

        switch (choiceBoxRoleCeo.getValue()){
            case "Tekniker": return 0;
            case "CEO": return 1;
            case "Projekt Manager": return 2;
            case "Salg": return 3;
            default: throw new RuntimeException("Rolle ikke gyldig");
        }

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
        stackPaneViewAllCustomersCeo.setVisible(true);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPaneViewAllCustomersSales.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(true);

        CustomerModel customerModel = new CustomerModel();
        this.customerModel = customerModel;

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        columnCustomerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address1"));
        columnCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("Phone"));
        columnCustomerZipCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("Zipcode"));
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


         choiceBoxRoleCeo.getItems().addAll("Tekniker", "CEO", "Projekt Manager", "Salg");


    }

}
