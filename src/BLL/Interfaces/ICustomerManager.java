package BLL.Interfaces;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerManager {
    ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption;

    ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundExeption;

    ICustomer getCustomerById(int iD) throws SQLException, CustomerNotFoundExeption;

    ICustomer updateCustomer(ICustomer selectedCustomer) throws SQLException, CustomerNotFoundExeption;

    void deleteCustomer(int id) throws SQLException, SQLException, CustomerNotFoundExeption;
}
