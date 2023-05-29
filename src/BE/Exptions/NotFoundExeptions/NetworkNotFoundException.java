package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class NetworkNotFoundException extends NotFoundException {
    public NetworkNotFoundException(String message) {
        super(message);
    }
}
