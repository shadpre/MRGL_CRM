package BLL.Datavalidation;

import BLL.Interfaces.IValidationResult;

import java.util.ArrayList;
/**

 Represents the validation result that contains a list of errors.
 */
public class ValidationResult implements IValidationResult {


    private final ArrayList<String> errors;

    /**
     * Initializes a new instance of the ValidationResult class.
     */
    public ValidationResult() {
        errors = new ArrayList<>();
    }

    /**
     * Gets the list of errors.
     *
     * @return The list of errors.
     */
    @Override
    public ArrayList<String> getErrors() {
        return errors;
    }

    /**
     * Adds an error to the validation result.
     *
     * @param error The error message to add.
     */
    @Override
    public void addError(String error) {
        errors.add(error);
    }

    /**
     * Checks if the validation result has no errors.
     *
     * @return True if there are no errors, false otherwise.
     */
    @Override
    public boolean hasNoError() {
        return errors.size() == 0;
    }

    /**
     * Retrieves the string representation of the errors.
     *
     * @return The string representation of the errors.
     */
    @Override
    public String errorString() {
        StringBuilder sb = new StringBuilder();
        if (this.hasNoError()) return "";

        for (int i = 0; i < errors.size(); i++) {
            sb.append(errors.get(i));
            if (i != errors.size() - 1) sb.append("\r\n");
        }
        return sb.toString();
    }

}
