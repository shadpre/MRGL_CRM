package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BE.Exptions.UserValidationExeption;
import BE.Exptions.ValidationException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserManager {
    IUser getUser(String LoginName, String Password) throws Exception;

    IUser createUser(IUser user, String Password) throws SQLException, UserNotFoundExeption, ValidationException, UserValidationExeption;

    boolean loginNameAvailable(String LoginName) throws SQLException, UserValidationExeption;

    void resetPassword(IUser user, String Password) throws SQLException, UserNotFoundExeption;

    IUser updateUser(IUser user, String password) throws UserNotFoundExeption, SQLException;

    ArrayList<IUser> getAllUsers() throws SQLException, UserNotFoundExeption;

    ArrayList<IUser> getAllUsers(int installationId) throws SQLException, UserNotFoundExeption;

    void deleteUser(int id) throws SQLException, UserNotFoundExeption;
}
