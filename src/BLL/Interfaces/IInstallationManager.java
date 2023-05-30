package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInstallationManager {
    ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundException;

    ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException;

    ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException, InstallationNotFoundException;

    IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException;
    void deleteInstallation(int id) throws InstallationNotFoundException, SQLException;
}
