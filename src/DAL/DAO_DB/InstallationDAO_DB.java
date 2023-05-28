package DAL.DAO_DB;

import BE.DBEnteties.Installation;
import BE.DBEnteties.Interfaces.IInstallation;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundExeption;
import DAL.DatabaseConnector;
import DAL.Iterfaces.IInstallationDAO;

import java.sql.*;
import java.util.ArrayList;

public class InstallationDAO_DB implements IInstallationDAO {

    @Override
    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption {
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

    @Override
    public IInstallation getInstallation(int id) throws SQLException, InstallationNotFoundExeption {
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
            } else throw new InstallationNotFoundExeption("Installation not found");
        }
    }


    @Override
    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption {
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

            if (out.size() == 0) throw new InstallationNotFoundExeption("No installations found");
            else return out;
        }
    }


    @Override
    public ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundExeption {

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

    @Override
    public IInstallation updateInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption {

        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE Installations" +
                    "SET CustomerTaskId = ?, Description = ?, Remarks = ?" +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, inst.getCustomerTaskId());
            statement.setString(2, inst.getDescription());
            statement.setString(3, inst.getRemarks());
            statement.setInt(4, inst.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new InstallationNotFoundExeption("Installation not found");
        }
        return getInstallation(inst.getId());
    }


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


    @Override
    public void deleteInstallation(int id) throws SQLException, InstallationNotFoundExeption {
        throw new RuntimeException("Not implemented yet");
    }


    @Override
    public void deleteInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption {
        throw new RuntimeException("Not implemented yet");
    }
}
