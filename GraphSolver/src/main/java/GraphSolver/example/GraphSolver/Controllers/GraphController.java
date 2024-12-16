package GraphSolver.example.GraphSolver.Controllers;

import GraphSolver.example.GraphSolver.dto.NodeRequest;
import GraphSolver.example.GraphSolver.dto.RelationshipRequest;
import GraphSolver.example.GraphSolver.models.HierarchyRequest;
import GraphSolver.example.GraphSolver.services.GraphService;
import org.springframework.web.bind.annotation.*;
import GraphSolver.example.GraphSolver.models.Node;

import java.util.List;


@RestController
@RequestMapping("/api")
class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }


    @PostMapping("/hierarchy")
    public void createHierarchy(@RequestBody HierarchyRequest request) {
        graphService.createHierarchy(request);
    }


    @PostMapping("/nodes")
    public Node createNode(@RequestBody NodeRequest request) {
        return graphService.addNode(request);
    }

    @GetMapping("/nodes/{id}")
    public Node getNode(@PathVariable String id) {
        return graphService.getNode(id);
    }

    @PostMapping("/relationships")
    public void addRelationship(@RequestBody RelationshipRequest request) {
        graphService.addRelationship(request);
    }

    @GetMapping("/paths")
    public List<String> findPath(@RequestParam String startNodeId, @RequestParam String endNodeId) {
        return graphService.findPath(startNodeId, endNodeId);
    }

    @GetMapping("/nodes/{id}/depth")
    public int calculateDepth(@PathVariable String id) {
        return graphService.calculateDepth(id);
    }

    @GetMapping("/common-ancestor")
    public String findCommonAncestor(@RequestParam String nodeId1, @RequestParam String nodeId2) {
        return graphService.findCommonAncestor(nodeId1, nodeId2);
    }
}

