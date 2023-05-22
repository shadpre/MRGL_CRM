package BLL.Managers;

import BE.DBEnteties.Device;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import DAL.DB.DeviceDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceManager {

    public static Device createDevice(Device device) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.createDevice(device);

    }

    public static Device updateDevice(Device device) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.updateDevice(device);
    }

    public static ArrayList<Device> getDeviceList(int installationID) throws SQLException {
        return DeviceDAO_DB.getDeviceList(installationID);
    }

    public static void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption {
         DeviceDAO_DB.deleteDevice(id);
    }
}
