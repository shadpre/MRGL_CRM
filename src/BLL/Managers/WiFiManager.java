package BLL.Managers;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundException;
import BLL.Interfaces.IWiFiManager;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class WiFiManager implements IWiFiManager {
    @Override
    public IWiFi createWiFi(IWiFi wiFi) throws SQLException, WiFiNotFoundException {
        return DBFacade.getInstance().createWiFi(wiFi);
    }

    @Override
    public IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException {
        return DBFacade.getInstance().updateWiFi(wifi);
    }

    @Override
    public ArrayList<IWiFi> getWiFis(int installationID) throws SQLException {
        return DBFacade.getInstance().getWiFis(installationID);
    }

    @Override
    public void deleteWiFi(int id) throws SQLException, WiFiNotFoundException {
        DBFacade.getInstance().deleteWiFi(id);
    }
}
