package GUI.Controller;


import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Installation;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import BE.Exptions.NotFoundExeptions.DocumentNotFoundExeption;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BLL.DocumentGeneration;
import BLL.Managers.*;
import DAL.DB.CustomerTaskDAO_DB;
import GUI.Model.CustomerModel;
import GUI.Model.CustomerTaskModel;
import GUI.Model.InstallationModel;
import GUI.Model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class MainView2Controller extends BaseController implements Initializable {

    @FXML
    private Button btnAddCustomer, btnAddUser, btnBeginTask, btnSaveUserCeo, btnShowAllCustomers, btnShowAllCustomersSales, btnShowAllFinishedTasksSales, btnShowAllMyTasksPManager,
            btnShowAllMyTasksTech, btnShowAllTasksCeo, btnAddNewTask, btnShowAllUsers, btnSaveCustomerCeo, btnUpdateTaskPManager, btnGenerateDocument, btnAddTech, btnRemoveTech;



    @FXML
    private StackPane stackPaneAddCustomerCeo, stackPaneAddUserCeo, stackPaneAllUsersCeo, stackPaneCeoBtn, stackPaneTechBtn, stackPaneSalesBtn, stackPanePManagerBtn, stackPaneViewAllCustomersCeo,
            stackPaneViewAllCustomersTasks, stackpaneBtnEditCustomer, stackpaneBtnEditTask, stackpaneBtnEditUser, stackPaneAddTaskCeo, stackPaneViewAllMyTasksTech, stackPaneViewAllCompletedTasks,
            stackPaneViewAllCompletedTasksPm;

    @FXML
    private Button btnUpdateCustomer;
    @FXML
    private Button btnUpdateTask;
    @FXML
    private Button btnUpdateUser;
    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnDeleteTask;
    @FXML
    private Button btnSaveTaskCeo;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnAssignUser;

    @FXML
    private TableView<CustomerTask> tableViewAllTasksCeo;

    @FXML
    private TableColumn<CustomerTask, String> columnTaskNo;
    //private TableColumn<CustomerTask, String> columnCompanyName;
    @FXML
    private TableColumn<CustomerTask, String> columnDate;
    @FXML
    private TableColumn<CustomerTask, String> columnDescription;
    @FXML
    private TableColumn<CustomerTask, String> columnStatus;

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
    private TableView<CustomerTask> tableViewAllTasksTech;
    @FXML
    private TableColumn<CustomerTask, String> columnAllMyTasksTech;
    @FXML
    private TableColumn<CustomerTask, String> columnDescriptionTasksTech;
    @FXML
    private TableView<CustomerTask> tableViewAllCompletedTasks;
    @FXML
    private TableColumn<CustomerTask, String> columnAllCompletedTasksNo;
    @FXML
    private TableColumn<CustomerTask, String> columnAllCompletedTasksDescription;
    @FXML
    private TableView<CustomerTask> tableViewAllCompletedTasksPm;
    @FXML
    private TableColumn<CustomerTask, String> columnAllCompletedTasksNoPm;
    @FXML
    private TableColumn<CustomerTask, String> columnAllCompletedTasksDescriptionPm;
    @FXML
    private TableView<Customer> tableViewAddTaskAllCustomers;
    @FXML
    private TableColumn<Customer, String> columnAddTaskAllCustomers;

    @FXML
    private TableView<User> tableViewAddTaskTechAvalible;
    @FXML
    private TableColumn<User, String> columnAddTaskAvalibleTech, columnAddTaskAssignedTech;

    @FXML
    private TableView<User> tableViewAddTaskTechAssigned;

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


    @FXML
    private TextField txtFieldDescriptionTask;

    @FXML
    private TextField txtFieldRemarksTask;

    @FXML
    private DatePicker datePickerTask;

    private UserModel userModel;

    private CustomerTask customerTask;

    private User selectedUSer;
    private UserManager userManager;
    private CustomerModel customerModel;
    private CustomerManager customerManager;

    private InstallationModel installationModel;
    private CustomerTaskManager customerTaskManager;

    private CustomerTaskModel customerTaskModel;


    @FXML
    void btnHandleAddUser(ActionEvent event) {

        //Opens the Add User Window

        stackPaneCeoBtn.setVisible(true);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        tableViewAddTaskAllCustomers.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

    }
    @FXML
    void btnHandleAddCustomer(ActionEvent event) {

        //Opens the add Customer Window.

        stackPaneCeoBtn.setVisible(true);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(true);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAddTaskAllCustomers.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

    }
    @FXML
    void btnHandleAddNewTask(ActionEvent event) throws IOException {

        //Opens the Add Task Window.

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
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        tableViewAddTaskAllCustomers.setVisible(true);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneAddTaskCeo.setVisible(true);
        stackPaneViewAllCompletedTasks.setVisible(false);

        // Add Customers to the Customer table

        CustomerModel customerModel = new CustomerModel();
        this.customerModel = customerModel;

        columnAddTaskAllCustomers.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));


        // Try-Catch block for exeption handling.
        try {
            tableViewAddTaskAllCustomers.setItems(CustomerModel.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Add technicians to the Employee's avalible table
        this.userModel = userModel;

        columnAddTaskAvalibleTech.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        columnAddTaskAssignedTech.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));

        ObservableList<User> allUsers = userModel.getAllUsers();
        ObservableList<User> filteredUsers = FXCollections.observableArrayList();
        try {


            for (User techList: allUsers){
                if (techList.getRole() == 0){
                    filteredUsers.add(techList);
                }

            }

            try {
                tableViewAddTaskTechAvalible.setItems(filteredUsers);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnHandleAddTech(ActionEvent event){

        User selectedTech = tableViewAddTaskTechAvalible.getSelectionModel().getSelectedItem();
        tableViewAddTaskTechAvalible.getItems().remove(selectedTech);
        tableViewAddTaskTechAssigned.getItems().add(selectedTech);

    }

    @FXML
    void btnHandleRemoveTech(ActionEvent event){
        User selectedTech = tableViewAddTaskTechAssigned.getSelectionModel().getSelectedItem();
        tableViewAddTaskTechAssigned.getItems().remove(selectedTech);
        tableViewAddTaskTechAvalible.getItems().add(selectedTech);
    }
    @FXML
    void btnHandleSaveUserCeo(ActionEvent event) {

        // The save Button in the Add User Window.

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

    @FXML
    void btnHandleSaveCustomerCeo(ActionEvent event) {

        // The save Button in the Add Customer Window.
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
    void btnHandleSaveTaskCeo(ActionEvent event) throws SQLException {

        // Get the selected Customer from the Customer tableview.
        Customer selectedCustomer = tableViewAddTaskAllCustomers.getSelectionModel().getSelectedItem();
     try {
         createTask(selectedCustomer);

     } catch (Exception e) {
         throw new RuntimeException(e);
     }

     int taskInt = customerTaskModel.getAllCustomerTasks().size() - 1;
     CustomerTask selectedTask = customerTaskModel.getAllCustomerTasks().get(taskInt);

     try {
         techTaskLink(selectedTask);
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

     int installationsNr = 1;
     try{
         createInstallation(selectedTask, installationsNr);
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
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
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(true);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(true);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

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
    void btnHandleShowAllCustomers(ActionEvent event) {

        stackPaneCeoBtn.setVisible(true);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(true);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(true);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(true);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

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
    void btnHandleShowAllTasksCeo(ActionEvent event) {

        stackPaneCeoBtn.setVisible(true);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(true);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(true);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(true);
        stackPaneViewAllCompletedTasks.setVisible(false);

        CustomerTaskModel customerTaskModel = new CustomerTaskModel();
        this.customerTaskModel = customerTaskModel;

        columnTaskNo.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("CustomerId"));
        columnDate.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("Date"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("Description"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("Status"));


        try {
            tableViewAllTasksCeo.setItems(CustomerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private int getRoleValue() {

        // Get the User RoleValue

        switch (choiceBoxRoleCeo.getValue()){
            case "Tekniker": return 0;
            case "CEO": return 1;
            case "Projekt Manager": return 2;
            case "Salg": return 3;
            default: throw new RuntimeException("Rolle ikke gyldig");
        }

    }

    @FXML
    void btnHandleBeginTask(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("selectedUser.getLoginName()");
        stage.show();

    }

    @FXML
    void btnHandleShowAllCustomersSales(ActionEvent event) {

        stackPaneCeoBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(true);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(true);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(true);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(true);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

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

        stackPaneCeoBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(true);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(true);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(true);

        CustomerTaskModel customerTaskModel = new CustomerTaskModel();
        this.customerTaskModel = customerTaskModel;

        columnAllCompletedTasksNo.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("CustomerId"));
        columnAllCompletedTasksDescription.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("Description"));

        try {
            tableViewAllCompletedTasks.setItems(CustomerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleShowAllMyTasksPManager(ActionEvent event) {

        stackPaneCeoBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(true);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(true);
        stackPaneViewAllCompletedTasksPm.setVisible(true);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

        CustomerTaskModel customerTaskModel = new CustomerTaskModel();
        this.customerTaskModel = customerTaskModel;

        columnAllCompletedTasksNoPm.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("CustomerId"));
        columnAllCompletedTasksDescriptionPm.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("Description"));

        try {
            tableViewAllCompletedTasksPm.setItems(CustomerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleShowAllMyTasksTech(ActionEvent event) {

        stackPaneCeoBtn.setVisible(false);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(true);
        tableViewAllTasksTech.setVisible(true);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

        CustomerTaskModel customerTaskModel = new CustomerTaskModel();

        this.customerTaskModel = customerTaskModel;

        columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("CustomerId"));
        columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<CustomerTask, String>("Description"));



        try {
            tableViewAllTasksTech.setItems(CustomerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleShowAllTasks(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateTaskPManager(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("selectedUser.getLoginName()");
        stage.show();

    }

    @FXML
    void btnHandleGenerateDocument(ActionEvent event) throws MalformedURLException, DocumentNotFoundExeption, SQLException, FileNotFoundException {

        DocumentGeneration.documentGeneration();

    }

    @FXML
    void btnHandleAssignUser(ActionEvent event) throws MalformedURLException, DocumentNotFoundExeption, SQLException, FileNotFoundException {

        DocumentGeneration.documentGeneration();

    }

    @FXML
    void btnHandleDeleteCustomer(ActionEvent event) throws CustomerNotFoundExeption, SQLException {

       Customer selectedCustomer = tableViewAllCustomersCeo.getSelectionModel().getSelectedItem();
       CustomerManager.deleteCustomer(selectedCustomer.getId());


    }

    @FXML
    void btnHandleDeleteTask(ActionEvent event) {

    }

    @FXML
    void btnHandleDeleteUser(ActionEvent event) throws UserNotFoundExeption, SQLException {

        User selectedUser = tableViewAllUsersCeo.getSelectionModel().getSelectedItem();
        UserManager.deleteUser(selectedUser.getId());

    }

    @FXML
    void btnHandleUpdateCustomer(ActionEvent event) {

        stackPaneCeoBtn.setVisible(true);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(false);
        stackPaneAddCustomerCeo.setVisible(true);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

        Customer selectedCustomer = tableViewAllCustomersCeo.getSelectionModel().getSelectedItem();

        txtFieldCustomerName.setText(selectedCustomer.getName());
        txtFieldCustomerAddress.setText(selectedCustomer.getAddress1());
        txtFieldCustomerAddress2.setText(selectedCustomer.getAddress2());
        txtFieldCustomerAddress3.setText(selectedCustomer.getAddress3());
        txtFieldCustomerZipCode.setText(selectedCustomer.getZipcode());
        txtFieldCustomerCity.setText(selectedCustomer.getCity());
        txtFieldCustomerCountry.setText(selectedCustomer.getCountry());
        txtFieldCustomerTelephone.setText(selectedCustomer.getPhone());
        txtFieldCustomerEmail.setText(selectedCustomer.getCategory());
        txtFieldCustomerTaxNo.setText(selectedCustomer.getTaxNo());


    }

   @FXML
    void btnHandleUpdateTask(ActionEvent event) {

   // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView.fxml"));
    //Parent root = loader.load();
    //Stage stage = new Stage();
    //stage.setScene(new Scene(root));
    //stage.setTitle("selectedUser.getLoginName()");
    //stage.show();

    }
    @FXML
    void btnHandleUpdateUser(ActionEvent event) {

        stackPaneCeoBtn.setVisible(true);
        stackPaneSalesBtn.setVisible(false);
        stackPanePManagerBtn.setVisible(false);
        stackPaneTechBtn.setVisible(false);
        stackPaneAddUserCeo.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(false);
        stackPaneAddTaskCeo.setVisible(false);
        stackPaneAllUsersCeo.setVisible(false);
        stackPaneViewAllCustomersCeo.setVisible(false);
        stackPaneViewAllCustomersTasks.setVisible(false);
        stackPaneViewAllMyTasksTech.setVisible(false);
        tableViewAllTasksTech.setVisible(false);
        tableViewAllUsersCeo.setVisible(false);
        tableViewAllCustomersCeo.setVisible(false);
        tableViewAllTasksCeo.setVisible(false);
        tableViewAllCompletedTasks.setVisible(false);
        tableViewAllCompletedTasksPm.setVisible(false);
        stackPaneViewAllCompletedTasksPm.setVisible(false);
        stackpaneBtnEditCustomer.setVisible(false);
        stackpaneBtnEditUser.setVisible(false);
        stackpaneBtnEditTask.setVisible(false);
        stackPaneViewAllCompletedTasks.setVisible(false);

        User selectedUser = tableViewAllUsersCeo.getSelectionModel().getSelectedItem();

        txtFieldLoginName.setText(selectedUser.getLoginName());
        txtFieldFirstName.setText(selectedUser.getFirstName());
        txtFieldLastName.setText(selectedUser.getLastName());
        txtFieldEmail.setText(selectedUser.getEMail());

    }



@Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void Setup(User selectedUser, LogInController logInController, UserModel userModel, int role){

        this.selectedUSer = selectedUser;
        this.userModel = userModel;


        if (role == 0){

            stackPaneCeoBtn.setVisible(false);
            stackPaneSalesBtn.setVisible(false);
            stackPanePManagerBtn.setVisible(false);
            stackPaneTechBtn.setVisible(true);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);
            stackPaneAddTaskCeo.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllMyTasksTech.setVisible(false);
            tableViewAllTasksTech.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCompletedTasks.setVisible(false);
            tableViewAllCompletedTasksPm.setVisible(false);
            stackPaneViewAllCompletedTasksPm.setVisible(false);
            stackpaneBtnEditCustomer.setVisible(false);
            stackpaneBtnEditUser.setVisible(false);
            stackpaneBtnEditTask.setVisible(false);
            stackPaneViewAllCompletedTasks.setVisible(false);

        } else if(role == 1) {

            stackPaneCeoBtn.setVisible(true);
            stackPaneSalesBtn.setVisible(false);
            stackPanePManagerBtn.setVisible(false);
            stackPaneTechBtn.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);
            stackPaneAddTaskCeo.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllMyTasksTech.setVisible(false);
            tableViewAllTasksTech.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCompletedTasks.setVisible(false);
            tableViewAllCompletedTasksPm.setVisible(false);
            stackPaneViewAllCompletedTasksPm.setVisible(false);
            stackpaneBtnEditCustomer.setVisible(false);
            stackpaneBtnEditUser.setVisible(false);
            stackpaneBtnEditTask.setVisible(false);
            stackPaneViewAllCompletedTasks.setVisible(false);


            choiceBoxRoleCeo.getItems().addAll("Tekniker", "CEO", "Projekt Manager", "Salg");

        } else if (role == 2){

            stackPaneCeoBtn.setVisible(false);
            stackPaneSalesBtn.setVisible(false);
            stackPanePManagerBtn.setVisible(true);
            stackPaneTechBtn.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);
            stackPaneAddTaskCeo.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllMyTasksTech.setVisible(false);
            tableViewAllTasksTech.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCompletedTasks.setVisible(false);
            tableViewAllCompletedTasksPm.setVisible(false);
            stackPaneViewAllCompletedTasksPm.setVisible(false);
            stackpaneBtnEditCustomer.setVisible(false);
            stackpaneBtnEditUser.setVisible(false);
            stackpaneBtnEditTask.setVisible(false);
            stackPaneViewAllCompletedTasks.setVisible(false);


        } else if (role == 3) {

            stackPaneCeoBtn.setVisible(false);
            stackPaneSalesBtn.setVisible(true);
            stackPanePManagerBtn.setVisible(false);
            stackPaneTechBtn.setVisible(false);
            stackPaneAddUserCeo.setVisible(false);
            stackPaneAddCustomerCeo.setVisible(false);
            stackPaneAddTaskCeo.setVisible(false);
            stackPaneAllUsersCeo.setVisible(false);
            stackPaneViewAllCustomersCeo.setVisible(false);
            stackPaneViewAllCustomersTasks.setVisible(false);
            stackPaneViewAllMyTasksTech.setVisible(false);
            tableViewAllTasksTech.setVisible(false);
            tableViewAllUsersCeo.setVisible(false);
            tableViewAllCustomersCeo.setVisible(false);
            tableViewAllTasksCeo.setVisible(false);
            tableViewAllCompletedTasks.setVisible(false);
            tableViewAllCompletedTasksPm.setVisible(false);
            stackPaneViewAllCompletedTasksPm.setVisible(false);
            stackpaneBtnEditCustomer.setVisible(false);
            stackpaneBtnEditUser.setVisible(false);
            stackpaneBtnEditTask.setVisible(false);
            stackPaneViewAllCompletedTasks.setVisible(false);

        }


        System.out.println(selectedUser.getRole() + "");

    }

    public void createTask(Customer selectedCustomer){

        // Get the user submitted data from the textfields and the datepicker.

        String Description = txtFieldDescriptionTask.getText();
        String Remarks = txtFieldRemarksTask.getText();

        LocalDateTime Date = localDateTimeSelector();

        // Create the new Customer Task and send it up through the layers

        CustomerTask customerTask = new CustomerTask(0, Date, Description, Remarks,0, selectedCustomer.getId() );

        try {

            customerTaskManager.createCustomerTask(customerTask);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void techTaskLink(CustomerTask selectedTask) throws SQLException {

        for (int i = 0; i < tableViewAddTaskTechAssigned.getItems().size(); i++) {

            User user = tableViewAddTaskTechAssigned.getItems().get(i);

            customerTaskManager.addUserToCustomerTask(user.getId(), selectedTask.getId());
        }
    }
    public LocalDateTime localDateTimeSelector(){

        LocalDate localDate = datePickerTask.getValue();
        Time time = new Time(8, 00, 00);

        LocalDateTime localDateTime = localDate.atTime(time.toLocalTime());

        return localDateTime;
    }

    public void createInstallation(CustomerTask selectedTask, int installationNr){

        InstallationModel installationModel = new InstallationModel();
        this.installationModel = installationModel;


        String description = selectedTask.getDescription() + "Installation nr " + installationNr;

        Installation inst = new Installation(0, selectedTask.getId(), description, "Aktiv");

        try{
            InstallationManager.createInstallation(inst);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
