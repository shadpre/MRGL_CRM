package DAL.Interfaces;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerDAO {
    /**
     * Creates a new customer in the database.
     *
     * @param customer The customer to create.
     * @return The created customer.
     * @throws SQLException              If a database access error occurs.
     * @throws CustomerNotFoundException If the customer was not found.
     */
    ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundException;
    /**
     * Retrieves a customer by their ID from the database.
     *
     * @param ID The ID of the customer.
     * @return The retrieved customer.
     * @throws SQLException              If a database access error occurs.
     * @throws CustomerNotFoundException If the customer was not found.
     */
    ICustomer getCustomerByID(int ID) throws SQLException, CustomerNotFoundException;
    /**
     * Retrieves a list of all customers from the database.
     *
     * @return An ArrayList of all customers.
     * @throws SQLException              If a database access error occurs.
     * @throws CustomerNotFoundException If no customers were found.
     */
    ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundException;
    /**
     * Updates an existing customer in the database.
     *
     * @param customer The customer to update.
     * @return The updated customer.
     * @throws CustomerNotFoundException If the customer was not found.
     * @throws SQLException              If a database access error occurs.
     */
    ICustomer updateCustomer(ICustomer customer) throws CustomerNotFoundException, SQLException;
    /**
     * Anonymizes a customer in the database.
     *
     * @param id The ID of the customer to anonymize.
     * @throws SQLException              If a database access error occurs.
     * @throws CustomerNotFoundException If the customer was not found.
     */
    void anonymizeCustomer(int id) throws SQLException, CustomerNotFoundException;
    /**
     * Deletes a customer and (sub)relations from the database.
     *
     * @param id The ID of the customer to delete.
     * @throws SQLException              If a database access error occurs.
     * @throws CustomerNotFoundException If the customer was not found.
     */
    void deleteCustomer(int id) throws SQLException, CustomerNotFoundException;
}
