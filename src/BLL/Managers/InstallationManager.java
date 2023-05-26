package BLL.Managers;

import BE.DBEnteties.Interfaces.IInstallation;
import BE.DBEnteties.Interfaces.IUser;
import BE.Exptions.NotFoundExeptions.InstallationNotFoundExeption;
import BLL.Interfaces.IInstallationManager;
import DAL.DAO_DB.InstallationDAO_DB;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstallationManager implements IInstallationManager {

    @Override
    public ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundExeption {
        return DBFacade.getInstance().getAllInstallations();
    }

    @Override
    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption {
        return DBFacade.getInstance().getInstallations(customerTaskId);
    }


    @Override
    public ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException {
        return DBFacade.getInstance().getInstallationsForUser(selectedUserID);
    }

    @Override
    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption {
        return DBFacade.getInstance().createInstallation(inst);
    }
}
