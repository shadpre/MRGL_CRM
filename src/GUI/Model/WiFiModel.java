package GUI.Model;

import BE.DBEnteties.Interfaces.IWiFi;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundException;
import BLL.Interfaces.IWiFiManager;
import BLL.Managers.WiFiManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class WiFiModel {
    private final IWiFiManager wiFiManager = new WiFiManager();

    public ObservableList<IWiFi> getWiFis(int installationID) {
        ObservableList<IWiFi> out;
        try {
            out = FXCollections.observableArrayList(wiFiManager.getWiFis(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public void deleteWiFi(int id) throws WiFiNotFoundException, SQLException {
        wiFiManager.deleteWiFi(id);
    }

    public IWiFi createWiFi(IWiFi wiFi) throws WiFiNotFoundException, SQLException {
        return wiFiManager.createWiFi(wiFi);
    }

    public IWiFi updateWiFi(IWiFi wiFi) throws WiFiNotFoundException, SQLException {
        return wiFiManager.updateWiFi(wiFi);
    }
}
