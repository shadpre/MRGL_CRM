package GUI.Model;

import BE.DBEnteties.Network;
import BLL.Managers.NetworkManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetworkModel {
    public static ObservableList<Network> getNetworks(int installationID) {
        ObservableList<Network> out;
        try {
            out = FXCollections.observableArrayList(NetworkManager.getNetworks(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
