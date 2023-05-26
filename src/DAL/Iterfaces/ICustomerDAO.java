package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICustomerDAO {
    ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption;

    ICustomer getCustomerByID(int ID) throws SQLException, CustomerNotFoundExeption;

    ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundExeption;

    ICustomer updateCustomer(ICustomer customer) throws CustomerNotFoundExeption, SQLException;

    void anonymizeCustomer(int ID) throws SQLException, CustomerNotFoundExeption;

    public void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption;
}
