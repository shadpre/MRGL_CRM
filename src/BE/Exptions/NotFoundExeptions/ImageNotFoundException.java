package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class ImageNotFoundException extends NotFoundException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
