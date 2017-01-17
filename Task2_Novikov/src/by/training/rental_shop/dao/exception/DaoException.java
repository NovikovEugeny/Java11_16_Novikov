package by.training.rental_shop.dao.exception;

/**
 * Created by Евгений on 14.01.2017.
 */
public class DaoException extends Exception {
    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(Exception e){
        super(e);
    }

    public DaoException(String message, Exception e){
        super(message, e);
    }
}
