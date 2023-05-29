package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundException;
import BE.Exptions.UserValidationException;
import BE.Exptions.ValidationException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserManager {
    IUser getUser(String LoginName, String Password) throws Exception;

    IUser createUser(IUser user, String Password) throws SQLException, UserNotFoundException, ValidationException, UserValidationException;

    boolean loginNameAvailable(String LoginName) throws SQLException, UserValidationException;

    void resetPassword(IUser user, String Password) throws SQLException, UserNotFoundException;

    IUser updateUser(IUser user, String password) throws UserNotFoundException, SQLException;

    ArrayList<IUser> getAllUsers() throws SQLException, UserNotFoundException;

    ArrayList<IUser> getAllUsers(int installationId) throws SQLException, UserNotFoundException;

    void deleteUser(int id) throws SQLException, UserNotFoundException;
}
