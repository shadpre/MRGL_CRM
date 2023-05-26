package BLL.Managers;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BLL.Managers.Interfaces.IImageManager;
import DAL.DB.ImageDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class ImageManager implements IImageManager {

    @Override
    public IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption {
        return ImageDAO_DB.createImage(image);
    }

    @Override
    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption {

        return ImageDAO_DB.updateImage(image);
    }

    @Override
    public ArrayList<IImage> getImageList(int installationID) throws SQLException {
        return ImageDAO_DB.getImageList(installationID);
    }

    @Override
    public void deleteImage(int id) throws SQLException, ImageNotFoundExeption {
        ImageDAO_DB.deleteImage(id);
    }
}
