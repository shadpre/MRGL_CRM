/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BLL.Managers;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BE.Exptions.UserValidationExeption;
import BE.Exptions.ValidationException;
import BLL.Managers.Interfaces.IUserManager;
import BLL.PasswordHash;
import DAL.DB.UserDAO_DB;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager implements IUserManager {

    @Override
    public IUser getUser(String LoginName, String Password) throws Exception {

        if (validateUser(LoginName, Password)){
            return UserDAO_DB.getUser(LoginName);
        }
        else {
            throw new UserValidationExeption("Invalid Username or Password");
        }
    }

    @Override
    public IUser createUser(IUser user, String Password, String PasswordRetype) throws Exception{
        if (Password != PasswordRetype) throw new ValidationException("Passwords are different");
        String hash = PasswordHash.encryptPassword(Password);
        if (UserDAO_DB.loginNameAvailible(user.getLoginName())){
            return UserDAO_DB.createUser(user, hash);
        }
        else throw new SQLDataException("User not created");
    }

    @Override
    public void resetPassword(IUser user, String Password) throws Exception{
        String hash = PasswordHash.encryptPassword(Password);
        UserDAO_DB.resetPassword(user.getId(), hash);
    }

    @Override
    public ArrayList<IUser> getAllUsers() throws Exception{
        return UserDAO_DB.getAllUsers();
    }

    @Override
    public void deleteUser(int id) throws SQLException, UserNotFoundExeption {
        UserDAO_DB.deleteUser(id);
    }

    private boolean validateUser(String LoginName, String Password) throws Exception {
        String hash = UserDAO_DB.getUserHash(LoginName);
        return PasswordHash.chkPassword(Password, hash);
    }


}
