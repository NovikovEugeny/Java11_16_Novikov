package by.training.xml_analyzer.view;

import by.training.xml_analyzer.bean.Node;
import by.training.xml_analyzer.bean.Nodes;

/**
 * Created by Евгений on 24.12.2016.
 */
public class View {
    public void display(Nodes nodes) {
        for (Node obj : nodes.getNodes()) {
            System.out.print(obj.getType() + ": ");
            System.out.println(obj.getContent());
        }
    }
}
