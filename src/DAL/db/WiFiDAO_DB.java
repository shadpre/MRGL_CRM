package DAL.db;

import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;
import BE.WiFi;

import java.sql.*;
import java.util.ArrayList;

public class WiFiDAO_DB {
    public static WiFi createWiFi(WiFi wifi) throws SQLException, WiFiNotFoundExeption{
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO WiFI (InstallationID, Description, Remarks, SSID, PSK VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, wifi.getInstallationId());
            statement.setString(2, wifi.getDescription());
            statement.setString(3, wifi.getRemarks());
            statement.setString(4, wifi.getSSID());
            statement.setString(5, wifi.getPSK());

            var rs = statement.executeQuery();

            if (rs.next()){
                ID = rs.getInt(1);
            }
            else throw new SQLDataException("Wifi not saved");
        }
        return getWifi(ID);
    }

    public static WiFi getWifi(int id) throws SQLException, WiFiNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "Select InstallationId, Description, Remarks, SSID, PSK FROM WiFi WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()){
                return new WiFi(
                        id,
                        rs.getInt("InstallationId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("SSID"),
                        rs.getString("PSK")
                );
            }
            else throw new WiFiNotFoundExeption("WiFi not found");
        }
    }

    public static ArrayList<WiFi> getWifis(int installationId) throws SQLException, WiFiNotFoundExeption{
        ArrayList<WiFi> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "Select Id, Description, Remarks, SSID, PSK FROM WiFi WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,installationId);

            var rs = statement.executeQuery();

            while (rs.next()){
                out.add(new WiFi(
                        rs.getInt("Id"),
                        installationId,
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("SSID"),
                        rs.getString("PSK")
                ));
            }

            if (out.size() == 0) throw new WiFiNotFoundExeption("No WiFis found");
            else return out;
        }
    }

    public static WiFi updateWiFi(WiFi wifi) throws SQLException, WiFiNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "";
        }
    }
}
