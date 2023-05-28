package BE.DBEnteties;

public class Installation implements BE.DBEnteties.Interfaces.IInstallation {
    private final int Id;
    private final int CustomerTaskId;
    private final String Description;
    private final String Remarks;

    public Installation(int id, int customerTaskId, String description, String remarks) {
        Id = id;
        CustomerTaskId = customerTaskId;
        Description = description;
        Remarks = remarks;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public int getCustomerTaskId() {
        return CustomerTaskId;
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
