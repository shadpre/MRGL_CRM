package BLL.Managers;

import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.Interfaces.ICustomerTaskManager;
import DAL.DAO_DB.CustomerTaskDAO_DB;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerTaskManager implements ICustomerTaskManager {

    @Override
    public ArrayList<ICustomerTask> getAllCustomerTasks() throws Exception {
        return DBFacade.getInstance().getAllCustomerTasks();
    }

    @Override
    public ICustomerTask createCustomerTask(ICustomerTask customerTask) throws Exception {

        return DBFacade.getInstance().CreateCustomerTask(customerTask);
    }

    @Override
    public void addUserToCustomerTask(int userID, int customerTaskID) throws SQLException{
        DBFacade.getInstance().addUserToCustomerTask(userID, customerTaskID);
    }
}
