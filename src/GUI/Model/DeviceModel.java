package GUI.Model;

import BE.DBEnteties.Device;
import BE.DBEnteties.Interfaces.IDevice;
import BLL.Interfaces.IDeviceManager;
import BLL.Managers.DeviceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceModel {

    public static ObservableList<IDevice> getDeviceList(int installationID) {
        IDeviceManager deviceManager = new DeviceManager();
        ObservableList<IDevice> out;
        try {
            out = FXCollections.observableArrayList(deviceManager.getDeviceList(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

}
