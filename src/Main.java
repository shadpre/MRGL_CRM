import BE.Document;
import BE.User;
import BLL.DocumentManager;
import BLL.UserManager;
import DAL.db.DBLogin;
import DAL.db.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.time.LocalDateTime;

public class Main extends Application{

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

            //TEST

        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            //Parent root = FXMLLoader.load(getClass().getResource("TicketSystem/GUI/View/EventMaker.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("GUI/View/LogInView.fxml"));
            primaryStage.setTitle("LogIn");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(true);
            primaryStage.show();
        }

    }