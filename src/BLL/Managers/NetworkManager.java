package BLL.Managers;

import BE.DBEnteties.Interfaces.INetwork;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BLL.Managers.Interfaces.INetworkManager;
import DAL.DB.NetworkDAO_DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class NetworkManager implements INetworkManager {

        @Override
        public INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundExeption {
            return NetworkDAO_DB.createNetwork(net);
        }

        @Override
        public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption {
            return NetworkDAO_DB.updateNetwork(network);
        }

        @Override
        public ArrayList<INetwork> getNetworks(int installationID) throws SQLException {
            return NetworkDAO_DB.getNetworks(installationID);
        }

        @Override
        public void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption {
            NetworkDAO_DB.deleteNetwork(id);
        }
}
