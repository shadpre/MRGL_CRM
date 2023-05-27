package GUI.Model;

import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import BLL.Interfaces.IUserManager;
import BLL.Managers.UserManager;
import BLL.PasswordHash;
import DAL.DAO_DB.UserDAO_DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    private final static IUserManager userManager = new UserManager();
    private IUser selectedUser;
    private int role;

    public static IUser createUser(IUser user, String Password) throws Exception {
        String hash = PasswordHash.encryptPassword(Password);
        if (userManager.loginNameAvailable(user.getLoginName())) {
            return userManager.createUser(user, hash);
        } else throw new RuntimeException("User not created");
    }

    public IUser getUser(String LoginName, String Password) throws Exception {
        return userManager.getUser(LoginName, Password);
    }

    public ObservableList<IUser> getAllUsers() {
        ObservableList<IUser> out;
        try {
            out = FXCollections.observableArrayList(userManager.getAllUsers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public IUser getSelectedUser(IUser selectedUser) {

        return selectedUser;
    }

    public void setSelectedUser(IUser selectedUser) {

        this.selectedUser = selectedUser;
    }


}

