package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class InstallationNotFoundException extends NotFoundException {
    public InstallationNotFoundException(String message) {
        super(message);
    }
}
