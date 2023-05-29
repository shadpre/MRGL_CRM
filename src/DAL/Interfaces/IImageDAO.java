package DAL.Interfaces;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IImageDAO {
    /**
     * Creates a new image in the database.
     *
     * @param image The image to create.
     * @return The created image.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    IImage createImage(IImage image) throws SQLException, ImageNotFoundException;
    /**
     * Retrieves an image from the database by its ID.
     *
     * @param id The ID of the image to retrieve.
     * @return The retrieved image.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    IImage getImage(int id) throws SQLException, ImageNotFoundException;
    /**
     * Retrieves a list of images associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return An ArrayList of images associated with the installation.
     * @throws SQLException If a database access error occurs.
     */
    ArrayList<IImage> getImageList(int installationId) throws SQLException;
    /**
     * Updates an existing image in the database.
     *
     * @param image The image to update.
     * @return The updated image.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    IImage updateImage(IImage image) throws SQLException, ImageNotFoundException;
    /**
     * Deletes an image from the database by its ID.
     *
     * @param id The ID of the image to delete.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If the image was not found.
     */
    void deleteImage(int id) throws SQLException, ImageNotFoundException;
    /**
     * Deletes all images associated with a specific installation from the database.
     *
     * @param installationId The ID of the installation.
     * @return The number of images deleted.
     * @throws SQLException                 If a database access error occurs.
     * @throws ImageNotFoundException       If no images were found.
     */
    int deleteImages(int installationId) throws SQLException, ImageNotFoundException;
}
