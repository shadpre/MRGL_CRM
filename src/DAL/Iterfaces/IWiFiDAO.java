package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IWiFiDAO {
    IWiFi createWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundExeption;

    IWiFi getWifi(int id) throws SQLException, WiFiNotFoundExeption;

    ArrayList<IWiFi> getWiFis(int installationId) throws SQLException;

    IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundExeption;

    void deleteWiFi(int id) throws SQLException, WiFiNotFoundExeption;

    int deleteWiFis(int installationId) throws SQLException, WiFiNotFoundExeption;
}
