package BE;

import BE.DBEnteties.Interfaces.*;
import java.util.ArrayList;

/**
 * Represents the data associated with a document.
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

    /**
     * Initializes a new instance of the DocumentData class.
     */
    public DocumentData() {
    }

    /**
     * Initializes a new instance of the DocumentData class with a customer task.
     *
     * @param customerTask The customer task associated with the document data.
     */
    public DocumentData(ICustomerTask customerTask) {
        this.customerTask = customerTask;
    }

    /**
     * Gets the customer task associated with the document data.
     *
     * @return The customer task associated with the document data.
     */
    @Override
    public ICustomerTask getCustomerTask() {
        return customerTask;
    }

    /**
     * Sets the customer task associated with the document data.
     *
     * @param customerTask The customer task to set.
     */
    @Override
    public void setCustomerTask(ICustomerTask customerTask) {
        this.customerTask = customerTask;
    }

    /**
     * Gets the customer associated with the document data.
     *
     * @return The customer associated with the document data.
     */
    @Override
    public ICustomer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer associated with the document data.
     *
     * @param customer The customer to set.
     */
    @Override
    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    /**
     * Gets the list of users associated with the document data.
     *
     * @return The list of users associated with the document data.
     */
    @Override
    public ArrayList<IUser> getUsers() {
        return users;
    }

    /**
     * Sets the list of users associated with the document data.
     *
     * @param users The list of users to set.
     */
    @Override
    public void setUsers(ArrayList<IUser> users) {
        this.users = users;
    }

    /**
     * Gets the list of installations associated with the document data.
     *
     * @return The list of installations associated with the document data.
     */
    @Override
    public ArrayList<IInstallation> getInstallations() {
        return installations;
    }

    /**
     * Sets the list of installations associated with the document data.
     *
     * @param installations The list of installations to set.
     */
    @Override
    public void setInstallations(ArrayList<IInstallation> installations) {
        this.installations = installations;
    }

    /**
     * Gets the list of networks associated with the document data.
     *
     * @return The list of networks associated with the document data.
     */
    @Override
    public ArrayList<INetwork> getNetworks() {
        return networks;
    }

    /**
     * Sets the list of networks associated with the document data.
     *
     * @param networks The list of networks to set.
     */
    @Override
    public void setNetworks(ArrayList<INetwork> networks) {
        this.networks = networks;
    }

    /**
     * Gets the list of images associated with the document data.
     *
     * @return The list of images associated with the document data.
     */
    @Override
    public ArrayList<IImage> getImages() {
        return images;
    }

    /**
     * Sets the list of images associated with the document data.
     *
     * @param images The list of images to set.
     */
    @Override
    public void setImages(ArrayList<IImage> images) {
        this.images = images;
    }

    /**
     * Gets the list of devices associated with the document data.
     *
     * @return The list of devices associated with the document data.
     */
    @Override
    public ArrayList<IDevice> getDevices() {
        return devices;
    }

    /**
     * Sets the list of devices associated with the document data.
     *
     * @param devices The list of devices to set.
     */
    @Override
    public void setDevices(ArrayList<IDevice> devices) {
        this.devices = devices;
    }

    /**
     * Gets the list of Wi-Fi networks associated with the document data.
     *
     * @return The list of Wi-Fi networks associated with the document data.
     */
    @Override
    public ArrayList<IWiFi> getWiFis() {
        return wiFis;
    }

    /**
     * Sets the list of Wi-Fi networks associated with the document data.
     *
     * @param wiFis The list of Wi-Fi networks to set.
     */
    @Override
    public void setWiFis(ArrayList<IWiFi> wiFis) {
        this.wiFis = wiFis;
    }
}
