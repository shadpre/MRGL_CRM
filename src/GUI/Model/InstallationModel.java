package GUI.Model;

import BE.DBEnteties.Installation;
import BE.DBEnteties.User;

public class InstallationModel {
    private Installation selectedInstallation;

    public Installation getSelectedInstallation(Installation selectedInstallation) {

        return selectedInstallation;
    }

    public void setSelectedInstallation(Installation selectedInstallation) {

        this.selectedInstallation = selectedInstallation;
    }
}
