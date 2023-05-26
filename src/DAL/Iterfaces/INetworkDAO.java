package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.INetwork;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface INetworkDAO {
    INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundExeption;

    INetwork getNetwork(int id) throws SQLException, NetworkNotFoundExeption;

    ArrayList<INetwork> getNetworks(int InstallationId) throws SQLException;

    INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption;

    void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption;

    int deleteNetworks(int installationId) throws SQLException, NetworkNotFoundExeption;
}
