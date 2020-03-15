package treemanagementapi.utils;

import java.util.List;
import java.util.stream.Collectors;

import treemanagementapi.model.db.DbNode;
import treemanagementapi.model.rest.Node;

public class NodeMapper {

    public Node mapDbNodeToNode(DbNode input) {
        Node result = new Node();
        result.setKey(input.getKey());
        result.setValue(input.getValue());
        result.setParentNode(this.mapDbNodeToNode(input.getParentNode()));
        result.setExpanded(input.getExpanded());

        List<Node> nodeChildrenList = input.getChildren().stream()
        .map(t -> this.mapDbNodeToNode(t))
        .collect(Collectors.toList());
        result.setChildren(nodeChildrenList);

        return result;
    }

    public DbNode mapNodeToDbNode(Node input) {
        DbNode result = new DbNode();
        result.setKey(input.getKey());
        result.setValue(input.getValue());
        result.setParentNode(this.mapNodeToDbNode(input.getParentNode()));
        result.setExpanded(input.getExpanded());

        List<DbNode> dbNodeChildrenList = input.getChildren().stream()
        .map(t -> this.mapNodeToDbNode(t))
        .collect(Collectors.toList());
        result.setChildren(dbNodeChildrenList);

        return result;
    }
    

    
}
