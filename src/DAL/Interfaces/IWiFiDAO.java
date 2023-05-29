package DAL.Interfaces;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * The WiFiDAO_DB class implements the IWiFiDAO interface and provides methods for accessing and manipulating WiFi data in the database.
 */
public interface IWiFiDAO {
    /**
     * Creates a new WiFi entry in the database with the provided WiFi object.
     *
     * @param wifi The WiFi object to create.
     * @return The created WiFi object.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the created WiFi entry is not found.
     */
    IWiFi createWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException;
    /**
     * Retrieves a WiFi entry from the database with the specified ID.
     *
     * @param id The ID of the WiFi entry to retrieve.
     * @return The retrieved WiFi object.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the WiFi entry with the specified ID is not found.
     */
    IWiFi getWifi(int id) throws SQLException, WiFiNotFoundException;
    /**
     * Retrieves all WiFi entries associated with a specific installation ID.
     *
     * @param installationId The ID of the installation to retrieve WiFi entries for.
     * @return An ArrayList of WiFi objects associated with the installation.
     * @throws SQLException If an SQL exception occurs while executing the query.
     */
    ArrayList<IWiFi> getWiFis(int installationId) throws SQLException;
    /**
     * Updates an existing WiFi entry in the database with the provided WiFi object.
     *
     * @param wifi The WiFi object to update.
     * @return The updated WiFi object.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the WiFi entry to update is not found.
     */
    IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException;
    /**
     * Deletes a WiFi entry from the database with the specified ID.
     *
     * @param id The ID of the WiFi entry to delete.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If the WiFi entry with the specified ID is not found.
     */
    void deleteWiFi(int id) throws SQLException, WiFiNotFoundException;
    /**
     * Deletes all WiFi entries associated with a specific installation ID.
     *
     * @param installationId The ID of the installation to delete WiFi entries for.
     * @return The number of WiFi entries deleted.
     * @throws SQLException             If an SQL exception occurs while executing the query.
     * @throws WiFiNotFoundException     If no WiFi entries are found for the installation ID.
     */
    int deleteWiFis(int installationId) throws SQLException, WiFiNotFoundException;
}
