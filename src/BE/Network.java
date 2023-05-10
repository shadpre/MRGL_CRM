package BE;

public class Network extends InstallationUnit{
   private String NetworkIP;
   private String SubnetMask;
   private String DefaultGateway;
   private boolean HasPOE;

    public Network(int id, int installationId, String description, String remarks, String networkIP, String subnetMask, String defaultGateway, boolean hasPOE) {
        super(id, installationId, description, remarks);
        NetworkIP = networkIP;
        SubnetMask = subnetMask;
        DefaultGateway = defaultGateway;
        HasPOE = hasPOE;
    }

    public String getNetworkIP() {
        return NetworkIP;
    }

    public String getSubnetMask() {
        return SubnetMask;
    }

    public String getDefaultGateway() {
        return DefaultGateway;
    }

    public boolean isHasPOE() {
        return HasPOE;
    }
}
