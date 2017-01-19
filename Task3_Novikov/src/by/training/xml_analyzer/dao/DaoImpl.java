package by.training.xml_analyzer.dao;

import java.io.*;

/**
 * Created by Евгений on 24.12.2016.
 */
public class DaoImpl implements Dao {

    private String readFile(String filePath) throws DaoException{
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String fileChars = "";
            int buf;
            while ((buf = bufferedReader.read()) != -1) {
                fileChars = fileChars + (char)buf;
            }
            bufferedReader.close();
            fileReader.close();
            return fileChars;
        } catch (FileNotFoundException exc) {
            throw new DaoException("the file has not been found");
        } catch (IOException exc) {
            throw new DaoException("IOException error");
        }
    }

    public String getXmlFile(String filePath) throws DaoException {
        return readFile(filePath);
    }

}