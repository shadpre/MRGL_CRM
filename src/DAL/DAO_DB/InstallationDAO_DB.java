package DAL.DAO_DB;

import BE.DBEnteties.Installation;
import BE.DBEnteties.Interfaces.IInstallation;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundException;
import DAL.DatabaseConnector;
import DAL.Interfaces.IInstallationDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The implementation of the IInstallationDAO interface for accessing installation data in the database.
 */
public class InstallationDAO_DB implements IInstallationDAO {

    /**
     * Creates a new installation in the database.
     *
     * @param inst The installation to create.
     * @return The created installation.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    @Override
    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO Installations (CustomerTaskId, Description, Remarks) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inst.getCustomerTaskId());
            statement.setString(2, inst.getDescription());
            statement.setString(3, inst.getRemarks());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("Installation not saved");
        }
        return getInstallation(ID);
    }

    /**
     * Retrieves an installation from the database by its ID.
     *
     * @param id The ID of the installation to retrieve.
     * @return The retrieved installation.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    @Override
    public IInstallation getInstallation(int id) throws SQLException, InstallationNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT CustomerTaskId, Description, Remarks FROM Installations WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()) {
                return new Installation(
                        id,
                        rs.getInt("CustomerTaskId"),
                        rs.getString("Description"),
                        rs.getString("Remarks")
                );
            } else throw new InstallationNotFoundException("Installation not found");
        }
    }

    /**
     * Retrieves all installations associated with a specific customer task from the database.
     *
     * @param customerTaskId The ID of the customer task.
     * @return An ArrayList of installations associated with the customer task.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If no installations were found.
     */
    @Override
    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            ArrayList<IInstallation> out = new ArrayList<>();
            String query = "SELECT Id, Description, Remarks FROM Installations WHERE CustomerTaskId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, customerTaskId);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new Installation(
                        rs.getInt("Id"),
                        customerTaskId,
                        rs.getString("Description"),
                        rs.getString("Remarks")
                ));
            }

            if (out.size() == 0) throw new InstallationNotFoundException("No installations found");
            else return out;
        }
    }

    /**
     * Retrieves all installations from the database.
     *
     * @return An ArrayList of all installations.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If no installations were found.
     */
    @Override
    public ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            ArrayList<IInstallation> out = new ArrayList<>();
            String query = "SELECT Id, CustomerTaskId, Description, Remarks FROM Installations";

            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new Installation(
                        rs.getInt("Id"),
                        rs.getInt("customerTaskId"),
                        rs.getString("Description"),
                        rs.getString("Remarks")
                ));
            }

            return out;
        }
    }

    /**
     * Updates an existing installation in the database.
     *
     * @param inst The installation to update.
     * @return The updated installation.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    @Override
    public IInstallation updateInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE Installations " +
                    "SET CustomerTaskId = ?, Description = ?, Remarks = ? " +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, inst.getCustomerTaskId());
            statement.setString(2, inst.getDescription());
            statement.setString(3, inst.getRemarks());
            statement.setInt(4, inst.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new InstallationNotFoundException("Installation not found");
        }
        return getInstallation(inst.getId());
    }

    /**
     * Retrieves all installations associated with a specific user from the database.
     *
     * @param selectedUserID The ID of the user.
     * @return An ArrayList of installations associated with the user.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException {
        ArrayList<IInstallation> installationsForUser = new ArrayList<>();

        String sqlQuery = "SELECT i.* " +
                "FROM Installations i " +
                "JOIN CustomerTasks ct ON i.customerTaskId = ct.id " +
                "JOIN UserCustomerTasksRel uoct ON ct.id = uoct.customerTaskId " +
                "JOIN Users u ON uoct.userId = u.id " +
                "WHERE u.id = ?";

        try (Connection connection = DatabaseConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, selectedUserID);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Installation installation = new Installation(
                        rs.getInt("Id"),
                        rs.getInt("customerTaskId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"));
                installationsForUser.add(installation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception to handle it at a higher level if needed
        }
        return installationsForUser;
    }

    /**
     * Deletes an installation from the database by its ID.
     *
     * @param id The ID of the installation to delete.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installation was not found.
     */
    @Override
    public void deleteInstallation(int id) throws SQLException, InstallationNotFoundException {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Deletes all installations associated with a specific customer task from the database.
     *
     * @param customerTaskId The ID of the customer task.
     * @throws SQLException                 If a database access error occurs.
     * @throws InstallationNotFoundException If the installations were not found.
     */
    @Override
    public void deleteInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException {
        throw new RuntimeException("Not implemented yet");
    }
}
