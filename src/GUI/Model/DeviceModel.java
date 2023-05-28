package GUI.Model;

import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import BLL.Interfaces.IDeviceManager;
import BLL.Managers.DeviceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class DeviceModel {
    private final IDeviceManager deviceManager = new DeviceManager();

    public ObservableList<IDevice> getDeviceList(int installationID) {
        ObservableList<IDevice> out;
        try {
            out = FXCollections.observableArrayList(deviceManager.getDeviceList(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption {
        deviceManager.deleteDevice(id);
    }

    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return deviceManager.createDevice(device);
    }

    public IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return deviceManager.updateDevice(device);
    }
}
