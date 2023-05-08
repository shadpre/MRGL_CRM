package GUI.Controller;


import BE.User;
import GUI.Model.UserModel;

public class BaseController {

    private LogInController logInController;


    private UserModel userModel;

    public UserModel getModel(){return userModel;}

    public void setUserModel(UserModel userModel){this.userModel = userModel;}
}
