package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.ICustomerTask;
import BE.Exptions.NotFoundExeptions.CustomerTaskNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerTaskDAO {
    ICustomerTask CreateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundExeption;

    void addUserToCustomerTask(int userId, int ctId) throws SQLException;

    ICustomerTask getCustomerTask(int id) throws SQLException, CustomerTaskNotFoundExeption;

    ArrayList<ICustomerTask> getAllCustomerTasks() throws SQLException, CustomerTaskNotFoundExeption;

    ArrayList<ICustomerTask> getAllUserCustomerTasks(int userId) throws SQLException;

    ArrayList<ICustomerTask> getAllCustomerTasks(int customerId) throws SQLException, CustomerTaskNotFoundExeption;

    ICustomerTask updateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundExeption;

    void deleteCustomerTask(int ID);
}
