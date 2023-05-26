package GUI.Model;
import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import BLL.Interfaces.IUserManager;
import BLL.PasswordHash;
import BLL.Managers.UserManager;
import DAL.DAO_DB.UserDAO_DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    public User selectedUser;
    private static IUserManager userManager = new UserManager();

    private int role;



    public IUser getUser(String LoginName, String Password) throws Exception {
        return userManager.getUser(LoginName, Password);
    }

    public static IUser createUser(IUser user, String Password) throws Exception {
        String hash = PasswordHash.encryptPassword(Password);
        if (userManager.loginNameAvailable(user.getLoginName())) {
            return UserDAO_DB.createUser(user, hash);
        } else throw new RuntimeException("User not created");
    }

    public ObservableList<User> getAllUsers() {
        ObservableList<User> out;
        try {
            out = FXCollections.observableArrayList(UserManager.getAllUsers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public User getSelectedUser(User selectedUser) {

        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {

        this.selectedUser = selectedUser;
    }




}

