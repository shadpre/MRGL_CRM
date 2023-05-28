package GUI.Model;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.Interfaces.ICustomerManager;
import BLL.Interfaces.ICustomerTaskManager;
import BLL.Managers.CustomerTaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerTaskModel {
    private ICustomerTaskManager customerTaskManager = new CustomerTaskManager();
    public ObservableList<ICustomerTask> getAllCustomerTasks() {
        ObservableList<ICustomerTask> out;
        try {
            out = FXCollections.observableArrayList(customerTaskManager.getAllCustomerTasks());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public ICustomerTask createCustomerTask(ICustomerTask ct) throws Exception {
        return customerTaskManager.createCustomerTask(ct);
    }

    public void addUserToCustomerTask(int userId, int ctId) throws SQLException {
        customerTaskManager.addUserToCustomerTask(userId,ctId);
    }
}
