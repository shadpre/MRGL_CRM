package DAL.DB;

import BE.Exptions.NotFoundExeptions.InstallationNotFoundExeption;
import BE.DBEnteties.Installation;

import java.sql.*;
import java.util.ArrayList;

public class InstallationDAO_DB {
    public static Installation createInstallation(Installation inst) throws SQLException, InstallationNotFoundExeption {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "INSERT INTO Insallations (DocumentId, Description, Remarks) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,inst.getCustomerId());
            statement.setString(2,inst.getDescription());
            statement.setString(3, inst.getRemarks());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("Installation not saved");
        }
        return getInstallation(ID);
    }

    public static Installation getInstallation(int id) throws SQLException, InstallationNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT DocumentId, Description, Remarks FROM Installations WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()){
                return new Installation(
                        id,
                        rs.getInt("DocumentId"),
                        rs.getString("Description"),
                        rs.getString("Remarks")
                );
            }
            else throw new InstallationNotFoundExeption("Installation not found");
        }
    }

    public static ArrayList<Installation> getInstallations(int documentId) throws SQLException, InstallationNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            ArrayList<Installation> out = new ArrayList<>();
            String query = "SELECT Id, Description, Remarks FROM Installations WHERE DocumentId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,documentId);

            var rs = statement.executeQuery();

            while (rs.next()){
                out.add(new Installation(
                        rs.getInt("Id"),
                        documentId,
                        rs.getString("Description"),
                        rs.getString("Remarks")
                ));
            }

            if (out.size() == 0) throw new InstallationNotFoundExeption("No installations found");
            else return out;
        }
    }

    public static Installation updateInstallation(Installation inst) throws SQLException, InstallationNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "UPDATE Installations" +
                    "SET DocumentId = ?, Description = ?, Remarks = ?" +
                    "Where Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,inst.getCustomerId());
            statement.setString(2, inst.getDescription());
            statement.setString(3, inst.getRemarks());
            statement.setInt(4, inst.getId());

            var rs = statement.executeUpdate();

            if (rs == 0) throw new InstallationNotFoundExeption("Installation not found");
        }
        return getInstallation(inst.getId());
    }

    public static void deleteInstallation(int id) throws SQLException, InstallationNotFoundExeption{
        throw new RuntimeException("Not implemented yet");
    }

    public static void deleteInstallations(int documentId) throws SQLException, InstallationNotFoundExeption{
        throw new RuntimeException("Not implemented yet");
    }
}
