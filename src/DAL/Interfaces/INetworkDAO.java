package DAL.Interfaces;

import BE.DBEnteties.Interfaces.INetwork;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundException;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface INetworkDAO {
    /**
     * Creates a new network in the database.
     *
     * @param net The network to create.
     * @return The created network.
     * @throws SQLException                If a database access error occurs.
     * @throws NetworkNotFoundException    If the network was not found.
     * @throws SQLDataException            If the network was not saved.
     */
    INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundException;
    /**
     * Retrieves a network from the database by its ID.
     *
     * @param id The ID of the network to retrieve.
     * @return The retrieved network.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    INetwork getNetwork(int id) throws SQLException, NetworkNotFoundException;
    /**
     * Retrieves all networks associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return An ArrayList of networks associated with the installation.
     * @throws SQLException If a database access error occurs.
     */
    ArrayList<INetwork> getNetworks(int installationId) throws SQLException;
    /**
     * Updates an existing network in the database.
     *
     * @param network The network to update.
     * @return The updated network.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundException;
    /**
     * Deletes a network from the database by its ID.
     *
     * @param id The ID of the network to delete.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    void deleteNetwork(int id) throws SQLException, NetworkNotFoundException;
    /**
     * Deletes all networks associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return The number of networks deleted.
     * @throws SQLException               If a database access error occurs.
     * @throws NetworkNotFoundException   If the network was not found.
     */
    int deleteNetworks(int installationId) throws SQLException, NetworkNotFoundException;
}
