package GUI.Model;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.Interfaces.ICustomerTaskManager;
import BLL.Managers.CustomerTaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerTaskModel {
    public static ObservableList<ICustomerTask> getAllCustomerTasks() {
        ICustomerTaskManager customerTaskManager = new CustomerTaskManager();

        ObservableList<ICustomerTask> out;
        try {
            out = FXCollections.observableArrayList(customerTaskManager.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

}
