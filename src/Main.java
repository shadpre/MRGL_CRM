import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Interfaces.ICustomerTask;
import BLL.DocumentGeneration;
import DAL.DAO_DB.CustomerTaskDAO_DB;
import DAL.DBFacade;
import DAL.DBLogin;
import DAL.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

//cleo
public class Main extends Application {

    public static void main(String[] args) throws IOException {
        var url = Main.class.getResource("config.properties");

        Properties props = new Properties();
        if (url != null) {
            try (InputStream input = url.openStream()) {
                props.load(input);
            }
        }
        var dbServer = props.getProperty("DB_IP");
        var dbPort = Integer.parseInt(props.getProperty("DB_PORT"));
        var dbName = props.getProperty("DB_NAME");
        var dbUsername = props.getProperty("DB_USERNAME");
        var dbPassword = props.getProperty("DB_PASSWORD");

        DBLogin.init(dbServer, dbPort, dbName, dbUsername, dbPassword);
        DatabaseConnector.init(DBLogin.getInstance());


        //test
        /*
        try {
            ICustomerTask ct = DBFacade.getInstance().getCustomerTask(16);
            DocumentGeneration.documentGeneration(ct, "C:\\out\\test.pdf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

         */
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("TicketSystem/GUI/View/EventMaker.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/LogInView.fxml"));
        primaryStage.setTitle("LogIn");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.show();

        String[] supportedFormats = ImageIO.getWriterFileSuffixes();
        System.out.println(Arrays.toString(supportedFormats));

    }

}