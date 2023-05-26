package BLL.Managers;

import BE.DBEnteties.Interfaces.IInstallation;
import BLL.Managers.Interfaces.IInstallationManager;
import DAL.DB.InstallationDAO_DB;

import java.util.ArrayList;

public class InstallationManager implements IInstallationManager {

    @Override
    public ArrayList<IInstallation> getAllInstallations() throws Exception {
        return InstallationDAO_DB.getAllInstallations();
    }

    @Override
    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws Exception {
        return InstallationDAO_DB.getInstallations(customerTaskId);
    }

    @Override
    public ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws Exception {
        return InstallationDAO_DB.getInstallationsForUser(selectedUserID);
    }

    @Override
    public IInstallation createInstallation(IInstallation inst) throws Exception {
        return InstallationDAO_DB.createInstallation(inst);
    }
}
