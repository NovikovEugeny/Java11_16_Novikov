package by.training.xml_analyzer.view;

import by.training.xml_analyzer.bean.Node;
import by.training.xml_analyzer.bean.NodeSet;

/**
 * Created by Евгений on 24.12.2016.
 */
public class View {
    public void display(NodeSet nodeSet) {
        for (Node obj : nodeSet.getNodeList()) {
            System.out.print(obj.getType() + ": ");
            System.out.println(obj.getContent());
        }
    }
}
