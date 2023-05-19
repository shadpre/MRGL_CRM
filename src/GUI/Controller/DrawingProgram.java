package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawingProgram extends BaseController implements Initializable {



    @FXML
    private ImageView symbol1;

    @FXML
    private Canvas canvas;

    public void handleAddLineButtonClicked(ActionEvent actionEvent) {
    }

    public void handleSymbol2ButtonClicked(ActionEvent actionEvent) {
    }

    public void handleSymbol1ButtonClicked(ActionEvent actionEvent) {
    }

    public void handleSymbol3ButtonClicked(ActionEvent actionEvent) {
    }



    public void dragSymbolOne(MouseEvent event) {

        Dragboard dragboard = symbol1.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(symbol1.getImage());
        dragboard.setContent(content);
        event.consume();

        // Remove event handlers from the symbol after it's dragged
        symbol1.setOnDragDetected(null);
        symbol1.setOnDragDone(null);


    }

    public void dragDoneSymbolOne(DragEvent event) {
        if (event.getTransferMode() == TransferMode.COPY) {
            // Retrieve the dropped symbol position in the local coordinate system
            double symbolX = symbol1.getLayoutX();
            double symbolY = symbol1.getLayoutY();

            // Convert the symbol's position to the coordinate system of the canvas
            Point2D canvasPoint = symbol1.getParent().sceneToLocal(symbol1.localToScene(symbolX, symbolY));

            // Retrieve the dropped position in the canvas's coordinate system
            double canvasX = canvasPoint.getX() - (symbol1.getBoundsInLocal().getWidth() - symbol1.getFitWidth()) / 2;
            double canvasY = canvasPoint.getY() - (symbol1.getBoundsInLocal().getHeight() - symbol1.getFitHeight()) / 2;

            // Adjust the dropped position based on the size of the dragged symbol image
            double symbolWidth = symbol1.getBoundsInLocal().getWidth();
            double symbolHeight = symbol1.getBoundsInLocal().getHeight();
            canvasX -= (symbolWidth - symbol1.getFitWidth()) / 2;
            canvasY -= (symbolHeight - symbol1.getFitHeight()) / 2;

            // Draw the symbol on the canvas at the adjusted dropped position
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.drawImage(symbol1.getImage(), canvasX, canvasY);
        }
        event.consume();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        symbol1.setOnDragDetected(event -> dragSymbolOne(event));
        symbol1.setOnDragDone(event -> dragDoneSymbolOne(event));
        symbol1.setOnMouseEntered(event -> mouseEnterSymbol(event));
        canvas.setOnDragOver(event -> dragOverCanvas(event));
        canvas.setOnDragDropped(event -> dragDroppedCanvas(event));
    }

    public void dragOverCanvas(DragEvent event) {
        if (event.getGestureSource() != canvas && event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    public void dragDroppedCanvas(DragEvent event) {

        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        if (dragboard.hasImage()) {
            // Retrieve the dropped symbol position from the event
            double canvasX = event.getX();
            double canvasY = event.getY();

            // Adjust the dropped position based on the size of the dragged symbol image
            double symbolWidth = dragboard.getImage().getWidth();
            double symbolHeight = dragboard.getImage().getHeight();
            canvasX -= (symbolWidth / 2);
            canvasY -= (symbolHeight / 2);

            // Draw the symbol on the canvas at the adjusted dropped position
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.drawImage(dragboard.getImage(), canvasX, canvasY);

            success = true;
        }

        event.setDropCompleted(success);
        event.consume();


    }

    public void attachSymbolEventHandlers(ImageView symbol1) {

        symbol1.setOnDragDetected(event -> dragSymbolOne(event));
        symbol1.setOnDragDone(event -> dragDoneSymbolOne(event));
    }

    public void mouseEnterSymbol(MouseEvent event) {
        attachSymbolEventHandlers(symbol1);
    }
}
