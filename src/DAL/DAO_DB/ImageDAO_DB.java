package DAL.DAO_DB;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BE.DBEnteties.Image;
import DAL.DatabaseConnector;
import DAL.Iterfaces.IImageDAO;

import java.sql.*;
import java.util.ArrayList;

public class ImageDAO_DB implements IImageDAO {
    @Override
    public IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption {
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

    @Override
    public IImage getImage(int id) throws SQLException, ImageNotFoundExeption{
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
                        rs.getBytes("Data"),
                        rs.getInt("ImageType")
                );
            }
            else throw new ImageNotFoundExeption("Image not found");
        }
    }

    @Override
    public ArrayList<IImage> getImageList(int installationId) throws SQLException{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            ArrayList<IImage> out = new ArrayList<>();

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
                        rs.getBytes("Data"),
                        rs.getInt("ImageType")
                ));
            }
            return out;
        }
    }

    @Override
    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "UPDATE Images " +
                    "SET InstallationId = ?, Description = ?, Remarks = ?, Data = CONVERT(varbinary(max), ?), ImageType = ? " +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, image.getInstallationId());
            statement.setString(2, image.getDescription());
            statement.setString(3, image.getRemarks());
            statement.setBytes(4, image.getData());
            statement.setInt(5, image.getImageType());
            statement.setInt(6, image.getId()); // Use index 6 for the Id parameter

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ImageNotFoundExeption("Image not found");
            }
        }
        return getImage(image.getId());
    }


    @Override
    public void deleteImage(int id) throws SQLException, ImageNotFoundExeption{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "DELETE Images WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new ImageNotFoundExeption("Image not found");
        }
    }

    @Override
    public int deleteImages(int installationId) throws SQLException, ImageNotFoundExeption{
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
