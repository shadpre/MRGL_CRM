package DAL.DB;

import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BE.DBEnteties.Image;

import java.sql.*;
import java.util.ArrayList;

public class ImageDAO_DB {
    public static Image createImage(Image image) throws SQLException, ImageNotFoundExeption {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO Images (InstallationId, Description, Remarks, Data, ImageType) VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,image.getInstallationId());
            statement.setString(2, image.getDescription());
            statement.setString(3, image.getRemarks());
            statement.setBytes(4, image.getData());
            statement.setInt(5, image.getImageType());

            var rs = statement.executeQuery();

            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("Device not saved");
        }
        return getImage(ID);
    }

    public static Image getImage(int id) throws SQLException, ImageNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT InstallationId, Description, Remarks, Data, ImageType FROM Images WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()){
                return new Image(
                        id,
                        rs.getInt("InstallationId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getBytes("data"),
                        rs.getInt("ImageType")
                );
            }
            else throw new ImageNotFoundExeption("Image not found");
        }
    }

    public static ArrayList<Image> getImageList(int installationId) throws SQLException, ImageNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            ArrayList<Image> out = new ArrayList<>();

            String query = "SELECT Id, Description, Remarks, Data, ImageType FROM Images WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeQuery();
            while (rs.next()){
                out.add(new Image(
                        rs.getInt("Id"),
                        installationId,
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getBytes("data"),
                        rs.getInt("ImageType")
                ));
            }
            if (out.size()==0) throw new ImageNotFoundExeption("No images found");
            else return out;
        }
    }

    public static Image updateImage(Image image) throws SQLException, ImageNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "UPDATE Images" +
                    "SET Installation Id = ?, Description = ?, Remarks = ?, Data = ?, Imagetype = ?" +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,image.getId());

            var rs = statement.executeUpdate();

            if(rs == 0){
                throw new ImageNotFoundExeption("Image not found");
            }
        }
        return getImage(image.getId());
    }

    public static void deleteImage(int id) throws SQLException, ImageNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "DELETE Images WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new ImageNotFoundExeption("Image not found");
        }
    }

    public static int deleteImages(int installationId) throws SQLException, ImageNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE Images where InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new ImageNotFoundExeption("No images found");
            else return rs;
        }
    }
}
