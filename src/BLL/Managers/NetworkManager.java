package BLL.Managers;

import BE.DBEnteties.Interfaces.INetwork;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundExeption;
import BLL.Interfaces.INetworkManager;
import DAL.DAO_DB.NetworkDAO_DB;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class NetworkManager implements INetworkManager {

        @Override
        public INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundExeption {
            return DBFacade.getInstance().createNetwork(net);
        }

        @Override
        public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundExeption {
            return DBFacade.getInstance().updateNetwork(network);
        }

        @Override
        public ArrayList<INetwork> getNetworks(int installationID) throws SQLException {
            return DBFacade.getInstance().getNetworks(installationID);
        }

        @Override
        public void deleteNetwork(int id) throws SQLException, NetworkNotFoundExeption {
            DBFacade.getInstance().deleteNetwork(id);
        }
}
