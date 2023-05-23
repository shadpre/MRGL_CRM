package GUI.Controller;


import GUI.Model.InstallationModel;
import GUI.Model.UserModel;

public class BaseController {

    private LogInController logInController;
    private MainView2Controller mainView2Controller;
    private AddUserController addUserController;


    private UserModel userModel;

    public UserModel getModel(){

        return userModel;}

    public void setUserModel(UserModel userModel){

        this.userModel = userModel;}

    public InstallationModel getInstallationModel() {
        return installationModel;
    }

    public void setInstallationModel(InstallationModel installationModel) {
        this.installationModel = installationModel;
    }

    private InstallationModel installationModel;


}
