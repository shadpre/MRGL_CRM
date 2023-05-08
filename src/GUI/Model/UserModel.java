package GUI.Model;
import BE.User;
import BLL.PasswordHash;
import BLL.UserManager;
import DAL.db.UserDAO_DB;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    public User selectedUser;

    private UserManager userManager;

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public User getUser(String LoginName, String Password) throws Exception {
        return UserManager.getUser(LoginName, Password);
    }
    public static User createUser(User user, String Password) throws Exception{
        String hash = PasswordHash.encryptPassword(Password);
        if (UserDAO_DB.loginNameAvailible(user.getLoginName())){
            return UserDAO_DB.createUser(user, hash);
        }
        else throw new RuntimeException("User not created");
    }

    public ObservableList<User> getAllUsers(){
        ObservableList<User>  out;
        try {
            out =  FXCollections.observableArrayList(UserManager.getAllUsers());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public User getSelectedUser(User selectedUser){return selectedUser;}

    public void setSelectedUser(User selectedUser){this.selectedUser = selectedUser;}
}
