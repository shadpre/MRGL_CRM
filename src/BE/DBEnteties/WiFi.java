package BE.DBEnteties;

import BE.DBEnteties.Interfaces.IWiFi;

public class WiFi extends InstallationUnit implements IWiFi {
    private String SSID;
    private String PSK;

    public WiFi(int id, int installationId, String description, String remarks, String SSID, String PSK) {
        super(id, installationId, description, remarks);
        this.SSID = SSID;
        this.PSK = PSK;
    }

    @Override
    public String getSSID() {
        return SSID;
    }

    @Override
    public String getPSK() {
        return PSK;
    }
}
