package GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreateOrderController {

    @FXML
    private Button btnCreateOrder;

    @FXML
    private ChoiceBox<?> choiseBoxOrderType;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerRecieved;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private Label lblOrderNbr;

    @FXML
    private TextField txtAreaNotes;

    @FXML
    private TextField txtAreaTaskDescription;

    @FXML
    private TextField txtFieldAddress;

    @FXML
    private TextField txtFieldCity;

    @FXML
    private TextField txtFieldCompany;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldPostalCode;

    @FXML
    private TextField txtFieldTaxNo;

    @FXML
    private TextField txtFieldTelefone;
}
