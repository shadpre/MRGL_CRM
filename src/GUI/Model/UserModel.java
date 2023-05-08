package GUI.Model;
import BE.User;
import BLL.PasswordHash;
import BLL.UserManager;
import DAL.db.UserDAO_DB;

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
        return userManager.getUser(LoginName, Password);
    }
    public static User createUser(User user, String Password) throws Exception{
        String hash = PasswordHash.encryptPassword(Password);
        if (UserDAO_DB.loginNameAvailible(user.getLoginName())){
            return UserDAO_DB.createUser(user, hash);
        }
        else throw new RuntimeException("User not created");
    }


    public User getSelectedUser(User selectedUser){return selectedUser;}

    public void setSelectedUser(User selectedUser){this.selectedUser = selectedUser;}
}
