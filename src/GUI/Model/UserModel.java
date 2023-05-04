package GUI.Model;
import BE.User;
import BLL.UserManager;

public class UserModel {
    private User selectedUser;

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

    public User getSelectedUser(User selectedUser){return selectedUser;}

    public void setSelectedUser(User selectedUser){this.selectedUser = selectedUser;}
}
