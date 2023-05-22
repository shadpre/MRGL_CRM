package GUI.Controller;

import BE.DBEnteties.LineSegment;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawingProgram extends BaseController implements Initializable {

    private DocumentationViewController documentationViewController;

    @FXML
    private ImageView symbol1, imgTrash;

    private Image canvasImage;

    private List<LineSegment> lines = new ArrayList<>();
    private LineSegment currentLine;


    private GraphicsContext gc;
    private double startX, startY, endX, endY;
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button closeButton;

    @FXML
    private Canvas canvas;




    public void handleSymbol1ButtonClicked(ActionEvent actionEvent) {
    }

    public void handleCloseWindow(ActionEvent actionEvent) {

            // Create the canvas image
            Image canvasImage = createCanvasImage();

            // Set the image in the documentation controller
            documentationViewController.setCanvasImage(canvasImage);

            // Close the drawing program window
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();

    }

    public void setCanvasImage(Image canvasImage) {
        documentationViewController.setCanvasImage(canvasImage);
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
        
        gc = canvas.getGraphicsContext2D();
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

    public void saveCanvasAsImage() {
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);
        canvasImage = writableImage;
    }

    public void setDocumentationController(DocumentationViewController documentationController) {
        this.documentationViewController = documentationController;
    }

    public Image createCanvasImage() {
        // Get the current snapshot of the canvas
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT); // Set transparent background
        WritableImage snapshot = canvas.snapshot(params, null);

        // Convert the snapshot to a JavaFX Image
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
        Image canvasImage = SwingFXUtils.toFXImage(bufferedImage, null);

        return canvasImage;
    }



    public void onAddLineButtonClicked(ActionEvent actionEvent) {
        startX = 0;
        startY = 0;
    }

    public void onMouseDragged(MouseEvent event) {

        if (event.isPrimaryButtonDown() && currentLine != null) {
            double currentX = event.getX();
            double currentY = event.getY();

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  // Clear the canvas

            gc.setStroke(currentLine.getColor());
            gc.setLineWidth(4.0);

            redrawCanvas();

            gc.strokeLine(currentLine.getStartX(), currentLine.getStartY(), currentX, currentY);  // Draw the current line
        }
    }

    public void onMousePressed(MouseEvent event) {

        if (event.isPrimaryButtonDown()) {  // Check if the left mouse button is pressed
            startX = event.getX();
            startY = event.getY();
            Color lineColor = colorPicker.getValue();
            currentLine = new LineSegment(startX, startY, startX, startY, lineColor);
        }
    }

    public void onMouseReleased(MouseEvent event) {

        double endX = event.getX();
        double endY = event.getY();
        Color lineColor = colorPicker.getValue();
        LineSegment newLine = new LineSegment(startX, startY, endX, endY, lineColor );
        lines.add(newLine);
        redrawCanvas();
        startX = endX; // Update the startX and startY for the next line
        startY = endY;
    }

    private void redrawCanvas() {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());



        for (LineSegment line : lines) {
            gc.setStroke(line.getColor());
            gc.setLineWidth(4.0);
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }}
    

    public void handleRemoveLastLine(ActionEvent actionEvent) {
        int max = lines.size();
        lines.remove(max-1);
        redrawCanvas();
    }

    public void handleRemoveLines(MouseEvent event) {
        if(imgTrash.isHover()) {
            lines.clear();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    public void handleEnterTrash(MouseEvent event) {
        // Assuming you have the new image file path as a string
        String newImagePath = "data/images/Trashbtn02.png";

        // Create a new Image object with the new image file
        Image newImage = new Image(newImagePath);

        // Set the new image to imgTrash
        imgTrash.setImage(newImage);
    }

    public void handleExitTrash(MouseEvent event) {
        // Assuming you have the new image file path as a string
        String newImagePath = "data/images/Trashbtn01.png";

        // Create a new Image object with the new image file
        Image newImage = new Image(newImagePath);

        // Set the new image to imgTrash
        imgTrash.setImage(newImage);
    }
}
