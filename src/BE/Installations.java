package BE;

public class Installations {
    private int Id;
    private int DocumentId;
    private String Description;
    private String Remarks;

    public Installations(int id, int documentId, String description, String remarks) {
        Id = id;
        DocumentId = documentId;
        Description = description;
        Remarks = remarks;
    }

    public int getId() {
        return Id;
    }

    public int getDocumentId() {
        return DocumentId;
    }

    public String getDescription() {
        return Description;
    }

    public String getRemarks() {
        return Remarks;
    }
}
