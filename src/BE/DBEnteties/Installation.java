package BE.DBEnteties;

public class Installation {
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

    public int getId() {
        return Id;
    }

    public int getCustomerTaskId() {
        return CustomerTaskId;
    }

    public String getDescription() {
        return Description;
    }

    public String getRemarks() {
        return Remarks;
    }
}
