package BLL.Managers.Interfaces;

import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BLL.PasswordHash;
import DAL.DB.UserDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserManager {
    IUser getUser(String LoginName, String Password) throws Exception;

    IUser createUser(IUser user, String Password, String PasswordRetype) throws Exception;

    void resetPassword(IUser user, String Password) throws Exception;

    ArrayList<IUser> getAllUsers() throws Exception;

    void deleteUser(int id) throws SQLException, UserNotFoundExeption;
}
