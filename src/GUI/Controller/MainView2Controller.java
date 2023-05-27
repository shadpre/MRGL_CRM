package GUI.Controller;


import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Installation;
import BE.DBEnteties.Interfaces.ICustomer;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BE.DBEnteties.Interfaces.IInstallation;
import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import BE.Exptions.NotFoundExeptions.DocumentNotFoundExeption;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BLL.Datavalidation.ValidationHelper;
import BLL.Datavalidation.ValidationResult;
import BLL.Interfaces.*;
import BLL.Managers.CustomerManager;
import BLL.Managers.CustomerTaskManager;
import BLL.Managers.InstallationManager;
import BLL.Managers.UserManager;
import DAL.DBFacade;
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
import java.util.ResourceBundle;

public class MainView2Controller extends BaseController implements Initializable {
    @FXML
    private Button btnAddCustomer, btnAddUser, btnBeginTask, btnSaveUserCeo, btnShowAllCustomers, btnShowAllCustomersSales, btnShowAllFinishedTasksSales, btnShowAllMyTasksPManager,
            btnShowAllMyTasksTech, btnShowAllTasksCeo, btnAddNewTask, btnShowAllUsers, btnSaveCustomerCeo, btnUpdateTaskPManager, btnGenerateDocument, btnAddTech, btnRemoveTech, bthShowAllInstallations,
            btnOpenInstallation, btnUpdateInstallation, btnDeleteInstallation, btnGenDocumentation, btnUpdateCustomerbtn;

    @FXML
    private StackPane stackPaneAddCustomerCeo, stackPaneAddUserCeo, stackPaneAllUsersCeo, stackPaneCeoBtn, stackPaneTechBtn, stackPaneSalesBtn, stackPanePManagerBtn, stackPaneViewAllCustomersCeo,
            stackPaneViewAllCustomersTasks, stackpaneBtnEditCustomer, stackpaneBtnEditTask, stackpaneBtnEditUser, stackPaneAddTaskCeo, stackPaneViewAllMyTasksTech, stackPaneViewAllCompletedTasks,
            stackPaneViewAllCompletedTasksPm, stackPaneViewAllInstallations, stackpaneBtnEditInstallation;

    @FXML
    private Button btnUpdateCustomer;
    @FXML
    private Button btnUpdateTask;
    @FXML
    private Button btnShowUpdateUser;
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
    private TableView<ICustomerTask> tableViewAllTasksCeo;

    @FXML
    private TableColumn<ICustomerTask, String> columnDate;
    @FXML
    private TableColumn<ICustomerTask, String> columnDescription;
    @FXML
    private TableColumn<ICustomerTask, String> columnStatus;

    @FXML
    private TableView<IUser> tableViewAllUsersCeo;
    @FXML
    private TableColumn<IUser, String> columnLastName;
    @FXML
    private TableColumn<IUser, String> columnFirstName;
    @FXML
    private TableColumn<IUser, String> columnEmail;

    @FXML
    private TableView<ICustomer> tableViewAllCustomersCeo;
    @FXML
    private TableColumn<ICustomer, String> columnCustomerName;
    @FXML
    private TableColumn<ICustomer, String> columnCustomerAddress;
    @FXML
    private TableColumn<ICustomer, String> columnCustomerPhone;
    @FXML
    private TableColumn<ICustomer, String> columnCustomerZipCode;
    @FXML
    private TableColumn<ICustomer, String> columnCustomerCity;

    @FXML
    private TableView<IInstallation> tableViewAllTasksTech;
    @FXML
    private TableColumn<IInstallation, Integer> columnAllMyTasksTech;
    @FXML
    private TableColumn<IInstallation, String> columnDescriptionTasksTech;
    @FXML
    private TableView<ICustomerTask> tableViewAllCompletedTasks;
    @FXML
    private TableColumn<ICustomerTask, String> columnAllCompletedTasksNo;
    @FXML
    private TableColumn<ICustomerTask, String> columnAllCompletedTasksDescription;
    @FXML
    private TableView<ICustomerTask> tableViewAllCompletedTasksPm;
    @FXML
    private TableColumn<ICustomerTask, String> columnAllCompletedTasksNoPm;
    @FXML
    private TableColumn<ICustomerTask, String> columnAllCompletedTasksDescriptionPm;
    @FXML
    private TableView<ICustomer> tableViewAddTaskAllCustomers;
    @FXML
    private TableColumn<ICustomer, String> columnAddTaskAllCustomers;

    @FXML
    private TableView<IUser> tableViewAddTaskTechAvalible;
    @FXML
    private TableColumn<IUser, String> columnAddTaskAvalibleTech, columnAddTaskAssignedTech;

    @FXML
    private TableView<IUser> tableViewAddTaskTechAssigned;

    @FXML
    private TableView<IInstallation> tableViewAllInstallations;

    @FXML
    private TableColumn<IInstallation, String> columnInstallationNo;
    @FXML
    private TableColumn<IInstallation, String> columnInstallationDescription;


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
    private IValidationHelper iValidationHelper = new ValidationHelper();
    private UserModel userModel;

    private ICustomerTask customerTask;

    private IInstallationManager installationManager = new InstallationManager();

    private IUser selectedUSer;
    private IUserManager userManager = new UserManager();

    private CustomerModel customerModel;
    private ICustomerManager customerManager = new CustomerManager();

    private InstallationModel installationModel;
    private ICustomerTaskManager customerTaskManager = new CustomerTaskManager();

    private CustomerTaskModel customerTaskModel;

    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnHandleAddUser(ActionEvent event) {

        // open the Add User window.

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneAddUserCeo.setVisible(true);
        stackPaneCeoBtn.setVisible(true);

    }

    @FXML
    void bthHandleUpdateUser(ActionEvent event){

    }

    @FXML
    void btnHandleUpdateCustomerbtn(ActionEvent event) {

        ICustomer infoCustomer = tableViewAllCustomersCeo.getSelectionModel().getSelectedItem();
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
        int Id = infoCustomer.getId();

        ICustomer selectedCustomer = new Customer(Id, name, address1, address2, address3, zipcode, city, country, telephone, email, taxNo);

        try {
            IValidationResult vr = iValidationHelper.validate(selectedCustomer);
            if (vr.hasNoError()) {
                customerManager.updateCustomer(selectedCustomer);
            } else {
                for (String error : vr.getErrors()
                ) {
                    switch (error) {
                        case "Name":
                            txtFieldCustomerName.getStyleClass().add("invalid");
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
        resetFieldsCustomer();
    }

    @FXML
    void btnHandleAddCustomer(ActionEvent event) {

        //Opens the add Customer Window.
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(true);
        btnUpdateCustomerbtn.setVisible(false);
        btnSaveCustomerCeo.setVisible(true);

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

        columnAddTaskAllCustomers.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Name"));


        // Try-Catch block for exeption handling.
        try {
            tableViewAddTaskAllCustomers.setItems(CustomerModel.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Add technicians to the Employee's avalible table
        this.userModel = userModel;

        columnAddTaskAvalibleTech.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));
        columnAddTaskAssignedTech.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));

        ObservableList<IUser> allUsers = userModel.getAllUsers();
        ObservableList<IUser> filteredUsers = FXCollections.observableArrayList();
        try {


            for (IUser techList : allUsers) {
                if (techList.getRole() == 0) {
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
    void btnHandleAddTech(ActionEvent event) {

        IUser selectedTech = tableViewAddTaskTechAvalible.getSelectionModel().getSelectedItem();
        tableViewAddTaskTechAvalible.getItems().remove(selectedTech);
        tableViewAddTaskTechAssigned.getItems().add(selectedTech);

    }

    @FXML
    void btnHandleRemoveTech(ActionEvent event) {
        IUser selectedTech = tableViewAddTaskTechAssigned.getSelectionModel().getSelectedItem();
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
            if (password != passwordRetype){
                //set red border
            }
            userManager.createUser(user, password);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        resetFieldsUser();

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
            IValidationResult vr = iValidationHelper.validate(customer);
            if (vr.hasNoError()) {
                customerManager.createCustomer(customer);
            } else {
                for (String error : vr.getErrors()
                ) {
                    switch (error) {
                        case "Name":
                            txtFieldCustomerName.getStyleClass().add("invalid");
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
        resetFieldsCustomer();
    }

    @FXML
    void btnHandleSaveTaskCeo(ActionEvent event) throws SQLException {

        // Get the selected Customer from the Customer tableview.
        ICustomer selectedCustomer = tableViewAddTaskAllCustomers.getSelectionModel().getSelectedItem();
        try {
            createTask(selectedCustomer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int taskInt = customerTaskModel.getAllCustomerTasks().size() - 1;
        ICustomerTask selectedTask = customerTaskModel.getAllCustomerTasks().get(taskInt);

        try {
            techTaskLink(selectedTask);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int installationsNr = 1;
        try {
            createInstallation(selectedTask, installationsNr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resetFieldsTasks();
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

        columnFirstName.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<IUser, String>("lastName"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<IUser, String>("EMail"));


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

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Name"));
        columnCustomerAddress.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Address1"));
        columnCustomerPhone.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Phone"));
        columnCustomerZipCode.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Zipcode"));
        columnCustomerCity.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("City"));


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

        columnDate.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Date"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Description"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Status"));


        try {
            tableViewAllTasksCeo.setItems(CustomerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnHandleShowAllInstallations(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneViewAllInstallations.setVisible(true);
        stackpaneBtnEditInstallation.setVisible(true);
        stackPaneCeoBtn.setVisible(true);
        tableViewAllInstallations.setVisible(true);


        this.installationModel = new InstallationModel();

        columnInstallationNo.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("CustomerTaskId"));
        columnInstallationDescription.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("Description"));

        try {
            tableViewAllInstallations.setItems(InstallationModel.getAllInstallations());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getRoleValue() {

        // Get the User RoleValue

        switch (choiceBoxRoleCeo.getValue()) {
            case "Tekniker":
                return 0;
            case "CEO":
                return 1;
            case "Projekt Manager":
                return 2;
            case "Salg":
                return 3;
            default:
                throw new RuntimeException("Rolle ikke gyldig");
        }

    }

    @FXML
    void btnHandleBeginTask(ActionEvent event) throws IOException {

        IInstallation selectedInstallation = tableViewAllTasksTech.getSelectionModel().getSelectedItem();

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
        } else {
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

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Name"));
        columnCustomerAddress.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Address1"));
        columnCustomerPhone.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Phone"));
        columnCustomerZipCode.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Zipcode"));
        columnCustomerCity.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("City"));


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

        columnAllCompletedTasksNo.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("CustomerId"));
        columnAllCompletedTasksDescription.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Description"));

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

        columnAllCompletedTasksNoPm.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("CustomerId"));
        columnAllCompletedTasksDescriptionPm.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Description"));

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

        columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, Integer>("CustomerTaskId"));
        columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("Description"));


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
    void btnHandleOpenInstallation(ActionEvent event) throws IOException {

        IInstallation selectedInstallation = tableViewAllInstallations.getSelectionModel().getSelectedItem();

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

    }

    @FXML
    void btnHandleUpdateInstallation(ActionEvent event) {

    }

    @FXML
    void btnHandleDeleteInstallation(ActionEvent event) {

    }

    @FXML
    void btnHandleGenDocumentation(ActionEvent event) {

    }

    @FXML
    void btnHandleUpdateTaskPManager(ActionEvent event) throws IOException {

        IInstallation selectedInstallation = tableViewAllTasksTech.getSelectionModel().getSelectedItem();

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


    }

    @FXML
    void btnHandleGenerateDocument(ActionEvent event) throws MalformedURLException, DocumentNotFoundExeption, SQLException, FileNotFoundException {

        //  DocumentGeneration.documentGeneration();

    }

    @FXML
    void btnHandleFinishTask(ActionEvent event) throws MalformedURLException, DocumentNotFoundExeption, SQLException, FileNotFoundException {

        // DocumentGeneration.documentGeneration();

    }

    @FXML
    void btnHandleDeleteCustomer(ActionEvent event) throws CustomerNotFoundExeption, SQLException {

        ICustomer selectedCustomer = tableViewAllCustomersCeo.getSelectionModel().getSelectedItem();
        customerManager.deleteCustomer(selectedCustomer.getId());

        btnHandleShowAllCustomers(null);


    }

    @FXML
    void btnHandleDeleteTask(ActionEvent event) {

    }

    @FXML
    void btnHandleDeleteUser(ActionEvent event) throws UserNotFoundExeption, SQLException {

        IUser selectedUser = tableViewAllUsersCeo.getSelectionModel().getSelectedItem();
        userManager.deleteUser(selectedUser.getId());

    }

    @FXML
    void btnHandleUpdateCustomer(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(true);


        ICustomer selectedCustomer = tableViewAllCustomersCeo.getSelectionModel().getSelectedItem();

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

        btnSaveCustomerCeo.setVisible(false);
        btnUpdateCustomerbtn.setVisible(true);


    }

    @FXML
    void btnHandleAssignInstallCEO(ActionEvent event) {

        ICustomerTask selectedTask = tableViewAllTasksCeo.getSelectionModel().getSelectedItem();

        int installationsNr = installationModel.getInstallations(selectedTask.getId()).size() + 1;

        try {
            createInstallation(selectedTask, installationsNr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        showAlert("Ny Installation oprettet til " + selectedTask.getDescription());
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

    }

    @FXML
    void btnHandleShowUpdateUser(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(true);


        IUser selectedUser = tableViewAllUsersCeo.getSelectionModel().getSelectedItem();

        txtFieldLoginName.setText(selectedUser.getLoginName());
        txtFieldFirstName.setText(selectedUser.getFirstName());
        txtFieldLastName.setText(selectedUser.getLastName());
        txtFieldEmail.setText(selectedUser.getEMail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Setup(IUser selectedUser, LogInController logInController, UserModel userModel, int role) {

        this.selectedUSer = selectedUser;
        this.userModel = userModel;


        if (role == 0) {

            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPaneTechBtn.setVisible(true);
            stackPaneViewAllMyTasksTech.setVisible(true);
            tableViewAllTasksTech.setVisible(true);

            InstallationModel installationModel = new InstallationModel();

            this.installationModel = installationModel;

            columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, Integer>("CustomerTaskId"));
            columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("Description"));


            try {
                tableViewAllTasksTech.setItems(installationModel.getInstallationsForUser(selectedUSer));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if (role == 1) {

            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPaneCeoBtn.setVisible(true);

            choiceBoxRoleCeo.getItems().addAll("Tekniker", "CEO", "Projekt Manager", "Salg");

        } else if (role == 2) {

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

    public void createTask(ICustomer selectedCustomer) {

        // Get the user submitted data from the textfields and the datepicker.

        String Description = txtFieldDescriptionTask.getText();
        String Remarks = txtFieldRemarksTask.getText();

        LocalDateTime Date = localDateTimeSelector();

        // Create the new Customer Task and send it up through the layers

        ICustomerTask customerTask = new CustomerTask(0, Date, Description, Remarks, 0, selectedCustomer.getId());

        try {
            customerTaskManager.createCustomerTask(customerTask);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void techTaskLink(ICustomerTask selectedTask) throws SQLException {

        for (int i = 0; i < tableViewAddTaskTechAssigned.getItems().size(); i++) {

            IUser user = tableViewAddTaskTechAssigned.getItems().get(i);

            customerTaskManager.addUserToCustomerTask(user.getId(), selectedTask.getId());
        }
    }

    public LocalDateTime localDateTimeSelector() {

        LocalDate localDate = datePickerTask.getValue();
        Time time = new Time(8, 00, 00);

        LocalDateTime localDateTime = localDate.atTime(time.toLocalTime());

        return localDateTime;
    }

    public void createInstallation(ICustomerTask selectedTask, int installationNr) {

        InstallationModel installationModel = new InstallationModel();
        this.installationModel = installationModel;


        String description = selectedTask.getDescription() + "Installation nr " + installationNr;

        IInstallation inst = new Installation(0, selectedTask.getId(), description, "Aktiv");

        try {
            installationManager.createInstallation(inst);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setAllStackPanesFalse() {

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
        stackpaneBtnEditInstallation.setVisible(false);

    }

    private void setAllTableViewsFalse() {

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

    public void resetFieldsUser() {

        txtFieldLoginName.setText("");
        txtFieldFirstName.setText("");
        txtFieldLastName.setText("");
        txtFieldPassword.setText("");
        txtFieldPasswordRetype.setText("");
        txtFieldEmail.setText("");
        choiceBoxRoleCeo.setValue("");
    }

    public void resetFieldsCustomer() {

        txtFieldCustomerName.setText("");
        txtFieldCustomerAddress.setText("");
        txtFieldCustomerAddress2.setText("");
        txtFieldCustomerAddress3.setText("");
        txtFieldCustomerZipCode.setText("");
        txtFieldCustomerCity.setText("");
        txtFieldCustomerCountry.setText("");
        txtFieldCustomerTelephone.setText("");
        txtFieldCustomerEmail.setText("");
        txtFieldCustomerTaxNo.setText("");

    }

    public void resetFieldsTasks() {

        tableViewAddTaskAllCustomers.getSelectionModel().clearSelection();
        txtFieldDescriptionTask.setText("");
        txtFieldRemarksTask.setText("");
        datePickerTask.setValue(null);
        tableViewAddTaskTechAssigned.getItems().clear();


        // Add technicians to the Employee's avalible table
        this.userModel = userModel;

        columnAddTaskAvalibleTech.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));
        columnAddTaskAssignedTech.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));

        ObservableList<IUser> allUsers = userModel.getAllUsers();
        ObservableList<IUser> filteredUsers = FXCollections.observableArrayList();
        try {
            for (IUser techList : allUsers) {
                if (techList.getRole() == 0) {
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

}
