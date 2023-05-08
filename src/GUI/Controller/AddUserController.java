package GUI.Controller;

import BE.User;
import BLL.UserManager;
import DAL.db.UserDAO_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController {

    private UserManager userManager;

    private UserDAO_DB userDAO_db;
    @FXML
    private Button btnSave;

    @FXML
    private CheckBox chkBoxAdmin;

    @FXML
    private CheckBox chkBoxCeo;

    @FXML
    private CheckBox chkBoxSales;

    @FXML
    private CheckBox chkBoxTech;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldLoginName;

    private LogInController logInController;
    private MainViewController mainViewController;

    @FXML
    void btnHandleSave(ActionEvent event) {

        // Get User Information
        String loginName = txtFieldLoginName.getText();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        int RoleValue = 4;
        int role = RoleValue;


        //chkBoxTech.setUserData(0);
        //chkBoxCeo.setUserData(2);
        //chkBoxSales.setUserData(3);
        //chkBoxAdmin.setUserData(1);

        if (chkBoxTech.isSelected()) {
            RoleValue = 0;
        } else if (chkBoxAdmin.isSelected()) {
            RoleValue = 1;
        } else if (chkBoxCeo.isSelected()) {
            RoleValue = 2;
        } else if (chkBoxSales.isSelected()) {
            RoleValue = 3;
        } else { System.out.println("Du skal vælge en Rolle til Medarbejderen");

        }
        User user = new User(0, loginName, firstName, lastName, email, role);
        try {
            userManager.createUser(user, "123" );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public void initialize (URL location, ResourceBundle resources){
        try {
            userManager = new UserManager();
            userDAO_db = new UserDAO_DB();
            mainViewController = new MainViewController();
            logInController = new LogInController();


            this.userManager = userManager;
            this.userDAO_db = userDAO_db;
            this.mainViewController = new MainViewController();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
