package BLL.Managers;

import BE.DBEnteties.Customer;
import DAL.DB.CustomerDAO_DB;


import java.util.ArrayList;

public class CustomerManager {
    public static ArrayList<Customer> getAllCustomers() throws Exception{
        return CustomerDAO_DB.getAllCustomers();
    }
}
