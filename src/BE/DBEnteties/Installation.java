package BE.DBEnteties;

public class Installation implements BE.DBEnteties.Interfaces.IInstallation {
    private int Id;
    private int CustomerTaskId;
    private String Description;
    private String Remarks;

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
