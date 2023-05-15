package DAL.DB;

import BE.DBEnteties.Device;

public class TESTDB_DAO {

    public static void test() throws Exception {
        Device dev = new Device(-1, 1, "Test", "Test Remark", "123", "123,", "adm", "sdmin", true);


        var result = DeviceDAO_DB.createDevice(dev);

        System.out.println(result.getId() + "New id");
    }
}
