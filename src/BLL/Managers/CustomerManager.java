package BLL.Managers;

import BE.DBEnteties.Customer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import DAL.DB.CustomerDAO_DB;



import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager {
    public static ArrayList<Customer> getAllCustomers() throws Exception{
        return CustomerDAO_DB.getAllCustomers();
    }

    public static Customer createCustomer(Customer customer) throws Exception {

        return CustomerDAO_DB.createCustomer(customer);
    }

    public static void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption {

        CustomerDAO_DB.deleteCustomer(id);
    }

    }

