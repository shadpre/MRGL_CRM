package BLL.Managers.Interfaces;

import BE.DBEnteties.Customer;
import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import DAL.DB.CustomerDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerManager {
    ICustomer createCustomer(ICustomer customer) throws Exception;

    ArrayList<ICustomer> getAllCustomers() throws Exception;

    ICustomer updateCustomer(ICustomer selectedCustomer) throws Exception;

    void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption;
}
