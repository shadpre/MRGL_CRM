package BLL.Datavalidation;

import java.util.ArrayList;

public class ValidationResult {

    private ArrayList<String> Errors;

    public ValidationResult(){
        Errors = new ArrayList<>();
    }

    public void addError(String error){
        Errors.add(error);
    }

    public boolean hasNoError(){
        return Errors.size() == 0;
    }

    public String errorString(){
        String out = "";

    }
}
