package BLL.Managers.Interfaces;

import BE.DBEnteties.Installation;
import BE.DBEnteties.Interfaces.IInstallation;
import BE.DBEnteties.Interfaces.IUser;
import BE.DBEnteties.User;
import DAL.DB.InstallationDAO_DB;

import java.util.ArrayList;

public interface IInstallationManager {
    ArrayList<IInstallation> getAllInstallations() throws Exception;

    ArrayList<IInstallation> getInstallations(int customerTaskId) throws Exception;

    ArrayList<IInstallation> getInstallationsForUSer(IUser selectedUser) throws Exception;

    ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws Exception;

    IInstallation createInstallation(IInstallation inst) throws Exception;
}
