package BLL.Managers.Interfaces;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.DBEnteties.WiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IWiFiManager {
    IWiFi createWiFi(IWiFi wiFi) throws SQLException, WiFiNotFoundExeption;

    IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundExeption;

    ArrayList<IWiFi> getWiFis(int installationID) throws SQLException;

    void deleteWiFi(int id) throws SQLException, WiFiNotFoundExeption;
}
