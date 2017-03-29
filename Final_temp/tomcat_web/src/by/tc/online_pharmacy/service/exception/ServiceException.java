package by.tc.online_pharmacy.service.exception;

/**
 * Created by Евгений on 17.02.2017.
 */
public class ServiceException extends Exception {
    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Exception e){
        super(e);
    }

    public ServiceException(String message, Exception e){
        super(message, e);
    }
}
