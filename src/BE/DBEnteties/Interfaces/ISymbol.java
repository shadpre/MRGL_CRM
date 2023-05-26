package BE.DBEnteties.Interfaces;

import javafx.scene.image.Image;

public interface ISymbol {
    javafx.scene.image.Image getImage();

    void setImage(Image image);

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getWidth();

    void setWidth(double width);

    double getHeight();

    void setHeight(double height);
}
