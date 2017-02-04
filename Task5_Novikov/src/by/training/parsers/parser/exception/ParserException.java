package by.training.parsers.parser.exception;

/**
 * Created by Евгений on 29.01.2017.
 */
public class ParserException extends Exception {
    public ParserException(){
        super();
    }

    public ParserException(String message){
        super(message);
    }

    public ParserException(Exception e){
        super(e);
    }

    public ParserException(String message, Exception e){
        super(message, e);
    }

}
