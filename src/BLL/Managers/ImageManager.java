package BLL.Managers;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import BLL.Interfaces.IImageManager;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class ImageManager implements IImageManager {

    @Override
    public IImage createImage(IImage image) throws SQLException, ImageNotFoundExeption {
        return DBFacade.getInstance().createImage(image);
    }

    @Override
    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundExeption {

        return DBFacade.getInstance().updateImage(image);
    }

    @Override
    public ArrayList<IImage> getImageList(int installationID) throws SQLException {
        return DBFacade.getInstance().getImageList(installationID);
    }

    @Override
    public void deleteImage(int id) throws SQLException, ImageNotFoundExeption {
        DBFacade.getInstance().deleteImage(id);
    }
}
