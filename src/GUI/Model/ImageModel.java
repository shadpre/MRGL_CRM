package GUI.Model;

import BE.DBEnteties.Image;
import BE.DBEnteties.Interfaces.IImage;
import BLL.Interfaces.IImageManager;
import BLL.Managers.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImageModel {

    public static ObservableList<IImage> getImageList(int installationID) {
        IImageManager imageManager = new ImageManager();
        ObservableList<IImage> out;
        try {
            out = FXCollections.observableArrayList(imageManager.getImageList(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
