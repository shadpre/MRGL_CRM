package BLL.Managers;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Installation;
import BE.DBEnteties.User;
import DAL.DB.CustomerTaskDAO_DB;
import DAL.DB.InstallationDAO_DB;

import java.util.ArrayList;

public class InstallationManager {

    public static ArrayList<Installation> getAllInstallations() throws Exception{
        return InstallationDAO_DB.getAllInstallations();
    }

    public static ArrayList<Installation> getInstallations(int customerTaskId) throws Exception{
        return InstallationDAO_DB.getInstallations(customerTaskId);
    }

    public static ArrayList<Installation> getInstallationsForUSer(User selectedUser) throws Exception{
        return InstallationDAO_DB.getInstallationsForUser(selectedUser);
    }

    public static Installation createInstallation(Installation inst) throws Exception{

        return InstallationDAO_DB.createInstallation(inst);
    }
}
