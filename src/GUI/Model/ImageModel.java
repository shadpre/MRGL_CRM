package GUI.Model;

import BE.DBEnteties.Image;
import BLL.Managers.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImageModel {

    public static ObservableList<Image> getImageList(int installationID){
        ObservableList<Image>  out;
        try {
            out =  FXCollections.observableArrayList(ImageManager.getImageList(installationID));
        } catch (Exception e) {
            throw new RuntimeException("404");
        }
        return out;
    }
}
