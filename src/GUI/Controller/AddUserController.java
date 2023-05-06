package GUI.Controller;

import BLL.UserManager;
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

    @FXML
    void btnHandleSave(ActionEvent event) {

        // Get User Information
        String loginName = txtFieldLoginName.getText();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        int role = 0;


        chkBoxTech.isSelected();
        chkBoxCeo.isSelected();
        chkBoxSales.isSelected();
        chkBoxAdmin.isSelected();



    }
    public void initialize (URL location, ResourceBundle resources){
        try {
            userManager = new UserManager();
            this.userManager = userManager;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
