package DAL.Interfaces;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundException;
import BE.Exptions.UserValidationException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserDAO {
    /**
     * Creates a new user in the database.
     *
     * @param user The user to create.
     * @param hash The user's password hash.
     * @return The created user.
     * @throws SQLException            If a database error occurs.
     * @throws UserNotFoundException    If the user is not found.
     */
    IUser createUser(IUser user, String hash) throws SQLException, UserNotFoundException;
    /**
     * Retrieves a user from the database based on the login name.
     *
     * @param loginName The login name of the user.
     * @return The retrieved user.
     * @throws SQLException          If a database error occurs.
     * @throws UserValidationException If the user validation fails.
     */
    IUser getUser(String loginName) throws SQLException, UserValidationException;
    /**
     * Retrieves a user from the database based on the ID.
     *
     * @param id The ID of the user.
     * @return The retrieved user.
     * @throws SQLException          If a database error occurs.
     * @throws UserNotFoundException  If the user is not found.
     */
    IUser getUser(int id) throws SQLException, UserNotFoundException;
    /**
     * Retrieves all users associated with a specific customer task.
     *
     * @param customerTaskId The ID of the customer task.
     * @return An ArrayList of users associated with the customer task.
     * @throws SQLException If a database error occurs.
     */

    ArrayList<IUser> getAllUsers(int customerTaskId) throws SQLException;
    /**
     * Retrieves all users from the database.
     *
     * @return An ArrayList of all users.
     * @throws SQLException If a database error occurs.
     */

    ArrayList<IUser> getAllUsers() throws SQLException;
    /**
     * Retrieves the password hash for a user based on the login name.
     *
     * @param loginName The login name of the user.
     * @return The password hash of the user.
     * @throws SQLException          If a database error occurs.
     * @throws UserValidationException If the user validation fails.
     */
    String getUserHash(String loginName) throws SQLException, UserValidationException;
    /**
     * Checks if a login name is available in the database.
     *
     * @param loginName The login name to check.
     * @return True if the login name is available, false otherwise.
     * @throws UserValidationException If the user validation fails.
     * @throws SQLException          If a database error occurs.
     */
    boolean loginNameAvailable(String loginName) throws UserValidationException, SQLException;
    /**
     * Resets the password of a user in the database.
     *
     * @param id   The ID of the user.
     * @param hash The new password hash.
     * @throws SQLException If a database error occurs.
     */
    void resetPassword(int id, String hash) throws SQLException;
    /**
     * Updates a user in the database.
     *
     * @param user The user to update.
     * @param hash The new password hash.
     * @return The updated user.
     * @throws SQLException            If a database error occurs.
     * @throws UserNotFoundException    If the user is not found.
     */
    IUser updateUser(IUser user, String hash) throws SQLException, UserNotFoundException;
    /**
     * Deletes a user from the database.
     *
     * @param id The ID of the user to delete.
     * @throws SQLException         If a database error occurs.
     * @throws UserNotFoundException If the user is not found.
     */

    void deleteUser(int id) throws SQLException, UserNotFoundException;
}
