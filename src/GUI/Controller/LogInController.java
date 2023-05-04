package GUI.Controller;

import BE.Exptions.UserValidationExeption;
import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController extends BaseController implements Initializable {
    private UserModel userModel;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            userModel = new UserModel();
            this.userModel = userModel;



    }

    public void handleLogging(ActionEvent actionEvent) throws Exception {

        String LoginName = txtUsername.getText();
        String Password = txtPassword.getText();

        User selectedUser = userModel.getUser(LoginName, Password);
        userModel.setSelectedUser(selectedUser);

        if (selectedUser != null){
            userModel.setSelectedUser(selectedUser);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(selectedUser.getLoginName());
            stage.show();
        }

        else{
            throw new UserValidationExeption("Invalid Username or password");
        }
    }
}
