package GUI.Model;

import BE.DBEnteties.Interfaces.IImage;
import BE.Exptions.NotFoundExeptions.ImageNotFoundException;
import BLL.Interfaces.IImageManager;
import BLL.Managers.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ImageModel {

    private final IImageManager imageManager = new ImageManager();

    public ObservableList<IImage> getImageList(int installationID) {

        ObservableList<IImage> out;
        try {
            out = FXCollections.observableArrayList(imageManager.getImageList(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }

    public void deleteImage(int id) throws SQLException, ImageNotFoundException {
        imageManager.deleteImage(id);
    }

    public IImage createImage(IImage image) throws SQLException, ImageNotFoundException {
        return imageManager.createImage(image);
    }

    public IImage updateImage(IImage image) throws SQLException, ImageNotFoundException {
        return imageManager.updateImage(image);
    }
}
