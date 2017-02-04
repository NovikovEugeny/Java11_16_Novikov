package by.training.parsers.parser;

import by.training.parsers.bean.BeanSet;
import by.training.parsers.parser.exception.ParserException;

/**
 * Created by Евгений on 29.01.2017.
 */
public interface Parser {
    void startParsing(String filePath) throws ParserException;
    BeanSet takeBeanSet();
}
