package BLL.Managers;

import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.Managers.Interfaces.ICustomerTaskManager;
import DAL.DB.CustomerTaskDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerTaskManager implements ICustomerTaskManager {

    @Override
    public ArrayList<ICustomerTask> getAllCustomerTasks() throws Exception {
        return CustomerTaskDAO_DB.getAllCustomerTasks();
    }

    @Override
    public ICustomerTask createCustomerTask(ICustomerTask customerTask) throws Exception {

        return CustomerTaskDAO_DB.CreateCustomerTask(customerTask);
    }

    @Override
    public void addUserToCustomerTask(int userID, int customerTaskID) throws SQLException{
        CustomerTaskDAO_DB.addUserToCustomerTask(userID, customerTaskID);
    }
}
