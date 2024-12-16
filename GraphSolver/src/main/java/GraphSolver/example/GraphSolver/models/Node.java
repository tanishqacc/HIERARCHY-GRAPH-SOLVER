package GraphSolver.example.GraphSolver.models;

import java.util.*;

public class Node {
    private static int idCounter = 1;
    private final String id;
    private final String name;
    private Node parent;
    private final List<Node> children = new ArrayList<>();



    public Node(String name) {
        this.id = String.valueOf(idCounter++);
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }
}