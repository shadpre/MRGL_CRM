package BLL.Managers;

import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import BLL.Interfaces.IDeviceManager;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceManager implements IDeviceManager {

    @Override
    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return DBFacade.getInstance().createDevice(device);
    }

    @Override
    public ArrayList<IDevice> getDeviceList(int installationID) throws SQLException {
        return DBFacade.getInstance().getDeviceList(installationID);
    }

    @Override
    public IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return DBFacade.getInstance().updateDevice(device);
    }

    @Override
    public void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption {
        DBFacade.getInstance().deleteDevice(id);
    }
}
