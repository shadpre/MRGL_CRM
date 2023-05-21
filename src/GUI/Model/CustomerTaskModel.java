package GUI.Model;

import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BLL.Managers.CustomerManager;
import BLL.Managers.CustomerTaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerTaskModel {
    public static ObservableList<CustomerTask> getAllCustomerTasks(){
        ObservableList<CustomerTask>  out;
        try {
            out =  FXCollections.observableArrayList(CustomerTaskManager.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

}
