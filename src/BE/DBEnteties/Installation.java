package BE.DBEnteties;

public class Installation {
    private int Id;
    private int CustomerId;
    private String Description;
    private String Remarks;

    public Installation(int id, int documentId, String description, String remarks) {
        Id = id;
        CustomerId = documentId;
        Description = description;
        Remarks = remarks;
    }

    public int getId() {
        return Id;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getDescription() {
        return Description;
    }

    public String getRemarks() {
        return Remarks;
    }
}
