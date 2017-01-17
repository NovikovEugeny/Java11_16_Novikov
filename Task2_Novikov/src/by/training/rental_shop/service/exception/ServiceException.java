package by.training.rental_shop.service.exception;

/**
 * Created by Евгений on 14.01.2017.
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
