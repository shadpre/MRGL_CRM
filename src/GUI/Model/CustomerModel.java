package GUI.Model;

import BE.DBEnteties.Customer;
import BLL.Managers.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerModel {
    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer>  out;
        try {
            out =  FXCollections.observableArrayList(CustomerManager.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
