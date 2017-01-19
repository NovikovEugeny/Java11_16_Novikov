package by.training.xml_analyzer.service;

import by.training.xml_analyzer.bean.Node;
import by.training.xml_analyzer.bean.Nodes;
import by.training.xml_analyzer.bean.Type;
import by.training.xml_analyzer.dao.Dao;
import by.training.xml_analyzer.dao.DaoException;
import by.training.xml_analyzer.dao.DaoFactory;

import java.util.ArrayList;

/**
 * Created by Евгений on 24.12.2016.
 */
public class ServiceImpl implements Service {

    private String filePath;
    private String fileString;// считанный необработанный xml-файл

    public void setFilePath(String filePath) throws ServiceException {
        if (filePath == null || filePath.isEmpty()) {
            throw new ServiceException("The file path is empty");
        }
        // проверяем тип файла
        filePath = filePath.trim();
        if (!filePath.substring(filePath.length()-3,
                filePath.length()).equals("xml")) {
            throw new ServiceException("wrong file type");
        }
        this.filePath = filePath;
    }

    private String readFile() throws ServiceException {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            Dao dao = factory.getDao();
            return dao.getXmlFile(filePath);
        } catch (DaoException exc) {
            throw new ServiceException(exc.getMessage());
        }
    }

    private void setFileString() throws ServiceException {
        fileString = readFile();
    }

    //удаляем из xml-файла комментарии, пролог
    //и пустые строки до и после файла
    private void removeOtherLines() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < fileString.length() - 1; i++) {
            if (fileString.charAt(i) == '<' &&
                    (fileString.charAt(i + 1) == '!' ||
                            fileString.charAt(i + 1) == '?')) {
                String cutLine = "";
                while (fileString.charAt(i) != '>') {
                    cutLine = cutLine + fileString.charAt(i);
                    i++;
                }
                if (!cutLine.equals(""))
                    cutLine = cutLine + '>';
                list.add(cutLine);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            fileString = fileString.replace(list.get(i), "");
        }
        fileString = fileString.trim();
    }

    //удаляем путое пространство м/у тегами
    private void removeSpaces() {
        for (int i = 0; i < fileString.length()-1; i++) {
            if (fileString.charAt(i) == '>' && fileString.charAt(i + 1) == ' ') {
                int j = i + 1;
                while (fileString.charAt(j) == ' ') {
                    j++;
                }
                fileString = fileString.substring(0, i + 1) +
                        fileString.substring(j, fileString.length());
            }
        }
    }

    //  формируем массив сведений об узлах
    private Nodes toFormNodes() {
        Nodes nodes = new Nodes();

        for (int i = 0; i < fileString.length()-1; i++) {
            Node node = new Node();

            String content = "";// содержимое узла
            String text = "";// текст м/у откр. и закр. тегом

            if (fileString.charAt(i) == '<') {
                int j = i + 1;
                while (fileString.charAt(j) != '>') {
                    content = content + fileString.charAt(j);
                    j++;
                }
                content = content.trim();
                if(!content.contains("/")) {
                    if (content.contains(" ")) {
                        content = content.substring(0, content.indexOf(" "));
                    }
                    node.setContent(content);
                    node.setType(Type.OPENING_TAG);
                } else if (content.substring(content.length()-1).equals("/")) {
                    node.setType(Type.TAG_WITHOUT_BODY);
                    node.setContent(content.substring(0, content.length()-1));
                } else {
                    content = content.replace("/","");
                    node.setType(Type.CLOSING_TAG);
                    node.setContent(content);
                }
                nodes.addNode(node);
            }

            if(fileString.charAt(i) == '>' && fileString.charAt(i+1) != '<') {
                int j = i + 1;
                while (fileString.charAt(j) != '<') {
                    text = text + fileString.charAt(j);
                    j++;
                }
                text = text.trim();
                if (!text.equals("")) {
                    node.setContent(text);
                    node.setType(Type.TEXT);
                    nodes.addNode(node);
                }
            }
        }
        return nodes;
    }

    private Nodes analyze() throws ServiceException {
        readFile();
        setFileString();
        removeOtherLines();
        removeSpaces();
        return toFormNodes();
    }

    public Nodes getAnalyzedFile() throws ServiceException {
        return analyze();
    }
}