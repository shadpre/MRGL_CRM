package GUI.Model;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.DBEnteties.WiFi;
import BLL.Interfaces.IWiFiManager;
import BLL.Managers.WiFiManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WiFiModel {

    public static ObservableList<IWiFi> getWiFis(int installationID) {
        IWiFiManager wiFiManager = new WiFiManager();
        ObservableList<IWiFi> out;
        try {
            out = FXCollections.observableArrayList(wiFiManager.getWiFis(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
