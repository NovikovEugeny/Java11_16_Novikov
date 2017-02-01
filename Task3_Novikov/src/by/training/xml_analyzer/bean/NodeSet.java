package by.training.xml_analyzer.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 24.12.2016.
 */

public class NodeSet implements Serializable {
    private List<Node> nodeList = new ArrayList<>();

    public NodeSet() {
    }

    public void addNode(Node node) {
        nodeList.add(node);
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        NodeSet nodeSet = (NodeSet) obj;

        for (int i = 0; i < nodeList.size(); i++) {
            if (null == nodeList.get(i)) {
                return (nodeList.get(i) == nodeSet.nodeList.get(i));
            }
        }
        for (int i = 0; i < nodeList.size(); i++) {
            if (!nodeList.get(i).equals(nodeSet.nodeList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 31;
        for (int i = 0; i < nodeList.size(); i++) {
            result = result * nodeList.get(i).hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "nodeList: " + nodeList.toString();
    }

}