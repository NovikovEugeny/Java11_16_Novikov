package by.training.xml_analyzer.dao;

/**
 * Created by Евгений on 18.01.2017.
 */
public interface Dao {
    String getXmlFile(String path) throws DaoException;
}
