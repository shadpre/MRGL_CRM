package BLL.Managers;

import BE.DBEnteties.Image;
import BE.Exptions.NotFoundExeptions.ImageNotFoundExeption;
import DAL.DB.ImageDAO_DB;

import java.sql.SQLException;

public class ImageManager {

    public static Image createImage(Image image) throws SQLException, ImageNotFoundExeption
    {
        return ImageDAO_DB.createImage(image);
    }
}
