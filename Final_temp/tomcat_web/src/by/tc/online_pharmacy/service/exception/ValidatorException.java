package by.tc.online_pharmacy.service.exception;

import java.util.Map;


public class ValidatorException extends Exception {

    private Map<String, String> errors;


    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
