package by.training.parsers;

import by.training.parsers.parser.Parser;
import by.training.parsers.parser.exception.ParserException;
import by.training.parsers.parser.stax.Stax;
import by.training.parsers.view.FileView;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Main {
    public static void main(String[] args) {

        /*Parser sax = new Sax();
        try {
            sax.startParsing("D:/J_DATA/src/training/xsd_schema/web.xml");

            FileView fileView = new FileView();
            fileView.setParsedFile(sax.takeBeanSet());
            fileView.displayParsedFile();
        } catch (ParserException exc) {
            exc.printStackTrace();
        }*/

        /*Parser dom = new Dom();
        try {
            dom.startParsing("D:/J_DATA/src/training/xsd_schema/web.xml");

            FileView fileView = new FileView();
            fileView.setParsedFile(dom.takeBeanSet());
            fileView.displayParsedFile();
        } catch (ParserException exc) {
            exc.printStackTrace();
        }*/

        Parser stax = new Stax();
        try {
            stax.startParsing("D:/task4/src/by/training/parsers/resource/web.xml");

            FileView fileView = new FileView();
            fileView.setParsedFile(stax.takeBeanSet());
            fileView.displayParsedFile();
        } catch (ParserException exc) {
            exc.printStackTrace();
        }

    }
}
