package DAL.DAO_DB;

import BE.DBEnteties.Device;
import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundException;
import DAL.DatabaseConnector;
import DAL.Interfaces.IDeviceDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The DeviceDAO_DB class is responsible for performing database operations related to devices.
 */
public class DeviceDAO_DB implements IDeviceDAO {

    /**
     * Creates a new device in the database.
     *
     * @param device The device to create.
     * @return The created device.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    @Override
    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundException {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO Devices (InstallationId, Description, Remarks, IP, SubnetMask, Username, Password, IsPOE) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, device.getInstallationId());
            statement.setString(2, device.getDescription());
            statement.setString(3, device.getRemarks());
            statement.setString(4, device.getIP());
            statement.setString(5, device.getSubnetMask());
            statement.setString(6, device.getUserName());
            statement.setString(7, device.getPassword());
            statement.setBoolean(8, device.isPOE());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("Device not saved");
        }
        return getDevice(ID);
    }

    /**
     * Retrieves a device from the database by its ID.
     *
     * @param id The ID of the device to retrieve.
     * @return The retrieved device.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    @Override
    public IDevice getDevice(int id) throws SQLException, DeviceNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT InstallationId, Description, Remarks, IP, SubnetMask, Username, Password, IsPOE FROM Devices WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();
            if (rs.next()) {
                return new Device(
                        id,
                        rs.getInt("InstallationId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("IP"),
                        rs.getString("SubnetMask"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getBoolean("IsPOE")
                );
            } else throw new DeviceNotFoundException("Device not found");
        }
    }

    /**
     * Retrieves a list of devices associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return An ArrayList of devices associated with the installation.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public ArrayList<IDevice> getDeviceList(int installationId) throws SQLException {
        ArrayList<IDevice> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, Description, Remarks, IP, SubnetMask, Username, Password, IsPOE FROM Devices WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new Device(
                        rs.getInt("Id"),
                        installationId,
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("IP"),
                        rs.getString("SubnetMask"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getBoolean("IsPOE")
                ));
            }
            return out;
        }
    }

    /**
     * Updates an existing device in the database.
     *
     * @param device The device to update.
     * @return The updated device.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    @Override
    public IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query =
                    "UPDATE Devices " +
                            "SET InstallationId = ?, Description = ?, Remarks = ?, IP = ?, SubnetMask = ?, UserName = ?, Password = ?, IsPOE = ? " +
                            "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, device.getInstallationId());
            statement.setString(2, device.getDescription());
            statement.setString(3, device.getRemarks());
            statement.setString(4, device.getIP());
            statement.setString(5, device.getSubnetMask());
            statement.setString(6, device.getUserName());
            statement.setString(7, device.getPassword());
            statement.setBoolean(8, device.isPOE());
            statement.setInt(9, device.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new DeviceNotFoundException("Device not found");
        }
        return getDevice(device.getId());
    }

    /**
     * Deletes a device from the database by its ID.
     *
     * @param id The ID of the device to delete.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    @Override
    public void deleteDevice(int id) throws SQLException, DeviceNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE Devices WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();
            if (rs == 0) throw new DeviceNotFoundException("Device not found");
        }
    }

    /**
     * Deletes all devices associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return The number of devices deleted.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If no devices were found.
     */
    @Override
    public int deleteDevices(int installationId) throws SQLException, DeviceNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE Devices WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeUpdate();
            if (rs == 0) throw new DeviceNotFoundException("No devices found");
            return rs;
        }
    }
}
