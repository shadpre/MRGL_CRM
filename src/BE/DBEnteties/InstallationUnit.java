package BE.DBEnteties;

public abstract class InstallationUnit {
    private int Id;
    private int InstallationId;
    private String Description;
    private String Remarks;

    public InstallationUnit(int id, int installationId, String description, String remarks) {
        Id = id;
        InstallationId = installationId;
        Description = description;
        Remarks = remarks;
    }

    public int getId() {
        return Id;
    }

    public int getInstallationId() {
        return InstallationId;
    }

    public String getDescription() {
        return Description;
    }

    public String getRemarks() {
        return Remarks;
    }
}
