package by.training.parsers.parser.factory;

import by.training.parsers.parser.Parser;
import by.training.parsers.parser.dom.Dom;
import by.training.parsers.parser.sax.Sax;
import by.training.parsers.parser.stax.Stax;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Factory {
    private static final Factory instance = new Factory();

    private final Parser sax = new Sax();
    private final Parser stax = new Stax();
    private final Parser dom = new Dom();

    private Factory(){}

    public static Factory getInstance() {
        return instance;
    }

    public Parser getSax() {
        return sax;
    }

    public Parser getStax() {
        return stax;
    }

    public Parser getDom() {
        return dom;
    }
}
