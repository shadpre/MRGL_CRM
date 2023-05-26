package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInstallationManager {
    ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundExeption;

    ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption;

    ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException, InstallationNotFoundExeption;

    IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption;
}
