package DAL;

import BE.DBEnteties.Interfaces.*;
import BE.Exptions.NotFoundExeptions.*;
import BE.Exptions.UserValidationExeption;
import DAL.DAO_DB.*;
import DAL.Iterfaces.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBFacade {

    private static DBFacade instance;
    private final ICustomerDAO CustomerDAO_DB;
    private final ICustomerTaskDAO CustomerTaskDAO_DB;
    private final IDeviceDAO DeviceDAO_DB;
    private final IImageDAO ImageDAO_DB;
    private final IInstallationDAO InstallationDAO_DB;
    private final INetworkDAO NetwordDAO_DB;
    private final IUserDAO UserDAO_DB;
    private final IWiFiDAO WiFiDAO_DB;

    private DBFacade() {
        CustomerDAO_DB = new CustomerDAO_DB();
        CustomerTaskDAO_DB = new CustomerTaskDAO_DB();
        DeviceDAO_DB = new DeviceDAO_DB();
        ImageDAO_DB = new ImageDAO_DB();
        InstallationDAO_DB = new InstallationDAO_DB();
        NetwordDAO_DB = new NetworkDAO_DB();
        UserDAO_DB = new UserDAO_DB();
        WiFiDAO_DB = new WiFiDAO_DB();
    }

    public static void init() {
        if (instance != null) return;
        instance = new DBFacade();
    }

    public static DBFacade getInstance() {
        if (instance == null) init();
        return instance;
    }

    //Customer Methods
    public ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption {
        return CustomerDAO_DB.createCustomer(customer);
    }

    public ICustomer getCustomerByID(int ID) throws SQLException, CustomerNotFoundExeption {
        return CustomerDAO_DB.getCustomerByID(ID);
    }

    public ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundExeption {
        return CustomerDAO_DB.getAllCustomers();
    }

    public ICustomer updateCustomer(ICustomer customer) throws CustomerNotFoundExeption, SQLException {
        return CustomerDAO_DB.updateCustomer(customer);
    }

    public void anonymizeCustomer(int id) throws SQLException, CustomerNotFoundExeption {
        CustomerDAO_DB.anonymizeCustomer(id);
    }

    public void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption {
        CustomerDAO_DB.deleteCustomer(id);
    }

    //CustomerTask Methods
    public ICustomerTask CreateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundExeption {
        return CustomerTaskDAO_DB.CreateCustomerTask(ct);
    }

    public void addUserToCustomerTask(int userId, int ctId) throws SQLException {
        CustomerTaskDAO_DB.addUserToCustomerTask(userId, ctId);
    }

    public ICustomerTask getCustomerTask(int id) throws SQLException, CustomerTaskNotFoundExeption {
        return CustomerTaskDAO_DB.getCustomerTask(id);
    }

    public ArrayList<ICustomerTask> getAllCustomerTasks() throws SQLException, CustomerTaskNotFoundExeption {
        return CustomerTaskDAO_DB.getAllCustomerTasks();
    }

    public ArrayList<ICustomerTask> getAllUserCustomerTasks(int userId) throws SQLException {
        return CustomerTaskDAO_DB.getAllUserCustomerTasks(userId);
    }

    public ArrayList<ICustomerTask> getAllCustomerTasks(int customerId) throws SQLException, CustomerTaskNotFoundExeption {
        return CustomerTaskDAO_DB.getAllCustomerTasks();
    }

    public ICustomerTask updateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundExeption {
        return CustomerTaskDAO_DB.updateCustomerTask(ct);
    }

    public void deleteCustomerTask(int ID) {
        CustomerTaskDAO_DB.deleteCustomerTask(ID);
    }

    //Device Methods

    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.createDevice(device);
    }

    public IDevice getDevice(int id) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.getDevice(id);
    }

    public ArrayList<IDevice> getDeviceList(int installationId) throws SQLException {
        return DeviceDAO_DB.getDeviceList(installationId);
    }

    public IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.updateDevice(device);
    }

    public void deleteDevice(int id) throws SQLException, DeviceNotFoundExeption {
        DeviceDAO_DB.deleteDevices(id);
    }

    public int deleteDevices(int installationId) throws SQLException, DeviceNotFoundExeption {
        return DeviceDAO_DB.deleteDevices(installationId);
    }

    //Image Methods
    public IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption {
        return ImageDAO_DB.createImage(image);
    }

    public IImage getImage(int id) throws SQLException, ImageNotFoundExeption {
        return ImageDAO_DB.getImage(id);
    }

    public ArrayList<IImage> getImageList(int installationId) throws SQLException {
        return ImageDAO_DB.getImageList(installationId);
    }

    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption {
        return ImageDAO_DB.updateImage(image);
    }

    public void deleteImage(int id) throws SQLException, ImageNotFoundExeption {
        ImageDAO_DB.deleteImage(id);
    }

    public int deleteImages(int installationId) throws SQLException, ImageNotFoundExeption {
        return ImageDAO_DB.deleteImages(installationId);
    }

    //Installation Methods

    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption {
        return InstallationDAO_DB.createInstallation(inst);
    }

    public IInstallation getInstallation(int id) throws SQLException, InstallationNotFoundExeption {
        return InstallationDAO_DB.getInstallation(id);
    }

    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption {
        return InstallationDAO_DB.getInstallations(customerTaskId);
    }

    public ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundExeption {
        return InstallationDAO_DB.getAllInstallations();
    }

    public IInstallation updateInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption {
        return InstallationDAO_DB.updateInstallation(inst);
    }

    public ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException {
        return InstallationDAO_DB.getInstallationsForUser(selectedUserID);
    }

    public void deleteInstallation(int id) throws SQLException, InstallationNotFoundExeption {
        InstallationDAO_DB.deleteInstallation(id);
    }

    public void deleteInstallations(int customerTaskId) throws SQLException, InstallationNotFoundExeption {
        InstallationDAO_DB.deleteInstallations(customerTaskId);
    }

    //Network Methods
    public INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundExeption {
        return NetwordDAO_DB.createNetwork(net);
    }

    public INetwork getNetwork(int id) throws SQLException, NetworkNotFoundExeption {
        return NetwordDAO_DB.getNetwork(id);
    }

    public ArrayList<INetwork> getNetworks(int InstallationId) throws SQLException {
        return NetwordDAO_DB.getNetworks(InstallationId);
    }

    public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption {
        return NetwordDAO_DB.updateNetwork(network);
    }

    public void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption {
        NetwordDAO_DB.deleteNetwork(id);
    }

    public int deleteNetworks(int installationId) throws SQLException, NetworkNotFoundExeption {
        return NetwordDAO_DB.deleteNetworks(installationId);
    }

    //User Methods
    public IUser createUser(IUser user, String hash) throws SQLException, UserNotFoundExeption {
        return UserDAO_DB.createUser(user, hash);
    }

    public IUser getUser(String LoginName) throws SQLException, UserValidationExeption {
        return UserDAO_DB.getUser(LoginName);
    }

    public IUser getUser(int Id) throws SQLException, UserNotFoundExeption {
        return UserDAO_DB.getUser(Id);
    }

    public ArrayList<IUser> getAllUsers(int customerTaskId) throws SQLException {
        return UserDAO_DB.getAllUsers(customerTaskId);
    }

    public ArrayList<IUser> getAllUsers() throws SQLException {
        return UserDAO_DB.getAllUsers();
    }

    public String getUserHash(String loginName) throws SQLException, UserValidationExeption {
        return UserDAO_DB.getUserHash(loginName);
    }

    public boolean loginNameAvailable(String LoginName) throws UserValidationExeption, SQLException {
        return UserDAO_DB.loginNameAvailable(LoginName);
    }

    public void resetPassword(int id, String hash) throws SQLException {
        UserDAO_DB.resetPassword(id, hash);
    }

    public void deleteUser(int Id) throws SQLException, UserNotFoundExeption {
        UserDAO_DB.deleteUser(Id);
    }

    //WiFi Methods
    public IWiFi createWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundExeption {
        return WiFiDAO_DB.createWiFi(wifi);
    }

    public IWiFi getWifi(int id) throws SQLException, WiFiNotFoundExeption {
        return WiFiDAO_DB.getWifi(id);
    }

    public ArrayList<IWiFi> getWiFis(int installationId) throws SQLException {
        return WiFiDAO_DB.getWiFis(installationId);
    }

    public IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundExeption {
        return WiFiDAO_DB.updateWiFi(wifi);
    }

    public void deleteWiFi(int id) throws SQLException, WiFiNotFoundExeption {
        WiFiDAO_DB.deleteWiFi(id);
    }

    public int deleteWiFis(int installationId) throws SQLException, WiFiNotFoundExeption {
        return deleteWiFis(installationId);
    }

}
