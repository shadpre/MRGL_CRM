package BLL;

import BE.Document;
import BE.User;
import DAL.db.DocumentDAO_DB;

public class DocumentManager {

    public static Document saveDocument(Document doc, int UserID) throws Exception{
        return DocumentDAO_DB.saveDocument(doc, UserID);
    }
}
