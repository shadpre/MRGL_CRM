package BLL.Managers;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;
import BLL.Managers.Interfaces.IWiFiManager;
import DAL.DB.WiFiDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class WiFiManager implements IWiFiManager {
    @Override
    public IWiFi createWiFi(IWiFi wiFi) throws SQLException, WiFiNotFoundExeption{
        return WiFiDAO_DB.createWiFi(wiFi);
    }

    @Override
    public IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundExeption {
        return WiFiDAO_DB.updateWiFi(wifi);
    }

    @Override
    public ArrayList<IWiFi> getWiFis(int installationID) throws SQLException {
        return WiFiDAO_DB.getWiFis(installationID);
    }

    @Override
    public void deleteWiFi(int id) throws SQLException, WiFiNotFoundExeption {
        WiFiDAO_DB.deleteWiFi(id);
    }
}
