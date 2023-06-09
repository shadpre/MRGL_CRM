package GUI.Model;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundException;
import BLL.Interfaces.IInstallationManager;
import BLL.Managers.InstallationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class InstallationModel {
    private IInstallation selectedInstallation;
    private final IInstallationManager installationManager = new InstallationManager();

    public ObservableList<IInstallation> getInstallations(int customerTaskId) {
        IInstallationManager installationManager = new InstallationManager();
        ObservableList<IInstallation> out;
        try {
            out = FXCollections.observableArrayList(installationManager.getInstallations(customerTaskId));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public ObservableList<IInstallation> getAllInstallations() {
        IInstallationManager installationManager = new InstallationManager();
        ObservableList<IInstallation> output;
        try {
            output = FXCollections.observableArrayList(installationManager.getAllInstallations());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return output;
    }

    public ObservableList<IInstallation> getInstallationsForUser(IUser selectedUser) {
        IInstallationManager installationManager = new InstallationManager();
        ObservableList<IInstallation> installations;
        try {
            installations = FXCollections.observableArrayList(installationManager.getInstallationsForUser(selectedUser.getId()));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return installations;
    }

    public IInstallation getSelectedInstallation(IInstallation selectedInstallation) {
        return this.selectedInstallation;
    }

    public void setSelectedInstallation(IInstallation selectedInstallation) {

        this.selectedInstallation = selectedInstallation;
    }

    public IInstallation createInstallation(IInstallation installation) throws SQLException, InstallationNotFoundException {
        return installationManager.createInstallation(installation);
    }

    public void deleteInstallation(int id) throws InstallationNotFoundException, SQLException{
        installationManager.deleteInstallation(id);
    }
}
