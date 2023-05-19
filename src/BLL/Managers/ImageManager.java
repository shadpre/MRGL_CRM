package BLL.Managers;

import BE.DBEnteties.Device;
import BE.DBEnteties.Image;
import BE.Exptions.NotFoundExeptions.DeviceNotFoundExeption;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import DAL.DB.DeviceDAO_DB;
import DAL.DB.ImageDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class ImageManager {

    public static Image createImage(Image image) throws SQLException, ImageNotFoundExeption
    {
        return ImageDAO_DB.createImage(image);
    }

    public static Image updateImage(Image image) throws SQLException, ImageNotFoundExeption{

        return ImageDAO_DB.updateImage(image);
    }

    public static ArrayList<Image> getImageList(int installationID) throws SQLException, ImageNotFoundExeption {
        return ImageDAO_DB.getImageList(installationID);
    }
}
