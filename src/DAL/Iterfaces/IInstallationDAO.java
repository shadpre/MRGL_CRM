package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInstallationDAO {
    IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption;

    IInstallation getInstallation(int id) throws SQLException, InstallationNotFoundExeption;

    ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption;

    ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundExeption;

    IInstallation updateInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption;

    ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException;

    void deleteInstallation(int id) throws SQLException, InstallationNotFoundExeption;

    void deleteInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption;
}
