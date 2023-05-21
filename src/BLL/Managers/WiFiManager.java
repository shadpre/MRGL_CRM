package BLL.Managers;

import BE.DBEnteties.Device;
import BE.DBEnteties.WiFi;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;
import DAL.DB.DeviceDAO_DB;
import DAL.DB.WiFiDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class WiFiManager {
    public static WiFi createWiFi(WiFi wiFi) throws SQLException, WiFiNotFoundExeption{
        return WiFiDAO_DB.createWiFi(wiFi);
    }

    public static WiFi updateWiFi(WiFi wifi) throws SQLException, WiFiNotFoundExeption {
        return WiFiDAO_DB.updateWiFi(wifi);
    }

    public static ArrayList<WiFi> getWiFis(int installationID) throws SQLException, WiFiNotFoundExeption {
        return WiFiDAO_DB.getWiFis(installationID);
    }

    public static void deleteWiFi(int id) throws SQLException, WiFiNotFoundExeption {
        WiFiDAO_DB.deleteWiFi(id);
    }}
