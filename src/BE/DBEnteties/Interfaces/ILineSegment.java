package BE.DBEnteties.Interfaces;

import javafx.scene.paint.Color;

public interface ILineSegment {
    double getStartX();

    void setStartX(double startX);

    double getStartY();

    void setStartY(double startY);

    double getEndX();

    void setEndX(double endX);

    double getEndY();

    void setEndY(double endY);

    Color getColor();

    void setColor(Color color);
}
