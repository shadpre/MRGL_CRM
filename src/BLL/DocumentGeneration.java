package BLL;


import BE.DBEnteties.*;
import BE.DBEnteties.Interfaces.*;


import BE.DocumentData;
import BE.Exptions.NotFoundExeption;
import DAL.DB.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentGeneration {

    
    public static DocumentData CreateDocumentData(ICustomerTask customerTask) throws SQLException, NotFoundExeption{
        DocumentData documentData = new DocumentData(customerTask);

        ArrayList<IInstallation> installations = InstallationDAO_DB.getInstallations(customerTask.getId());

        ArrayList<INetwork> networks = new ArrayList<>();
        ArrayList<IImage> images = new ArrayList<>();
        ArrayList<IDevice> devices = new ArrayList<>();
        ArrayList<IWiFi> wiFis = new ArrayList<>();

        documentData.setCustomer(CustomerDAO_DB.getCustomerByID(customerTask.getCustomerID()));
        documentData.setUsers(UserDAO_DB.getAllUsers(customerTask.getId()));
        documentData.setInstallations(installations);

        for (Installation inst: installations){
            networks.addAll(NetworkDAO_DB.getNetworks(inst.getId()));
            images.addAll(ImageDAO_DB.getImageList(inst.getId()));
            devices.addAll(DeviceDAO_DB.getDeviceList(inst.getId()));
            wiFis.addAll(WiFiDAO_DB.getWiFis(inst.getId()));
        }
        documentData.setNetworks(networks);
        documentData.setImages(images);
        documentData.setDevices(devices);
        documentData.setWiFis(wiFis);

        return documentData;
    }

    public static void documentGeneration(CustomerTask customerTask, String FilePath) throws FileNotFoundException, MalformedURLException, SQLException, NotFoundExeption {


        DocumentData dd = CreateDocumentData(customerTask);
        ArrayList<BE.DBEnteties.Image> images = new ArrayList<>();
        ArrayList<BE.DBEnteties.Image> sketches = new ArrayList<>();
        if (dd.getImages().size() > 0){
            for (BE.DBEnteties.Image image : dd.getImages()){
                switch (image.getImageType()){
                    case 1:
                        images.add(image);
                        break;
                    case 2:
                        sketches.add(image);
                        break;
                }
            }
        }

        boolean needNewPage = false;

        //String path = "C:\\out\\test.pdf";

        PdfWriter pdfWriter = new PdfWriter(FilePath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A4);
        document.add(generateDocumentHeader());
        document.add(new Paragraph());
        document.add(generateMetaDataForCustomer(dd));
        document.add(new Paragraph());
        if(sketches.size() > 0){
            for (BE.DBEnteties.Image image : sketches){
                document.add(new Paragraph(image.getDescription()).setBold());
                document.add(new Paragraph(image.getRemarks()).setFontSize(9f));
                document.add(generateImage(image));
                document.add(new AreaBreak());
            }
        }
        if(dd.getNetworks().size() > 0) {
            document.add(generateNetworkTable(dd.getNetworks()));
            document.add(new Paragraph());
            needNewPage = true;
        }

        if(dd.getNetworks().size() > 0) {
            document.add(generateWiFiTable(dd.getWiFis()));
            document.add(new Paragraph());
            needNewPage = true;
        }

        if(dd.getDevices().size() > 0) {
            document.add(generateDeviceTable(dd.getDevices()));
            document.add(new Paragraph());
            needNewPage = true;
        }

        if(needNewPage){
            document.add(new AreaBreak());
            needNewPage = false;
        }

        if(images.size() > 0){
            int i = 1;
            for (BE.DBEnteties.Image image : images){
                document.add(new Paragraph(image.getDescription()).setBold());
                document.add(new Paragraph(image.getRemarks()).setFontSize(9f));
                document.add(generateImage(image));
                if(i != images.size()) {
                    document.add(new AreaBreak());
                }
                i++;
            }
        }

        document.close();

        System.out.println("Document Created");

        File file = new File(FilePath);
        try {
            if (file.toString().endsWith(".pdf"))
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
            else {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    private static Table generateDocumentHeader() throws MalformedURLException{
        float col = 280f;
        float columnWidth[] = {col, col};

        Table table = new Table(columnWidth);

        table.setBackgroundColor(Color.DARK_GRAY);

        String imgPath = "C:\\out\\Wuavlogoprgtest.JPG";
        ImageData imageData = ImageDataFactory.create(imgPath);
        com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(imageData);

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
        return table;
    }

    private static Paragraph generateMetaDataForCustomer(DocumentData dd){
        Paragraph out = new Paragraph();
        StringBuilder sb = new StringBuilder();
        //sb.append("\r\n");
        if (!dd.getCustomer().getName().isBlank()) sb.append(dd.getCustomer().getName() + "\r\n");
        if (!dd.getCustomer().getAddress1().isBlank()) sb.append(dd.getCustomer().getAddress1() + "\r\n");
        if (!dd.getCustomer().getAddress2().isBlank()) sb.append(dd.getCustomer().getAddress2() + "\r\n");
        if (!dd.getCustomer().getAddress3().isBlank()) sb.append(dd.getCustomer().getAddress3() + "\r\n");
        if (!dd.getCustomer().getZipcode().isBlank()) sb.append(dd.getCustomer().getZipcode() + " ");
        if (!dd.getCustomer().getCity().isBlank()) sb.append(dd.getCustomer().getCity() + "\r\n");
        if (!dd.getCustomer().getCountry().isBlank()) sb.append(dd.getCustomer().getCountry());

        return out.add(sb.toString());
    }

    private static Table generateNetworkTable(ArrayList<Network> networks) {
        float colWidth[] = {120, 185, 85, 85, 85};
        Table out = new Table(colWidth);
        out.addCell(new Cell(0,5).add("Information om netværk").setBold().setTextAlignment(TextAlignment.CENTER));
        out.addCell(headerCell("Beskrivelse"));
        out.addCell(headerCell("Bemærkninger"));
        out.addCell(headerCell("Netværks IP"));
        out.addCell(headerCell("Subnetmask"));
        out.addCell(headerCell("Default Gateway"));

        for (Network network:networks
             ) {
            out.addCell(infoCell(network.getDescription()));
            out.addCell(infoCell(network.getRemarks()));
            out.addCell(infoCell(network.getNetworkIP()));
            out.addCell(infoCell(network.getSubnetMask()));
            out.addCell(infoCell(network.getDefaultGateway()));
        }
        return out;
    }

    private static Table generateWiFiTable(ArrayList<WiFi> wiFis){
        float colWidth[] = {120, 185, 127.5f, 127.5f};
        Table out = new Table(colWidth);
        out.addCell(new Cell(0,4).add("Information om WiFi").setBold().setTextAlignment(TextAlignment.CENTER));
        out.addCell(headerCell("Beskrivelse"));
        out.addCell(headerCell("Bemærkninger"));
        out.addCell(headerCell("SSID"));
        out.addCell(headerCell("PSK"));

        for(WiFi wiFi : wiFis){
            out.addCell(infoCell(wiFi.getDescription()));
            out.addCell(infoCell(wiFi.getRemarks()));
            out.addCell(infoCell(wiFi.getSSID()));
            out.addCell(infoCell(wiFi.getPSK()));
        }

        return out;
    }

    private static Table generateDeviceTable(ArrayList<Device> devices){
        float colWidth[] = {120, 185, 70, 70, 40, 40, 20};
        Table out = new Table(colWidth);
        out.addCell(new Cell(0,7).add("Information om enheder").setBold().setTextAlignment(TextAlignment.CENTER));
        out.addCell(headerCell("Beskrivelse"));
        out.addCell(headerCell("Bemærkninger"));
        out.addCell(headerCell("IP"));
        out.addCell(headerCell("Subnet"));
        out.addCell(headerCell("Bruger"));
        out.addCell(headerCell("Kode"));
        out.addCell(headerCell("POE"));

        for(Device device : devices){
            out.addCell(infoCell(device.getDescription()));
            out.addCell(infoCell(device.getRemarks()));
            out.addCell(infoCell(device.getIP()));
            out.addCell(infoCell(device.getSubnetMask()));
            out.addCell(infoCell(device.getUserName()));
            out.addCell(infoCell(device.getPassword()));
            if (device.isPOE()) out.addCell(infoCell("Ja"));
            else out.addCell(infoCell("Nej"));
        }

        return out;
    }

    private static com.itextpdf.layout.element.Image generateImage(BE.DBEnteties.Image image){
        return new com.itextpdf.layout.element.Image(ImageDataFactory.create(image.getData())).setAutoScale(true);
    }

    private static Cell headerCell(String header){
        return new Cell().add(header).setTextAlignment(TextAlignment.CENTER).setFontSize(10f);
    }

    private static Cell infoCell(String info){
        return new Cell().add(info).setFontSize(9f);
    }
}
