package BE.DBEnteties;

public class Device extends InstallationUnit implements BE.DBEnteties.Interfaces.IDevice {

    private final String IP;
    private final String SubnetMask;
    private final String UserName;
    private final String Password;
    private final boolean IsPOE;

    public Device(int id, int installationId, String description, String remarks, String IP, String subnetMask, String userName, String password, boolean isPOE) {
        super(id, installationId, description, remarks);
        this.IP = IP;
        SubnetMask = subnetMask;
        UserName = userName;
        Password = password;
        IsPOE = isPOE;
    }

    @Override
    public String getIP() {
        return IP;
    }

    @Override
    public String getSubnetMask() {
        return SubnetMask;
    }

    @Override
    public String getUserName() {
        return UserName;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public boolean isPOE() {
        return IsPOE;
    }
}
