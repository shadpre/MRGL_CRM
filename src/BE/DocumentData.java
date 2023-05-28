package BE;

import BE.DBEnteties.Interfaces.*;

import java.util.ArrayList;

/**
 *
 */
public class DocumentData implements IDocumentData {
    private ICustomerTask customerTask;
    private ICustomer customer;
    private ArrayList<IUser> users;
    private ArrayList<IInstallation> installations;
    private ArrayList<INetwork> networks;
    private ArrayList<IImage> images;
    private ArrayList<IDevice> devices;
    private ArrayList<IWiFi> wiFis;

    public DocumentData() {
    }

    /**
     * @param customerTask
     */
    public DocumentData(ICustomerTask customerTask) {
        this.customerTask = customerTask;
    }

    @Override
    public ICustomerTask getCustomerTask() {
        return customerTask;
    }

    @Override
    public void setCustomerTask(ICustomerTask customerTask) {
        this.customerTask = customerTask;
    }

    @Override
    public ICustomer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    @Override
    public ArrayList<IUser> getUsers() {
        return users;
    }

    @Override
    public void setUsers(ArrayList<IUser> users) {
        this.users = users;
    }

    @Override
    public ArrayList<IInstallation> getInstallations() {
        return installations;
    }

    @Override
    public void setInstallations(ArrayList<IInstallation> installations) {
        this.installations = installations;
    }

    @Override
    public ArrayList<INetwork> getNetworks() {
        return networks;
    }

    @Override
    public void setNetworks(ArrayList<INetwork> networks) {
        this.networks = networks;
    }

    @Override
    public ArrayList<IImage> getImages() {
        return images;
    }

    @Override
    public void setImages(ArrayList<IImage> images) {
        this.images = images;
    }

    @Override
    public ArrayList<IDevice> getDevices() {
        return devices;
    }

    @Override
    public void setDevices(ArrayList<IDevice> devices) {
        this.devices = devices;
    }

    @Override
    public ArrayList<IWiFi> getWiFis() {
        return wiFis;
    }

    @Override
    public void setWiFis(ArrayList<IWiFi> wiFis) {
        this.wiFis = wiFis;
    }
}
