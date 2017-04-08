package by.tc.online_pharmacy.service.exception;

import by.tc.online_pharmacy.service.util.Validator;

/**
 * Created by Евгений on 02.04.2017.
 */
public class ValidatorException extends Exception {
    public ValidatorException(){
        super();
    }

    public ValidatorException(String message){
        super(message);
    }

    public ValidatorException(Exception e){
        super(e);
    }

    public ValidatorException(String message, Exception e){
        super(message, e);
    }
}
