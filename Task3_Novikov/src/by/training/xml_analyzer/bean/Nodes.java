package by.training.xml_analyzer.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 24.12.2016.
 */

public class Nodes {
    private List<Node> nodes = new ArrayList<>();

    public Nodes() {
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }
}