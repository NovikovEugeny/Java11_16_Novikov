package by.training.parsers.parser.sax;

import by.training.parsers.bean.BeanSet;
import by.training.parsers.parser.Parser;
import by.training.parsers.parser.exception.ParserException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Sax implements Parser {
    private SaxHandler handler;

    @Override
    public void startParsing(String filePath) throws ParserException {

        filePath = filePath.trim();
        if (!filePath.substring(filePath.length()-3,
                filePath.length()).equals("xml")) {
            throw new ParserException("wrong file type");
        }

        File inputFile = new File(filePath);
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            handler = new SaxHandler();
            saxParser.parse(inputFile, handler);
        } catch (ParserConfigurationException exc) {
            throw new ParserException(exc);
        } catch (SAXException exc) {
            throw new ParserException(exc);
        } catch (IOException exc) {
            throw new ParserException(exc);
        }
    }

    @Override
    public BeanSet takeBeanSet() {
        return handler.getBeanSet();
    }
}
