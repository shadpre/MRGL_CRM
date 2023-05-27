package DAL.DAO_DB;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BE.Exptions.NotFoundExeptions.CustomerTaskNotFoundExeption;
import DAL.DatabaseConnector;
import DAL.Iterfaces.ICustomerTaskDAO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerTaskDAO_DB implements ICustomerTaskDAO {

    @Override
    public ICustomerTask CreateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundExeption {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO CustomerTasks (Date, Description, Remarks, Status, CustomerId) VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ct.GetDateString());
            statement.setString(2, ct.getDescription());
            statement.setString(3, ct.getRemarks());
            statement.setInt(4, ct.getStatus());
            statement.setInt(5, ct.getCustomerID());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("CustomerTask not saved");
        }
        return getCustomerTask(ID);
    }

    @Override
    public void addUserToCustomerTask(int userId, int ctId) throws SQLException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO UserCustomerTasksRel (UserId, CustomerTaskId) VALUES (?,?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, ctId);

            var rs = statement.executeUpdate();
            if (rs == 0) throw new SQLDataException("Relation not saved");
        }
    }

    @Override
    public ICustomerTask getCustomerTask(int id) throws SQLException, CustomerTaskNotFoundExeption {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Date, Description, Remarks, Status, CustomerID FROM CustomerTasks WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()) {
                return new CustomerTask(
                        id,
                        rs.getTimestamp("Date").toLocalDateTime(),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getInt("Status"),
                        rs.getInt("CustomerID")
                );
            } else throw new CustomerTaskNotFoundExeption("CustomerTask not found");
        }
    }

    @Override
    public ArrayList<ICustomerTask> getAllCustomerTasks() throws SQLException, CustomerTaskNotFoundExeption {
        ArrayList<ICustomerTask> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, Date, Description, Remarks, Status, CustomerId FROM CustomerTasks";

            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new CustomerTask(
                        rs.getInt("Id"),
                        rs.getTimestamp("Date").toLocalDateTime(),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getInt("Status"),
                        rs.getInt("CustomerId")
                ));
            }

            if (out.size() == 0) throw new CustomerTaskNotFoundExeption("No CustomerTasks found");
            else return out;
        }
    }

    @Override
    public ArrayList<ICustomerTask> getAllUserCustomerTasks(int userId) throws SQLException {
        ArrayList<ICustomerTask> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, CustomerId, Date, Description, Remarks, Status FROM CustomerTasks " +
                    "WHERE Id IN (SELECT CustomerTaskId FROM UserCustomerTasksRel " +
                    "WHERE UserId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userId);

            var rs = statement.executeQuery();
            while (rs.next()) {
                out.add(new CustomerTask(
                        rs.getInt("Id"),
                        rs.getTimestamp("Date").toLocalDateTime(),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getInt("Status"),
                        rs.getInt("CustomerId")
                ));
            }
            return out;
        }
    }

    @Override
    public ArrayList<ICustomerTask> getAllCustomerTasks(int customerId) throws SQLException, CustomerTaskNotFoundExeption {
        ArrayList<ICustomerTask> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, Date, Description, Remarks, Status, FROM CustomerTasks WHERE CustomerId= ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, customerId);

            var rs = statement.executeQuery();

            while (rs.next()) {
                out.add(new CustomerTask(
                        rs.getInt("Id"),
                        rs.getTimestamp("Date").toLocalDateTime(),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getInt("Status"),
                        customerId
                ));
            }

            if (out.size() == 0) throw new CustomerTaskNotFoundExeption("No CustomerTasks found for Customer");
            else return out;
        }
    }

    @Override
    public ICustomerTask updateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundExeption {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query =
                    "UPDATE CustomerTasks" +
                            "SET Date = ?, Description = ?, Remarks = ?, Status = ?, CustomerId = ? " +
                            "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ct.GetDateString());
            statement.setString(2, ct.getDescription());
            statement.setString(3, ct.getRemarks());
            statement.setInt(4, ct.getStatus());
            statement.setInt(5, ct.getCustomerID());
            statement.setInt(6, ct.getId());

            var rs = statement.executeUpdate();
            if (rs == 0) throw new CustomerTaskNotFoundExeption("CustomerTask not found");
        }

        return getCustomerTask(ct.getId());
    }

    @Override
    public void deleteCustomerTask(int ID) {
        throw new RuntimeException("Not implemented");
    }
}
