package DAL.db;

import BE.Document;
import BE.Exptions.DocumentNotFoundExeption;
import BE.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DocumentDAO_DB {

    public static ArrayList<Document> getAllDocuments() throws Exception{
        ArrayList<Document> output = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT Id, SketchIMG, SketchDescription, ImgBefore, DescriptionBefore, ImgAfter, CreatedDate, UserID FROM Documents";
            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();

            while (rs.next()){
                output.add(new Document(
                        rs.getInt("Id"),
                        rs.getBytes("SketchIMG"),
                        rs.getString("SketchDescription"),
                        rs.getBytes("ImgBefore"),
                        rs.getString("DescriptionBefore"),
                        rs.getBytes("ImgAfter"),
                        rs.getTimestamp("CreatedDate").toLocalDateTime(),
                        rs.getInt("UserId")
                ));
            }
        }
        if (output.size() == 0){
            throw new DocumentNotFoundExeption("No documents found");
        }
        return output;
    }

    public static ArrayList<Document> getDocumentsByUserID(int UserID) throws Exception{
        ArrayList<Document> output = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){

            String query = "SELECT Id, SketchIMG, SketchDescription, ImgBefore, DescriptionBefore, ImgAfter, CreatedDate FROM Documents WHERE UserID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,UserID);

            var rs = statement.executeQuery();

            while (rs.next()){
                output.add(new Document(
                        rs.getInt("Id"),
                        rs.getBytes("SketchIMG"),
                        rs.getString("SketchDescription"),
                        rs.getBytes("ImgBefore"),
                        rs.getString("DescriptionBefore"),
                        rs.getBytes("ImgAfter"),
                        rs.getTimestamp("CreatedDate").toLocalDateTime(),
                        UserID
                ));
            }
        }
        if (output.size() == 0){
            throw new DocumentNotFoundExeption("No documents found");
        }
        return output;
    }

    public static Document getDocumentById (int Id) throws Exception{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT SketchIMG, SketchDescription, ImgBefore, DescriptionBefore, ImgAfter, CreatedDate, UserID FROM Documents WHERE Id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, Id);

            var rs = statement.executeQuery();

            if (rs.next()) {
                return new Document(
                        Id,
                        rs.getBytes("SketchIMG"),
                        rs.getString("SketchDescription"),
                        rs.getBytes("ImgBefore"),
                        rs.getString("DescriptionBefore"),
                        rs.getBytes("ImgAfter"),
                        rs.getTimestamp("CreatedDate").toLocalDateTime(),
                        rs.getInt("UserId"));
            } else {
                throw new DocumentNotFoundExeption("Document not found");
            }
        }
    }

    public static Document saveDocument(Document doc, int UserID) throws Exception{
        int newID = 0;
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "INSERT INTO Documents (SketchIMG, SketchDescription, ImgBefore, DescriptionVefore, ImgAfter, CreatedDate, UserID) VALUES (?,?,?,?,?,GETDATE(),?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setBytes(1, doc.getSketchIMG());
            statement.setString(2, doc.getSketchDescription());
            statement.setBytes(3, doc.getImgBefore());
            statement.setString(4, doc.getDescriptionBefore());
            statement.setBytes(5, doc.getImgAfter());
            statement.setInt(6, UserID);


            var rs = statement.executeQuery();
            if(rs.next()){
                newID = rs.getInt(1);
            }
        }

        return getDocumentById(newID);
    }
}

