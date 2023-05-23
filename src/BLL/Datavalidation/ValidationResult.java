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
        StringBuilder sb = new StringBuilder();
        if (this.hasNoError()) return "";

        for (int i = 0 ; errors.size() < i ; i++){
            sb.append(errors.get(i));
            if(i != errors.size()- 1 ) sb.append("\r\n");
        }
        return sb.toString();
    }
}
