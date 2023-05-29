package BLL.Interfaces;

import BE.DBEnteties.Interfaces.INetwork;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface INetworkManager {
    INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundException;

    INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundException;

    ArrayList<INetwork> getNetworks(int installationID) throws SQLException;

    void deleteNetwork(int id) throws SQLException, NetworkNotFoundException;
}
