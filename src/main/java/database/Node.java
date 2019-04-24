package database;

public class Node {

    private String name;
    private String param1;
    private String param2;

    public Node(String param1, String param2, String name) {
        this.param1 = param1;
        this.param2 = param2;
        this.name = name;
    }

    public Node() {
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[{\"id\":\""+name+"\\\\param1\",\"parent\":\""+name+"\",\"text\":\"param1 : "+param1+"\",\"children\":false},"+
                "{\"id\":\""+name+"\\\\param2\",\"parent\":\""+name+"\",\"text\":\"param2 : "+param2+"\",\"children\":false}]";
    }
}