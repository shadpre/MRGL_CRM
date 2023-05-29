package BLL.Interfaces;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IImageManager {
    IImage createImage(IImage image) throws SQLException, ImageNotFoundException;

    IImage updateImage(IImage image) throws SQLException, ImageNotFoundException;

    ArrayList<IImage> getImageList(int installationID) throws SQLException;

    void deleteImage(int id) throws SQLException, ImageNotFoundException;
}
