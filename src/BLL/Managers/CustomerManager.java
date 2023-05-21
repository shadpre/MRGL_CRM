package BLL.Managers;

import BE.DBEnteties.Customer;
import BE.DBEnteties.User;
import BLL.PasswordHash;
import DAL.DB.CustomerDAO_DB;
import DAL.DB.UserDAO_DB;


import java.util.ArrayList;

public class CustomerManager {
    public static ArrayList<Customer> getAllCustomers() throws Exception{
        return CustomerDAO_DB.getAllCustomers();
    }

    public static Customer createCustomer(Customer customer) throws Exception{

            return CustomerDAO_DB.createCustomer(customer);
        }

       // public static deleteCustomer(Customer selectedCustomer) throws Exception{


        //}

    }

