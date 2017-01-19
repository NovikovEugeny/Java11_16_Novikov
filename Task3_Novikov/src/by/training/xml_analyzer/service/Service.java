package by.training.xml_analyzer.service;

import by.training.xml_analyzer.bean.Nodes;

/**
 * Created by Евгений on 18.01.2017.
 */
public interface Service {
    void setFilePath(String filePath) throws ServiceException;
    Nodes getAnalyzedFile() throws ServiceException;
}
