package GUI.Controller;

import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController extends BaseController implements Initializable {
    private UserModel userModel;

    private MainView2Controller mainView2Controller;

    private IUser selectedUser;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = new UserModel();
        this.userModel = userModel;
        mainView2Controller = new MainView2Controller();
        this.mainView2Controller = mainView2Controller;

    }

    public void handleLogging(ActionEvent actionEvent) throws Exception {

        String loginName = txtUsername.getText();
        String password = txtPassword.getText();

        IUser selectedUser = userModel.getUser(loginName, password);

        if (selectedUser != null) {

            userModel.setSelectedUser(selectedUser);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/MainView2.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(selectedUser.getLoginName());
            stage.show();

            MainView2Controller controller = loader.getController();
            controller.setUserModel(userModel);
            controller.Setup(selectedUser, this, userModel, selectedUser.getRole());


        } else {
            throw new Exception("Invalid Username or password");
        }
    }

    public IUser setSelectedUser(IUser selectedUser) {
        return selectedUser;
    }
}
