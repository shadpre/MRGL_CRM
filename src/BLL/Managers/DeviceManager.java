package BLL.Managers;

import BE.DBEnteties.Device;
import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import BLL.Managers.Interfaces.IDeviceManager;
import DAL.DB.DeviceDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceManager implements IDeviceManager {

    @Override
    public Device createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.createDevice(device);
    }

    @Override
    public ArrayList<IDevice> getDeviceList(int installationID) throws SQLException {
        return DeviceDAO_DB.getDeviceList(installationID);
    }

    @Override
    public Device updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption{
        return DeviceDAO_DB.updateDevice(device);
    }

    @Override
    public void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption {
        DeviceDAO_DB.deleteDevice(id);
    }
}
