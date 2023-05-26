package BLL.Managers.Interfaces;

import BE.DBEnteties.Image;
import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import DAL.DB.ImageDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IImageManager {
    IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption;

    IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption;

    ArrayList<IImage> getImageList(int installationID) throws SQLException;

    void deleteImage(int id) throws SQLException, ImageNotFoundExeption;
}
