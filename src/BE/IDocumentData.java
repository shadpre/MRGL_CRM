package BE;

import BE.DBEnteties.*;
import BE.DBEnteties.Interfaces.*;

import java.util.ArrayList;

public interface IDocumentData {
    ICustomerTask getCustomerTask();

    void setCustomerTask(ICustomerTask customerTask);

    ICustomer getCustomer();

    void setCustomer(ICustomer customer);

    ArrayList<IUser> getUsers();

    void setUsers(ArrayList<IUser> users);

    ArrayList<IInstallation> getInstallations();

    void setInstallations(ArrayList<IInstallation> installations);

    ArrayList<INetwork> getNetworks();

    void setNetworks(ArrayList<INetwork> networks);

    ArrayList<IImage> getImages();

    void setImages(ArrayList<IImage> images);

    ArrayList<IDevice> getDevices();

    void setDevices(ArrayList<IDevice> devices);

    ArrayList<IWiFi> getWiFis();

    void setWiFis(ArrayList<IWiFi> wiFis);
}
