package by.training.xml_analyzer.bean;

import by.training.xml_analyzer.node_type.NodeType;

import java.io.Serializable;

/**
 * Created by Евгений on 18.01.2017.
 */
public class Node implements Serializable {
    private NodeType type;
    private String content;

    public Node() {}

    public void setType(NodeType type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NodeType getType() {
        return type;
    }

    public String getContent() {
        return content;
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

        Node node = (Node) obj;
        if (null == type) {
            return (type == node.type);
        }
        if (type != node.type) {
            return false;
        }
        if (null == content) {
            return (content == node.content);
        }
        if (!content.equals(node.content)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 + (null == type ? 0 : type.hashCode()) +
                (null == content ? 0 : content.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "type: " + type +
                ", content: " + content;
    }

}
