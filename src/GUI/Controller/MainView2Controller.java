package GUI.Controller;


import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.User;
import GUI.Model.CustomerModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import BLL.Managers.UserManager;
import BLL.Managers.CustomerManager;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView2Controller extends BaseController implements Initializable {
    @FXML
    private Button btnAddCustomer;

    private User selectedUser;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnBeginTask;

    @FXML
    private Button btnSaveUserCeo;

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
    private Button btnShowAllTasksCeo;

    @FXML
    private Button btnShowAllUsers;

    @FXML
    private ImageView btnUpdateCustomer;

    @FXML
    private Button btnUpdateTask;

    @FXML
    private Button btnSaveCustomerCeo;

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
    private StackPane stackPaneViewAllCustomersTasks;

    @FXML
    private StackPane stackPaneBtnEditCustomer;

    @FXML
    private StackPane stackPaneBtnEditUser;

    @FXML
    private TableView<CustomerTask> tableViewAllTasksCeo;

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
    @FXML
    private TextField txtFieldCustomerAddress;

    @FXML
    private TextField txtFieldCustomerAddress2;

    @FXML
    private TextField txtFieldCustomerAddress3;

    @FXML
    private TextField txtFieldCustomerCity;

    @FXML
    private TextField txtFieldCustomerCountry;

    @FXML
    private TextField txtFieldCustomerEmail;

    @FXML
    private TextField txtFieldCustomerName;

    @FXML
    private TextField txtFieldCustomerTaxNo;

    @FXML
    private TextField txtFieldCustomerTelephone;

    @FXML
    private TextField txtFieldCustomerZipCode;


    private UserModel userModel;
    private UserManager userManager;
    private CustomerModel customerModel;
    private CustomerManager customerManager;

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
        stackPaneViewAllCustomersTasks.setVisible(false);
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
        stackPaneViewAllCustomersTasks.setVisible(false);
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
        stackPaneViewAllCustomersTasks.setVisible(false);
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
    void btnHandleSaveCustomerCeo(ActionEvent event) {

        //Get Customer Information

        String name = txtFieldCustomerName.getText();
        String address1 = txtFieldCustomerAddress.getText();
        String address2 = txtFieldCustomerAddress2.getText();
        String address3 = txtFieldCustomerAddress3.getText();
        String zipcode = txtFieldCustomerZipCode.getText();
        String city = txtFieldCustomerCity.getText();
        String country = txtFieldCustomerCountry.getText();
        String telephone = txtFieldCustomerTelephone.getText();
        String email = txtFieldCustomerEmail.getText();
        String taxNo = txtFieldCustomerTaxNo.getText();

        Customer customer = new Customer(-1, name, address1, address2, address3, zipcode, city, country, telephone, email, taxNo);

        try {

            customerManager.createCustomer(customer);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnHandleShowAllCustomersSales(ActionEvent event) {

        stackPaneAllUsersCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneCeoBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(true);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(true);
        stackPanePManagerBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(true);
        stackPaneViewAllCustomersTasks.setVisible(false);
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
    void btnHandleShowAllFinishedTasksSales(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllMyTasksPManager(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllMyTasksTech(ActionEvent event) {

    }

    @FXML
    void btnHandleShowAllTasksCeo(ActionEvent event) {

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
        stackPaneViewAllCustomersTasks.setVisible(false);
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
    void btnHandleUpdateTask(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView2.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("selectedUser.getLoginName()");
        stage.show();

    }

    @FXML
    void btnHandleUpdateTaskPManager(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateUser(MouseEvent event) {

    }

@Override
    public void initialize(URL location, ResourceBundle resources) {


    }



    public void Setup(UserModel userModel, int role){


        if (role == 0){

            stackPaneCeoBtn.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPanePManagerBtn.setVisible(false);
            stackPaneSalesBtn.setVisible(false);
            stackPaneTechBtn.setVisible(true);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);

        } else if(role == 1) {

            stackPaneCeoBtn.setVisible(true);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPanePManagerBtn.setVisible(false);
            stackPaneSalesBtn.setVisible(false);
            stackPaneTechBtn.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);


            choiceBoxRoleCeo.getItems().addAll("Tekniker", "CEO", "Projekt Manager", "Salg");

        } else if (role == 2){

            stackPaneCeoBtn.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPanePManagerBtn.setVisible(true);
            stackPaneSalesBtn.setVisible(false);
            stackPaneTechBtn.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);


        } else if (role == 3) {

            stackPaneCeoBtn.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPanePManagerBtn.setVisible(false);
            stackPaneSalesBtn.setVisible(true);
            stackPaneTechBtn.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);

        }

    }

}
