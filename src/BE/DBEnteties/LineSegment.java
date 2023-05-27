package BE.DBEnteties;

import javafx.scene.paint.Color;

public class LineSegment implements BE.DBEnteties.Interfaces.ILineSegment {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Color color;

    public LineSegment(double startX, double startY, double endX, double endY, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
    }

    @Override
    public double getStartX() {
        return startX;
    }

    @Override
    public void setStartX(double startX) {
        this.startX = startX;
    }

    @Override
    public double getStartY() {
        return startY;
    }

    @Override
    public void setStartY(double startY) {
        this.startY = startY;
    }

    @Override
    public double getEndX() {
        return endX;
    }

    @Override
    public void setEndX(double endX) {
        this.endX = endX;
    }

    @Override
    public double getEndY() {
        return endY;
    }

    @Override
    public void setEndY(double endY) {
        this.endY = endY;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
