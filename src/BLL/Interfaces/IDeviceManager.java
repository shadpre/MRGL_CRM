package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDeviceManager {
    IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundException;

    ArrayList<IDevice> getDeviceList(int installationID) throws SQLException;

    IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundException;

    void deleteDevice(int id) throws SQLException, DeviceNotFoundException;
}
