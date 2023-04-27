package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController extends BaseController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldUserName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
