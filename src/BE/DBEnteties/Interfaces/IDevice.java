package BE.DBEnteties.Interfaces;

public interface IDevice extends IInstallationUnit {
    String getIP();

    String getSubnetMask();

    String getUserName();

    String getPassword();

    boolean isPOE();
}
