package GUI.Model;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundException;
import BLL.Interfaces.ICustomerManager;
import BLL.Managers.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerModel {
    private final ICustomerManager customerManager = new CustomerManager();

    public ObservableList<ICustomer> getAllCustomers() {

        ObservableList<ICustomer> out;
        try {
            out = FXCollections.observableArrayList(customerManager.getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public ICustomer updateCustomer(ICustomer customer) throws SQLException, CustomerNotFoundException {
        return customerManager.updateCustomer(customer);
    }

    public ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundException {
        return customerManager.createCustomer(customer);
    }

    public void deleteCustomer(int customerId) throws SQLException, CustomerNotFoundException {
        customerManager.deleteCustomer(customerId);
    }
}
