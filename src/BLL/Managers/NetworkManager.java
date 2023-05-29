package BLL.Managers;

import BE.DBEnteties.Interfaces.INetwork;
import BE.Exptions.NotFoundExeptions.NetworkNotFoundException;
import BLL.Interfaces.INetworkManager;
import DAL.DBFacade;

import java.sql.SQLException;
import java.util.ArrayList;

public class NetworkManager implements INetworkManager {

    @Override
    public INetwork createNetwork(INetwork net) throws SQLException, NetworkNotFoundException {
        return DBFacade.getInstance().createNetwork(net);
    }

    @Override
    public INetwork updateNetwork(INetwork network) throws SQLException, NetworkNotFoundException {
        return DBFacade.getInstance().updateNetwork(network);
    }

    @Override
    public ArrayList<INetwork> getNetworks(int installationID) throws SQLException {
        return DBFacade.getInstance().getNetworks(installationID);
    }

    @Override
    public void deleteNetwork(int id) throws SQLException, NetworkNotFoundException {
        DBFacade.getInstance().deleteNetwork(id);
    }
}
