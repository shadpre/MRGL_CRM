package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDeviceDAO {
    IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption;

    IDevice getDevice(int id) throws SQLException, DeviceNotFoundExeption;

    ArrayList<IDevice> getDeviceList(int installationId) throws SQLException;

    IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption;

    void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption;

    int deleteDevices(int installationId) throws SQLException, DeviceNotFoundExeption;
}
