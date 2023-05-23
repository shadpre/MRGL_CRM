package BLL.Datavalidation;

import java.util.ArrayList;

public class ValidationResult {



    private ArrayList<String> errors;

    public ArrayList<String> getErrors() {
        return errors;
    }

    public ValidationResult(){
        errors = new ArrayList<>();
    }

    public void addError(String error){
        errors.add(error);
    }

    public boolean hasNoError(){
        return errors.size() == 0;
    }

    public String errorString(){
        String out = "";
    return  null;
    }
}
