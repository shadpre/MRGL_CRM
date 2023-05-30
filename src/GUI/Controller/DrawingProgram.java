package GUI.Controller;

import BE.DBEnteties.Interfaces.ILineSegment;
import BE.DBEnteties.Interfaces.ISymbol;
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
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DrawingProgram extends BaseController implements Initializable {

    private DocumentationViewController documentationViewController;
    @FXML
    private ImageView symbol1, imgTrash, symbol2, symbol3;

    private Image canvasImage;

    private final List<ILineSegment> lines = new ArrayList<>();
    private ILineSegment currentLine;


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


    //handle event for closing drawingprogram and setting imageview in DocumentationViewController.
    public void handleCloseWindow(ActionEvent actionEvent) {

        //running the method for creating an image of the canvas.
        Image canvasImage = createCanvasImage();

        //Setting the imageview in DocumentationViewController as the image of the canvas.
        documentationViewController.setCanvasImage(canvasImage);

        // Closing window.
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public void setCanvasImage(Image canvasImage) {
        documentationViewController.setCanvasImage(canvasImage);
    }

    public void dragSymbolOne(MouseEvent event) {

        //Creatin a dragboard and preparing for drag and drop.
        Dragboard dragboard = symbol1.startDragAndDrop(TransferMode.COPY);

        //creating a clipboard and putting symbol1 as content.
        // Then puts that content in the dragboard this is also for transferring the data onto the canvas.
        ClipboardContent content = new ClipboardContent();
        content.putImage(symbol1.getImage());
        dragboard.setContent(content);
        event.consume();

        // Removing event handlers from the symbol, so it doesn't duplicate every time you drag one.
        symbol1.setOnDragDetected(null);
        symbol1.setOnDragDone(null);
    }

    public void dragDoneSymbolOne(DragEvent event) {
        //we check if dragevent is set to copy, ensuring that the data was copied succesfully.
        if (event.getTransferMode() == TransferMode.COPY) {


            //store the cordinates of the symbol
            double symbolX = symbol1.getLayoutX();
            double symbolY = symbol1.getLayoutY();


            Point2D canvasPoint = symbol1.getParent().sceneToLocal(symbol1.localToScene(symbolX, symbolY));

            //finding out where to place the symbol based on canvas cordinates.
            double canvasX = canvasPoint.getX() - (symbol1.getBoundsInLocal().getWidth() - symbol1.getFitWidth()) / 2;
            double canvasY = canvasPoint.getY() - (symbol1.getBoundsInLocal().getHeight() - symbol1.getFitHeight()) / 2;


            //making sure the symbol is placed on the curser instead of the lower right due to size.
            double symbolWidth = symbol1.getBoundsInLocal().getWidth();
            double symbolHeight = symbol1.getBoundsInLocal().getHeight();
            canvasX -= (symbolWidth - symbol1.getFitWidth()) / 2;
            canvasY -= (symbolHeight - symbol1.getFitHeight()) / 2;


            //getting graphicscontext object associated with canvas so we can draw the image on the canvas with drawImage.
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.drawImage(symbol1.getImage(), canvasX, canvasY);
        }
        event.consume();
    }


    //same as dragSymbolOne
    public void dragSymbolTwo(MouseEvent event) {
        Dragboard dragboard = symbol2.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(symbol2.getImage());
        dragboard.setContent(content);
        event.consume();

        symbol2.setOnDragDetected(null);
        symbol2.setOnDragDone(null);
    }

    //same as dragDoneSymbolOne
    public void dragDoneSymbolTwo(DragEvent event) {

        if (event.getTransferMode() == TransferMode.COPY) {
            double symbolX = symbol2.getLayoutX();
            double symbolY = symbol2.getLayoutY();
            Point2D canvasPoint = symbol2.getParent().sceneToLocal(symbol2.localToScene(symbolX, symbolY));
            double canvasX = canvasPoint.getX() - (symbol2.getBoundsInLocal().getWidth() - symbol2.getFitWidth()) / 2;
            double canvasY = canvasPoint.getY() - (symbol2.getBoundsInLocal().getHeight() - symbol2.getFitHeight()) / 2;
            double symbolWidth = symbol2.getBoundsInLocal().getWidth();
            double symbolHeight = symbol2.getBoundsInLocal().getHeight();
            canvasX -= (symbolWidth - symbol2.getFitWidth()) / 2;
            canvasY -= (symbolHeight - symbol2.getFitHeight()) / 2;
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.drawImage(symbol2.getImage(), canvasX, canvasY);
        }
        event.consume();
    }


    //same as dragSymbolOne
    public void dragSymbolThree(MouseEvent event) {
        Dragboard dragboard = symbol3.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(symbol3.getImage());
        dragboard.setContent(content);
        event.consume();

        symbol3.setOnDragDetected(null);
        symbol3.setOnDragDone(null);
    }


    //same as dragDoneSymbolOne
    public void dragDoneSymbolThree(DragEvent event) {
        if (event.getTransferMode() == TransferMode.COPY) {
            double symbolX = symbol3.getLayoutX();
            double symbolY = symbol3.getLayoutY();
            Point2D canvasPoint = symbol3.getParent().sceneToLocal(symbol3.localToScene(symbolX, symbolY));
            double canvasX = canvasPoint.getX() - (symbol3.getBoundsInLocal().getWidth() - symbol3.getFitWidth()) / 2;
            double canvasY = canvasPoint.getY() - (symbol3.getBoundsInLocal().getHeight() - symbol3.getFitHeight()) / 2;
            double symbolWidth = symbol3.getBoundsInLocal().getWidth();
            double symbolHeight = symbol3.getBoundsInLocal().getHeight();
            canvasX -= (symbolWidth - symbol3.getFitWidth()) / 2;
            canvasY -= (symbolHeight - symbol3.getFitHeight()) / 2;
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.drawImage(symbol3.getImage(), canvasX, canvasY);
        }
        event.consume();
    }


    //setting event handlers for our symbols.
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        symbol1.setOnDragDetected(event -> dragSymbolOne(event));
        symbol1.setOnDragDone(event -> dragDoneSymbolOne(event));
        symbol1.setOnMouseEntered(event -> mouseEnterSymbolOne(event));

        symbol2.setOnDragDetected(event -> dragSymbolTwo(event));
        symbol2.setOnDragDone(event -> dragDoneSymbolTwo(event));
        symbol2.setOnMouseEntered(event -> mouseEnterSymbolTwo(event));

        symbol3.setOnDragDetected(event -> dragSymbolThree(event));
        symbol3.setOnDragDone(event -> dragDoneSymbolThree(event));
        symbol3.setOnMouseEntered(event -> mouseEnterSymbolThree(event));

        canvas.setOnDragOver(event -> dragOverCanvas(event));
        canvas.setOnDragDropped(event -> dragDroppedCanvas(event));


        //setting our graphicscontext as the one for the canvas.
        gc = canvas.getGraphicsContext2D();

        colorPicker.setValue(Color.BLACK);
    }



    //this is the method that makes the image we're draggin appear on canvas during the dragging process
    public void dragOverCanvas(DragEvent event) {
        if (event.getGestureSource() != canvas && event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }


    //this drops our image on the canvas.
    public void dragDroppedCanvas(DragEvent event) {

        //getting the image data from the dragboard we set in our drag events for the symbo.
        Dragboard dragboard = event.getDragboard();
        boolean success = false;


        //simple if statement so it only runs the code if the dragboard succesfully aquired the content.
        if (dragboard.hasImage()) {

            // Retrieve the dropped symbol position from the event
            double canvasX = event.getX();
            double canvasY = event.getY();

            // Set a fixed size for the dropped symbols
            double symbolSize = 80;

            // Adjust the dropped position based on the size of the fixed symbol size
            canvasX -= (symbolSize / 2);
            canvasY -= (symbolSize / 2);

            // Draw the symbol on the canvas at the adjusted dropped position with the fixed size
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.drawImage(dragboard.getImage(), canvasX, canvasY, symbolSize, symbolSize);

            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }


    //attaching event handlers again because else we can only do the whole thing once.
    public void attachSymbolOneEventHandlers(ImageView symbol1) {

        symbol1.setOnDragDetected(event -> dragSymbolOne(event));
        symbol1.setOnDragDone(event -> dragDoneSymbolOne(event));
    }

    //same as above
    public void attachSymbolTwoEventHandlers(ImageView symbol2) {

        symbol2.setOnDragDetected(event -> dragSymbolTwo(event));
        symbol2.setOnDragDone(event -> dragDoneSymbolTwo(event));
    }

    //same as above
    public void attachSymbolThreeEventHandlers(ImageView symbol3) {

        symbol3.setOnDragDetected(event -> dragSymbolThree(event));
        symbol3.setOnDragDone(event -> dragDoneSymbolThree(event));
    }


    //this is a on mouse entered event so we know we attach them when user wants to drag a new one.
    public void mouseEnterSymbolOne(MouseEvent event) {
        attachSymbolOneEventHandlers(symbol1);
    }

    //same as above
    public void mouseEnterSymbolTwo(MouseEvent event) {
       attachSymbolTwoEventHandlers(symbol2);
    }

    //same as above
    public void mouseEnterSymbolThree(MouseEvent event) {
        attachSymbolThreeEventHandlers(symbol3);
    }


    //this is a method that creates a writable image from a canvas snapshot.
    public void saveCanvasAsImage() {
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);
        canvasImage = writableImage;
    }


    //setting the canvas image in our DocumentationViewController imageview.
    public void setDocumentationController(DocumentationViewController documentationController) {
        this.documentationViewController = documentationController;
    }


    //creating a Image based on our canvas.
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

    //this is resonsible for drawing a line between the start point cordinate and where your current cursor is.
    public void onMouseDragged(MouseEvent event) {

        if (event.isPrimaryButtonDown() && currentLine != null) {
            double currentX = event.getX();
            double currentY = event.getY();

            //we clear so it doesn't constantly add 1 million lines to the canvas.
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  // Clear the canvas

            //setting color and width of the line
            gc.setStroke(currentLine.getColor());
            gc.setLineWidth(4.0);


            //running our redraw method so it clears again and redraws all the lines we have stored in array.
            redrawCanvas();

            gc.strokeLine(currentLine.getStartX(), currentLine.getStartY(), currentX, currentY);  // Draw the current line
        }
    }

    public void onMousePressed(MouseEvent event) {

        //making a new Linesegtment object that we call current line and give properties based on selected color.

        if (event.isPrimaryButtonDown()) {  // Check if the left mouse button is pressed
            startX = event.getX();
            startY = event.getY();
            Color lineColor = colorPicker.getValue();
            currentLine = new LineSegment(startX, startY, startX, startY, lineColor);
        }
    }

    public void onMouseReleased(MouseEvent event) {

        //getting end point of the line. Makes new linesegment that has start point and endpoint as cordinates.

        double endX = event.getX();
        double endY = event.getY();
        Color lineColor = colorPicker.getValue();
        ILineSegment newLine = new LineSegment(startX, startY, endX, endY, lineColor);

        //adding line to array so we can redraw it.
        lines.add(newLine);
        redrawCanvas();
        startX = endX; // Update the startX and startY for the next line
        startY = endY;
    }

    private void redrawCanvas() {

        //clearing canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //redrawing all stored lines in the array so we.
        for (ILineSegment line : lines) {
            gc.setStroke(line.getColor());
            gc.setLineWidth(4.0);
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }


    //removes the last line added to array so it is no longer redrawn, then we redraw so there's visual feedback that it worked.
    public void handleRemoveLastLine(ActionEvent actionEvent) {
        int max = lines.size();
        lines.remove(max - 1);
        redrawCanvas();
    }

    //clears our list so no lines are left, runs redraw method so we have visual feedback.

    public void handleRemoveLines(MouseEvent event) {
        if (imgTrash.isHover()) {
            lines.clear();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }


    //simple method for chaging the color of our trash image, we have 2 different imagefiles, one grey, one orange. This one changes the color to orange on enter.
    public void handleEnterTrash(MouseEvent event) {
        // Assuming you have the new image file path as a string
        String newImagePath = "data/images/Trashbtn02.png";

        // Create a new Image object with the new image file
        Image newImage = new Image(newImagePath);

        // Set the new image to imgTrash
        imgTrash.setImage(newImage);
    }


    //same as above but changes back to grey on exit.
    public void handleExitTrash(MouseEvent event) {
        // Assuming you have the new image file path as a string
        String newImagePath = "data/images/Trashbtn01.png";

        // Create a new Image object with the new image file
        Image newImage = new Image(newImagePath);

        // Set the new image to imgTrash
        imgTrash.setImage(newImage);
    }
}
