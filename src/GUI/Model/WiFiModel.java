package GUI.Model;

import BE.DBEnteties.Device;
import BE.DBEnteties.WiFi;
import BLL.Managers.DeviceManager;
import BLL.Managers.WiFiManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WiFiModel {

    public static ObservableList<WiFi> getWiFis(int installationID){
        ObservableList<WiFi>  out;
        try {
            out =  FXCollections.observableArrayList(WiFiManager.getWiFis(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
