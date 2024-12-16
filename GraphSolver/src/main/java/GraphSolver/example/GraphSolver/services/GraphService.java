package GraphSolver.example.GraphSolver.services;

import GraphSolver.example.GraphSolver.dto.NodeRequest;
import GraphSolver.example.GraphSolver.dto.RelationshipRequest;
import GraphSolver.example.GraphSolver.models.HierarchyRequest;
import GraphSolver.example.GraphSolver.models.Node;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class GraphService {

    private final Map<String,Node> nodes = new HashMap<>();

    public void createHierarchy(HierarchyRequest request) {
        // Add all nodes
        for (NodeRequest nodeRequest : request.getNodes()) {
            addNode(nodeRequest);
        }

        // Add all relationships
        for (RelationshipRequest relationshipRequest : request.getRelationships()) {
            addRelationship(relationshipRequest);
        }
    }


    public Node addNode(NodeRequest request) {
        if (nodes.containsKey(request.getName())) {
            throw new IllegalArgumentException("Node already exists");
        }
        Node node = new Node(request.getName());
        nodes.put(node.getId(), node);

        if (request.getParentId() != null) {
            Node parent = nodes.get(request.getParentId());
            if (parent != null) {
                parent.getChildren().add(node);
                node.setParent(parent);
            }
        }

        return node;
    }

    public Node getNode(String id) {
        return nodes.getOrDefault(id, null);
    }

    public void addRelationship(RelationshipRequest request) {
        Node parent = nodes.get(request.getParentId());
        Node child = nodes.get(request.getChildId());
        if (parent == null || child == null) {
            throw new IllegalArgumentException("Invalid parent or child node");
        }
        parent.getChildren().add(child);
        child.setParent(parent);
    }

    public List<String> findPath(String startNodeId, String endNodeId) {
        Node startNode = nodes.get(startNodeId);
        Node endNode = nodes.get(endNodeId);
        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Invalid start or end node");
        }

        List<String> path = new ArrayList<>();
        if (findPathDFS(startNode, endNode, new HashSet<>(), path)) {
            return path;
        } else {
            throw new IllegalArgumentException("Path not found");
        }
    }

    private boolean findPathDFS(Node current, Node target, Set<Node> visited, List<String> path) {
        if (current == null || visited.contains(current)) return false;
        visited.add(current);
        path.add(current.getId());

        if (current.equals(target)) return true;

        for (Node child : current.getChildren()) {
            if (findPathDFS(child, target, visited, path)) return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public int calculateDepth(String nodeId) {
        Node node = nodes.get(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("Node not found");
        }

        int depth = 0;
        while (node.getParent() != null) {
            depth++;
            node = node.getParent();
        }
        return depth;
    }

    public String findCommonAncestor(String nodeId1, String nodeId2) {
        Node node1 = nodes.get(nodeId1);
        Node node2 = nodes.get(nodeId2);

        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Node not found");
        }

        Set<Node> ancestors1 = new HashSet<>();
        while (node1 != null) {
            ancestors1.add(node1);
            node1 = node1.getParent();
        }

        while (node2 != null) {
            if (ancestors1.contains(node2)) {
                return node2.getId();
            }
            node2 = node2.getParent();
        }

        throw new IllegalArgumentException("No common ancestor found");
    }
}
