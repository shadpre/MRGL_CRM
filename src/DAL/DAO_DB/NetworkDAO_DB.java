package DAL.DAO_DB;

import BE.DBEnteties.Interfaces.INetwork;
import BE.DBEnteties.Network;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundException;
import DAL.DatabaseConnector;
import DAL.Interfaces.INetworkDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The implementation of the INetworkDAO interface for accessing network data in the database.
 */
public class NetworkDAO_DB implements INetworkDAO {

    /**
     * Creates a new network in the database.
     *
     * @param net The network to create.
     * @return The created network.
     * @throws SQLException                If a database access error occurs.
     * @throws NetworkNotFoundException    If the network was not found.
     * @throws SQLDataException            If the network was not saved.
     */
    @Override
    public INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundException {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO Networks (InstallationId, Description, Remarks, NetworkIP, SubnetMask, DefaultGateway, HasPOE) " +
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

    /**
     * Retrieves a network from the database by its ID.
     *
     * @param id The ID of the network to retrieve.
     * @return The retrieved network.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    @Override
    public INetwork getNetwork(int id) throws SQLException, NetworkNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT InstallationId, Description, Remarks, NetworkIP, SubnetMask, DefaultGateway, HasPOE FROM Networks WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();
            if (rs.next()) {
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
            } else throw new NetworkNotFoundException("Network not found");
        }
    }

    /**
     * Retrieves all networks associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return An ArrayList of networks associated with the installation.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public ArrayList<INetwork> getNetworks(int installationId) throws SQLException {
        ArrayList<INetwork> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, Description, Remarks, NetworkIP, SubnetMask, DefaultGateway, HasPOE FROM Networks WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new Network(
                        rs.getInt("Id"),
                        installationId,
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getString("NetworkIP"),
                        rs.getString("SubnetMask"),
                        rs.getString("DefaultGateway"),
                        rs.getBoolean("HasPOE")
                ));
            }
            return out;
        }
    }

    /**
     * Updates an existing network in the database.
     *
     * @param network The network to update.
     * @return The updated network.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    @Override
    public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE Networks " +
                    "SET InstallationId = ?, Description = ?, Remarks = ?, NetworkIP = ?, SubnetMask =?, DefaultGateway = ?, HasPOE =? " +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, network.getInstallationId());
            statement.setString(2, network.getDescription());
            statement.setString(3, network.getRemarks());
            statement.setString(4, network.getNetworkIP());
            statement.setString(5, network.getSubnetMask());
            statement.setString(6, network.getDefaultGateway());
            statement.setBoolean(7, network.isHasPOE());
            statement.setInt(8, network.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new NetworkNotFoundException("Network not found");
        }
        return getNetwork(network.getId());
    }

    /**
     * Deletes a network from the database by its ID.
     *
     * @param id The ID of the network to delete.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    @Override
    public void deleteNetwork(int id) throws SQLException, NetworkNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE FROM Networks WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new NetworkNotFoundException("Network not found");
        }
    }

    /**
     * Deletes all networks associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return The number of networks deleted.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    @Override
    public int deleteNetworks(int installationId) throws SQLException, NetworkNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE FROM Networks WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new NetworkNotFoundException("Network not found");
            else return rs;
        }
    }
}
