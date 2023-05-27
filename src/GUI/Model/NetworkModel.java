package GUI.Model;

import BE.DBEnteties.Interfaces.INetwork;
import BE.DBEnteties.Network;
import BLL.Interfaces.INetworkManager;
import BLL.Managers.NetworkManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetworkModel {
    public static ObservableList<INetwork> getNetworks(int installationID) {
        INetworkManager networkManager = new NetworkManager();
        ObservableList<INetwork> out;
        try {
            out = FXCollections.observableArrayList(networkManager.getNetworks(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
