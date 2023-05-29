package BLL.Interfaces;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerManager {
    ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundException;

    ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundException;

    ICustomer getCustomerById(int iD) throws SQLException, CustomerNotFoundException;

    ICustomer updateCustomer(ICustomer selectedCustomer) throws SQLException, CustomerNotFoundException;

    void deleteCustomer(int id) throws SQLException, CustomerNotFoundException;
}
