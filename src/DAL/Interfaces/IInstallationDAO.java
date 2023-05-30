package DAL.Interfaces;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInstallationDAO {
    /**
     * Creates a new installation in the database.
     *
     * @param inst The installation to create.
     * @return The created installation.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException;
    /**
     * Retrieves an installation from the database by its ID.
     *
     * @param id The ID of the installation to retrieve.
     * @return The retrieved installation.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    IInstallation getInstallation(int id) throws SQLException, InstallationNotFoundException;
    /**
     * Retrieves all installations associated with a specific customer task from the database.
     *
     * @param customerTaskId The ID of the customer task.
     * @return An ArrayList of installations associated with the customer task.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If no installations were found.
     */
    ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException;
    /**
     * Retrieves all installations from the database.
     *
     * @return An ArrayList of all installations.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If no installations were found.
     */
    ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundException;
    /**
     * Updates an existing installation in the database.
     *
     * @param inst The installation to update.
     * @return The updated installation.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    IInstallation updateInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException;
    /**
     * Retrieves all installations associated with a specific user from the database.
     *
     * @param selectedUserID The ID of the user.
     * @return An ArrayList of installations associated with the user.
     * @throws SQLException If a database access error occurs.
     */
    ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException;
    /**
     * Deletes an installation from the database by its ID.
     *
     * @param id The ID of the installation to delete.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    void deleteInstallation(int id) throws SQLException, InstallationNotFoundException;

}
