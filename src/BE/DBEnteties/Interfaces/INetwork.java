package BE.DBEnteties.Interfaces;

public interface INetwork extends IInstallationUnit {
    String getNetworkIP();

    String getSubnetMask();

    String getDefaultGateway();

    boolean isHasPOE();
}
