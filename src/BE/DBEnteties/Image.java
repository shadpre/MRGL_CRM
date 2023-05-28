package BE.DBEnteties;

import BE.DBEnteties.Interfaces.IImage;

public class Image extends InstallationUnit implements IImage {
    private final byte[] Data;
    private final int ImageType;

    public Image(int id, int installationId, String description, String remarks, byte[] data, int imageType) {
        super(id, installationId, description, remarks);
        Data = data;
        ImageType = imageType;
    }

    @Override
    public byte[] getData() {
        return Data;
    }

    @Override
    public int getImageType() {
        return ImageType;
    }
}
