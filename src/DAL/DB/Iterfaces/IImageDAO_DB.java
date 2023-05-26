package DAL.DB.Iterfaces;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IImageDAO_DB {
    IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption;

    IImage getImage(int id) throws SQLException, ImageNotFoundExeption;

    ArrayList<IImage> getImageList(int installationId) throws SQLException;

    IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption;

    void deleteImage(int id) throws SQLException, ImageNotFoundExeption;

    int deleteImages(int installationId) throws SQLException, ImageNotFoundExeption;
}
