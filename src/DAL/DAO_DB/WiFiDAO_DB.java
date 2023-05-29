package DAL.DAO_DB;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.DBEnteties.WiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundException;
import DAL.DatabaseConnector;
import DAL.Interfaces.IWiFiDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The WiFiDAO_DB class implements the IWiFiDAO interface and provides methods for accessing and manipulating WiFi data in the database.
 */
public class WiFiDAO_DB implements IWiFiDAO {
    /**
     * Creates a new WiFi entry in the database with the provided WiFi object.
     *
     * @param wifi The WiFi object to create.
     * @return The created WiFi object.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the created WiFi entry is not found.
     */
    @Override
    public IWiFi createWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO WiFis (InstallationID, Description, Remarks, SSID, PSK) VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, wifi.getInstallationId());
            statement.setString(2, wifi.getDescription());
            statement.setString(3, wifi.getRemarks());
            statement.setString(4, wifi.getSSID());
            statement.setString(5, wifi.getPSK());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("Wifi not saved");
        }
        return getWifi(ID);
    }

    /**
     * Retrieves a WiFi entry from the database with the specified ID.
     *
     * @param id The ID of the WiFi entry to retrieve.
     * @return The retrieved WiFi object.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the WiFi entry with the specified ID is not found.
     */
    @Override
    public IWiFi getWifi(int id) throws SQLException, WiFiNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "Select InstallationId, Description, Remarks, SSID, PSK FROM WiFis WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()) {
                return new WiFi(
                        id,
                        rs.getInt("InstallationId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("SSID"),
                        rs.getString("PSK")
                );
            } else throw new WiFiNotFoundException("WiFi not found");
        }
    }
    /**
     * Retrieves all WiFi entries associated with a specific installation ID.
     *
     * @param installationId The ID of the installation to retrieve WiFi entries for.
     * @return An ArrayList of WiFi objects associated with the installation.
     * @throws SQLException If an SQL exception occurs while executing the query.
     */
    @Override
    public ArrayList<IWiFi> getWiFis(int installationId) throws SQLException {
        ArrayList<IWiFi> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "Select Id, Description, Remarks, SSID, PSK FROM WiFis WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new WiFi(
                        rs.getInt("Id"),
                        installationId,
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("SSID"),
                        rs.getString("PSK")
                ));
            }
            return out;
        }
    }
    /**
     * Updates an existing WiFi entry in the database with the provided WiFi object.
     *
     * @param wifi The WiFi object to update.
     * @return The updated WiFi object.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the WiFi entry to update is not found.
     */
    @Override
    public IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE WiFis " +
                    "SET  InstallationId = ?, Description = ?, Remarks = ?, SSID =?, PSK =? " +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, wifi.getInstallationId());
            statement.setString(2, wifi.getDescription());
            statement.setString(3, wifi.getRemarks());
            statement.setString(4, wifi.getSSID());
            statement.setString(5, wifi.getPSK());
            statement.setInt(6, wifi.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new WiFiNotFoundException("WiFi not found");
        }
        return getWifi(wifi.getId());
    }
    /**
     * Deletes a WiFi entry from the database with the specified ID.
     *
     * @param id The ID of the WiFi entry to delete.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the WiFi entry with the specified ID is not found.
     */
    @Override
    public void deleteWiFi(int id) throws SQLException, WiFiNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE WiFis WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new WiFiNotFoundException("WiFi not found");
        }
    }
    /**
     * Deletes all WiFi entries associated with a specific installation ID.
     *
     * @param installationId The ID of the installation to delete WiFi entries for.
     * @return The number of WiFi entries deleted.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If no WiFi entries are found for the installation ID.
     */
    @Override
    public int deleteWiFis(int installationId) throws SQLException, WiFiNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE WiFis WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new WiFiNotFoundException("No WiFis found");
            else return rs;
        }
    }
}
