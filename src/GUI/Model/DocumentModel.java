package GUI.Model;

import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.DocumentGeneration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentModel {

    public void saveDocumentToDisk(ICustomerTask customerTask, String filePath) throws Exception {
        DocumentGeneration.documentGeneration(customerTask,filePath);
    }

    public void saveDocumentToDisk(ICustomerTask customerTask) throws Exception {

        Files.createDirectories(Paths.get("C:\\ProgramData\\MRGL\\TEMP"));
        DocumentGeneration.documentGeneration(customerTask, "C:\\ProgramData\\MRGL\\TEMP\\temp.pdf");
    }
}
