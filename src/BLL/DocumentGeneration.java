package BLL;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class DocumentGeneration {
    public static void documentGeneration (String[] args) throws FileNotFoundException, MalformedURLException {

        String path = "data/documents/Invtest.pdf";
        String sketchDescription = "";
        String taskDescription = "Tekst bliver i daglig tale tit identificeret med det medie, de fremstår i, og som sådan er begrebet en meget flertydig størrelse. En tekst er her f.eks. en bog, et brev eller en avis. Den kan også være en del af en større tekst, f.eks. en nærmere afgrænset del af bogen (i forskellige former for antologier og samlinger) et bestemt afsnit i brevet (eller f.eks. et bilag til brevet) eller en enkelt artikel i avisen.";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col = 280f;
        float columnWidth[] = {col, col};

        Table table = new Table(columnWidth);

        table.setBackgroundColor(Color.DARK_GRAY);

        String imgPath = "data/images/Wuavlogoprgtest.JPG";
        ImageData imageData = ImageDataFactory.create(imgPath);
        Image image = new Image(imageData);

        table.addCell(new Cell().add(image)
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginLeft(60f)
        );


        table.addCell(new Cell().add("WUAV \n Murervej 7a \n 6710 Esbjerg V \n Tlf: 7511 9191 \n Mail: info@wuav.dk \n CVR nr: 26855667")
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30f)
                .setMarginBottom(10f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(20f)
                .setFontColor(Color.WHITE)

        );

        float colWidth[] = {80, 300, 100, 80};
        Table taskDescriptionTable = new Table(colWidth);

        taskDescriptionTable.addCell(new Cell(0,4)
                .add("Opgave Dokumentation")
                .setBold()
                .setFontSize(20f)
        );

        taskDescriptionTable.addCell(new Cell().add("Kunde")
                .setBold());

        taskDescriptionTable.addCell(new Cell().add("EASV Esbjerg"));

        taskDescriptionTable.addCell(new Cell().add("CVR nr")
                .setBold());

        taskDescriptionTable.addCell(new Cell().add("65656565:"));

        taskDescriptionTable.addCell(new Cell().add("Opgave nr")
                .setBold());

        taskDescriptionTable.addCell(new Cell().add("2300001"));

        taskDescriptionTable.addCell(new Cell().add("Dato")
                .setBold());

        taskDescriptionTable.addCell(new Cell().add("16-05-2023"));

        float sketchColumnWidth[] = {560f};

        Table sketchTable = new Table(sketchColumnWidth);
        String sketchPath = "data/images/sketch1.jpg";

        ImageData imageData1 = ImageDataFactory.create(sketchPath);
        Image sketch = new Image(imageData1);

        sketchTable.addCell(new Cell().add("Skitse")
                .setFontSize(15f)
                .setBold()
                .setBorder(Border.NO_BORDER)
                .setMarginTop(15f)

        );

        sketchTable.addCell(new Cell().add(sketch)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
        );

        sketchTable.addCell(new Cell().add("Beskrivelse af Skitse")
                .setFontSize(15f)
                .setBold()
                .setBorder(Border.NO_BORDER)
        );
        sketchTable.addCell(new Cell().add("Se min flotte tegning blah blah")
                .setBorder(Border.NO_BORDER)
        );

        float taskColumnWidth[] = {560f};
        Table taskTable = new Table(taskColumnWidth);

        taskTable.addCell(new Cell().add("Opgave Beskrivelse")
                .setFontSize(15f)
                .setBold()
                .setBorder(Border.NO_BORDER)
                .setMarginTop(15f)
        );
        Paragraph paragraph = new Paragraph(taskDescription);


        document.add(table);
        document.add(taskDescriptionTable);
        document.add(sketchTable);
        document.add(taskTable);
        document.add(paragraph);

        document.close();

        System.out.println("Hello world!");
    }

}
