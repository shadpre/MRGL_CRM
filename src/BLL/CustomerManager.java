package BLL;

import BE.Customer;
import DAL.db.CustomerDAO_DB;


import java.util.ArrayList;

public class CustomerManager {

    public static ArrayList<Customer> getAllCustomers() throws Exception{
        return CustomerDAO_DB.getAllCustomers();
    }
}
