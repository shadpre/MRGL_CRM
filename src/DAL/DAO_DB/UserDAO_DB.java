package DAL.DAO_DB;

import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.UserNotFoundException;
import BE.Exptions.UserValidationException;
import DAL.DatabaseConnector;
import DAL.Interfaces.IUserDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The UserDAO_DB class implements the IUserDAO interface and provides
 * database operations for User entities.
 */
public class UserDAO_DB implements IUserDAO {

    /**
     * Creates a new user in the database.
     *
     * @param user The user to create.
     * @param hash The user's password hash.
     * @return The created user.
     * @throws SQLException            If a database error occurs.
     * @throws UserNotFoundException    If the user is not found.
     */
    @Override
    public IUser createUser(IUser user, String hash) throws SQLException, UserNotFoundException {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO Users (LoginName, FirstName, LastName, EMail, Hash, Role) Values (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLoginName().toUpperCase());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEMail());
            statement.setString(5, hash);
            statement.setInt(6, user.getRole());

            var rs = statement.executeQuery();
            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("User not saved");
        }
        return getUser(ID);
    }

    /**
     * Retrieves a user from the database based on the login name.
     *
     * @param loginName The login name of the user.
     * @return The retrieved user.
     * @throws SQLException          If a database error occurs.
     * @throws UserValidationException If the user validation fails.
     */
    @Override
    public IUser getUser(String loginName) throws SQLException, UserValidationException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, FirstName, LastName, Email, Role FROM Users WHERE LoginName = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, loginName);

            var rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("Id"),
                        loginName,
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                );
            } else {
                throw new UserValidationException("Invalid Username or Password");
            }
        }
    }

    /**
     * Retrieves a user from the database based on the ID.
     *
     * @param id The ID of the user.
     * @return The retrieved user.
     * @throws SQLException          If a database error occurs.
     * @throws UserNotFoundException  If the user is not found.
     */
    @Override
    public IUser getUser(int id) throws SQLException, UserNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT LoginName, FirstName, LastName, Email, Role FROM Users WHERE Id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        id,
                        rs.getString("LoginName"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                );
            } else {
                throw new UserNotFoundException("There is no user with that ID");
            }
        }
    }

    /**
     * Retrieves all users associated with a specific customer task.
     *
     * @param customerTaskId The ID of the customer task.
     * @return An ArrayList of users associated with the customer task.
     * @throws SQLException If a database error occurs.
     */
    @Override
    public ArrayList<IUser> getAllUsers(int customerTaskId) throws SQLException {
        ArrayList<IUser> output = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, LoginName, FirstName, LastName, Email, Role FROM Users " +
                    "WHERE Id IN (SELECT UserId FROM UserCustomerTasksRel " +
                    "WHERE CustomerTaskId = ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, customerTaskId);

            var rs = statement.executeQuery();

            while (rs.next()) {
                output.add(new User(
                        rs.getInt("Id"),
                        rs.getString("LoginName"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                ));
            }
        }
        return output;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return An ArrayList of all users.
     * @throws SQLException If a database error occurs.
     */
    @Override
    public ArrayList<IUser> getAllUsers() throws SQLException {
        ArrayList<IUser> output = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Id, LoginName, FirstName, LastName, Email, Role FROM Users";
            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();
            while (rs.next()) {
                output.add(new User(
                        rs.getInt("Id"),
                        rs.getString("LoginName"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                ));
            }
        }
        return output;
    }

    /**
     * Retrieves the password hash for a user based on the login name.
     *
     * @param loginName The login name of the user.
     * @return The password hash of the user.
     * @throws SQLException          If a database error occurs.
     * @throws UserValidationException If the user validation fails.
     */
    @Override
    public String getUserHash(String loginName) throws SQLException, UserValidationException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT Hash FROM Users WHERE LoginName = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, loginName);

            var rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("Hash");
            } else {
                throw new UserValidationException("Invalid Username or Password");
            }
        }
    }

    /**
     * Checks if a login name is available in the database.
     *
     * @param loginName The login name to check.
     * @return True if the login name is available, false otherwise.
     * @throws UserValidationException If the user validation fails.
     * @throws SQLException          If a database error occurs.
     */
    @Override
    public boolean loginNameAvailable(String loginName) throws UserValidationException, SQLException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT COUNT(ID) FROM Users WHERE LoginName = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, loginName);

            var rs = statement.executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) {
                return true;
            } else throw new UserValidationException("LoginName not available");
        }
    }

    /**
     * Resets the password of a user in the database.
     *
     * @param id   The ID of the user.
     * @param hash The new password hash.
     * @throws SQLException If a database error occurs.
     */
    @Override
    public void resetPassword(int id, String hash) throws SQLException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE Users SET Hash = ? WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, hash);

            if (statement.executeUpdate() != 1) {
                throw new RuntimeException("Nothing is updated");
            }
        }
    }

    /**
     * Updates a user in the database.
     *
     * @param user The user to update.
     * @param hash The new password hash.
     * @return The updated user.
     * @throws SQLException            If a database error occurs.
     * @throws UserNotFoundException    If the user is not found.
     */
    @Override
    public IUser updateUser(IUser user, String hash) throws SQLException, UserNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE Users" +
                    "SET LoginName = ?, FirstName = ?, LastName = ?, EMail = ?, Hash = ?, Role = ?" +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getLoginName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEMail());
            statement.setString(5, hash);
            statement.setInt(6, user.getRole());
            statement.setInt(7, user.getId());

            var rs = statement.executeUpdate();

            if (rs == 1) {
                return user;
            } else throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param id The ID of the user to delete.
     * @throws SQLException         If a database error occurs.
     * @throws UserNotFoundException If the user is not found.
     */
    @Override
    public void deleteUser(int id) throws SQLException, UserNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            try {
                conn.setAutoCommit(false);

                String query = "DELETE Users where Id = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, id);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}
