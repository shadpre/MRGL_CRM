package DAL.DAO_DB;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BE.Exptions.NotFoundExeptions.CustomerTaskNotFoundException;
import DAL.DatabaseConnector;
import DAL.Interfaces.ICustomerTaskDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The CustomerTaskDAO_DB class is responsible for performing database operations related to customer tasks.
 */
public class CustomerTaskDAO_DB implements ICustomerTaskDAO {

    /**
     * Creates a new customer task in the database.
     *
     * @param ct The customer task to create.
     * @return The created customer task.
     * @throws SQLException                  If a database access error occurs.
     * @throws CustomerTaskNotFoundException If the customer task was not found.
     */
    @Override
    public ICustomerTask CreateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundException {
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

    /**
     * Adds a user to a customer task in the database.
     *
     * @param userId The ID of the user.
     * @param ctId   The ID of the customer task.
     * @throws SQLException If a database access error occurs.
     */
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

    /**
     * Retrieves a customer task from the database by its ID.
     *
     * @param id The ID of the customer task to retrieve.
     * @return The retrieved customer task.
     * @throws SQLException                  If a database access error occurs.
     * @throws CustomerTaskNotFoundException If the customer task was not found.
     */
    @Override
    public ICustomerTask getCustomerTask(int id) throws SQLException, CustomerTaskNotFoundException {
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
            } else throw new CustomerTaskNotFoundException("CustomerTask not found");
        }
    }

    /**
     * Retrieves a list of all customer tasks from the database.
     *
     * @return An ArrayList of all customer tasks.
     * @throws SQLException                  If a database access error occurs.
     * @throws CustomerTaskNotFoundException If no customer tasks were found.
     */
    @Override
    public ArrayList<ICustomerTask> getAllCustomerTasks() throws SQLException, CustomerTaskNotFoundException {
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

            if (out.size() == 0) throw new CustomerTaskNotFoundException("No CustomerTasks found");
            else return out;
        }
    }

    /**
     * Retrieves a list of all customer tasks assigned to a specific user from the database.
     *
     * @param userId The ID of the user.
     * @return An ArrayList of customer tasks assigned to the user.
     * @throws SQLException If a database access error occurs.
     */
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

    /**
     * Retrieves a list of all customer tasks associated with a specific customer from the database.
     *
     * @param customerId The ID of the customer.
     * @return An ArrayList of customer tasks associated with the customer.
     * @throws SQLException                  If a database access error occurs.
     * @throws CustomerTaskNotFoundException If no customer tasks were found for the customer.
     */
    @Override
    public ArrayList<ICustomerTask> getAllCustomerTasks(int customerId) throws SQLException, CustomerTaskNotFoundException {
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

            if (out.size() == 0) throw new CustomerTaskNotFoundException("No CustomerTasks found for Customer");
            else return out;
        }
    }

    /**
     * Updates an existing customer task in the database.
     *
     * @param ct The customer task to update.
     * @return The updated customer task.
     * @throws SQLException                  If a database access error occurs.
     * @throws CustomerTaskNotFoundException If the customer task was not found.
     */
    @Override
    public ICustomerTask updateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundException {
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
            if (rs == 0) throw new CustomerTaskNotFoundException("CustomerTask not found");
        }

        return getCustomerTask(ct.getId());
    }

    /**
     * Deletes a customer task from the database.
     *
     * @param id The ID of the customer task to delete.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public void deleteCustomerTask(int id) throws SQLException {

        ArrayList<Integer> installationsIds = new ArrayList<>();
        PreparedStatement statement;
        String query;
        Connection conn = DatabaseConnector.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);

            //Get all installations for CustomerTask
            query="SELECT Id FROM Installations where CustomerTaskId = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1,id);

            var rs = statement.executeQuery();

            while(rs.next()){
                installationsIds.add(rs.getInt("Id"));
            }

            //Delete each installation
            if (installationsIds.size() > 0) {
                for (int installationId : installationsIds) {
                    query = "DELETE Images WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();

                    query = "DELETE Devices WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();

                    query = "DELETE Networks WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();

                    query = "DELETE WiFis WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();
                }
            }

            //If all went well
            conn.commit();
        }
        catch (Exception e){
            conn.rollback();
            //rethrow
            throw e;
        }

    }
}
