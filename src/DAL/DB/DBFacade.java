package DAL.DB;

import BE.DBEnteties.Interfaces.*;
import BE.DBEnteties.User;
import BE.Exptions.NotFoundExeptions.*;
import DAL.DB.Iterfaces.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBFacade {

    private static DBFacade instance;
    private final ICustomerDAO_DB CustomerDAO_DB;
    private final ICustomerTaskDAO_DB CustomerTaskDAO_DB;
    private final IDeviceDAO_DB DeviceDAO_DB;
    private final IImageDAO_DB ImageDAO_DB;
    private final IInstallationDAO_DB InstallationDAO_DB;

    private DBFacade() {
        CustomerDAO_DB = new CustomerDAO_DB();
        CustomerTaskDAO_DB = new CustomerTaskDAO_DB();
        DeviceDAO_DB = new DeviceDAO_DB();
        ImageDAO_DB = new ImageDAO_DB();
        InstallationDAO_DB = new InstallationDAO_DB();
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

    public IDevice createDevice(IDevice device) throws SQLException, DeviceNotFoundExeption{
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

    public IInstallation createInstallation(IInstallation inst) throws SQLException, InstallationNotFoundExeption{
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



}
