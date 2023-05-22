package BLL.Managers;

import BE.DBEnteties.Network;
import BE.DBEnteties.WiFi;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BE.Exptions.NotFoundExeptions.WiFiNotFoundExeption;
import DAL.DB.NetworkDAO_DB;
import DAL.DB.WiFiDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class NetworkManager {

        public static Network createNetwork(Network net) throws SQLException, NetworkNotFoundExeption {
            return NetworkDAO_DB.createNetwork(net);
        }

        public static Network updateNetwork(Network network) throws SQLException, NetworkNotFoundExeption {
            return NetworkDAO_DB.updateNetwork(network);
        }

        public static ArrayList<Network> getNetworks(int installationID) throws SQLException {
            return NetworkDAO_DB.getNetworks(installationID);
        }

        public static void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption {
            NetworkDAO_DB.deleteNetwork(id);
        }

}
