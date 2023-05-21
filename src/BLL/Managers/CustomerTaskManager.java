package BLL.Managers;

import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import DAL.DB.CustomerTaskDAO_DB;

import java.util.ArrayList;

public class CustomerTaskManager {
    public static ArrayList<CustomerTask> getAllCustomerTasks() throws Exception{
        return CustomerTaskDAO_DB.getAllCustomerTasks();
    }
}
