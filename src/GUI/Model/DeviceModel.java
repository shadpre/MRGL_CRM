package GUI.Model;

import BE.DBEnteties.Customer;
import BE.DBEnteties.Device;
import BLL.Managers.CustomerManager;
import BLL.Managers.DeviceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceModel {

    public static ObservableList<Device> getDeviceList(int installationID){
        ObservableList<Device>  out;
        try {
            out =  FXCollections.observableArrayList(DeviceManager.getDeviceList(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

}
