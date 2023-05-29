/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BLL.Managers;

import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.UserNotFoundException;
import BE.Exptions.UserValidationException;
import BE.Exptions.ValidationException;
import BLL.Interfaces.IUserManager;
import BLL.PasswordHash;
import DAL.DBFacade;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager implements IUserManager {

    @Override
    public IUser getUser(String LoginName, String Password) throws Exception {

        if (validateUser(LoginName, Password)) {
            return DBFacade.getInstance().getUser(LoginName);
        } else {
            throw new UserValidationException("Invalid Username or Password");
        }
    }

    public boolean loginNameAvailable(String loginName) throws SQLException, UserValidationException {
        return DBFacade.getInstance().loginNameAvailable(loginName);
    }

    @Override
    public IUser createUser(IUser user, String Password) throws SQLException, UserNotFoundException, ValidationException, UserValidationException {
        String hash = PasswordHash.encryptPassword(Password);
        if (DBFacade.getInstance().loginNameAvailable(user.getLoginName())) {
            return DBFacade.getInstance().createUser(user, hash);
        } else throw new SQLDataException("User not created");
    }

    @Override
    public void resetPassword(IUser user, String Password) throws SQLException, UserNotFoundException {
        String hash = PasswordHash.encryptPassword(Password);
        DBFacade.getInstance().resetPassword(user.getId(), hash);
    }

    public IUser updateUser(IUser user, String password) throws UserNotFoundException, SQLException {
        String hash = PasswordHash.encryptPassword(password);
        return DBFacade.getInstance().updateUser(user, hash);
    }

    @Override
    public ArrayList<IUser> getAllUsers() throws SQLException, UserNotFoundException {
        return DBFacade.getInstance().getAllUsers();
    }

    @Override
    public ArrayList<IUser> getAllUsers(int installationId) throws SQLException, UserNotFoundException {
        return DBFacade.getInstance().getAllUsers(installationId);
    }


    @Override
    public void deleteUser(int id) throws SQLException, UserNotFoundException {
        DBFacade.getInstance().deleteUser(id);
    }

    private boolean validateUser(String LoginName, String Password) throws Exception {
        String hash = DBFacade.getInstance().getUserHash(LoginName);
        return PasswordHash.chkPassword(Password, hash);
    }
}
