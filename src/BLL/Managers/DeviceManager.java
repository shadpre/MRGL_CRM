package BLL.Managers;

import BE.DBEnteties.Device;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import DAL.DB.DeviceDAO_DB;

import java.sql.SQLException;

public class DeviceManager {

    public static Device createDevice(Device device) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.createDevice(device);

    }
}
