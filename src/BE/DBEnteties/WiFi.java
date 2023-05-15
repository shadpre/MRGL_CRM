package BE.DBEnteties;

public class WiFi extends InstallationUnit{
    private String SSID;
    private String PSK;

    public WiFi(int id, int installationId, String description, String remarks, String SSID, String PSK) {
        super(id, installationId, description, remarks);
        this.SSID = SSID;
        this.PSK = PSK;
    }

    public String getSSID() {
        return SSID;
    }

    public String getPSK() {
        return PSK;
    }
}
