package BLL.Managers;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import BLL.Interfaces.ICustomerManager;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager implements ICustomerManager {

    @Override
    public ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption {
        return DBFacade.getInstance().createCustomer(customer);
    }

    @Override
    public ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundExeption {
        return DBFacade.getInstance().getAllCustomers();
    }

    public ICustomer getCustomerById(int id) throws SQLException, CustomerNotFoundExeption {
        return DBFacade.getInstance().getCustomerByID(id);
    }

    @Override
    public ICustomer updateCustomer(ICustomer selectedCustomer) throws SQLException, CustomerNotFoundExeption {
        return DBFacade.getInstance().updateCustomer(selectedCustomer);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption {
        DBFacade.getInstance().deleteCustomer(id);
    }

}

