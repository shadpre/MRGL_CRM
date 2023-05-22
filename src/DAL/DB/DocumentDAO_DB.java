package DAL.DB;

import BE.DBEnteties.Document;
import BE.Exptions.NotFoundExeptions.DocumentNotFoundExeption;

import java.sql.*;

public class DocumentDAO_DB {

    public static Document createDocument(Document doc) throws SQLException, DocumentNotFoundExeption {
        int ID;
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "INSERT INTO Documents (CustomerTaskId, Description, Remarks) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,doc.getCustomerTaskId());
            statement.setString(2, doc.getDescription());
            statement.setString(3, doc.getRemarks());

            var rs = statement.executeQuery();

            if (rs.next()){
                ID = rs.getInt(1);
            }
            else throw new SQLDataException("Document not saved");
        }

        return getDocument(ID);
    }

    public static Document getDocument(int id) throws SQLException, DocumentNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT CustomerTaskId, Description, Remarks from Documents WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if(rs.next()){
                return new Document(
                        id,
                        rs.getInt("CustomerTaskId"),
                        rs.getString("Description"),
                        rs.getString("Remarks")
                );
            }
            else throw new DocumentNotFoundExeption("Document not found");
        }
    }



}
