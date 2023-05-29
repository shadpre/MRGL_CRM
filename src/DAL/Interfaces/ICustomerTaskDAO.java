package DAL.Interfaces;

import BE.DBEnteties.Interfaces.ICustomerTask;
import BE.Exptions.NotFoundExeptions.CustomerTaskNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerTaskDAO {
    /**
     * Creates a new customer task in the database.
     *
     * @param ct The customer task to create.
     * @return The created customer task.
     * @throws SQLException                    If a database access error occurs.
     * @throws CustomerTaskNotFoundException   If the customer task was not found.
     */
    ICustomerTask CreateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundException;
    /**
     * Adds a user to a customer task in the database.
     *
     * @param userId The ID of the user.
     * @param ctId   The ID of the customer task.
     * @throws SQLException If a database access error occurs.
     */
    void addUserToCustomerTask(int userId, int ctId) throws SQLException;
    /**
     * Retrieves a customer task from the database by its ID.
     *
     * @param id The ID of the customer task to retrieve.
     * @return The retrieved customer task.
     * @throws SQLException                    If a database access error occurs.
     * @throws CustomerTaskNotFoundException   If the customer task was not found.
     */
    ICustomerTask getCustomerTask(int id) throws SQLException, CustomerTaskNotFoundException;
    /**
     * Retrieves a list of all customer tasks from the database.
     *
     * @return An ArrayList of all customer tasks.
     * @throws SQLException                    If a database access error occurs.
     * @throws CustomerTaskNotFoundException   If no customer tasks were found.
     */
    ArrayList<ICustomerTask> getAllCustomerTasks() throws SQLException, CustomerTaskNotFoundException;
    /**
     * Retrieves a list of all customer tasks assigned to a specific user from the database.
     *
     * @param userId The ID of the user.
     * @return An ArrayList of customer tasks assigned to the user.
     * @throws SQLException If a database access error occurs.
     */
    ArrayList<ICustomerTask> getAllUserCustomerTasks(int userId) throws SQLException;
    /**
     * Retrieves a list of all customer tasks associated with a specific customer from the database.
     *
     * @param customerId The ID of the customer.
     * @return An ArrayList of customer tasks associated with the customer.
     * @throws SQLException                    If a database access error occurs.
     * @throws CustomerTaskNotFoundException   If no customer tasks were found for the customer.
     */
    ArrayList<ICustomerTask> getAllCustomerTasks(int customerId) throws SQLException, CustomerTaskNotFoundException;
    /**
     * Updates an existing customer task in the database.
     *
     * @param ct The customer task to update.
     * @return The updated customer task.
     * @throws SQLException                    If a database access error occurs.
     * @throws CustomerTaskNotFoundException   If the customer task was not found.
     */
    ICustomerTask updateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundException;
    /**
     * Deletes a customer task from the database.
     *
     * @param ID The ID of the customer task to delete.
     * @throws SQLException                    If a database access error occurs.
     */
    void deleteCustomerTask(int ID) throws SQLException;
}
