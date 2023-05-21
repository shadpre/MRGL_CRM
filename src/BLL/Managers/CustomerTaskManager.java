package BLL.Managers;

import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import DAL.DB.CustomerDAO_DB;
import DAL.DB.CustomerTaskDAO_DB;

import java.util.ArrayList;

public class CustomerTaskManager {
    public static ArrayList<CustomerTask> getAllCustomerTasks() throws Exception{
        return CustomerTaskDAO_DB.getAllCustomerTasks();
    }

    public static CustomerTask createCustomerTask(CustomerTask customerTask) throws Exception{

        return CustomerTaskDAO_DB.CreateCustomerTask(customerTask);
    }

}
