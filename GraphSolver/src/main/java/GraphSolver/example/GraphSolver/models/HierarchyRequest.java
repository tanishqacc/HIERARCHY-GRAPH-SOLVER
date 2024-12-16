package GraphSolver.example.GraphSolver.models;

import GraphSolver.example.GraphSolver.dto.NodeRequest;
import GraphSolver.example.GraphSolver.dto.RelationshipRequest;

import java.util.List;

public class HierarchyRequest {
    private List<NodeRequest> nodes;
    private List<RelationshipRequest> relationships;

    // Getters and Setters
    public List<NodeRequest> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeRequest> nodes) {
        this.nodes = nodes;
    }

    public List<RelationshipRequest> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipRequest> relationships) {
        this.relationships = relationships;
    }
}


