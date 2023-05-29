package DAL.Interfaces;

import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDeviceDAO {
    /**
     * Creates a new device in the database.
     *
     * @param device The device to create.
     * @return The created device.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundException;
    /**
     * Retrieves a device from the database by its ID.
     *
     * @param id The ID of the device to retrieve.
     * @return The retrieved device.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    IDevice getDevice(int id) throws SQLException, DeviceNotFoundException;
    /**
     * Retrieves a list of devices associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return An ArrayList of devices associated with the installation.
     * @throws SQLException If a database access error occurs.
     */
    ArrayList<IDevice> getDeviceList(int installationId) throws SQLException;
    /**
     * Updates an existing device in the database.
     *
     * @param device The device to update.
     * @return The updated device.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundException;
    /**
     * Deletes a device from the database by its ID.
     *
     * @param id The ID of the device to delete.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If the device was not found.
     */
    void deleteDevice(int id) throws SQLException, DeviceNotFoundException;
    /**
     * Deletes all devices associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return The number of devices deleted.
     * @throws SQLException                 If a database access error occurs.
     * @throws DeviceNotFoundException      If no devices were found.
     */
    int deleteDevices(int installationId) throws SQLException, DeviceNotFoundException;
}
