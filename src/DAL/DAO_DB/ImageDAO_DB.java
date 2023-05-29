package DAL.DAO_DB;

import BE.DBEnteties.Image;
import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundException;
import DAL.DatabaseConnector;
import DAL.Interfaces.IImageDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * The ImageDAO_DB class is responsible for performing database operations related to images.
 */
public class ImageDAO_DB implements IImageDAO {

    /**
     * Creates a new image in the database.
     *
     * @param image The image to create.
     * @return The created image.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    @Override
    public IImage createImage(IImage image) throws SQLException, ImageNotFoundException {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "INSERT INTO Images (InstallationId, Description, Remarks, Data, ImageType) VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, image.getInstallationId());
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

    /**
     * Retrieves an image from the database by its ID.
     *
     * @param id The ID of the image to retrieve.
     * @return The retrieved image.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    @Override
    public IImage getImage(int id) throws SQLException, ImageNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "SELECT InstallationId, Description, Remarks, Data, ImageType FROM Images WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeQuery();

            if (rs.next()) {
                return new Image(
                        id,
                        rs.getInt("InstallationId"),
                        rs.getString("Description"),
                        rs.getString("Remarks"),
                        rs.getBytes("Data"),
                        rs.getInt("ImageType")
                );
            } else throw new ImageNotFoundException("Image not found");
        }
    }

    /**
     * Retrieves a list of images associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return An ArrayList of images associated with the installation.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public ArrayList<IImage> getImageList(int installationId) throws SQLException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            ArrayList<IImage> out = new ArrayList<>();

            String query = "SELECT Id, Description, Remarks, Data, ImageType FROM Images WHERE InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeQuery();
            while (rs.next()) {
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

    /**
     * Updates an existing image in the database.
     *
     * @param image The image to update.
     * @return The updated image.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    @Override
    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundException {
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
            statement.setInt(6, image.getId()); // Use index 6 for the id parameter

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ImageNotFoundException("Image not found");
            }
        }
        return getImage(image.getId());
    }

    /**
     * Deletes an image from the database by its ID.
     *
     * @param id The ID of the image to delete.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    @Override
    public void deleteImage(int id) throws SQLException, ImageNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE Images WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new ImageNotFoundException("Image not found");
        }
    }

    /**
     * Deletes all images associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return The number of images deleted.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If no images were found.
     */
    @Override
    public int deleteImages(int installationId) throws SQLException, ImageNotFoundException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
            String query = "DELETE Images where InstallationId = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, installationId);

            var rs = statement.executeUpdate();

            if (rs == 0) throw new ImageNotFoundException("No images found");
            else return rs;
        }
    }
}
