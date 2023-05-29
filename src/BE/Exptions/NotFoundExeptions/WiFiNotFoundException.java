package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class WiFiNotFoundException extends NotFoundException {
    public WiFiNotFoundException(String message) {
        super(message);
    }
}
