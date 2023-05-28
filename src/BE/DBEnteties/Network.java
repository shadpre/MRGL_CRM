package BE.DBEnteties;

public class Network extends InstallationUnit implements BE.DBEnteties.Interfaces.INetwork {
    private final String NetworkIP;
    private final String SubnetMask;
    private final String DefaultGateway;
    private final boolean HasPOE;

    public Network(int id, int installationId, String description, String remarks, String networkIP, String subnetMask, String defaultGateway, boolean hasPOE) {
        super(id, installationId, description, remarks);
        NetworkIP = networkIP;
        SubnetMask = subnetMask;
        DefaultGateway = defaultGateway;
        HasPOE = hasPOE;
    }

    @Override
    public String getNetworkIP() {
        return NetworkIP;
    }

    @Override
    public String getSubnetMask() {
        return SubnetMask;
    }

    @Override
    public String getDefaultGateway() {
        return DefaultGateway;
    }

    @Override
    public boolean isHasPOE() {
        return HasPOE;
    }
}
