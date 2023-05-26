package BLL.Interfaces;

import BE.DBEnteties.Interfaces.INetwork;
import BE.DBEnteties.Network;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface INetworkManager {
    INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundExeption;

    INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption;

    ArrayList<INetwork> getNetworks(int installationID) throws SQLException;

    void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption;
}
