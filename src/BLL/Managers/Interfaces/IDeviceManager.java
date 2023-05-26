package BLL.Managers.Interfaces;

import BE.DBEnteties.Device;
import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import DAL.DB.DeviceDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDeviceManager {
    IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption;

    ArrayList<IDevice> getDeviceList(int installationID) throws SQLException;

    IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption;

    void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption;
}
