package DAL;

import BE.DBEnteties.Interfaces.*;
import BE.Exptions.NotFoundExeptions.*;
import BE.Exptions.UserValidationException;
import DAL.DAO_DB.*;
import DAL.Interfaces.*;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * The DBFacade class serves as a facade for accessing the data access layer (DAL).
 * It provides methods for interacting with various data entities in the database.
 */
public class DBFacade {

    private static DBFacade instance;
    private final ICustomerDAO customerDAO;
    private final ICustomerTaskDAO customerTaskDAO;
    private final IDeviceDAO deviceDAO;
    private final IImageDAO imageDAO;
    private final IInstallationDAO installationDAO;
    private final INetworkDAO networkDAO;
    private final IUserDAO userDAO;
    private final IWiFiDAO wiFiDAO;
    /**
     * Constructs a new instance of the DBFacade class.
     * The constructor initializes the DAO objects used for database access.
     */
    private DBFacade() {
        customerDAO = new CustomerDAO_DB();
        customerTaskDAO = new CustomerTaskDAO_DB();
        deviceDAO = new DeviceDAO_DB();
        imageDAO = new ImageDAO_DB();
        installationDAO = new InstallationDAO_DB();
        networkDAO = new NetworkDAO_DB();
        userDAO = new UserDAO_DB();
        wiFiDAO = new WiFiDAO_DB();
    }
    /**
     * Initializes the DBFacade instance.
     * This method should be called before using the DBFacade instance.
     */
    public static void init() {
        if (instance != null) return;
        instance = new DBFacade();
    }
    /**
     * Retrieves the singleton instance of the DBFacade.
     *
     * @return The DBFacade instance.
     */
    public static DBFacade getInstance() {
        if (instance == null) init();
        return instance;
    }

    //Customer Methods
    public ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundException {
        return customerDAO.createCustomer(customer);
    }

    public ICustomer getCustomerByID(int ID) throws SQLException, CustomerNotFoundException {
        return customerDAO.getCustomerByID(ID);
    }

    public ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundException {
        return customerDAO.getAllCustomers();
    }

    public ICustomer updateCustomer(ICustomer customer) throws CustomerNotFoundException, SQLException {
        return customerDAO.updateCustomer(customer);
    }

    public void anonymizeCustomer(int id) throws SQLException, CustomerNotFoundException {
        customerDAO.anonymizeCustomer(id);
    }

    public void deleteCustomer(int id) throws SQLException, CustomerNotFoundException {
        customerDAO.deleteCustomer(id);
    }

    //CustomerTask Methods
    public ICustomerTask CreateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundException {
        return customerTaskDAO.CreateCustomerTask(ct);
    }

    public void addUserToCustomerTask(int userId, int ctId) throws SQLException {
        customerTaskDAO.addUserToCustomerTask(userId, ctId);
    }

    public ICustomerTask getCustomerTask(int id) throws SQLException, CustomerTaskNotFoundException {
        return customerTaskDAO.getCustomerTask(id);
    }

    public ArrayList<ICustomerTask> getAllCustomerTasks() throws SQLException, CustomerTaskNotFoundException {
        return customerTaskDAO.getAllCustomerTasks();
    }

    public ArrayList<ICustomerTask> getAllUserCustomerTasks(int userId) throws SQLException {
        return customerTaskDAO.getAllUserCustomerTasks(userId);
    }

    public ArrayList<ICustomerTask> getAllCustomerTasks(int customerId) throws SQLException, CustomerTaskNotFoundException {
        return customerTaskDAO.getAllCustomerTasks(customerId);
    }

    public ICustomerTask updateCustomerTask(ICustomerTask ct) throws SQLException, CustomerTaskNotFoundException {
        return customerTaskDAO.updateCustomerTask(ct);
    }

    public void deleteCustomerTask(int ID) throws SQLException{
        customerTaskDAO.deleteCustomerTask(ID);
    }

    //Device Methods

    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundException {
        return deviceDAO.createDevice(device);
    }

    public IDevice getDevice(int id) throws SQLException, DeviceNotFoundException {
        return deviceDAO.getDevice(id);
    }

    public ArrayList<IDevice> getDeviceList(int installationId) throws SQLException {
        return deviceDAO.getDeviceList(installationId);
    }

    public IDevice updateDevice(IDevice device) throws SQLException, DeviceNotFoundException {
        return deviceDAO.updateDevice(device);
    }

    public void deleteDevice(int id) throws SQLException, DeviceNotFoundException {
        deviceDAO.deleteDevice(id);
    }

    public int deleteDevices(int installationId) throws SQLException, DeviceNotFoundException {
        return deviceDAO.deleteDevices(installationId);
    }

    //Image Methods
    public IImage createImage(IImage image) throws SQLException, ImageNotFoundException {
        return imageDAO.createImage(image);
    }

    public IImage getImage(int id) throws SQLException, ImageNotFoundException {
        return imageDAO.getImage(id);
    }

    public ArrayList<IImage> getImageList(int installationId) throws SQLException {
        return imageDAO.getImageList(installationId);
    }

    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundException {
        return imageDAO.updateImage(image);
    }

    public void deleteImage(int id) throws SQLException, ImageNotFoundException {
        imageDAO.deleteImage(id);
    }

    public int deleteImages(int installationId) throws SQLException, ImageNotFoundException {
        return imageDAO.deleteImages(installationId);
    }

    //Installation Methods

    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException {
        return installationDAO.createInstallation(inst);
    }

    public IInstallation getInstallation(int id) throws SQLException, InstallationNotFoundException {
        return installationDAO.getInstallation(id);
    }

    public ArrayList<IInstallation> getInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException {
        return installationDAO.getInstallations(customerTaskId);
    }

    public ArrayList<IInstallation> getAllInstallations() throws SQLException, InstallationNotFoundException {
        return installationDAO.getAllInstallations();
    }

    public IInstallation updateInstallation(IInstallation inst) throws SQLException, InstallationNotFoundException {
        return installationDAO.updateInstallation(inst);
    }

    public ArrayList<IInstallation> getInstallationsForUser(int selectedUserID) throws SQLException {
        return installationDAO.getInstallationsForUser(selectedUserID);
    }

    public void deleteInstallation(int id) throws SQLException, InstallationNotFoundException {
        installationDAO.deleteInstallation(id);
    }

    public void deleteInstallations(int customerTaskId) throws SQLException, InstallationNotFoundException {
        installationDAO.deleteInstallations(customerTaskId);
    }

    //Network Methods
    public INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundException {
        return networkDAO.createNetwork(net);
    }

    public INetwork getNetwork(int id) throws SQLException, NetworkNotFoundException {
        return networkDAO.getNetwork(id);
    }

    public ArrayList<INetwork> getNetworks(int InstallationId) throws SQLException {
        return networkDAO.getNetworks(InstallationId);
    }

    public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundException {
        return networkDAO.updateNetwork(network);
    }

    public void deleteNetwork(int id) throws SQLException, NetworkNotFoundException {
        networkDAO.deleteNetwork(id);
    }

    public int deleteNetworks(int installationId) throws SQLException, NetworkNotFoundException {
        return networkDAO.deleteNetworks(installationId);
    }

    //User Methods
    public IUser createUser(IUser user, String hash) throws SQLException, UserNotFoundException {
        return userDAO.createUser(user, hash);
    }

    public IUser getUser(String LoginName) throws SQLException, UserValidationException {
        return userDAO.getUser(LoginName);
    }

    public IUser getUser(int Id) throws SQLException, UserNotFoundException {
        return userDAO.getUser(Id);
    }

    public ArrayList<IUser> getAllUsers(int customerTaskId) throws SQLException {
        return userDAO.getAllUsers(customerTaskId);
    }

    public ArrayList<IUser> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public String getUserHash(String loginName) throws SQLException, UserValidationException {
        return userDAO.getUserHash(loginName);
    }

    public IUser updateUser(IUser user, String hash) throws SQLException, UserNotFoundException {
        return userDAO.updateUser(user, hash);
    }

    public boolean loginNameAvailable(String LoginName) throws UserValidationException, SQLException {
        return userDAO.loginNameAvailable(LoginName);
    }

    public void resetPassword(int id, String hash) throws SQLException {
        userDAO.resetPassword(id, hash);
    }

    public void deleteUser(int Id) throws SQLException, UserNotFoundException {
        userDAO.deleteUser(Id);
    }

    //WiFi Methods
    public IWiFi createWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException {
        return wiFiDAO.createWiFi(wifi);
    }

    public IWiFi getWifi(int id) throws SQLException, WiFiNotFoundException {
        return wiFiDAO.getWifi(id);
    }

    public ArrayList<IWiFi> getWiFis(int installationId) throws SQLException {
        return wiFiDAO.getWiFis(installationId);
    }

    public IWiFi updateWiFi(IWiFi wifi) throws SQLException, WiFiNotFoundException {
        return wiFiDAO.updateWiFi(wifi);
    }

    public void deleteWiFi(int id) throws SQLException, WiFiNotFoundException {
        wiFiDAO.deleteWiFi(id);
    }

    public int deleteWiFis(int installationId) throws SQLException, WiFiNotFoundException {
        return deleteWiFis(installationId);
    }

}
