package DAL.db;

import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BE.Network;

import java.sql.*;
import java.util.ArrayList;

public class NetworkDAO_DB {
    public static Network createNetwork(Network net) throws SQLException, NetworkNotFoundExeption{
        int ID;
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "INSERT INTO Networks (InstallationId, Description, Remarks, NetworkIP, SubnetMask, DEfaultGateway, HasPOE) " +
                    "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, net.getInstallationId());
            statement.setString(2, net.getDescription());
            statement.setString(3, net.getRemarks());
            statement.setString(4, net.getNetworkIP());
            statement.setString(5, net.getSubnetMask());
            statement.setString(6, net.getDefaultGateway());
            statement.setBoolean(7, net.isHasPOE());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("Network not saved");
        }
        return getNetwork(ID);
    }

    public static Network getNetwork(int id) throws SQLException, NetworkNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT InstallationId, Description, Remarks, NetworkIP, SubnetMask, DefaultGateway, HasPOE FROM Networks WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);

            var rs = statement.executeQuery();
            if (rs.next()){
                return new Network(
                        id,
                        rs.getInt("InstallationId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("NetworkIP"),
                        rs.getString("SubnetMask"),
                        rs.getString("DefaultGateway"),
                        rs.getBoolean("HasPOE")
                        );
            }
            else throw new NetworkNotFoundExeption("Network not found");
        }
    }

    public static ArrayList<Network> getNetworks(int InstallationId) throws SQLException, NetworkNotFoundExeption{
        ArrayList<Network> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT Id, Description, Remarks, NetworkIP, SubnetMask, Defaultgateway, HasPOE FROM"
        }
    }
}
