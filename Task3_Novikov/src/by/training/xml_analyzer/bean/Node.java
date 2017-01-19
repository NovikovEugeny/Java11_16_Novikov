package by.training.xml_analyzer.bean;

/**
 * Created by Евгений on 18.01.2017.
 */
public class Node {
    private Type type;
    private String content;

    public Node() {}

    public void setType(Type type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
