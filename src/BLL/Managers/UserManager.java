/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BLL.Managers;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundExeption;
import BE.Exptions.UserValidationExeption;
import BE.Exptions.ValidationException;
import BLL.Interfaces.IUserManager;
import BLL.PasswordHash;
import DAL.DAO_DB.UserDAO_DB;
import DAL.DBFacade;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager implements IUserManager {

    @Override
    public IUser getUser(String LoginName, String Password) throws Exception {

        if (validateUser(LoginName, Password)){
            return DBFacade.getInstance().getUser(LoginName);
        }
        else {
            throw new UserValidationExeption("Invalid Username or Password");
        }
    }

    public boolean loginNameAvailable(String loginName) throws SQLException, UserValidationExeption {
        return DBFacade.getInstance().loginNameAvailable(loginName);
    }

    @Override
    public IUser createUser(IUser user, String Password, String PasswordRetype) throws SQLException, UserNotFoundExeption, ValidationException, UserValidationExeption {
        if (Password != PasswordRetype) throw new ValidationException("Passwords are different");
        String hash = PasswordHash.encryptPassword(Password);
        if (DBFacade.getInstance().loginNameAvailable(user.getLoginName())){
            return DBFacade.getInstance().createUser(user, hash);
        }
        else throw new SQLDataException("User not created");
    }

    @Override
    public void resetPassword(IUser user, String Password) throws SQLException, UserNotFoundExeption{
        String hash = PasswordHash.encryptPassword(Password);
        DBFacade.getInstance().resetPassword(user.getId(), hash);
    }

    @Override
    public ArrayList<IUser> getAllUsers() throws SQLException, UserNotFoundExeption{
        return DBFacade.getInstance().getAllUsers();
    }

    @Override
    public ArrayList<IUser> getAllUsers(int installationId) throws SQLException, UserNotFoundExeption {
        DBFacade.getInstance()
    }

    @Override
    public void deleteUser(int id) throws SQLException, UserNotFoundExeption {
        DBFacade.getInstance().deleteUser(id);
    }

    private boolean validateUser(String LoginName, String Password) throws Exception {
        String hash = DBFacade.getInstance().getUserHash(LoginName);
        return PasswordHash.chkPassword(Password, hash);
    }
}
