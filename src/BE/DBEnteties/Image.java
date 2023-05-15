package BE.DBEnteties;

public class Image extends InstallationUnit{
    private byte[] Data;
    private int ImageType;

    public Image(int id, int installationId, String description, String remarks, byte[] data, int imageType) {
        super(id, installationId, description, remarks);
        Data = data;
        ImageType = imageType;
    }

    public byte[] getData() {
        return Data;
    }

    public int getImageType() {
        return ImageType;
    }
}
