package BE;

import BE.DBEnteties.*;

import java.util.ArrayList;

public class DocumentData {
    private CustomerTask customerTask;
    private Customer customer;
    private ArrayList<User> users;
    private ArrayList<Installation> installations;
    private ArrayList<Network> networks;
    private ArrayList<Image> images;
    private ArrayList<Device> devices;
    private ArrayList<WiFi> wiFis;

    public DocumentData(){};

    public DocumentData(CustomerTask ct){
        this.customerTask = ct;
    }

    public CustomerTask getCustomerTask() {
        return customerTask;
    }

    public void setCustomerTask(CustomerTask customerTask) {
        this.customerTask = customerTask;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Installation> getInstallations() {
        return installations;
    }

    public void setInstallations(ArrayList<Installation> installations) {
        this.installations = installations;
    }

    public ArrayList<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<Network> networks) {
        this.networks = networks;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public ArrayList<WiFi> getWiFis() {
        return wiFis;
    }

    public void setWiFis(ArrayList<WiFi> wiFis) {
        this.wiFis = wiFis;
    }
}
