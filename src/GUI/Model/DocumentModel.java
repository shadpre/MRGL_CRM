package GUI.Model;

import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.DocumentGeneration;

public class DocumentModel {

    public void saveDocumentToDisk(ICustomerTask customerTask, String filePath) throws Exception {
        DocumentGeneration.documentGeneration(customerTask,filePath);
    }
}
