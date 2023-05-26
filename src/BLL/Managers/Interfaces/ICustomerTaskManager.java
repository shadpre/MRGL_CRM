package BLL.Managers.Interfaces;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Interfaces.ICustomerTask;
import DAL.DB.CustomerTaskDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerTaskManager {


    ICustomerTask createCustomerTask(ICustomerTask customerTask) throws Exception;

    void addUserToCustomerTask(int userID, int customerTaskID) throws SQLException;

    ArrayList<ICustomerTask> getAllCustomerTasks() throws Exception;

}
