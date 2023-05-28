package BE.DBEnteties;

public abstract class InstallationUnit implements BE.DBEnteties.Interfaces.IInstallationUnit {
    private final int Id;
    private final int InstallationId;
    private final String Description;
    private final String Remarks;

    public InstallationUnit(int id, int installationId, String description, String remarks) {
        Id = id;
        InstallationId = installationId;
        Description = description;
        Remarks = remarks;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public int getInstallationId() {
        return InstallationId;
    }

    @Override
    public String getDescription() {
        return Description;
    }

    @Override
    public String getRemarks() {
        return Remarks;
    }
}
