package DAL.Iterfaces;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BE.Exptions.UserValidationExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserDAO {
    IUser createUser(IUser user, String hash) throws SQLException, UserNotFoundExeption;

    IUser getUser(String LoginName) throws SQLException, UserValidationExeption;

    IUser getUser(int Id) throws SQLException, UserNotFoundExeption;

    ArrayList<IUser> getAllUsers(int customerTaskId) throws SQLException;

    ArrayList<IUser> getAllUsers() throws SQLException;

    String getUserHash(String loginName) throws SQLException, UserValidationExeption;

    boolean loginNameAvailable(String LoginName) throws UserValidationExeption, SQLException;

    void resetPassword(int id, String hash) throws SQLException;

    void deleteUser(int Id) throws SQLException, UserNotFoundExeption;
}
