package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class DeviceNotFoundException extends NotFoundException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
