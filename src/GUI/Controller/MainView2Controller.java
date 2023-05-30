package GUI.Controller;


import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Installation;
import BE.DBEnteties.Interfaces.ICustomer;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BE.DBEnteties.Interfaces.IInstallation;
import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundException;
import BE.Exptions.NotFoundExeptions.DocumentNotFoundException;
import BE.Exptions.NotFoundExeptions.UserNotFoundException;
import BLL.Interfaces.IValidationResult;
import GUI.Model.*;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    private Button btnAddCustomer, btnAddUser, btnBeginTask, btnSaveUserCeo, btnShowAllCustomers, btnShowAllCustomersSales;
    @FXML
    private Button btnShowAllFinishedTasksSales, btnShowAllMyTasksPManager, btnShowAllMyTasksTech, btnShowAllTasksCeo, btnAddNewTask;
    @FXML
    private Button btnShowAllUsers, btnSaveCustomerCeo, btnUpdateTaskPManager, btnGenerateDocument, btnAddTech, btnRemoveTech;
    @FXML
    private Button bthShowAllInstallations, btnOpenInstallation, btnUpdateInstallation, btnDeleteInstallation, btnGenDocumentation;
    @FXML
    private Button btnUpdateCustomerbtn, btnShowDocumentationSales, btnGenerateDocumentationSales, btnUpdateCustomer, btnUpdateTask;
    @FXML
    private Button btnShowUpdateUser, btnUpdateUser, btnDeleteCustomer, btnDeleteTask, btnSaveTaskCeo, btnDeleteUser, btnFinishTask;

    @FXML
    private StackPane stackPaneAddCustomerCeo, stackPaneAddUserCeo, stackPaneAllUsersCeo, stackPaneCeoBtn, stackPaneTechBtn;
    @FXML
    private StackPane stackPaneSalesBtn, stackPanePManagerBtn, stackPaneViewAllCustomersCeo, stackPaneViewAllCustomersTasks;
    @FXML
    private StackPane stackpaneBtnEditCustomer, stackpaneBtnEditTask, stackpaneBtnEditUser, stackPaneAddTaskCeo, stackPaneViewAllMyTasksTech;
    @FXML
    private StackPane stackPaneViewAllCompletedTasks, stackPaneViewAllCompletedTasksPm, stackPaneViewAllInstallations;
    @FXML
    private StackPane stackpaneBtnEditInstallation;





    


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
    private TableView<IUser> tableViewAddTaskTechAvailable;
    @FXML
    private TableColumn<IUser, String> columnAddTaskAvailableTech, columnAddTaskAssignedTech;
    @FXML
    private TableView<IUser> tableViewAddTaskTechAssigned;
    @FXML
    private TableView<IInstallation> tableViewAllInstallations;
    @FXML
    private TableColumn<IInstallation, String> columnInstallationNo;
    @FXML
    private TableColumn<IInstallation, String> columnInstallationDescription;
     @FXML
    private TextField txtFieldEmail, txtFieldFirstName, txtFieldLastName ;

    @FXML
    private TextField txtFieldLoginName, txtFieldPassword, txtFieldPasswordRetype, txtFieldCustomerAddress, txtFieldCustomerAddress2;
   @FXML
   private TextField txtFieldCustomerAddress3, txtFieldCustomerCity, txtFieldCustomerCountry, txtFieldCustomerEmail, txtFieldCustomerName;
   @FXML
   private TextField txtFieldCustomerTaxNo, txtFieldCustomerTelephone, txtFieldCustomerZipCode, txtFieldDescriptionTask, txtFieldRemarksTask;
    @FXML
    private ChoiceBox<String> choiceBoxRoleCeo;
    @FXML
    private DatePicker datePickerTask;
    private IUser selectedUser;
    private final ValidationModel validationModel = new ValidationModel();
    private final UserModel userModel = new UserModel();
    private final CustomerModel customerModel = new CustomerModel();
    private final InstallationModel installationModel = new InstallationModel();
    private final CustomerTaskModel customerTaskModel = new CustomerTaskModel();

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void btnHandleAddUser(ActionEvent event) {
        // open the Add User window.
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneAddUserCeo.setVisible(true);
        stackPaneCeoBtn.setVisible(true);
    }

    @FXML
    private void btnHandleUpdateCustomerbtn(ActionEvent event) {

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

        IValidationResult vr = validate(selectedCustomer);
        if (!vr.hasNoError()) {
            return;
        }
        try {
            customerModel.updateCustomer(selectedCustomer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resetFieldsCustomer();
    }

    @FXML
    private void btnHandleAddCustomer(ActionEvent event) {

        //Opens the add Customer Window.
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddCustomerCeo.setVisible(true);
        btnUpdateCustomerbtn.setVisible(false);
        btnSaveCustomerCeo.setVisible(true);
    }

    @FXML
    private void btnHandleAddNewTask(ActionEvent event) throws IOException {

        //Opens the Add Task Window.
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddTaskCeo.setVisible(true);
        tableViewAddTaskTechAssigned.setVisible(true);
        tableViewAddTaskTechAvailable.setVisible(true);
        tableViewAddTaskAllCustomers.setVisible(true);


        // Add Customers to the Customer table
        columnAddTaskAllCustomers.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Name"));

        // Try-Catch block for exeption handling.
        try {
            tableViewAddTaskAllCustomers.setItems(customerModel.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Add technicians to the Employee's avalible table

        columnAddTaskAvailableTech.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));
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
                tableViewAddTaskTechAvailable.setItems(filteredUsers);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleAddTech(ActionEvent event) {
        IUser selectedTech = tableViewAddTaskTechAvailable.getSelectionModel().getSelectedItem();
        tableViewAddTaskTechAvailable.getItems().remove(selectedTech);
        tableViewAddTaskTechAssigned.getItems().add(selectedTech);
    }

    @FXML
    private void btnHandleRemoveTech(ActionEvent event) {
        IUser selectedTech = tableViewAddTaskTechAssigned.getSelectionModel().getSelectedItem();
        tableViewAddTaskTechAssigned.getItems().remove(selectedTech);
        tableViewAddTaskTechAvailable.getItems().add(selectedTech);
    }

    @FXML
    private void btnHandleUpdateUserCEO(ActionEvent event) {
        // The save Button in the Add User Window.
        //Get Customer Information

        int id = selectedUser.getId();
        String loginName = txtFieldLoginName.getText();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String password = txtFieldPassword.getText();
        String passwordRetype = txtFieldPasswordRetype.getText();
        String email = txtFieldEmail.getText();
        int roleValue = getRoleValue();

        try {
            IUser user = new User(id, loginName, firstName, lastName, email, roleValue);
            if (!password.equals(passwordRetype)) {
                txtFieldPasswordRetype.getStyleClass().add("invalid");
                return;
            }
            IValidationResult vr = validate(user);
            if (!vr.hasNoError()) return;

            userModel.updateUser(user, password);
            resetFieldsUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleSaveUserCeo(ActionEvent event) {

        // The save Button in the Add User Window.

        // Get User Information
        String loginName = txtFieldLoginName.getText();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String password = txtFieldPassword.getText();
        String passwordRetype = txtFieldPasswordRetype.getText();
        String email = txtFieldEmail.getText();
        int roleValue = getRoleValue();

        IUser user = new User(-1, loginName, firstName, lastName, email, roleValue);
        try {
            if (!password.equals(passwordRetype)) {
                txtFieldPasswordRetype.getStyleClass().add("invalid");
                return;
            }
            IValidationResult vr = validate(user);
            if (!vr.hasNoError()) return;

            userModel.updateUser(user, password);
            resetFieldsUser();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        resetFieldsUser();
    }


    @FXML
    private void btnHandleSaveCustomerCeo(ActionEvent event) {

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

        ICustomer customer = new Customer(-1, name, address1, address2, address3, zipcode, city, country, telephone, email, taxNo);

        try {
            IValidationResult vr = validate(customer);
            if (!vr.hasNoError()) return;

            customerModel.createCustomer(customer);
            resetFieldsCustomer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleSaveTaskCeo(ActionEvent event) throws SQLException {

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
    private void btnHandleShowAllUsers(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneAllUsersCeo.setVisible(true);
        stackPaneCeoBtn.setVisible(true);
        tableViewAllUsersCeo.setVisible(true);
        stackpaneBtnEditUser.setVisible(true);

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
    private void btnHandleShowAllCustomers(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneViewAllCustomersCeo.setVisible(true);
        tableViewAllCustomersCeo.setVisible(true);
        stackpaneBtnEditCustomer.setVisible(true);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Name"));
        columnCustomerAddress.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Address1"));
        columnCustomerPhone.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Phone"));
        columnCustomerZipCode.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Zipcode"));
        columnCustomerCity.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("City"));

        try {
            tableViewAllCustomersCeo.setItems(customerModel.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleShowAllTasksCeo(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneViewAllCustomersTasks.setVisible(true);
        tableViewAllTasksCeo.setVisible(true);
        stackpaneBtnEditTask.setVisible(true);

        columnDate.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Date"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Description"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Status"));

        try {
            tableViewAllTasksCeo.setItems(customerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleShowAllInstallations(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneViewAllInstallations.setVisible(true);
        stackpaneBtnEditInstallation.setVisible(true);
        stackPaneCeoBtn.setVisible(true);
        tableViewAllInstallations.setVisible(true);

        columnInstallationNo.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("CustomerTaskId"));
        columnInstallationDescription.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("Description"));

        try {
            tableViewAllInstallations.setItems(installationModel.getAllInstallations());
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
    private void btnHandleBeginTask(ActionEvent event) throws IOException {

        IInstallation selectedInstallation = tableViewAllTasksTech.getSelectionModel().getSelectedItem();

        if (selectedInstallation != null) {

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
        System.out.println("" + selectedUser.getFirstName() + selectedUser.getLoginName());

    }

    @FXML
    private void btnHandleShowAllCustomersSales(ActionEvent event) {
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneSalesBtn.setVisible(true);
        stackPaneViewAllCustomersCeo.setVisible(true);
        tableViewAllCustomersCeo.setVisible(true);
        stackpaneBtnEditCustomer.setVisible(true);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Name"));
        columnCustomerAddress.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Address1"));
        columnCustomerPhone.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Phone"));
        columnCustomerZipCode.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("Zipcode"));
        columnCustomerCity.setCellValueFactory(new PropertyValueFactory<ICustomer, String>("City"));

        try {
            tableViewAllCustomersCeo.setItems(customerModel.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleShowAllFinishedTasksSales(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneSalesBtn.setVisible(true);
        tableViewAllCompletedTasks.setVisible(true);
        stackPaneViewAllCompletedTasks.setVisible(true);

        columnAllCompletedTasksNo.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("CustomerId"));
        columnAllCompletedTasksDescription.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Description"));

        try {
            tableViewAllCompletedTasks.setItems(customerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void btnHandleShowDocumentationSales (ActionEvent event){

    }

    @FXML
    private void btnHandleGenerateDocumentationSales (ActionEvent event){

    }

    @FXML
    private void btnHandleShowAllMyTasksPManager(ActionEvent event) {
        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPanePManagerBtn.setVisible(true);
        tableViewAllCompletedTasksPm.setVisible(true);
        stackPaneViewAllCompletedTasksPm.setVisible(true);

        columnAllCompletedTasksNoPm.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("CustomerId"));
        columnAllCompletedTasksDescriptionPm.setCellValueFactory(new PropertyValueFactory<ICustomerTask, String>("Description"));

        try {
            tableViewAllCompletedTasksPm.setItems(customerTaskModel.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleShowAllMyTasksTech(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneTechBtn.setVisible(true);
        stackPaneViewAllMyTasksTech.setVisible(true);
        tableViewAllTasksTech.setVisible(true);

        columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, Integer>("CustomerTaskId"));
        columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("Description"));

        try {
            tableViewAllTasksTech.setItems(installationModel.getInstallationsForUser(selectedUser));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnHandleShowAllTasks(ActionEvent event) {

    }

    @FXML
    private void btnHandleOpenInstallation(ActionEvent event) throws IOException {

        IInstallation selectedInstallation = tableViewAllInstallations.getSelectionModel().getSelectedItem();

        if (selectedInstallation != null) {

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
    private void btnHandleUpdateInstallation(ActionEvent event) {

    }

    @FXML
    private void btnHandleDeleteInstallation(ActionEvent event) {

    }

    @FXML
    private void btnHandleGenDocumentation(ActionEvent event) {

    }

    @FXML
    private void btnHandleUpdateTaskPManager(ActionEvent event) throws IOException {

        IInstallation selectedInstallation = tableViewAllTasksTech.getSelectionModel().getSelectedItem();

        if (selectedInstallation != null) {
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
    private void btnHandleGenerateDocument(ActionEvent event) throws MalformedURLException, DocumentNotFoundException, SQLException, FileNotFoundException {
        //  DocumentGeneration.documentGeneration();
    }

    @FXML
    private void btnHandleFinishTask(ActionEvent event) throws MalformedURLException, DocumentNotFoundException, SQLException, FileNotFoundException {
    }

    @FXML
    private void btnHandleDeleteCustomer(ActionEvent event) throws CustomerNotFoundException, SQLException {

        ICustomer selectedCustomer = tableViewAllCustomersCeo.getSelectionModel().getSelectedItem();
        customerModel.deleteCustomer(selectedCustomer.getId());

        btnHandleShowAllCustomers(null);
    }

    @FXML
    private void btnHandleDeleteTask(ActionEvent event) {

    }

    @FXML
    private void btnHandleDeleteUser(ActionEvent event) throws UserNotFoundException, SQLException {

        IUser selectedUser = tableViewAllUsersCeo.getSelectionModel().getSelectedItem();
        userModel.deleteUser(selectedUser.getId());
    }

    @FXML
    private void btnHandleUpdateCustomer(ActionEvent event) {

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
    private void btnHandleAssignInstallCEO(ActionEvent event) {

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
    private void btnHandleUpdateTask(ActionEvent event) {

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DocumentationView.fxml"));
        //Parent root = loader.load();
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.setTitle("selectedUser.getLoginName()");
        //stage.show();
    }

    @FXML
    private void btnHandleUpdateUser(ActionEvent event) {

    }

    @FXML
    private void btnHandleShowUpdateUser(ActionEvent event) {

        setAllStackPanesFalse();
        setAllTableViewsFalse();
        stackPaneCeoBtn.setVisible(true);
        stackPaneAddUserCeo.setVisible(true);

        selectedUser = tableViewAllUsersCeo.getSelectionModel().getSelectedItem();

        txtFieldLoginName.setText(selectedUser.getLoginName());
        txtFieldFirstName.setText(selectedUser.getFirstName());
        txtFieldLastName.setText(selectedUser.getLastName());
        txtFieldEmail.setText(selectedUser.getEMail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Setup(IUser selectedUser, LogInController logInController, UserModel userModel, int role) {

        if (role == 0) {
            setAllStackPanesFalse();
            setAllTableViewsFalse();
            stackPaneTechBtn.setVisible(true);
            stackPaneViewAllMyTasksTech.setVisible(true);
            tableViewAllTasksTech.setVisible(true);

            columnAllMyTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, Integer>("CustomerTaskId"));
            columnDescriptionTasksTech.setCellValueFactory(new PropertyValueFactory<IInstallation, String>("Description"));

            try {
                tableViewAllTasksTech.setItems(installationModel.getInstallationsForUser(selectedUser));
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

    private void createTask(ICustomer selectedCustomer) {
        // Get the user submitted data from the textfields and the datepicker.
        String Description = txtFieldDescriptionTask.getText();
        String Remarks = txtFieldRemarksTask.getText();
        LocalDateTime Date = localDateTimeSelector();
        // Create the new Customer Task and send it up through the layers
        ICustomerTask customerTask = new CustomerTask(0, Date, Description, Remarks, 0, selectedCustomer.getId());

        try {
            IValidationResult vr = validate(customerTask);
            if (!vr.hasNoError()) return;
            customerTaskModel.createCustomerTask(customerTask);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void techTaskLink(ICustomerTask selectedTask) throws SQLException {

        for (int i = 0; i < tableViewAddTaskTechAssigned.getItems().size(); i++) {
            IUser user = tableViewAddTaskTechAssigned.getItems().get(i);
            customerTaskModel.addUserToCustomerTask(user.getId(), selectedTask.getId());
        }
    }

    private LocalDateTime localDateTimeSelector() {
        LocalDate localDate = datePickerTask.getValue();
        Time time = new Time(8, 0, 0);

        LocalDateTime localDateTime = localDate.atTime(time.toLocalTime());

        return localDateTime;
    }

    private void createInstallation(ICustomerTask selectedTask, int installationNr) {

        String description = selectedTask.getDescription() + "Installation nr " + installationNr;

        IInstallation inst = new Installation(0, selectedTask.getId(), description, "Aktiv");
        IValidationResult vr = validate(inst);

        try {
            if (!vr.hasNoError()) return;
            installationModel.createInstallation(inst);
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
        tableViewAddTaskTechAvailable.setVisible(false);
        tableViewAddTaskAllCustomers.setVisible(false);
        tableViewAllInstallations.setVisible(false);
    }

    private void resetFieldsUser() {
        txtFieldLoginName.setText("");
        txtFieldFirstName.setText("");
        txtFieldLastName.setText("");
        txtFieldPassword.setText("");
        txtFieldPasswordRetype.setText("");
        txtFieldEmail.setText("");
        choiceBoxRoleCeo.setValue("");
    }

    private void resetFieldsCustomer() {

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

    private void resetFieldsTasks() {

        tableViewAddTaskAllCustomers.getSelectionModel().clearSelection();
        txtFieldDescriptionTask.setText("");
        txtFieldRemarksTask.setText("");
        datePickerTask.setValue(null);
        tableViewAddTaskTechAssigned.getItems().clear();

        // Add technicians to the Employee's avalible table


        columnAddTaskAvailableTech.setCellValueFactory(new PropertyValueFactory<IUser, String>("firstName"));
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
                tableViewAddTaskTechAvailable.setItems(filteredUsers);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private IValidationResult validate(ICustomer customer) {
        IValidationResult result = validationModel.validate(customer);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Name":
                        txtFieldCustomerName.getStyleClass().add("invalid");
                        break;
                    case "Address1":
                        txtFieldCustomerAddress.getStyleClass().add("invalid");
                        break;
                    case "Address2":
                        txtFieldCustomerAddress2.getStyleClass().add("invalid");
                        break;
                    case "Address3":
                        txtFieldCustomerAddress3.getStyleClass().add("invalid");
                        break;
                    case "Zipcode":
                        txtFieldCustomerZipCode.getStyleClass().add("invalid");
                        break;
                    case "City":
                        txtFieldCustomerCity.getStyleClass().add("invalid");
                        break;
                    case "Country":
                        txtFieldCustomerCountry.getStyleClass().add("invalid");
                        break;
                    case "Phone":
                        txtFieldCustomerTelephone.getStyleClass().add("invalid");
                        break;
                    case "Category":
                        txtFieldCustomerEmail.getStyleClass().add("invalid");
                        break;
                    case "TaxNo":
                        txtFieldCustomerTaxNo.getStyleClass().add("invalid");
                        break;
                }
            }
        }
        return result;
    }

    private IValidationResult validate(IUser user) {
        IValidationResult result = validationModel.validate(user);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "LoginName":
                        txtFieldLoginName.getStyleClass().add("invalid");
                        break;
                    case "FirstName":
                        txtFieldFirstName.getStyleClass().add("invalid");
                        break;
                    case "LastName":
                        txtFieldLastName.getStyleClass().add("invalid");
                        break;
                    case "Email":
                        txtFieldEmail.getStyleClass().add("invalid");
                        break;
                }
            }
        }
        return result;
    }

    private IValidationResult validate(ICustomerTask ct) {
        IValidationResult result = validationModel.validate(ct);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Description":
                        txtFieldDescriptionTask.getStyleClass().add("invalid");
                        break;
                    case "Remarks":
                        txtFieldRemarksTask.getStyleClass().add("invalid");
                        break;
                    case "Status":
                        throw new RuntimeException("Illigal status");
                }
            }
        }
        return result;
    }

    private IValidationResult validate(IInstallation inst) {
        IValidationResult result = validationModel.validate(inst);
        if (!result.hasNoError()) {
            for (String error : result.getErrors()) {
                switch (error) {
                    case "Description":
                        break;
                    case "Remarks":
                        break;
                }
            }
        }
        return result;
    }
}
