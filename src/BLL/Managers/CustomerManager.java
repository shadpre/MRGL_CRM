package BLL.Managers;

import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import BLL.Managers.Interfaces.ICustomerManager;
import DAL.DB.DBFacade;


import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager implements ICustomerManager {

    @Override
    public ICustomer createCustomer(ICustomer customer) throws Exception {
        return DBFacade.getInstance().createCustomer(customer);
    }

    @Override
    public ArrayList<ICustomer> getAllCustomers() throws Exception {
        return DBFacade.getInstance().getAllCustomers();
    }

    @Override
    public ICustomer updateCustomer(ICustomer selectedCustomer) throws Exception {
        return DBFacade.getInstance().updateCustomer(selectedCustomer);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption{
        DBFacade.getInstance().deleteCustomer(id);
    }

}

