package BLL.Interfaces;

import java.util.ArrayList;

public interface IValidationResult {
    ArrayList<String> getErrors();

    void addError(String error);

    boolean hasNoError();

    String errorString();
}
