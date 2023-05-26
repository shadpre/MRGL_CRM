package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IImageManager {
    IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption;

    IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption;

    ArrayList<IImage> getImageList(int installationID) throws SQLException;

    void deleteImage(int id) throws SQLException, ImageNotFoundExeption;
}
