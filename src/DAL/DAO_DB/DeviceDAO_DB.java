package DAL.DAO_DB;

import BE.DBEnteties.Device;
import BE.DBEnteties.Interfaces.IDevice;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import DAL.DatabaseConnector;
import DAL.Iterfaces.IDeviceDAO;

import java.sql.*;
import java.util.ArrayList;

public class DeviceDAO_DB implements IDeviceDAO {
    @Override
    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
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

    @Override
    public IDevice getDevice(int id) throws SQLException, DeviceNotFoundExeption {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT InstallationId, Description, Remarks, IP, SubnetMask, Username, Password, IsPOE FROM Devices WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();
            if (rs.next()){
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
            }
            else throw new DeviceNotFoundExeption("Device not found");
        }
    }

    @Override
    public ArrayList<IDevice> getDeviceList(int installationId) throws SQLException{
        ArrayList<IDevice> out = new ArrayList<>();
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT Id, Description, Remarks, IP, SubnetMask, Username, Password, IsPOE FROM Devices WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeQuery();

            while (rs.next()){
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

    @Override
    public IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
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
            statement.setInt(9,device.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new DeviceNotFoundExeption("Device not found");
        }
        return getDevice(device.getId());
    }

    @Override
    public void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "DELETE Devices WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();
            if (rs == 0) throw new DeviceNotFoundExeption("Device not found");
        }
    }
    @Override
    public  int deleteDevices(int installationId) throws SQLException, DeviceNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "DELETE Devices WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeUpdate();
            if (rs == 0) throw new DeviceNotFoundExeption("No devices found");
            return rs;
        }
    }
}
