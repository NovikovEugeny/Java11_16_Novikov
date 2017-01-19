package by.training.xml_analyzer;

import by.training.xml_analyzer.service.Service;
import by.training.xml_analyzer.service.ServiceException;
import by.training.xml_analyzer.service.ServiceImpl;
import by.training.xml_analyzer.view.View;

/**
 * Created by Евгений on 27.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Service service = new ServiceImpl();
            service.setFilePath("D:/task3/src/by/" +
                    "training/xml_analyzer/source/breakfastMenu.xml");

            View view = new View();
            view.display(service.getAnalyzedFile());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
