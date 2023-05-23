package GUI.Model;

import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Installation;
import BE.DBEnteties.User;
import BLL.Managers.CustomerTaskManager;
import BLL.Managers.InstallationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstallationModel {
    private Installation selectedInstallation;

    public Installation getSelectedInstallation(Installation selectedInstallation) {

        return selectedInstallation;
    }

    public void setSelectedInstallation(Installation selectedInstallation) {

        this.selectedInstallation = selectedInstallation;
    }

    public static ObservableList<Installation> getInstallations(int customerTaskId){
        ObservableList<Installation>  out;
        try {
            out =  FXCollections.observableArrayList(InstallationManager.getInstallations(customerTaskId));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public static ObservableList<Installation> getAllInstallations(){
        ObservableList<Installation>  output;
        try {
            output =  FXCollections.observableArrayList(InstallationManager.getAllInstallations());
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return output;
    }
}
