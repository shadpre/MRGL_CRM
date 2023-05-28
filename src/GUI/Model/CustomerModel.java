package GUI.Model;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import BLL.Interfaces.ICustomerManager;
import BLL.Managers.CustomerManager;
import DAL.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerModel {
    private ICustomerManager customerManager = new CustomerManager();
    public ObservableList<ICustomer> getAllCustomers() {

        ObservableList<ICustomer> out;
        try {
            out = FXCollections.observableArrayList(customerManager.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public ICustomer updateCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption {
        return customerManager.updateCustomer(customer);
    }

    public ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption {
        return customerManager.createCustomer(customer);
    }

    public void deleteCustomer (int customerId) throws SQLException, CustomerNotFoundExeption {
        customerManager.deleteCustomer(customerId);
    }
}
