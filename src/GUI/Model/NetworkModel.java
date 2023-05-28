package GUI.Model;

import BE.DBEnteties.Interfaces.INetwork;
import BE.DBEnteties.Network;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BLL.Interfaces.INetworkManager;
import BLL.Managers.NetworkManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class NetworkModel {
    private INetworkManager networkManager = new NetworkManager();
    public ObservableList<INetwork> getNetworks(int installationID) {
        INetworkManager networkManager = new NetworkManager();
        ObservableList<INetwork> out;
        try {
            out = FXCollections.observableArrayList(networkManager.getNetworks(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption {
        networkManager.deleteNetwork(id);
    }

    public INetwork createNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption {
        return networkManager.createNetwork(network);
    }

    public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption {
        return networkManager.updateNetwork(network);
    }
}
