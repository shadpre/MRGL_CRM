package GUI.Controller;


import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Installation;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import BE.Exptions.NotFoundExeptions.DocumentNotFoundExeption;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BLL.Datavalidation.ValidationHelper;
import BLL.Datavalidation.ValidationResult;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
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
            btnShowAllMyTasksTech, btnShowAllTasksCeo, btnAddNewTask, btnShowAllUsers, btnSaveCustomerCeo, btnUpdateTaskPManager, btnGenerateDocument, btnAddTech, btnRemoveTech, bthShowAllInstallations,
            btnOpenInstallation, btnUpdateInstallation, btnDeleteInstallation;

    @FXML
    private StackPane stackPaneAddCustomerCeo, stackPaneAddUserCeo, stackPaneAllUsersCeo, stackPaneCeoBtn, stackPaneTechBtn, stackPaneSalesBtn, stackPanePManagerBtn, stackPaneViewAllCustomersCeo,
            stackPaneViewAllCustomersTasks, stackpaneBtnEditCustomer, stackpaneBtnEditTask, stackpaneBtnEditUser, stackPaneAddTaskCeo, stackPaneViewAllMyTasksTech, stackPaneViewAllCompletedTasks,
            stackPaneViewAllCompletedTasksPm, stackPaneViewAllInstallations, stackpaneBtnEditInstallation;

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
    private Button btnFinishTask;

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
    private TableView<Installation> tableViewAllTasksTech;
    @FXML
    private TableColumn<Installation, Integer> columnAllMyTasksTech;
    @FXML
    private TableColumn<Installation, String> columnDescriptionTasksTech;
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
    private TableView<Installation> tableViewAllInstallations;

    @FXML
    private TableColumn<Installation, String> columnInstallationNo;
    @FXML
    private TableColumn<Installation, String> columnInstallationDescription;






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

        // open the Add User window.

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneAddUserCeo.setVisible(true);
        stackPaneCeoBtn.setVisible(true);

    }
    @FXML
    void btnHandleAddCustomer(ActionEvent event) {

        //Opens the add Customer Window.
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(true);

    }
    @FXML
    void btnHandleAddNewTask(ActionEvent event) throws IOException {

        //Opens the Add Task Window.
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddTaskCeo.setVisible(true);
        tableViewAddTaskTechAssigned.setVisible(true);
        tableViewAddTaskTechAvalible.setVisible(true);
        tableViewAddTaskAllCustomers.setVisible(true);


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
            ValidationResult vr = ValidationHelper.validate(customer);
            if (vr.hasNoError()){
            customerManager.createCustomer(customer);
            }
            else {
                for (String error: vr.getErrors()
                     ) {
                    switch (error){
                        case "Name":
                            txtFieldCustomerName.setStyle();
                        break;
                        case "Address1":
                            int i = 0;//Do something2
                            break;
                        case "Address2":
                            int ij = 1;
                    }
                }
            }

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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneAllUsersCeo.setVisible(true);
        stackPaneCeoBtn.setVisible(true);
        tableViewAllUsersCeo.setVisible(true);
        stackpaneBtnEditUser.setVisible(true);


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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneViewAllCustomersCeo.setVisible(true);
        tableViewAllCustomersCeo.setVisible(true);
        stackpaneBtnEditCustomer.setVisible(true);


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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneViewAllCustomersTasks.setVisible(true);
        tableViewAllTasksCeo.setVisible(true);
        stackpaneBtnEditTask.setVisible(true);


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

    @FXML
    void btnHandleShowAllInstallations(ActionEvent event){

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneViewAllInstallations.setVisible(true);
        stackpaneBtnEditInstallation.setVisible(true);
        stackPaneCeoBtn.setVisible(true);
        tableViewAllInstallations.setVisible(true);


        this.installationModel = new InstallationModel();

        columnInstallationNo.setCellValueFactory(new PropertyValueFactory<Installation, String>("CustomerTaskId"));
        columnInstallationDescription.setCellValueFactory(new PropertyValueFactory<Installation, String>("Description"));



        try {
            tableViewAllInstallations.setItems(InstallationModel.getAllInstallations());
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

        Installation selectedInstallation = tableViewAllTasksTech.getSelectionModel().getSelectedItem();

        if (selectedInstallation != null) {

            this.installationModel = installationModel;

            installationModel.setSelectedInstallation(selectedInstallation);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("selectedUser.getLoginName()");
            stage.show();


            DocumentationViewController controller = loader.getController();
            controller.setInstallationModel(installationModel);


            controller.setUpDocu(selectedInstallation, installationModel);
        }

        else{
            System.out.println("get fucked nerd");
        }

        System.out.println("" + selectedUSer.getFirstName() + selectedUSer.getLoginName());

    }

    @FXML
    void btnHandleShowAllCustomersSales(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneSalesBtn.setVisible(true);
        stackPaneViewAllCustomersCeo.setVisible(true);
        tableViewAllCustomersCeo.setVisible(true);
        stackpaneBtnEditCustomer.setVisible(true);


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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneSalesBtn.setVisible(true);
        tableViewAllCompletedTasks.setVisible(true);
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


        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPanePManagerBtn.setVisible(true);
        tableViewAllCompletedTasksPm.setVisible(true);
        stackPaneViewAllCompletedTasksPm.setVisible(true);


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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneTechBtn.setVisible(true);
        stackPaneViewAllMyTasksTech.setVisible(true);
        tableViewAllTasksTech.setVisible(true);


        InstallationModel installationModel = new InstallationModel();

        this.installationModel = installationModel;

        columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<Installation, Integer>("CustomerTaskId"));
        columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<Installation, String>("Description"));


        try {
            tableViewAllTasksTech.setItems(installationModel.getInstallationsForUser(selectedUSer));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleShowAllTasks(ActionEvent event) {

    }

   @FXML
   void btnHandleOpenInstallation(ActionEvent event){

   }
   @FXML
   void btnHandleUpdateInstallation(ActionEvent event){

   }
   @FXML
   void btnHandleDeleteInstallation(ActionEvent event){

   }

    @FXML
    void btnHandleUpdateTaskPManager(ActionEvent event) throws IOException {

        Installation selectedInstallation = tableViewAllTasksTech.getSelectionModel().getSelectedItem();

        if (selectedInstallation != null) {

            this.installationModel = installationModel;

            installationModel.setSelectedInstallation(selectedInstallation);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("selectedUser.getLoginName()");
            stage.show();


            DocumentationViewController controller = loader.getController();
            controller.setInstallationModel(installationModel);


            controller.setUpDocu(selectedInstallation, installationModel);
        }

        else{
            System.out.println("get fucked nerd");
        }

        System.out.println("" + selectedUSer.getFirstName() + selectedUSer.getLoginName());
    }

    @FXML
    void btnHandleGenerateDocument(ActionEvent event) throws MalformedURLException, DocumentNotFoundExeption, SQLException, FileNotFoundException {

        DocumentGeneration.documentGeneration();

    }

    @FXML
    void btnHandleFinishTask(ActionEvent event) throws MalformedURLException, DocumentNotFoundExeption, SQLException, FileNotFoundException {

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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(true);


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
    void btnHandleAssignInstallCEO(ActionEvent event){

       CustomerTask selectedTask = tableViewAllTasksCeo.getSelectionModel().getSelectedItem();

        int installationsNr = installationModel.getInstallations(selectedTask.getId()).size() + 1;

        try{
            createInstallation(selectedTask, installationsNr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(true);


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

            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPaneTechBtn.setVisible(true);
            stackPaneViewAllMyTasksTech.setVisible(true);
            tableViewAllTasksTech.setVisible(true);

            InstallationModel installationModel = new InstallationModel();

            this.installationModel = installationModel;

            columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<Installation, Integer>("CustomerTaskId"));
            columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<Installation, String>("Description"));


            try {
                tableViewAllTasksTech.setItems(installationModel.getInstallationsForUser(selectedUSer));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if(role == 1) {

            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPaneCeoBtn.setVisible(true);

            choiceBoxRoleCeo.getItems().addAll("Tekniker", "CEO", "Projekt Manager", "Salg");

        } else if (role == 2){

            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPanePManagerBtn.setVisible(true);


        } else if (role == 3) {

            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPaneSalesBtn.setVisible(true);


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

    public void createInstallation(CustomerTask selectedTask, int installationNr) {

        InstallationModel installationModel = new InstallationModel();
        this.installationModel = installationModel;


        String description = selectedTask.getDescription() + "Installation nr " + installationNr;

        Installation inst = new Installation(0, selectedTask.getId(), description, "Aktiv");

        try {
            InstallationManager.createInstallation(inst);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


            private void setAllStackPanesFalse () {

                stackPaneCeoBtn.setVisible(false);
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
                stackPaneViewAllCompletedTasksPm.setVisible(false);
                stackpaneBtnEditCustomer.setVisible(false);
                stackpaneBtnEditUser.setVisible(false);
                stackpaneBtnEditTask.setVisible(false);
                stackPaneViewAllCompletedTasks.setVisible(false);
                stackPaneViewAllInstallations.setVisible(false);
                stackpaneBtnEditInstallation.setVisible(true);

            }

            private void setAllTableViewsFalse () {

                tableViewAllTasksTech.setVisible(false);
                tableViewAllUsersCeo.setVisible(false);
                tableViewAllCustomersCeo.setVisible(false);
                tableViewAllTasksCeo.setVisible(false);
                tableViewAllCompletedTasks.setVisible(false);
                tableViewAllCompletedTasksPm.setVisible(false);
                tableViewAddTaskTechAssigned.setVisible(false);
                tableViewAddTaskTechAvalible.setVisible(false);
                tableViewAddTaskAllCustomers.setVisible(false);
                tableViewAllInstallations.setVisible(false);


            }

}
