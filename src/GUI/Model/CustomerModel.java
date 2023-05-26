package GUI.Model;

import BE.DBEnteties.Interfaces.ICustomer;
import DAL.DB.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerModel {
    public static ObservableList<ICustomer> getAllCustomers(){
        ObservableList<ICustomer>  out;
        try {
            out =  FXCollections.observableArrayList(DBFacade.getInstance().getAllCustomers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }


}
