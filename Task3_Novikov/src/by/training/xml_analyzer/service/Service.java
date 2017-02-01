package by.training.xml_analyzer.service;

import by.training.xml_analyzer.bean.NodeSet;

/**
 * Created by Евгений on 18.01.2017.
 */
public interface Service {
    void setFilePath(String filePath) throws ServiceException;
    NodeSet takeAnalyzedFile() throws ServiceException;
}
