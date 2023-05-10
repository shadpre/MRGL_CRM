package BE;

public class Document {
    private int Id;
    private int CustomerTaskId;
    private String Description;
    private String Remarks;

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

    public Document(int id, int customerTaskId, String description, String remarks) {
        Id = id;
        CustomerTaskId = customerTaskId;
        Description = description;
        Remarks = remarks;


    }
}
