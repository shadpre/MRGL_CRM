package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class DocumentNotFoundException extends NotFoundException {
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
