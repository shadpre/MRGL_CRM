package BE;

public class Device extends InstallationUnit{

    private String IP;
    private String SubnetMask;
    private String UserName;
    private String Password;
    private boolean IsPOE;

    public Device(int id, int installationId, String description, String remarks, String IP, String subnetMask, String userName, String password, boolean isPOE) {
        super(id, installationId, description, remarks);
        this.IP = IP;
        SubnetMask = subnetMask;
        UserName = userName;
        Password = password;
        IsPOE = isPOE;
    }

    public String getIP() {
        return IP;
    }

    public String getSubnetMask() {
        return SubnetMask;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isPOE() {
        return IsPOE;
    }
}
