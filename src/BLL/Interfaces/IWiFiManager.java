package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IWiFiManager {
    IWiFi createWiFi(IWiFi wiFi) throws SQLException, WiFiNotFoundException;

    IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException;

    ArrayList<IWiFi> getWiFis(int installationID) throws SQLException;

    void deleteWiFi(int id) throws SQLException, WiFiNotFoundException;
}
