package BLL.Managers;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundException;
import BLL.Interfaces.IInstallationManager;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstallationManager implements IInstallationManager {

    @Override
    public ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundException {
        return DBFacade.getInstance().getAllInstallations();
    }

    @Override
    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException {
        return DBFacade.getInstance().getInstallations(customerTaskId);
    }


    @Override
    public ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException {
        return DBFacade.getInstance().getInstallationsForUser(selectedUserID);
    }

    @Override
    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException {
        return DBFacade.getInstance().createInstallation(inst);
    }

    @Override
    public void deleteInstallation(int id) throws InstallationNotFoundException, SQLException {
        DBFacade.getInstance().deleteInstallation(id);
    }
}
